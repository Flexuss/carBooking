package ru.kpfu.itis.dmitry_ivanov.viewControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.kpfu.itis.dmitry_ivanov.configurations.ConfigurationController;
import ru.kpfu.itis.dmitry_ivanov.entity.Car;
import ru.kpfu.itis.dmitry_ivanov.services.CarService;

import javax.annotation.PostConstruct;

/**
 * Created by Dmitry on 23.05.2017.
 */
public class EditCarController {

    @Autowired
    CarService carService;

    @Autowired
    @Qualifier("adminHomeView")
    ConfigurationController.View adminHomeView;

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @FXML
    TextField model;
    @FXML
    TextField mileage;
    @FXML
    TextField year;
    @FXML
    TextField power;
    @FXML
    TextField cost;
    @FXML
    Button saveCarBtn;


    @FXML
    public void initialize() {
    }

    @PostConstruct
    public void init() {
        if(id!=null) {
            Car car = carService.getCarById(id);
            model.setText(car.getModel());
            mileage.setText(String.valueOf(car.getMileage()));
            year.setText(String.valueOf(car.getYear()));
            power.setText(String.valueOf(car.getPower()));
            cost.setText(String.valueOf(car.getCost()));
        }
    }

    @FXML
    public void saveCar(){
        Car car = new Car(
                model.getText(),
                Integer.parseInt(year.getText()),
                Integer.parseInt(mileage.getText()),
                Integer.parseInt(power.getText()),
                Integer.parseInt(cost.getText())
        );
        car.setId(Long.valueOf(id));
        carService.save(car);
        AdminHomeController controller = (AdminHomeController) adminHomeView.getController();
        controller.refreshCars();
        Stage stage = (Stage) saveCarBtn.getScene().getWindow();
        stage.getScene().setRoot(new Pane());
        stage.close();
    }
}
