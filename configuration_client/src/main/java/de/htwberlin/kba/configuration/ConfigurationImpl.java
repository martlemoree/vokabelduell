package de.htwberlin.kba.configuration;

import de.htwberlin.kba.user_management.export.UserAlreadyExistsException;
import de.htwberlin.kba.vokabelduell_ui.export.VokabellduellUi;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class ConfigurationImpl {

    private static ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("de.htwberlin");

    public static void main(String[] args) throws FileNotFoundException, UserAlreadyExistsException {
        VokabellduellUi controller = context.getBean(VokabellduellUi.class);
        controller.run();
    }



}
