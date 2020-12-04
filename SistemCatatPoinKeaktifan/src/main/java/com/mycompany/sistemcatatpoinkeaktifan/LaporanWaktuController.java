/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemcatatpoinkeaktifan;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class LaporanWaktuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        @FXML
        private TableView<Kegiatan> tvKegiatan;
        
        @FXML
        private Button backButton;
        
        @FXML
        private TableColumn<Prestasi, String> colJenisKegiatan;

        @FXML
        private TableColumn<Prestasi, String> colJenisPrestasi;

        @FXML
        private TableColumn<Prestasi, String> colJenisJabatan;
        
        @FXML
        private TableColumn<Prestasi, Integer> colTanggal;
        
    }    
    
}
