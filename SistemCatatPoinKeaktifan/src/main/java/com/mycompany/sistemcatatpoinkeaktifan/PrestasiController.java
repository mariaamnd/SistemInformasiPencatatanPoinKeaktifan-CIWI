/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author acer
 */
public class PrestasiController implements Initializable{
    
    @FXML
    private Button cancelButton;
    
     @FXML
    private void switchToSecondary() throws IOException {
            App.setRoot("secondary");
    }
    
      @FXML
    private TextField IDTextField;

    @FXML
    private TextField KeteranganTextField;
    
    @FXML
    private TextField TanggalTextField;
    
    @FXML
    private TextField InternasionalTextField;

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
    private TableColumn<Prestasi, Integer> colInternasional;

    @FXML
    private Button updateButton;

    @FXML
    private Button insertButton;

    @FXML
    private Button deleteButton;
    
    @FXML 
    public ComboBox<String> combobox;
    
     @FXML 
    public ComboBox<String> combobox1;
    
    ObservableList<String> list = FXCollections.observableArrayList("Juara I", "Juara II", "Juara III", "Juara Harapan I", "Juara Harapan II", "Pembicara Seminar", "Moderator Seminar", "Peserta Seminar/Utusan", "Penulis Artikel");
    ObservableList<String> list1 = FXCollections.observableArrayList("Intern", "DIY", "Nasional", "Internasional");
    
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
                kegiatanprestasi = new Prestasi(rs.getInt("IDPrestasi"), rs.getString("JenisPrestasi"), rs.getString("TingkatPrestasi"), rs.getString("Keterangan"),rs.getString("Tanggal"),rs.getInt("Poin"));
                kegiatanprestasiList.add(kegiatanprestasi);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return kegiatanprestasiList;
    }
    
    public void showPrestasi(){
        ObservableList<Prestasi> list = getPrestasiList();
        
        colID.setCellValueFactory(new PropertyValueFactory<Prestasi, Integer>("IDPrestasi"));
        colJenis.setCellValueFactory(new PropertyValueFactory<Prestasi, String>("JenisPrestasi"));
        colTingkat.setCellValueFactory(new PropertyValueFactory<Prestasi, String>("TingkatPrestasi"));
        colKeterangan.setCellValueFactory(new PropertyValueFactory<Prestasi, String>("Keterangan"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<Prestasi, String>("Tanggal"));
        colInternasional.setCellValueFactory(new PropertyValueFactory<Prestasi, Integer>("Poin"));
        
        PrestasiTableView.setItems(list);
    }
    
    private void insertRecord(){
        String query = "INSERT INTO KegiatanPrestasi VALUES (" + IDTextField.getText() + ", '" + combobox.getValue() + "', '" + combobox1.getValue() + "', '" + KeteranganTextField.getText()+ "', '" + TanggalTextField.getText()+ "'," + InternasionalTextField.getText() + ")";
        executeQuery(query);
        showPrestasi();
    }
    
    private void updateRecord(){
        String query = "UPDATE KegiatanPrestasi SET JenisPrestasi = '" + combobox.getValue() + "',  TingkatPrestasi = '" + combobox.getValue() + "' , Keterangan = '" + KeteranganTextField.getText() + "' , Tanggal = '" + TanggalTextField.getText() + "' , Poin = " + InternasionalTextField.getText() + " WHERE IDPrestasi = " + IDTextField.getText() + "";
        executeQuery(query);
        showPrestasi();
        
    }
    
    private void deleteButton(){
        String query = "DELETE FROM KegiatanPrestasi WHERE IDPrestasi = '" + IDTextField.getText() + "'";
        executeQuery(query);
        showPrestasi();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combobox.setItems(list);
        combobox1.setItems(list1);
        showPrestasi();
        
    }
    
    public void comboChanged(ActionEvent event){
        colJenis.setText(combobox.getValue());
        colTingkat.setText(combobox1.getValue());
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
