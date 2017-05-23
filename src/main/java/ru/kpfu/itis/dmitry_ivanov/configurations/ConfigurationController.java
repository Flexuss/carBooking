package ru.kpfu.itis.dmitry_ivanov.configurations;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.itis.dmitry_ivanov.viewControllers.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Dmitry on 20.05.2017.
 */
@Configuration
public class ConfigurationController {

    @Bean(name = "mainView")
    public View getMainView() throws IOException {
        return loadView("login.fxml");
    }

    @Bean(name = "regitrationView")
    public View getRegistrationView() throws IOException {
        return loadView("registration.fxml");
    }

    @Bean(name = "adminHomeView")
    public View getAdminHomeView() throws IOException {
        return loadView("adminHome.fxml");
    }

    @Bean(name = "userHomeView")
    public View getUserHomeView() throws IOException {
        return loadView("userHome.fxml");
    }

    @Bean(name = "editCarView")
    public View getEditCarView() throws IOException {
        return loadView("editCar.fxml");
    }

    @Bean(name = "editReservationView")
    public View getEditReservationView() throws IOException {
        return loadView("editReservation.fxml");
    }

    @Bean(name = "rentCarView")
    public View getRentCarView() throws IOException {
        return loadView("rentCar.fxml");
    }

    @Bean
    public MainController getMainController() throws IOException {
        return (MainController) getMainView().getController();
    }

    @Bean
    public RegistrationController getRegistrationController() throws IOException {
        return (RegistrationController) getRegistrationView().getController();
    }

    @Bean
    public AdminHomeController getAdminHomeController() throws IOException {
        return (AdminHomeController) getAdminHomeView().getController();
    }

    @Bean
    public UserHomeController getUserHomeController() throws IOException {
        return (UserHomeController) getUserHomeView().getController();
    }

    @Bean
    public EditCarController getEditCarController() throws IOException {
        return (EditCarController) getEditCarView().getController();
    }

    @Bean
    public EditReservationController getEditReservationController() throws IOException {
        return (EditReservationController) getEditReservationView().getController();
    }

    @Bean
    public RentCarController getRentCarController() throws IOException {
        return (RentCarController) getRentCarView().getController();
    }

    protected View loadView(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return new View((Parent) loader.getRoot(), loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }

    public class View {
        private Parent view;
        private Object controller;

        public View(Parent view, Object controller) {
            this.view = view;
            this.controller = controller;
        }

        public Parent getView() {
            return view;
        }

        public void setView(Parent view) {
            this.view = view;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }
    }

}