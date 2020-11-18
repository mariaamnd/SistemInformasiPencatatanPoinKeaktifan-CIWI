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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author acer
 */
public class KemahasiswaanController implements Initializable{
    
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
    private TextField PoinTextField;

    @FXML
    private TextField SifatTextField;

    @FXML
    private TableView<Kemahasiswaan> kemahasiswaanTableView;

    @FXML
    private TableColumn<Kemahasiswaan, Integer> colID;

    @FXML
    private TableColumn<Kemahasiswaan, String> colJenis;

    @FXML
    private TableColumn<Kemahasiswaan, Integer> colPoin;

    @FXML
    private TableColumn<Kemahasiswaan, String> colSifat;

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
    
    public ObservableList<Kemahasiswaan> getKemahasiswaanList(){
        ObservableList<Kemahasiswaan> kegiatankemahasiswaanList = FXCollections.observableArrayList();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "SELECT * FROM kegiatankemahasiswaan";
        Statement st;
        ResultSet rs;
        
        try{
            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            Kemahasiswaan kegiatankemahasiswaan;
            while(rs.next()){
                kegiatankemahasiswaan = new Kemahasiswaan(rs.getInt("IDKegiatan"), rs.getString("JenisKegiatan"), rs.getInt("Poin"), rs.getString("Sifat"));
                kegiatankemahasiswaanList.add(kegiatankemahasiswaan);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return kegiatankemahasiswaanList;
    }
    
    public void showKemahasiswaan(){
        ObservableList<Kemahasiswaan> list = getKemahasiswaanList();
        
        colID.setCellValueFactory(new PropertyValueFactory<Kemahasiswaan, Integer>("IDKegiatan"));
        colJenis.setCellValueFactory(new PropertyValueFactory<Kemahasiswaan, String>("JenisKegiatan"));
        colPoin.setCellValueFactory(new PropertyValueFactory<Kemahasiswaan, Integer>("Poin"));
        colSifat.setCellValueFactory(new PropertyValueFactory<Kemahasiswaan, String>("Sifat"));
        
        kemahasiswaanTableView.setItems(list);
    }
    
    private void insertRecord(){
        String query = "INSERT INTO kegiatankemahasiswaan VALUES (" + IDTextField.getText() + ", '" + JenisTextField.getText() + "'," + PoinTextField.getText() + ",'" + SifatTextField.getText() + "')";
        executeQuery(query);
        showKemahasiswaan();
    }
    
    private void updateRecord(){
        String query = "UPDATE kegiatankemahasiswaan SET JenisKegiatan = '" + JenisTextField.getText() + "', Poin = " + PoinTextField.getText() + " , Sifat = '" + SifatTextField.getText() + "' WHERE IDKegiatan = " + IDTextField.getText() + "";
        executeQuery(query);
        showKemahasiswaan();
        
    }
    
    private void deleteButton(){
        String query = "DELETE FROM kegiatankemahasiswaan WHERE IDKegiatan = '" + IDTextField.getText() + "'";
        executeQuery(query);
        showKemahasiswaan();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showKemahasiswaan();
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
