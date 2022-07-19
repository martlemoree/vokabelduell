package org.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(scanBasePackages = {"de.htwberlin.kba","org.example"}, exclude = {DataSourceAutoConfiguration.class })
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
