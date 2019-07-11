package com.hazelcast.hazelcompose;

public class Main {
    public static void main(String[] args) {
        String dockerCompose = new DockerComposeGenerator().generateDockerCompose(3,
                5702,
                false,
                8081);

        System.out.println(dockerCompose);
    }
}

