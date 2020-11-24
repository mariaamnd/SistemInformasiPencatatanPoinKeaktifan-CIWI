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
public class jabatanKegiatanController implements Initializable{
    
    
     @FXML
    private void switchToSecondary() throws IOException {
            App.setRoot("secondary");
    }
    
    @FXML
    private Button cancelButton;

    @FXML
    private TextField IDTextField;

    @FXML
    private TextField KeteranganTextField;

    @FXML
    private TextField MasaTextField;

    @FXML
    private TextField InternasionalTextField;

    @FXML
    private Button insertButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox<String> comboOrganisasi;

    @FXML
    private ComboBox<String> comboTingkat;
    
    @FXML
    private ComboBox<String> comboJenis;

    @FXML
    private TableView<Jabatan> jabatanTableView;

    @FXML
    private TableColumn<Jabatan, Integer> colID;

    @FXML
    private TableColumn<Jabatan, String> colOrganisasi;
    
    @FXML
    private TableColumn<Jabatan, String> colTingkat;
    

    @FXML
    private TableColumn<Jabatan, String> colJenis;

    @FXML
    private TableColumn<Jabatan, String> colKeterangan;

    @FXML
    private TableColumn<Jabatan, String> colMasa;

    @FXML
    private TableColumn<Jabatan, Integer> colPoin;
    
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
    
    ObservableList<String> list = FXCollections.observableArrayList("Universitas~1 Tahun (Kepengurusan)", "Universitas~<1 Tahun (Kepanitiaan)", "Fakultas/Program Studi~1 Tahun(Kepengurusan)", "Fakultas/Program Studi~<1 Tahun (Kepanitiaan)");
    ObservableList<String> list1 = FXCollections.observableArrayList("Intern", "DIY", "Nasional", "Internasional");
    ObservableList<String> list2 = FXCollections.observableArrayList("Anggota", "Anggota UKM", "Ketua", "Ketua UKM", "Koordinastor Divisi UKM", "Koordinator Dept/Seksi", "Sekr/Bendahara UKM", "Sekretatis/Bendahara", "Wakil Ketua", "Wakil Ketua UKM");
    
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
                kegiatanjabatan = new Jabatan(rs.getInt("IDJabatan"), rs.getString("Organisasi"), rs.getString("Tingkat"), rs.getString("JenisJabatan"), rs.getString("Keterangan"), rs.getString("MasaJabatan"), rs.getInt("Poin"));    
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
        colOrganisasi.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("Organisasi"));
        colTingkat.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("Tingkat"));
        colJenis.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("JenisJabatan"));
        colKeterangan.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("Keterangan"));
        colMasa.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("MasaJabatan"));
        colPoin.setCellValueFactory(new PropertyValueFactory<Jabatan, Integer>("Poin"));
        
        jabatanTableView.setItems(list);
    }
    
    private void insertRecord(){
        String query = "INSERT INTO KegiatanJabatan VALUES (" + IDTextField.getText() + ", '" + comboOrganisasi.getValue() + "', '" + comboTingkat.getValue() + "' , '" + comboJenis.getValue() + "' , '" + KeteranganTextField.getText()+ "' , '" + MasaTextField.getText()+ "' , " + InternasionalTextField.getText() + ")";
        executeQuery(query);
        showJabatan();
    }
    
    private void updateRecord(){
        String query = "UPDATE KegiatanJabatan SET Organisasi = '" + comboOrganisasi.getValue() + "', Tingkat = '" + comboTingkat.getValue() + "' , JenisJabatan = '" + comboJenis.getValue() + "',Keterangan = '" + KeteranganTextField.getText() + "', MasaJabatan = '" + MasaTextField.getText() + "', Poin = " + InternasionalTextField.getText() + " WHERE IDJabatan = " + IDTextField.getText() + "";
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
        comboOrganisasi.setItems(list);
        comboTingkat.setItems(list1);
        comboJenis.setItems(list2);
        showJabatan();
    }
    
    public void comboChanged(ActionEvent event){
        colOrganisasi.setText(comboOrganisasi.getValue());
        colTingkat.setText(comboTingkat.getValue());
        colJenis.setText(comboJenis.getValue());
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
