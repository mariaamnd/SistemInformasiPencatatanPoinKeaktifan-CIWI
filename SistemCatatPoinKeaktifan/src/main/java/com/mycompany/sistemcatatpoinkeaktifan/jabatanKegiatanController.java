package com.mycompany.sistemcatatpoinkeaktifan;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author acer
 */
public class jabatanKegiatanController implements Initializable{
    
    @FXML
    private Button cancelButton;
    
     @FXML
    private void switchToSecondary() throws IOException {
            App.setRoot("secondary");
    }
    
      @FXML
    private TextField IDTextField;

    @FXML
    private TextField JenisTextField;

    @FXML
    private TextField InternTextField;

    @FXML
    private TextField DIYTextField;
    
    @FXML
    private TextField NasionalTextField;
    
    @FXML
    private TextField InternasionalTextField;

    @FXML
    private TableView<Jabatan> jabatanTableView;

    @FXML
    private TableColumn<Jabatan, Integer> colID;

    @FXML
    private TableColumn<Jabatan, String> colJenis;

    @FXML
    private TableColumn<Jabatan, Integer> colIntern;

    @FXML
    private TableColumn<Jabatan, Integer> colDIY;
    
    @FXML
    private TableColumn<Jabatan, Integer> colNasional;
    
    @FXML
    private TableColumn<Jabatan, Integer> colInternasional;

    @FXML
    private Button updateButton;

    @FXML
    private Button insertButton;

    @FXML
    private Button deleteButton;
    
    @FXML
    private void handleButtonAction(ActionEvent event){
        if(event.getSource() ==  insertButton){
            insertRecord();
        }else if (event.getSource() == updateButton){
            updateRecord();
        }else if(event.getSource() == deleteButton){
            deleteButton();
    }
        
        
    }
    
    public ObservableList<Jabatan> getJabatanList(){
        ObservableList<Jabatan> kegiatanjabatanList = FXCollections.observableArrayList();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "SELECT * FROM KegiatanJabatan";
        Statement st;
        ResultSet rs;
        
        try{
            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            Jabatan kegiatanjabatan;
            while(rs.next()){
                kegiatanjabatan = new Jabatan(rs.getInt("IDJabatan"), rs.getString("JenisJabatan"), rs.getInt("PoinIntern"), rs.getInt("PoinDIY"), rs.getInt("PoinNasional"), rs.getInt("PoinInternasional"));
                kegiatanjabatanList.add(kegiatanjabatan);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return kegiatanjabatanList;
    }
    
    public void showJabatan(){
        ObservableList<Jabatan> list = getJabatanList();
        
        colID.setCellValueFactory(new PropertyValueFactory<Jabatan, Integer>("IDJabatan"));
        colJenis.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("JenisJabatan"));
        colIntern.setCellValueFactory(new PropertyValueFactory<Jabatan, Integer>("PoinIntern"));
        colDIY.setCellValueFactory(new PropertyValueFactory<Jabatan, Integer>("PoinDIY"));
        colNasional.setCellValueFactory(new PropertyValueFactory<Jabatan, Integer>("PoinNasional"));
        colInternasional.setCellValueFactory(new PropertyValueFactory<Jabatan, Integer>("PoinInternasional"));
        
        jabatanTableView.setItems(list);
    }
    
    private void insertRecord(){
        String query = "INSERT INTO KegiatanJabatan VALUES (" + IDTextField.getText() + ", '" + JenisTextField.getText() + "'," + InternTextField.getText() + "," + DIYTextField.getText()+ ", " + NasionalTextField.getText()+ ", " + InternasionalTextField.getText() + ")";
        executeQuery(query);
        showJabatan();
    }
    
    private void updateRecord(){
        String query = "UPDATE KegiatanJabatan SET JenisJabatan = '" + JenisTextField.getText() + "', PoinIntern = " + InternTextField.getText() + " , PoinDIY = " + DIYTextField.getText() + ", PoinInternasional = " + InternasionalTextField.getText() + " WHERE IDJabatan = " + IDTextField.getText() + "";
        executeQuery(query);
        showJabatan();
        
    }
    
    private void deleteButton(){
        String query = "DELETE FROM KegiatanJabatan WHERE IDJabatan = '" + IDTextField.getText() + "'";
        executeQuery(query);
        showJabatan();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showJabatan();
    }

    private void executeQuery(String query) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        Statement st;
        try{
            st = connectDB.createStatement();
            st.execute(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
}
