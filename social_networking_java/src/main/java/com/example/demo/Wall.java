package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Wall implements Initializable {

    @FXML
    private Button addFriendBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Button blockBtn;

    @FXML
    private TableColumn<StatusObject, ImageView> imageCol;

    @FXML
    private Button messageBtn;

    @FXML
    private TableView<StatusObject> otherStatusTable;

    @FXML
    private TableColumn<StatusObject, String> timeCol;

    @FXML
    private TableColumn<StatusObject, String> statusCol;

    @FXML
    private TableColumn<StatusObject, String> userCol;

    @FXML
    private Label user_fullnameLabel;

    private ObservableList oblist = FXCollections.observableArrayList();

    public void load_friendship_status(String username1, String username2) {
        // here we do:
        // if these two are friend(1) -> set disable Add friend button
        // if pending request(0) -> set disable add friend button and set text to pending
        // if block(-1) -> no see status, set block button's text to unblock
        // if not pending(2) -> be rejected -> set add friend button to normal
        // if receive(3) -> set add button to menu button, with reject or accept friend request
        // else if (-2) means nothing to each other
        DatabaseConnection db = new DatabaseConnection();
        int status = db.getFriend(username1, username2);
        System.out.println(status);
        if (status == -2){
            // do nothing
        }
        else if (status == -1) {
            otherStatusTable.setVisible(false);
            blockBtn.setText("Unblock");
            addFriendBtn.setDisable(true);
        }
        else if (status == 0) {
            addFriendBtn.setText("Pending...");
            addFriendBtn.setDisable(true);
        }
        else if (status == 1) {
            addFriendBtn.setDisable(true);
        }
        else if (status == 2) {
            return;
        }
        else if (status == 3) {
             // temporarily do nothing
        }
        else {
            System.out.println("status: " + status);
        }
    }

    @FXML
    void addFriendOnClicked(ActionEvent event) {
        DatabaseConnection db = new DatabaseConnection();
        db.setFriend(User.username_, User.usersearch, 0);
        addFriendBtn.setText("Pending...");
        addFriendBtn.setDisable(true);
    }

    @FXML
    void backOnClicked(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SearchResult.fxml"));
            Stage window = (Stage) backBtn.getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void blockOnClicked(ActionEvent event) {

    }

    @FXML
    void messageOnClicked(ActionEvent event) {

    }

    public void setUserFullName(String username) {
        DatabaseConnection db = new DatabaseConnection();
        String user_fullname = db.getUserFullName_fromUsername(username);
        if (user_fullname == null) {
            user_fullname = "null";
        }
        user_fullnameLabel.setText(user_fullname) ;
        User.userfullname_ = user_fullname;
    }

    public void load_DashBoard(String Username) {
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<StatusObject> allStatus = db.loadStatus(Username);

        for (int i = 0; i < allStatus.size(); i++) {
            oblist.add(allStatus.get(i));
        }
        otherStatusTable.setItems(oblist);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUserFullName(User.usersearch);
        load_DashBoard(User.usersearch);
        // set up friendship
        load_friendship_status(User.username_, User.usersearch);

        // set up for the table
        userCol.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        imageCol.setCellValueFactory(new PropertyValueFactory<>("image"));


    }
}
