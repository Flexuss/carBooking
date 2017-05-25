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
public class EditReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    CarService carService;

    @Autowired
    Validator validator;

    @Autowired
    @Qualifier("adminHomeView")
    ConfigurationController.View adminHomeView;

    private String id;

    public void setId(String id) {
        this.id = id;
    }

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
    Button saveReservationBtn;

    @FXML
    public void initialize() {
    }

    @PostConstruct
    public void init() {
        if(id!=null) {
            Reservation reservation = reservationService.findById(id);
            name.setText(reservation.getClient());
            number.setText(reservation.getNumber());
            car.setText(reservation.getCar());
            issueDate.setText(reservation.getIssueDate());
            returnDate.setText(reservation.getReturnDate());
        }
    }

    @FXML
    public void saveReservation(){
        String result = validator.rentCarValidate(name.getText(),number.getText(),car.getText(),issueDate.getText(),returnDate.getText());
        if(result.equals("Success")) {
        Reservation reservation = new Reservation(
                name.getText(),
                number.getText(),
                carService.getCar(car.getText()),
                issueDate.getText(),
                returnDate.getText()
        );
        reservation.setId(Long.valueOf(id));
        name.clear();
        number.clear();
        car.clear();
        issueDate.clear();
        returnDate.clear();
        reservationService.update(reservation);
        AdminHomeController controller = (AdminHomeController) adminHomeView.getController();
        controller.refreshReservation();
        Stage stage = (Stage) saveReservationBtn.getScene().getWindow();
        stage.getScene().setRoot(new Pane());
        stage.close();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(result);
            alert.show();
        }
    }

}
