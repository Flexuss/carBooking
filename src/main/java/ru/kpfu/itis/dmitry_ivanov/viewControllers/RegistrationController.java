package ru.kpfu.itis.dmitry_ivanov.viewControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.kpfu.itis.dmitry_ivanov.configurations.ConfigurationController;
import ru.kpfu.itis.dmitry_ivanov.entity.User;
import ru.kpfu.itis.dmitry_ivanov.services.UserService;

import javax.annotation.PostConstruct;

/**
 * Created by Dmitry on 21.05.2017.
 */

@SuppressWarnings("SpringJavaAutowiringInspection")
public class RegistrationController {

    @Autowired
    @Qualifier("mainView")
    private ConfigurationController.View mainView;

    @Autowired
    UserService userService;

    @FXML
    private Button backbtn;

    @FXML
    private Button registrationbtn;

    @FXML
    private TextField username;

    @FXML
    private TextField password;


    @PostConstruct
    public void init() {

    }

    public void back(){
        backbtn.getScene().setRoot(mainView.getView());
    }

    public void registration(){
        String usernameString = username.getText();
        String passwordString = password.getText();
        username.clear();
        password.clear();
        if(userService.findByUsername(usernameString)==null){
            User user = new User();
            user.setUsername(usernameString);
            user.setPassword(passwordString);
            userService.save(user);
            back();
        }
    }
}
