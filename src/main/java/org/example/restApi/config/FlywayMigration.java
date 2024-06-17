package org.example.restApi.config;

import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class FlywayMigration {
    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        props.load(Files.newInputStream(Paths.get("src/main/resources/flyway.properties")));

        String url = props.getProperty("flyway.url");
        String user = props.getProperty("flyway.username");
        String password = props.getProperty("flyway.password");
        String location = props.getProperty("flyway.locations");

        Flyway flyway = Flyway.configure()
                .dataSource(url, user, password).locations(location).load();

        flyway.migrate();
    }
}
