package lk.ijse.Charleston.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Charleston.bo.BoFactory;
import lk.ijse.Charleston.bo.custom.RoomBo;
import lk.ijse.Charleston.db.DBConnection;
import lk.ijse.Charleston.dto.Food;
import lk.ijse.Charleston.dto.Room;
import lk.ijse.Charleston.dto.tm.FoodTM;
import lk.ijse.Charleston.dto.tm.RoomTM;
import lk.ijse.Charleston.entity.room;
import lk.ijse.Charleston.model.FoodModel;
import lk.ijse.Charleston.model.RoomModel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RoomFormController {

    @FXML
    private AnchorPane RoomPane;

    @FXML
    private TextField txtRoomId;

    @FXML
    private TextField txtRoomType;

    @FXML
    private TextField txtRoomDetails;

    @FXML
    private TextField txtNumberofBeds;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtSearch;

    public TableView <RoomTM> tblRoom;

    @FXML
    private TableColumn colRoomId;

    @FXML
    private TableColumn colRoomType;

    @FXML
    private TableColumn colRoomDetails;

    @FXML
    private TableColumn colNumberofBeds;

    @FXML
    private TableColumn<?, ?> colPrice;

    RoomBo roomBo = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ROOM);

    public void initialize() throws ClassNotFoundException {
        setCellValueFactory();
        setSelectStaffToTxt();
        getAll();
    }

    @FXML
    private void setCellValueFactory() {
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colRoomDetails.setCellValueFactory(new PropertyValueFactory<>("roomDetails"));
        colNumberofBeds.setCellValueFactory(new PropertyValueFactory<>("numberofBeds"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    private void getAll() throws ClassNotFoundException {
        try {
            ObservableList<RoomTM> obList = FXCollections.observableArrayList();
            List<room> roomList = roomBo.getAll();

            for (room room : roomList) {
                obList.add(new RoomTM(
                        room.getRoom_ID(),
                        room.getRoom_Type(),
                        room.getRoom_Details(),
                        room.getNumber_OF_Beds(),
                        room.getRoom_price()

                ));
            }
            tblRoom.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void setSelectStaffToTxt() {
        tblRoom.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtRoomId.setText(newSelection.getRoomId());
                txtRoomType.setText(newSelection.getRoomType());
                txtRoomDetails.setText(newSelection.getRoomDetails());
                txtNumberofBeds.setText(newSelection.getNumberofBeds());
                txtPrice.setText(String.valueOf(newSelection.getPrice()));


            }
        });
    }
    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));

        Stage stage = (Stage) RoomPane.getScene().getWindow();
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        if(!txtRoomId.getText().isBlank() && !txtRoomType.getText().isBlank() && !txtRoomDetails.getText().isBlank() && !txtNumberofBeds.getText().isBlank() && !txtPrice.getText().isBlank()) {
            String roomId = txtRoomId.getText();
            String roomType = txtRoomType.getText();
            String roomDetails = txtRoomDetails.getText();
            String numberofBeds= txtNumberofBeds.getText();
            double price = Double.parseDouble(txtPrice .getText());

            try {
                boolean isSaved = roomBo.save(new Room(roomId,roomType,roomDetails,numberofBeds,price));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Room saved!").show();
                    txtClear();
                    getAll();
                    setCellValueFactory();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Room Not saved ,Please Check Details & Try Again!").show();
                txtClear();
            }
        }else{new Alert(Alert.AlertType.ERROR, "Please Fill Details!").show(); }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String roomId = txtRoomId.getText();
        String roomType = txtRoomType.getText();
        String roomDetails = txtRoomDetails.getText();
        String numberofBeds = txtNumberofBeds.getText();
        double price = Double.parseDouble(txtPrice.getText());

        try {
            boolean isUpdated = roomBo.update(new Room(roomId,roomType,roomDetails,numberofBeds,price));
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Room updated!").show();
                txtClear();
                getAll();
                setCellValueFactory();
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Room Id Not Exist!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Room Id Not Exist!").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws ClassNotFoundException {

        String roomId = txtRoomId.getText();
        if(!txtRoomId.getText().isBlank()) {
            try {
                boolean isDeleted = roomBo.delete(roomId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Room Deleted!").show();
                    txtClear();
                    getAll();
                    setCellValueFactory();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Room Id Not Exist!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Please Check Details & Try Again!").show();
                 txtClear();
            }
        }else{new Alert(Alert.AlertType.ERROR, "Please Fill Room Id!").show(); }


    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        btnSearchOnAction(actionEvent);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        tblRoom.getItems().stream()
                .filter(item -> item.getRoomId().equals(txtSearch.getText()) )
                .findAny()
                .ifPresent(item -> {
                    tblRoom.getSelectionModel().select(item);
                    tblRoom.scrollTo(item);
                    txtSearch.clear();
                });
        tblRoom.getItems().stream()
                .filter(item -> item.getRoomType().equals(txtSearch.getText()) )
                .findAny()
                .ifPresent(item -> {
                    tblRoom.getSelectionModel().select(item);
                    tblRoom.scrollTo(item);
                    txtSearch.clear();
                });

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtClear();
    }
    private void txtClear() {txtRoomId.clear();
        txtRoomType.clear();
        txtRoomDetails.clear();
        txtNumberofBeds.clear();
        txtPrice.clear();
    }
}
