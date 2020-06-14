 package controllers;
 
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.ConnectionUtil;
 
 public class RegisterController implements javafx.fxml.Initializable
 {
   
   @FXML
   private Label lblErrors;
   
   @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtEmail;

    @FXML
    private Button btnSave;

    @FXML
    private PasswordField txtPass;

    @FXML
    private Label labSingleFile;

    @FXML
    private ComboBox txtGender;

    @FXML
    private Label lblStatus;
   
   Connection con = null;
   PreparedStatement pst = null;
   ResultSet rs = null;

   private ObservableList<ObservableList> data;
   
   @Override
   public void initialize(URL url, ResourceBundle rb)
   {
     txtGender.getItems().addAll("Laki laki", "Perempuan");

   }

   @FXML
   public void handleButtonAction(MouseEvent event)
   {
     if (event.getSource() == this.btnSave  && txtGender.getValue().equals("Perempuan")){
         if (Register().equals("Success")){
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
   
   public RegisterController()
   {
     this.con = ((Connection)ConnectionUtil.conDB());
   }
   private String Register() {
        String status = "Success";
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String password = txtPass.getText();
        String gender = txtGender.getValue().toString();
        if(username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            //query
            String sql = "insert into users (username,email,password,gender) values (?,?,?,?)";
            try {
                pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, email);
                pst.setString(3, password);
                pst.setString(4, gender);
                pst.execute();
                
                
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
