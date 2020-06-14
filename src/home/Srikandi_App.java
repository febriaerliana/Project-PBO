 package home;
 
 import javafx.application.Application;
 import static javafx.application.Application.launch;
 import javafx.event.EventHandler;
 import javafx.fxml.FXMLLoader;
 import javafx.scene.Parent;
 import javafx.scene.Scene;
 import javafx.scene.input.MouseEvent;
 import javafx.stage.Stage;
 import javafx.stage.StageStyle;
 
 public class Srikandi_App extends Application
 {
   private double xOffset = 0.0D;
   private double yOffset = 0.0D;
   
   public void start(final Stage stage) throws Exception
   {
     Parent root = (Parent)FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
     
     stage.initStyle(StageStyle.DECORATED);
     stage.setMaximized(false);
     
     root.setOnMousePressed(new EventHandler<MouseEvent>()
     {
       public void handle(MouseEvent event) {
         Srikandi_App.this.xOffset = event.getSceneX();
         Srikandi_App.this.yOffset = event.getSceneY();
       }
       
     });
     root.setOnMouseDragged(new EventHandler<MouseEvent>()
     {
       public void handle(MouseEvent event) {
         stage.setX(event.getScreenX() - Srikandi_App.this.xOffset);
         stage.setY(event.getScreenY() - Srikandi_App.this.yOffset);
       }
       
     });
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
   }
   
   public static void main(String[] args) {
     launch(args);
   }
 }
