package lk.ijse.Charleston.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Charleston.bo.BoFactory;
import lk.ijse.Charleston.bo.custom.GuestBo;
import lk.ijse.Charleston.dao.DaoFactory;
import lk.ijse.Charleston.db.DBConnection;
import lk.ijse.Charleston.dto.Food;
import lk.ijse.Charleston.dto.Guest;
import lk.ijse.Charleston.dto.tm.GuestTM;
import lk.ijse.Charleston.entity.guest;
import lk.ijse.Charleston.model.FoodModel;
import lk.ijse.Charleston.model.GuestModel;

import javafx.event.ActionEvent;
import lk.ijse.Charleston.model.RoomModel;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lk.ijse.Charleston.model.GuestModel.getAll;

public class GuestFormController {

    @FXML
    private AnchorPane GuestPane;
    @FXML
    private JFXComboBox<String> cmbType;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtNo;
    @FXML
    private TextField txtCountry;
    @FXML
    private TextField txtEmail;

    public TableView<GuestTM> tblGuest;

    @FXML
    private TableColumn colGuestIdType;
    @FXML
    private TableColumn colGuestId;
    @FXML
    private TableColumn colGuestName;
    @FXML
    private TableColumn colContactNo;
    @FXML
    private TableColumn colCountry;
    @FXML
    private TableColumn colEmail;
    @FXML
    private TextField txtSearch;

    GuestBo guestBo = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.GUEST);

    public void initialize() throws ClassNotFoundException {
        ObservableList<String> titleList = FXCollections.observableArrayList("ID card", "Passport");
        cmbType.setItems(titleList);

        setCellValueFactory();
        setSelectStaffToTxt();
        getAll();
    }

    @FXML
    private void setCellValueFactory() {
        colGuestIdType.setCellValueFactory(new PropertyValueFactory<>("idType"));
        colGuestId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colGuestName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    @FXML
    private void getAll() throws ClassNotFoundException {
        try {
            ObservableList<GuestTM> obList = FXCollections.observableArrayList();
            List<guest> guestList = guestBo.getAll();

            for (guest guest : guestList) {
                obList.add(new GuestTM(
                        guest.getGuest_ID_type(),
                        guest.getGuest_ID(),
                        guest.getGuest_Name(),
                        guest.getGuest_Contact_No(),
                        guest.getGuest_Country(),
                        guest.getGuest_Email()
                ));
            }
            tblGuest.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void setSelectStaffToTxt() {
        tblGuest.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cmbType.setValue(newSelection.getIdType());
                txtId.setText(newSelection.getId());
                txtName.setText(newSelection.getName());
                txtNo.setText(newSelection.getContactNo());
                txtCountry.setText(newSelection.getCountry());
                txtEmail.setText(newSelection.getEmail());

            }
        });
    }

    @FXML
    void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));

        Stage stage = (Stage) GuestPane.getScene().getWindow();
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();

    }

    @FXML
    void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (cmbType.getValue() != null && !txtId.getText().isBlank() && !txtName.getText().isBlank()
                && !txtNo.getText().isBlank() && !txtCountry.getText().isBlank() && !txtEmail.getText().isBlank()) {
            String idType = cmbType.getValue();
            String id = txtId.getText();
            String name = txtName.getText();
            String contractNo = txtNo.getText();
            String country = txtCountry.getText();
            String email = txtEmail.getText();

            // Email validation regex
            String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
            Pattern emailPattern = Pattern.compile(emailRegex);
            Matcher emailMatcher = emailPattern.matcher(email);

            // Contact number validation regex
            String contactNumberRegex = "^(\\+\\d{1,3})?\\s?\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}$";
            Pattern contactNumberPattern = Pattern.compile(contactNumberRegex);
            Matcher contactNumberMatcher = contactNumberPattern.matcher(contractNo);

            // Check if email is valid
            if (!emailMatcher.matches()) {
                new Alert(Alert.AlertType.ERROR, "Invalid email address!").show();
                return;
            }

            // Check if contact number is valid
            if (!contactNumberMatcher.matches()) {
                new Alert(Alert.AlertType.ERROR, "Invalid contact number!").show();
                return;
            }

            try {
                boolean isSaved = guestBo.save(new Guest(idType, id, name, contractNo, country, email));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Guest saved!").show();
                    setCellValueFactory();
                    getAll();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Guest not saved, please check details and try again!").show();
                txtClear();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please fill in all details!").show();
        }
    }


    @FXML
    void btnClearOnAction(ActionEvent actionEvent) {
        txtClear();
    }

    @FXML
    void txtSearchOnAction(ActionEvent actionEvent) {
        btnSearchOnAction(actionEvent);
    }

    @FXML
    void btnSearchOnAction(ActionEvent actionEvent) {
        tblGuest.getItems().stream()
                .filter(item -> item.getId().equals(txtSearch.getText()))
                .findAny()
                .ifPresent(item -> {
                    tblGuest.getSelectionModel().select(item);
                    tblGuest.scrollTo(item);
                    txtSearch.clear();
                });
        tblGuest.getItems().stream()
                .filter(item -> item.getName().equals(txtSearch.getText()))
                .findAny()
                .ifPresent(item -> {
                    tblGuest.getSelectionModel().select(item);
                    tblGuest.scrollTo(item);
                    txtSearch.clear();
                });
    }

    @FXML
    void btnDeleteOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String id = txtId.getText();
        if (!txtId.getText().isBlank()) {
            try {
                boolean isDeleted = guestBo.delete(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Guest Deleted!").show();
                    txtClear();
                    getAll();
                    setCellValueFactory();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Guest Id Not Exist!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Please Check Details & Try Again!").show();
                txtClear();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please Fill Guest Id!").show();
        }

    }

    @FXML
    void txtClear() {
        cmbType.getSelectionModel().clearSelection();
        txtId.clear();
        txtName.clear();
        txtNo.clear();
        txtCountry.clear();
        txtEmail.clear();
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
       // if (!txtId.getText().isBlank() && !txtName.getText().isBlank() && !(cmbType.getValue() != null) && !txtNo.getText().isBlank() && !txtCountry.getText().isBlank() && !txtEmail.getText().isBlank()) {
            String idType = cmbType.getValue();
            String id = txtId.getText();
            String name = txtName.getText();
            String contractNo = txtNo.getText();
            String country = txtCountry.getText();
            String email = txtEmail.getText();

            try {
                boolean isUpdated = guestBo.update(new Guest(idType, id, name, contractNo, country, email));
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Guest updated!").show();
                    //setSelectStaffToTxt();
                    setCellValueFactory();
                    getAll();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Guest Id Not Exist!").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Guest Id Not Exist!").show();
            }

      //  }

    }
}
