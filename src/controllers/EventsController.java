package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.Events;
import utils.ConnectionUtil;


public class EventsController implements Initializable {
    
    @FXML
    private TableView<Events> table_events;

    @FXML
    private TableColumn<Events, Integer> col_id;

    @FXML
    private TableColumn<Events, String> col_judul;

    @FXML
    private TableColumn<Events, String> col_konten;

    @FXML
    private TableColumn<Events, String> col_link;
   
    @FXML
    private TextField txt_judul;

    @FXML
    private TextField txt_konten;

    @FXML
    private TextField txt_link;
       
    @FXML
    private TextField txt_id;
    
    @FXML
    private Button btnTips;
    
    @FXML
    private Button btnBerita;
    
    @FXML
    private Button btnEvents;
    
    @FXML
    private Button btnProfil;
    
    @FXML
    private TextField filterField;
       
    ObservableList<Events> listM;
    ObservableList<Events> dataList;  
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
     
    public void Add_events (){    
        conn = ConnectionUtil.conDB();
        String sql = "insert into eventss (eventid,judul,konten,link)values(?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.setString(2, txt_judul.getText());
            pst.setString(3, txt_konten.getText());
            pst.setString(4, txt_link.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Events Add succes");
            UpdateTable();
            search_events();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    

    @FXML
    void getSelected (MouseEvent event){
    index = table_events.getSelectionModel().getSelectedIndex();
    if (index <= -1){
        return;
    }
    txt_id.setText(col_id.getCellData(index).toString());
    txt_judul.setText(col_judul.getCellData(index).toString());
    txt_konten.setText(col_konten.getCellData(index).toString());
    txt_link.setText(col_link.getCellData(index).toString());
    
    }

    public void Edit (){   
        try {
            conn = ConnectionUtil.conDB();
            String value1 = txt_id.getText();
            String value2 = txt_judul.getText();
            String value3 = txt_konten.getText();
            String value4 = txt_link.getText();
            String sql = "update eventss set eventid= '"+value1+"',judul= '"+value2+"',konten= '"+
                    value3+"',link= '"+value4+"' where eventid='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            search_events();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Events,Integer>("eventid"));
        col_judul.setCellValueFactory(new PropertyValueFactory<Events,String>("judul"));
        col_konten.setCellValueFactory(new PropertyValueFactory<Events,String>("konten"));
        col_link.setCellValueFactory(new PropertyValueFactory<Events,String>("link"));
        
        listM = ConnectionUtil.getDataevents();
        table_events.setItems(listM);
    }
    
    @FXML
    void search_events() {          
        col_id.setCellValueFactory(new PropertyValueFactory<Events,Integer>("id"));
        col_judul.setCellValueFactory(new PropertyValueFactory<Events,String>("judul"));
        col_konten.setCellValueFactory(new PropertyValueFactory<Events,String>("konten"));
        col_link.setCellValueFactory(new PropertyValueFactory<Events,String>("link"));
        
        dataList = ConnectionUtil.getDataevents();
        table_events.setItems(dataList);
        FilteredList<Events> filteredData = new FilteredList<>(dataList, b -> true);  
 filterField.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (person.getJudul().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (person.getKonten().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }else if (person.getLink().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Events> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(table_events.comparatorProperty());  
  table_events.setItems(sortedData);      
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
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    UpdateTable();
    search_events();
    } 
}