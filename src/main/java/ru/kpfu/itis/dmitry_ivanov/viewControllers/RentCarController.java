package ru.kpfu.itis.dmitry_ivanov.viewControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.kpfu.itis.dmitry_ivanov.Validator;
import ru.kpfu.itis.dmitry_ivanov.configurations.ConfigurationController;
import ru.kpfu.itis.dmitry_ivanov.entity.Reservation;
import ru.kpfu.itis.dmitry_ivanov.services.CarService;
import ru.kpfu.itis.dmitry_ivanov.services.ReservationService;

import javax.annotation.PostConstruct;

/**
 * Created by Dmitry on 23.05.2017.
 */
public class RentCarController {

    @Autowired
    CarService carService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    Validator validator;

    @Autowired
    @Qualifier("userHomeView")
    ConfigurationController.View userHomeView;

    @FXML
    TextField name;
    @FXML
    TextField number;
    @FXML
    TextField car;
    @FXML
    TextField issueDate;
    @FXML
    TextField returnDate;
    @FXML
    Button rentCarBtn;
    @FXML
    Button cancelBtn;

    @FXML
    public void initialize() {
    }

    @PostConstruct
    public void init() {
    }

    public void rentCar(){
        String result = validator.rentCarValidate(name.getText(),number.getText(),car.getText(),issueDate.getText(),returnDate.getText());
        if(result.equals("Success")) {
            if (carService.getCar(car.getText()) != null) {
                Reservation reservation = new Reservation(
                        name.getText(),
                        number.getText(),
                        carService.getCar(car.getText()),
                        issueDate.getText(),
                        returnDate.getText()
                );
                reservationService.save(reservation);
                UserHomeController controller = (UserHomeController) userHomeView.getController();
                controller.refresh();
                cancel();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(result);
            alert.show();
        }
    }

    public void cancel(){
        Stage stage = (Stage) rentCarBtn.getScene().getWindow();
        stage.getScene().setRoot(new Pane());
        stage.close();
    }
}
