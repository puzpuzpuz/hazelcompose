package com.hazelcast.hazelcompose;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

class DockerComposeGenerator {

    String generateDockerCompose(int nodes) {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/docker-compose.twig");
        JtwigModel model = JtwigModel
                .newModel()
                .with("nodes", nodes);
        return template.render(model);
    }
}
