/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemcatatpoinkeaktifan;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author acer
 */
public class menuAdminController {
    @FXML
    private Button menuAccountButton;

    @FXML
    private Button logoutButton;
    
    @FXML
    private void cancelSwitchTampilan() throws IOException {
        App.setRoot("TampilanAwal");
    }
    
    @FXML
    private void accountButton() throws IOException {
        App.setRoot("menu1Admin");
    }
}
