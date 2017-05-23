package ru.kpfu.itis.dmitry_ivanov;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by Dmitry on 20.05.2017.
 */


public class AbstractJavaFxApplicationSupport extends Application {

    private static String[] savedArgs;

    protected ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(getClass(), savedArgs);
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }

    protected static void launchApp(Class<? extends AbstractJavaFxApplicationSupport> clazz, String[] args) {
        AbstractJavaFxApplicationSupport.savedArgs = args;
        Application.launch(clazz, args);
    }

}
