package com.mycompany.sistemcatatpoinkeaktifan;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class GrafikController implements Initializable {
       @FXML
    private BarChart<?, ?> PoinChart;

    @FXML
    private CategoryAxis X;

    @FXML
    private NumberAxis Y;
    
     @FXML
    private Button backButton;
   
    /**
     * Initializes the controller class.
     */
     
    @FXML
    private void switchToMenuAdmin() throws IOException {
        App.setRoot("menuAdmin");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       XYChart.Series set1 = new XYChart.Series<>();
       set1.getData().add(new XYChart.Data("Natasha Nadya", 98));
       set1.getData().add(new XYChart.Data("Maria Amanda", 54));
       set1.getData().add(new XYChart.Data("Novia Samosir", 76));
       set1.getData().add(new XYChart.Data("Mark Lee", 88));
       set1.getData().add(new XYChart.Data("Jung Jaehyun", 65));
       set1.getData().add(new XYChart.Data("Joel", 98));
       
       
       PoinChart.getData().addAll(set1);
    }    
    
}
