package org.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import java.io.IOException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
/*@ComponentScan("org.example.GameManagement",
        "org.example.GameManagementExport",
        "org.example.game_management.rest_server",
        "org.example.game_management.rest_client",
        "org.example.UserManagementExport",
        "org.example.user-management.impl",
        "org.example.VocabManagement",
        "org.example.vokabelduell_ui.export",
        "org.example.GameManagementExport",
        "org.example.vokabelduell_ui.impl",
        "org.example.vocab-management.impl")*/
public class App {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(App.class, args);
    }

}
