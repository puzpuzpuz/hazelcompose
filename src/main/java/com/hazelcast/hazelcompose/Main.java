package com.hazelcast.hazelcompose;

import jline.console.ConsoleReader;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private enum Argument {

        MC_PORT("MC port (default: 8080)", "8080"),
        IMDG_START_PORT("IMDG port, incremented for each additional member (default: 5701)", "5701"),
        IMDG_CLUSTER_SIZE("IMDG cluster size (default: 3)", "3");

        final String descr;
        final String defValue;

        Argument(String descr, String defValue) {
            this.descr = descr;
            this.defValue = defValue;
        }

    }

    public static void main(String[] args) {
        try {
            ConsoleReader in = new ConsoleReader();
            in.setPrompt("> ");

            PrintWriter out = new PrintWriter(in.getOutput());
            out.println("Welcome to HazelCompose. Please enter parameter values to generate IMDG Docker Compose file.");

            Map<String, String> argValues = new HashMap<>();
            for (Argument arg : Argument.values()) {
                out.println("Enter " + arg.descr + ":");
                String line = in.readLine();
                if (line == null) {
                    out.println("Stopping generator...");
                    return;
                }

                if (!line.trim().isEmpty()) {
                    argValues.put(arg.name(), line);
                } else {
                    argValues.put(arg.name(), arg.defValue);
                }
            }

            System.out.println(argValues);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}

