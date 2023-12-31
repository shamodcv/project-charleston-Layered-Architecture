package lk.ijse.Charleston.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import lk.ijse.Charleston.bo.custom.GuestBo;
import lk.ijse.Charleston.bo.custom.ReservationBo;
import lk.ijse.Charleston.bo.custom.RoomBo;
import lk.ijse.Charleston.dto.*;
import lk.ijse.Charleston.dto.tm.ReservationTM;
import lk.ijse.Charleston.dto.tm.TourTM;
import lk.ijse.Charleston.entity.guest;
import lk.ijse.Charleston.entity.resavations;
import lk.ijse.Charleston.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ReservationFormController {
    public  TableView <ReservationTM>tblReservation;

    @FXML
    private TextField txtguestId;
    @FXML
    private TextField txtGuestName;
    @FXML
    private TextField txtContact;
    @FXML
    private TextField txtCountry;
    @FXML
    private TextField txtEmail;

    @FXML
    private JFXComboBox cmbType;
    @FXML
    private Label lblRoomPrice;

    @FXML
    private AnchorPane ReservationPane;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField lblReservationID;
    @FXML
    private JFXComboBox <String> cmdRoomID;

    public DatePicker datePickerCheckinDate;
    public DatePicker datePickerCheckoutDate;
    @FXML
    private TableColumn colReservationId;
    @FXML
    private TableColumn colGuestId;
    @FXML
    private TableColumn colRoomId;
    @FXML
    private TableColumn colCheckinDate;
    @FXML
    private TableColumn colCheckoutDate;
    @FXML
    private TableColumn colRoomPrice;

    ReservationBo reservationBo = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.RESERVATION);
    GuestBo guestBo = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.GUEST);
    RoomBo roomBo = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ROOM);

    public void initialize() throws ClassNotFoundException {
        ObservableList<String> titleList = FXCollections.observableArrayList("ID card","Passport");
        cmbType.setItems(titleList);

        loadRoomIds();
        generateNextResId();
        getAll();
        setCellValueFactory();
        setSelectToTxt();
    }

    @FXML
    private void setCellValueFactory() {
        colReservationId.setCellValueFactory(new PropertyValueFactory<>("resId"));
        colGuestId.setCellValueFactory(new PropertyValueFactory<>("gusId"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colRoomPrice.setCellValueFactory(new PropertyValueFactory<>("roomPrice"));
        colCheckinDate.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        colCheckoutDate.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
    }
    @FXML
    private void getAll() throws ClassNotFoundException {
        try {
            ObservableList<ReservationTM> obList = FXCollections.observableArrayList();
            List<resavations> resList = reservationBo.getAll();

            for (resavations reservation : resList) {
                obList.add(new ReservationTM(
                        reservation.getRes_ID(),
                        reservation.getGuest_ID(),
                        reservation.getRoom_ID(),
                        reservation.getRoom_price(),
                        reservation.getCheck_in_Date(),
                        reservation.getCheck_out_Date()
                ));
            }
            tblReservation.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }
    private void setSelectToTxt() {
        tblReservation.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                lblReservationID.setText(newSelection.getResId());
                txtguestId.setText(newSelection.getGusId());
                ObservableList<String> titleList = FXCollections.observableArrayList(newSelection.getRoomId());
                cmdRoomID.setItems(titleList);
                lblRoomPrice.setText(String.valueOf(newSelection.getRoomPrice()));
                datePickerCheckinDate.setValue(LocalDate.parse((CharSequence) newSelection.getCheckInDate()));
                datePickerCheckoutDate.setValue(LocalDate.parse((CharSequence) newSelection.getCheckOutDate()));
            }
        });
    }
    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));

        Stage stage = (Stage) ReservationPane.getScene().getWindow();
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }


    public void btnAddOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        if (!lblReservationID.getText().isBlank() && !txtguestId.getText().isBlank() && cmdRoomID.getValue() != null
                && !lblRoomPrice.getText().isBlank() && datePickerCheckinDate.getValue() != null) {
            String idType = String.valueOf(cmbType.getValue());
            String guestID = txtguestId.getText();
            String guestName = txtGuestName.getText();
            String contact = txtContact.getText();
            String country = txtCountry.getText();
            String email = txtEmail.getText();

            // Regex patterns for email and contact number
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            String contactRegex = "^[0-9]{10}$"; // Assuming 10-digit contact number

            // Compile the regex patterns
            Pattern emailPattern = Pattern.compile(emailRegex);
            Pattern contactPattern = Pattern.compile(contactRegex);

            // Match the patterns against the input strings
            Matcher emailMatcher = emailPattern.matcher(email);
            Matcher contactMatcher = contactPattern.matcher(contact);

            // Check if all input fields are valid
            if (emailMatcher.matches() && contactMatcher.matches()) {
                String reservationID = lblReservationID.getText();
                String roomID = cmdRoomID.getValue();
                double roomPrice = Double.parseDouble(lblRoomPrice.getText());
                LocalDate selectedInDate = datePickerCheckinDate.getValue();
                java.sql.Date in = java.sql.Date.valueOf(selectedInDate.toString());
                LocalDate selectedOutDate = datePickerCheckoutDate.getValue();
                java.sql.Date out = java.sql.Date.valueOf(selectedOutDate.toString());
                String outDate = selectedOutDate.toString();

                Reservation reservation = new Reservation(reservationID, guestID, roomID, roomPrice, in, out);
                try {
                    Guest searchGuest = GuestModel.searchById(guestID);
                    if (searchGuest == null) {
                        boolean guestSave = guestBo.save(new Guest(idType, guestID, guestName, contact, country, email));
                        if (guestSave) {
                            boolean isSaved = ReservationModel.save(reservation);
                            if (isSaved) {
                                new Alert(Alert.AlertType.CONFIRMATION, "Reservation saved!").show();
                                txtClear();
                                getAll();
                                setCellValueFactory();
                                generateNextResId();
                                loadRoomIds();
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Guest Not saved!").show();
                        }
                    } else {
                        boolean isSaved = ReservationModel.save(reservation);
                        if (isSaved) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Guest Already Registered! Reservation saved!")
                                    .show();
                            txtClear();
                            getAll();
                            setCellValueFactory();
                            generateNextResId();
                            loadRoomIds();
                        }
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, "Reservation Not saved, Please Check Details & Try Again!").show();
                    txtClear();
                }
            } else {
                if (!emailMatcher.matches()) {
                    new Alert(Alert.AlertType.ERROR, "Invalid email address!").show();
                }
                if (!contactMatcher.matches()) {
                    new Alert(Alert.AlertType.ERROR, "Invalid contact number!").show();
                }
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please Fill Details!").show();
        }
    }



    private void txtClear() {
        lblReservationID.setText("");
        txtguestId.setText("");
        cmdRoomID.setValue(null);
        lblRoomPrice.setText("");
        datePickerCheckinDate.setValue(null);
        datePickerCheckoutDate.setValue(null);
        txtguestId.setText("");
        txtGuestName.setText("");
        txtContact.setText("");
        txtCountry.setText("");
        txtEmail.setText("");
        loadRoomIds();
        cmbType.setValue(null);
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtClear();
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        btnSearchOnAction(actionEvent);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        tblReservation.getItems().stream()
                .filter(item -> item.getResId().equals(txtSearch.getText()) )
                .findAny()
                .ifPresent(item -> {
                    tblReservation.getSelectionModel().select(item);
                    tblReservation.scrollTo(item);
                    txtSearch.clear();
                });
        tblReservation.getItems().stream()
                .filter(item -> item.getRoomId().equals(txtSearch.getText()) )
                .findAny()
                .ifPresent(item -> {
                    tblReservation.getSelectionModel().select(item);
                    tblReservation.scrollTo(item);
                    txtSearch.clear();
                });
    }

