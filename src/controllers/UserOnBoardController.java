package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UserOnBoardController implements Initializable {

   @FXML
   private Button btnTips;
   @FXML
   private Button btnEvents;
   @FXML
   private Button btnBerita;
   @FXML
   private Button btnProfil;

   
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
   
   @FXML
   public void handleButtonAction(MouseEvent event)
   {
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
    
}
