package de.htwberlin.kba.configuration;

import de.htwberlin.kba.game_ui.export.GameUi;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSpringIml {
    private static ConfigurableApplicationContext context = new AnnotationConfigApplicationContext ("de.htwberlin");

    public static void main(String[] args) {
        GameUi controller = context.getBean(GameUi.class);
        controller.run();
    }
}
