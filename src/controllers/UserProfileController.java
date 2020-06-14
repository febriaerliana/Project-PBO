package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.User;


public class UserProfileController implements Initializable {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    

    @FXML
    private Button btnKeluar;
    @FXML
    private Button btnTips;
    @FXML
    private Button btnBerita;
    @FXML
    private Button btnEvents;
    @FXML
    private Button btnProfil;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> col_uname;
    @FXML
    private TableColumn<User, String> col_email;
    @FXML
    private TableColumn<User, String> col_pass;
    
    
    
    

    private void initTable() {
        
        User user = User.userInstance;
        ObservableList<User> users = FXCollections.observableArrayList(
                user
        );
        table.setItems(users);
        
        col_uname.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_pass.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
    }    
    
    
    
    @FXML
    void getSelected(MouseEvent event) {
    }
    
    @FXML
    public void handleButtonAction(MouseEvent event) {
        if (event.getSource() == this.btnTips)
     {
       try
       {
         Node node = (Node)event.getSource();
         Stage stage = (Stage)node.getScene().getWindow();
         stage.close();
         Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/UserTips.fxml")));
         stage.setScene(scene);
         stage.show();
       }
       catch (IOException ex) {
         System.err.println(ex.getMessage());
       }
     }
     if (event.getSource() == this.btnBerita)
     {
       try
       {
         Node node = (Node)event.getSource();
         Stage stage = (Stage)node.getScene().getWindow();
         stage.close();
         Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/UserNews.fxml")));
         stage.setScene(scene);
         stage.show();
       }
       catch (IOException ex) {
         System.err.println(ex.getMessage());
       }
     }
     if (event.getSource() == this.btnProfil)
     {
       try
       {
         Node node = (Node)event.getSource();
         Stage stage = (Stage)node.getScene().getWindow();
         stage.close();
         Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/UserProfile.fxml")));
         stage.setScene(scene);
         stage.show();
       }
       catch (IOException ex) {
         System.err.println(ex.getMessage());
       }
     }
     if (event.getSource() == this.btnEvents)
     {
       try
       {
         Node node = (Node)event.getSource();
         Stage stage = (Stage)node.getScene().getWindow();
         stage.close();
         Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/UserEvents.fxml")));
         stage.setScene(scene);
         stage.show();
       }
       catch (IOException ex) {
         System.err.println(ex.getMessage());
       }
     }
     if (event.getSource() == this.btnKeluar)
     {
       try
       {
         Node node = (Node)event.getSource();
         Stage stage = (Stage)node.getScene().getWindow();
         stage.close();
         Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/Login.fxml")));
         stage.setScene(scene);
         stage.show();
       }
       catch (IOException ex) {
         System.err.println(ex.getMessage());
       }
     }
    }
    
    
}
