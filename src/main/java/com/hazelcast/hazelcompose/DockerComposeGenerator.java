package com.hazelcast.hazelcompose;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

class DockerComposeGenerator {

    String generateDockerCompose(int membersCount,
                                 int initialMemberPort,
                                 boolean includeManCenter,
                                 int manCenterExposedPort,
                                 boolean useTls) {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/docker-compose.twig");
        JtwigModel model = JtwigModel
                .newModel()
                .with("membersCount", membersCount)
                .with("initialMemberPort", initialMemberPort)
                .with("includeManCenter", includeManCenter)
                .with("manCenterExposedPort", manCenterExposedPort)
                .with("useTls", useTls);
        return template.render(model);
    }
}
