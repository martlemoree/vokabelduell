package de.htwberlin.kba.configuration;

import de.htwberlin.kba.user_management.export.UserAlreadyExistAuthenticationException;
import de.htwberlin.kba.vokabelduell_ui.export.VokabellduellUi;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.FileNotFoundException;

@ComponentScan
public class ConfigurationImpl {

    private static ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("de.htwberlin");

    public static void main(String[] args) throws FileNotFoundException, UserAlreadyExistAuthenticationException {
        VokabellduellUi controller = context.getBean(VokabellduellUi.class);
        controller.run();
    }



}
