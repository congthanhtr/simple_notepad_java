package com.example.demo;

import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class DatabaseConnection {
    Connection conn;
    PreparedStatement prep;
    ResultSet rs;

    DatabaseConnection() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.sql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/demo","root","");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUserFullName_fromUsername(String username) {
        try {
            prep = conn.prepareStatement("Select * from user where user_name = ?");
            prep.setString(1, username);
            rs = prep.executeQuery();
            if (rs.next()) {
                return rs.getString("user_fullname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return User.username_;
    }

    public boolean accountValidation(String username, String password) {
        try {
            prep = conn.prepareStatement("Select * from user where user_name = ?");
            prep.setString(1, username);
            rs = prep.executeQuery();
            if (rs.next()) {
                if (rs.getString("password").equals(password))
                    return true;
                else return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addStatus(String user_id, String status, String imagePath) {
        try {
            prep = conn.prepareStatement("Insert into status(user_name, time, image_path, status) values (?,?,?,?)");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String time = now.toString();
            prep.setString(1, user_id);
            prep.setString(2, time);
            prep.setString(3, imagePath);
            prep.setString(4, status);

            int result = prep.executeUpdate();
            if (result == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setContentText("Posted to your FuckBug");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Notification");
                alert.setContentText("Failed to post status... try again...");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFriend(String username1, String username2, int friendship_status) {
        try {
            // add 2 user to friend table - that manage friendship of all user | -1 = block; 0 = pending; 1 = friend; 2 = not pending
            prep = conn.prepareStatement("insert into friend(user_name1, user_name2, friendship_status) values (?,?,?)");
            prep.setString(1, username1);
            prep.setString(2, username2);
            prep.setInt(3, friendship_status);
            prep.executeUpdate();

            if (friendship_status == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Friend request");
                alert.setContentText("Friend request was sent!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getFriend(String username1, String username2) { // get friendship status between username1 and username 2
        try {
            // cause username1 can be at column user_name1 but also can be at column user_name2
            prep = conn.prepareStatement("select * from friend where user_name1 = ? or user_name1 = ?");
            prep.setString(1, username1);
            prep.setString(2, username2);
            rs = prep.executeQuery();
            rs.next();
            String col1 = rs.getString("user_name1");
            String col2 = rs.getString("user_name2");
            int st = rs.getInt("friendship_status"); // st means status of friendship
            if (st == 0) {
                if (col1 == username1) {
                    // col1 = username1 means username1 sent friend request to username2 -> current user is the sender -> return 0
                    return 0;
                }
                else {
                    // else, col2 = username 1 -> user2 sent request -> current user is the receiver -> return 3
                    return 3;
                }
            }
            return st;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -2; // nothing to each other
    }

    public ArrayList<StatusObject> loadStatus(String user_name) {
        try {
            prep = conn.prepareStatement("select * from status where user_name = ?");
            prep.setString(1, user_name);
            rs = prep.executeQuery();
            ArrayList<StatusObject> res = new ArrayList<>();
            while(rs.next()) {
                String user_id = rs.getString("user_name");
                String time = rs.getString("time");
                String image_path = rs.getString("image_path");
                String status = rs.getString("status");
                ImageView img = new ImageView();
                if (image_path.equals("")) {
                    img.setVisible(false);
                }
                else {
                    img.setImage(new Image(image_path));
                }
                StatusObject statusObject = new StatusObject(user_id, time, status, img);
                res.add(statusObject);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String searchUser(String username) {
        try {
            prep = conn.prepareStatement("Select * from user where user_name = ?");
            prep.setString(1, username);
            rs = prep.executeQuery();
            if (rs.next()) {
                return rs.getString("user_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "no user match";
    }
}
