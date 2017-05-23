package ru.kpfu.itis.dmitry_ivanov.viewControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.dmitry_ivanov.configurations.ConfigurationController;
import ru.kpfu.itis.dmitry_ivanov.entity.Car;
import ru.kpfu.itis.dmitry_ivanov.services.CarService;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Dmitry on 22.05.2017.
 */

public class UserHomeController {

    @Autowired
    CarService carService;

    @Autowired
    @Qualifier("mainView")
    ConfigurationController.View mainView;

    @Autowired
    @Qualifier("rentCarView")
    ConfigurationController.View rentCarView;


    @FXML
    TableView<Car> carTable;

    @FXML
    Button rentCarBtn;

    @FXML
    Button logoutBtn;

    @FXML
    public void initialize() {

    }

    @PostConstruct
    public void init() {
        List<Car> cars = carService.findAll();
        ObservableList<Car> carData = FXCollections.observableArrayList(cars);

        TableColumn<Car, String> carIdColumn = new TableColumn<>("ID");
        carIdColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("id"));
        TableColumn<Car, String> carModelColumn = new TableColumn<>("Model");
        carModelColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        TableColumn<Car, String> carMileageColumn = new TableColumn<>("Mileage");
        carMileageColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("mileage"));
        TableColumn<Car, String> carPowerColumn = new TableColumn<>("Power");
        carPowerColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("power"));
        TableColumn<Car, String> carYearColumn = new TableColumn<>("Year");
        carYearColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("year"));
        TableColumn<Car, String> carCostColumn = new TableColumn<>("Cost");
        carCostColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("cost"));
        carTable.getColumns().setAll(carIdColumn, carModelColumn, carMileageColumn, carPowerColumn, carYearColumn, carCostColumn);
        carTable.setItems(carData);
    }

    @FXML
    public void rentCar(){
        final Stage stage = new Stage();
        stage.setTitle("Edit Car");
        stage.setScene(new Scene(rentCarView.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                stage.getScene().setRoot(new Pane());
                stage.close();
            }
        });
        stage.show();
    }

    @FXML
    public void logout(){
        SecurityContextHolder.clearContext();
        logoutBtn.getScene().setRoot(mainView.getView());
    }

    public void refresh(){
        List<Car> cars = carService.findAll();
        ObservableList<Car> carData = FXCollections.observableArrayList(cars);
        carTable.setItems(carData);
    }
}
