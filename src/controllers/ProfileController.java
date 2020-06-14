package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
import models.AllUser;
import utils.ConnectionUtil;

public class ProfileController implements Initializable {

    @FXML
    private Button btnTips;
    @FXML
    private Button btnBerita;
    @FXML
    private Button btnEvents;
    @FXML
    private Button btnProfil;
    @FXML
    private Button btnKeluar;
    @FXML
    private TableView<AllUser> table;
    @FXML
    private TableColumn<AllUser, String> col_uname;
    @FXML
    private TableColumn<AllUser, String> col_email;
    @FXML
    private TableColumn<AllUser, String> col_id;
    @FXML
    private TableColumn<AllUser, String> col_pas;
    @FXML
    private TableColumn<AllUser, String> col_gender;
    
    ObservableList<AllUser> oblist = FXCollections.observableArrayList();
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public void View(){
        try {
            conn = ConnectionUtil.conDB();
            rs = conn.createStatement().executeQuery("select * from users");
            
            while (rs.next()){
                oblist.add(new AllUser (rs.getString("user_id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("gender")));
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        col_id.setCellValueFactory(new PropertyValueFactory<AllUser,String>("user_id"));
        col_uname.setCellValueFactory(new PropertyValueFactory<AllUser,String>("username"));
        col_email.setCellValueFactory(new PropertyValueFactory<AllUser,String>("email"));
        col_pas.setCellValueFactory(new PropertyValueFactory<AllUser,String>("password"));
        col_gender.setCellValueFactory(new PropertyValueFactory<AllUser,String>("gender"));
        
        table.setItems(oblist);
        
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
         Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/Tips.fxml")));
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
         Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/News.fxml")));
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
         Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/Profile.fxml")));
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
         Scene scene = new Scene((Parent)FXMLLoader.load(getClass().getResource("/fxml/Events.fxml")));
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        View();
    }    
}
