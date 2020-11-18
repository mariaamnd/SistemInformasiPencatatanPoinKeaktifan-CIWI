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
    private TableView<Prestasi> PrestasiTableView;

    @FXML
    private TableColumn<Prestasi, Integer> colID;

    @FXML
    private TableColumn<Prestasi, String> colJenis;

    @FXML
    private TableColumn<Prestasi, Integer> colIntern;

    @FXML
    private TableColumn<Prestasi, Integer> colDIY;
    
    @FXML
    private TableColumn<Prestasi, Integer> colNasional;
    
    @FXML
    private TableColumn<Prestasi, Integer> colInternasional;

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
                kegiatanprestasi = new Prestasi(rs.getInt("IDPrestasi"), rs.getString("JenisPrestasi"), rs.getInt("PoinIntern"), rs.getInt("PoinDIY"),rs.getInt("PoinNasional"),rs.getInt("PoinInternasional"));
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
        colIntern.setCellValueFactory(new PropertyValueFactory<Prestasi, Integer>("PoinIntern"));
        colDIY.setCellValueFactory(new PropertyValueFactory<Prestasi, Integer>("PoinDIY"));
        colNasional.setCellValueFactory(new PropertyValueFactory<Prestasi, Integer>("PoinNasional"));
        colInternasional.setCellValueFactory(new PropertyValueFactory<Prestasi, Integer>("PoinInternasional"));
        
        PrestasiTableView.setItems(list);
    }
    
    private void insertRecord(){
        String query = "INSERT INTO KegiatanPrestasi VALUES (" + IDTextField.getText() + ", '" + JenisTextField.getText() + "'," + InternTextField.getText() + "," + DIYTextField.getText()+ "," + NasionalTextField.getText()+ "," + InternasionalTextField.getText() + ")";
        executeQuery(query);
        showPrestasi();
    }
    
    private void updateRecord(){
        String query = "UPDATE KegiatanPrestasi SET JenisPrestasi = '" + JenisTextField.getText() + "',  PoinIntern = " + InternTextField.getText() + " , PoinDIY = " + DIYTextField.getText() + " , PoinNasional = " + NasionalTextField.getText()+ " , PoinInternasional = " + InternasionalTextField.getText() + " WHERE IDPrestasi = " + IDTextField.getText() + "";
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
