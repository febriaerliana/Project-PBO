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
import models.AllEvents;
import utils.ConnectionUtil;

public class UserEventsController implements Initializable {
    @FXML
    private TableView<AllEvents> table_events;

    @FXML
    private TableColumn<AllEvents, String> col_id;
    
    @FXML
    private TableColumn<AllEvents, String> col_judul;

    @FXML
    private TableColumn<AllEvents, String> col_konten;

    @FXML
    private TableColumn<AllEvents, String> col_link;
    
    @FXML
    private Button btnTips;
    
    @FXML
    private Button btnBerita;
    
    @FXML
    private Button btnEvents;
    
    @FXML
    private Button btnProfil;
       
    ObservableList<AllEvents> oblist = FXCollections.observableArrayList();
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public void View(){
        try {
            conn = ConnectionUtil.conDB();
            rs = conn.createStatement().executeQuery("select * from eventss");
            
            while (rs.next()){
                oblist.add(new AllEvents (rs.getString("eventid"), rs.getString("judul"), rs.getString("konten"), rs.getString("link")));
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(UserEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        col_id.setCellValueFactory(new PropertyValueFactory<AllEvents,String>("eventid"));
        col_judul.setCellValueFactory(new PropertyValueFactory<AllEvents,String>("judul"));
        col_konten.setCellValueFactory(new PropertyValueFactory<AllEvents,String>("konten"));
        col_link.setCellValueFactory(new PropertyValueFactory<AllEvents,String>("link"));
        
        table_events.setItems(oblist);
        
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
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    View();
    } 
 
}
