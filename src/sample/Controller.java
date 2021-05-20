package sample;



import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.EventHandler;


import javax.swing.*;


public class Controller implements Initializable{


    @FXML
    private TableView<Projects> projects_table;

    @FXML
    private TableColumn<Projects, String> projects_column;
    //textfields
    @FXML
    private TextField describtion_input;

    @FXML
    private TextField projectName_input;

    @FXML
    private TextField client_input;

    @FXML
    private TextField ManagerName_input;

    //DatePicker

    @FXML
    private DatePicker date_input;
    //Menu Button
    @FXML
    private MenuButton type_input;



    ObservableList<Projects> listP ;
    int index = -1;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

//    public EventHandler<Event> eventHandler = event -> System.out.println("Hello World");

    public void AddUsers(){
        con = MYSQLconnect.ConnectDb();
        String sql = "INSERT INTO sql4409579.Projects (title,projectDescription,date,type,client_name,Manager_name)values(?,?,?,?,?,?)";
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1,projectName_input.getText());
            pst.setString(2,describtion_input.getText());
            pst.setString(3, String.valueOf(date_input.getValue()));
            pst.setString(4,type_input.getAccessibleText()	);
            pst.setString(5,client_input.getText());
            pst.setString(6,ManagerName_input.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Project add success");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }

    }
    public void UpdateTable(){


        projects_column.setCellValueFactory(new PropertyValueFactory<Projects,String>("projectTitle"));
        listP = MYSQLconnect.getDataProjects();
        projects_table.setItems(listP);

    }

    public void DeleteProject(){
        Projects selected = projects_table.getSelectionModel().getSelectedItem();
        con = MYSQLconnect.ConnectDb();
        String sql = "DELETE FROM sql4409579.Projects WHERE id="+selected.getProjectId()+";";

        try{
            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();

        }catch (Exception e){
            System.out.println("failed to delete");
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("controller initialized");
        UpdateTable();




    }
}
