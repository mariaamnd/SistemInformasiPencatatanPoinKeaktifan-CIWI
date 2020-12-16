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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ValidasiController {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField IDTextField;

    @FXML
    private Button insertButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Label labelValidasi;

    @FXML
    private TextField validasi;

    @FXML
    private TableView<Prestasi> PrestasiTableView;

    @FXML
    private TableColumn<Prestasi, Integer> colID;

    @FXML
    private TableColumn<Prestasi, String> colJenis;

    @FXML
    private TableColumn<Prestasi, String> colTingkat;

    @FXML
    private TableColumn<Prestasi, String> colKeterangan;
    
    @FXML
    private TableColumn<Prestasi, String> colTanggal;
    
    @FXML
    private TableColumn<Prestasi, Integer> colPoin;
    
    @FXML
    private TableColumn<Prestasi, String> colBukti;

    @FXML
    private TableColumn<Prestasi, String> colValidasi;

    @FXML
    void handleButtonAction(ActionEvent event) {
         if(event.getSource() ==  insertButton){
            insertRecord();
        }else if (event.getSource() == deleteButton){
            deleteButton();
        }
    }

    @FXML
    void switchToSecondary(ActionEvent event) throws IOException {
        App.setRoot("menuAdmin");
    }
    
        public ObservableList<Prestasi> getPrestasiList(){
        ObservableList<Prestasi> kegiatanprestasiList = FXCollections.observableArrayList();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "SELECT * FROM KegiatanPrestasi";
        Statement st;
        ResultSet rs;
        
        try{
            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            Prestasi kegiatanprestasi;
            while(rs.next()){
                kegiatanprestasi = new Prestasi(rs.getInt("IDPrestasi"), rs.getString("JenisPrestasi"), rs.getString("TingkatPrestasi"), rs.getString("Keterangan"),rs.getString("Tanggal"),rs.getInt("Poin"), rs.getString("Image"), rs.getString("StatusValidasi"));
                kegiatanprestasiList.add(kegiatanprestasi);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return kegiatanprestasiList;
    }
    
    public void showPrestasi(){
        ObservableList<Prestasi> list = getPrestasiList();
        
        colID.setCellValueFactory(new PropertyValueFactory<>("IDPrestasi"));
        colJenis.setCellValueFactory(new PropertyValueFactory<>("JenisPrestasi"));
        colTingkat.setCellValueFactory(new PropertyValueFactory<>("TingkatPrestasi"));
        colKeterangan.setCellValueFactory(new PropertyValueFactory<>("Keterangan"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("Tanggal"));
        colPoin.setCellValueFactory(new PropertyValueFactory<>("Poin"));
        colBukti.setCellValueFactory(new PropertyValueFactory<>("Image"));
        colValidasi.setCellValueFactory(new PropertyValueFactory<>("StatusValidasi"));
        
        PrestasiTableView.setItems(list);
    }
    
    private void insertRecord(){
        String query = "INSERT INTO KegiatanPrestasi VALUES (" + IDTextField.getText() + ",  '" + validasi.getText() + "' )";
        executeQuery(query);
        showPrestasi();
    }    
    
        private void deleteButton(){
        String query = "DELETE FROM KegiatanPrestasi WHERE IDPrestasi = '" + IDTextField.getText() + "'";
        executeQuery(query);
        showPrestasi();
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        showPrestasi();
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