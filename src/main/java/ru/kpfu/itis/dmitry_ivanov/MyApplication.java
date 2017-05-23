package ru.kpfu.itis.dmitry_ivanov;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import ru.kpfu.itis.dmitry_ivanov.configurations.ConfigurationController;

/**
 * Created by Dmitry on 20.05.2017.
 */
@Lazy
@SpringBootApplication
public class MyApplication extends AbstractJavaFxApplicationSupport {

    @Value("Car Booking")
    private String windowTitle;

    @Autowired
    @Qualifier("mainView")
    private ConfigurationController.View loginView;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(loginView.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launchApp(MyApplication.class, args);
    }
}
