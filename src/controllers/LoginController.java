 package controllers;
 
 import java.io.IOException;
 import java.net.URL;
 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.util.ResourceBundle;
 import javafx.fxml.FXML;
 import javafx.fxml.FXMLLoader;
 import javafx.fxml.Initializable;
 import javafx.scene.Node;
 import javafx.scene.Parent;
 import javafx.scene.Scene;
 import javafx.scene.control.Button;
 import javafx.scene.control.Label;
 import javafx.scene.control.TextField;
 import javafx.scene.input.MouseEvent;
 import javafx.scene.paint.Color;
 import javafx.stage.Stage;
import models.User;
 import utils.ConnectionUtil;
 

 public class LoginController implements Initializable {
   
   @FXML
   private Label lblErrors;

   @FXML
   private TextField txtUsername;

   @FXML
   private TextField txtPassword;
   
   @FXML
   private Button btnSignin;
   
   @FXML
   private Button btnSignup;
   
   Connection conn = null;
   PreparedStatement pst = null;
   ResultSet rs = null;
   
   @FXML
   public void handleButtonAction(MouseEvent event)
   {
     if (event.getSource() == this.btnSignup)
     {
       try
       {
         Node node = (Node)event.getSource();
         Stage stage = (Stage)node.getScene().getWindow();
         stage.close();
         Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/Register.fxml")));
         stage.setScene(scene);
         stage.show();
       }
       catch (IOException ex) {
         System.err.println(ex.getMessage());
       }
     }
     
     if (event.getSource() == btnSignin && txtUsername.getText().equals("febria")) {
            if (logIn().equals("Success")) {
                try {

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/OnBoard.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }
     
     if (event.getSource() == btnSignin) {
            if (logIn().equals("Success")) {
                try {

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/UserOnBoard.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }
   }
   
   public void initialize(URL url, ResourceBundle rb)
   {

   }
   
   public LoginController() {
     this.conn = ConnectionUtil.conDB();
   }
   private String logIn() {
        String status = "Success";
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        if(username.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM users Where username = ? and password = ?";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (!rs.next()) {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                } else {
                    User.getInstance(rs.getString("username"), rs.getString("email"), rs.getString("password"));
                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
                    
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        
        return status;
    }
   private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }

 }
