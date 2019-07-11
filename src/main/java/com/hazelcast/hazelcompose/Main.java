package com.hazelcast.hazelcompose;

import jline.console.ConsoleReader;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static com.hazelcast.hazelcompose.ArgUtil.parseBoolean;
import static com.hazelcast.hazelcompose.ArgUtil.parseInt;
import static com.hazelcast.hazelcompose.Main.Argument.IMDG_CLUSTER_SIZE;
import static com.hazelcast.hazelcompose.Main.Argument.IMDG_START_PORT;
import static com.hazelcast.hazelcompose.Main.Argument.MC_ENABLE;
import static com.hazelcast.hazelcompose.Main.Argument.MC_PORT;

public class Main {

    public static final String OUTPUT_FILE_NAME = "docker-compose.yml";

    public enum Argument {

        MC_ENABLE("Include Management Center (default: true)", "true"),
        MC_PORT("Management Center port (default: 8080)", "8080"),
        IMDG_START_PORT("IMDG port, incremented for each additional member (default: 5701)", "5701"),
        IMDG_CLUSTER_SIZE("IMDG cluster size (default: 3)", "3");

        final String descr;
        final String defVal;

        Argument(String descr, String defVal) {
            this.descr = descr;
            this.defVal = defVal;
        }

    }

    private static String generate(Map<Argument, String> args) {
        return new DockerComposeGenerator()
                .generateDockerCompose(
                        parseInt(args.get(IMDG_CLUSTER_SIZE), IMDG_CLUSTER_SIZE.defVal),
                        parseInt(args.get(IMDG_START_PORT), IMDG_START_PORT.defVal),
                        parseBoolean(args.get(MC_ENABLE), MC_ENABLE.defVal),
                        parseInt(args.get(MC_PORT), MC_PORT.defVal)
                );
    }

    public static void main(String[] arguments) {
        try {
            ConsoleReader in = new ConsoleReader();
            in.setPrompt("> ");

            PrintWriter out = new PrintWriter(in.getOutput());
            out.println("Welcome to HazelCompose. Please enter parameter values to generate IMDG Docker Compose file.");

            Map<Argument, String> args = new HashMap<>();
            for (Argument arg : Argument.values()) {
                out.println("Enter " + arg.descr + ":");
                String line = in.readLine();
                if (line == null) {
                    out.println("Stopping generator...");
                    return;
                }
                args.put(arg, line);
            }

            try (FileWriter fileWriter = new FileWriter(OUTPUT_FILE_NAME)) {
                fileWriter.write(generate(args));
                out.println("Generated " + OUTPUT_FILE_NAME + "file");
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}

