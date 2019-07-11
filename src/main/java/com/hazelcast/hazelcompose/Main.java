package com.hazelcast.hazelcompose;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        String dockerCompose = new DockerComposeGenerator().generateDockerCompose(3);
        System.out.println(dockerCompose);
    }
}

