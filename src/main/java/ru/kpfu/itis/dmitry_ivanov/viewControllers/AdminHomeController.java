package ru.kpfu.itis.dmitry_ivanov.viewControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.dmitry_ivanov.Validator;
import ru.kpfu.itis.dmitry_ivanov.configurations.ConfigurationController;
import ru.kpfu.itis.dmitry_ivanov.entity.Car;
import ru.kpfu.itis.dmitry_ivanov.entity.Reservation;
import ru.kpfu.itis.dmitry_ivanov.services.CarService;
import ru.kpfu.itis.dmitry_ivanov.services.ReservationService;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Dmitry on 22.05.2017.
 */
public class AdminHomeController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    CarService carService;

    @Autowired
    Validator validator;

    @Autowired
    @Qualifier("editCarView")
    private ConfigurationController.View editCarView;

    @Autowired
    @Qualifier("mainView")
    private ConfigurationController.View mainView;

    @Autowired
    @Qualifier("editReservationView")
    private ConfigurationController.View editReservationView;

    @FXML
    TableView<Reservation> reservationsTable;
    @FXML
    TableView<Car> carsTable;
    @FXML
    TextField name;
    @FXML
    TextField number;
    @FXML
    TextField car;
    @FXML
    TextField issue;
    @FXML
    TextField returndate;
    @FXML
    Button addbtn;
    @FXML
    TextField carModel;
    @FXML
    TextField carMileage;
    @FXML
    TextField carYear;
    @FXML
    TextField carPower;
    @FXML
    TextField carCost;
    @FXML
    Button addNewCarBtn;
    @FXML
    TextField carId;
    @FXML
    Button removeCarBtn;
    @FXML
    Button editCarBtn;
    @FXML
    Button logoutBtn;
    @FXML
    TextField reservationId;
    @FXML
    Button editReservationBtn;
    @FXML
    Button removeReservationBtn;

    @FXML
    public void initialize() {
    }

    @PostConstruct
    public void init() {
        List<Reservation> reservetions = reservationService.findAll();
        ObservableList<Reservation> data = FXCollections.observableArrayList(reservetions);

        // Добавляем столбцы к таблице
        TableColumn<Reservation, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("id"));
        TableColumn<Reservation, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("client"));
        TableColumn<Reservation, String> phoneColumn = new TableColumn<>("Number");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("number"));
        TableColumn<Reservation, String> carColumn = new TableColumn<>("Car");
        carColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("car"));
        TableColumn<Reservation, String> issueColumn = new TableColumn<>("Issue");
        issueColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("issueDate"));
        TableColumn<Reservation, String> returnColumn = new TableColumn<>("Return");
        returnColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("returnDate"));
        reservationsTable.getColumns().setAll(idColumn, nameColumn, phoneColumn, carColumn, issueColumn, returnColumn);

        // Добавляем данные в таблицу
        reservationsTable.setItems(data);

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
        carsTable.getColumns().setAll(carIdColumn, carModelColumn, carMileageColumn, carPowerColumn, carYearColumn, carCostColumn);
        carsTable.setItems(carData);
    }

    @FXML
    public void addnew(){
        String result = validator.rentCarValidate(name.getText(),number.getText(),car.getText(),issue.getText(),returndate.getText());
        if(result.equals("Success")) {
        Reservation reservation = new Reservation(name.getText(),
                number.getText(),
                carService.getCar(car.getText()),
                issue.getText(),
                returndate.getText());
        reservationService.save(reservation);
        number.clear();
        name.clear();
        car.clear();
        issue.clear();
        returndate.clear();
        refreshReservation();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(result);
            alert.show();
        }
    }

    @FXML
    public void removeReservation(){
        String id = reservationId.getText();
        reservationService.remove(id);
        reservationId.clear();
        refreshReservation();
    }

    @FXML
    public void editReservation(){
        final Stage stage = new Stage();
        stage.setTitle("Edit Reservation");
        EditReservationController controller = (EditReservationController) editReservationView.getController();
        controller.setId(reservationId.getText());
        controller.init();
        stage.setScene(new Scene(editReservationView.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        reservationId.clear();
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
    public void addNewCar(){
        String result = validator.editCarValidate(carModel.getText(),carMileage.getText(),carYear.getText(),carPower.getText(),carCost.getText());
        if(result.equals("Success")) {
        Car car = new Car(
                carModel.getText(),
                Integer.parseInt(carYear.getText()),
                Integer.parseInt(carMileage.getText()),
                Integer.parseInt(carPower.getText()),
                Integer.parseInt(carCost.getText()));
        carService.save(car);
        carModel.clear();
        carMileage.clear();
        carYear.clear();
        carPower.clear();
        carCost.clear();
        refreshCars();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(result);
            alert.show();
        }
    }

    public void removeCar(){
        String id=carId.getText();
        carService.remove(Long.valueOf(id));
        carId.clear();
        refreshCars();
    }

    public void editCar(){
        final Stage stage = new Stage();
        stage.setTitle("Edit Car");
        EditCarController controller = (EditCarController) editCarView.getController();
        controller.setId(carId.getText());
        controller.init();
        stage.setScene(new Scene(editCarView.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                stage.getScene().setRoot(new Pane());
                stage.close();
            }
        });
        carId.clear();
        stage.show();
    }

    public void logout(){
        SecurityContextHolder.clearContext();
        logoutBtn.getScene().setRoot(mainView.getView());
    }

    public void refreshReservation() {
        List<Reservation> reservations = reservationService.findAll();
        ObservableList<Reservation> reservationData = FXCollections.observableArrayList(reservations);
        reservationsTable.setItems(reservationData);
    }

    public void refreshCars(){
        List<Car> cars = carService.findAll();
        ObservableList<Car> carData = FXCollections.observableArrayList(cars);
        carsTable.setItems(carData);
    }
}