//    public void btnUpdateOnAction(ActionEvent actionEvent) {
//
//        if (!lblReservationID.getText().isBlank() && !txtguestId.getText().isBlank() && cmdRoomID.getValue() != null && !lblRoomPrice.getText().isBlank() && datePickerCheckinDate.getValue() != null) {
//            String guestID = txtguestId.getText();
//            String reservationID = lblReservationID.getText();
//            String roomID = cmdRoomID.getValue();
//            double roomPrice = Double.parseDouble(lblRoomPrice.getText());
//            LocalDate selectedInDate = datePickerCheckinDate.getValue();
//            String inDate = selectedInDate.toString();
//            LocalDate selectedOutDate = datePickerCheckoutDate.getValue();
//            String outDate = selectedOutDate.toString();
//
//            Reservation reservation = new Reservation(reservationID, guestID, roomID, roomPrice, inDate, outDate);
//            try {
//                boolean isSaved = ReservationModel.update(reservation);
//                if (isSaved) {
//                    new Alert(Alert.AlertType.CONFIRMATION, "Reservation saved!").show();
//                    txtClear();
//                    getAll();
//                    setCellValueFactory();
//                    generateNextResId();
//                    loadRoomIds();
//                }
//            } catch (SQLException e) {
//                new Alert(Alert.AlertType.ERROR, "Reservation Not Update ,Please Check Details & Try Again!").show();
//                txtClear();
//            }loadRoomIds();
//        }
//    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String resId = lblReservationID.getText();
        if(!lblReservationID.getText().isBlank()) {
            try {
                boolean isDeleted = reservationBo.delete(resId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Reservation Deleted!").show();
                    txtClear();
                    getAll();
                    setCellValueFactory();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Reservation Id Not Exist!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Please Check Details & Try Again!").show();
                txtClear();
            }
        }else{new Alert(Alert.AlertType.ERROR, "Please Fill Reservation Id!").show(); }
    }

    private void loadRoomIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = roomBo.loadIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmdRoomID.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }
    private void generateNextResId() throws ClassNotFoundException {
        try {
            String id = reservationBo.generateNewID();
            lblReservationID.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void roomOnAction(ActionEvent actionEvent) {
        String roomID = cmdRoomID.getValue();
        try {
        Room room = RoomModel.searchById(roomID);
        lblRoomPrice.setText(String.valueOf(room.getPrice()));

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();

        }
    }

    public void gueestIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!txtguestId.getText().isBlank()) {
            guest guest = guestBo.search(txtguestId.getText());
            if(guest != null){
                cmbType.setValue(guest.getGuest_ID_type());
                txtguestId.setText(guest.getGuest_ID());
                txtGuestName.setText(guest.getGuest_Name());
                txtContact.setText(guest.getGuest_Contact_No());
                txtCountry.setText(guest.getGuest_Country());
                txtEmail.setText(guest.getGuest_Email());
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Guest Id Not Exist!").show();
            }
        }
    }
}
