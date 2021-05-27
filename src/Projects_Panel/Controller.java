package Projects_Panel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Projects_Panel.singleProjectDetails.DetailsController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.sql.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import javax.swing.*;


public class Controller implements Initializable {

    @FXML
    private TextField Cost_input;

    @FXML
    private TableView<Projects> projects_table;

    @FXML
    private TableColumn<Projects, String> projects_column;

    @FXML
    private TableColumn<Projects, Integer> id_column0;
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


    ObservableList<Projects> listP;
    int index = -1;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String CheckedId = null;
    String CheckedManager = null;

    private void CheckClients() {

        Connection con = MYSQLconnect.ConnectDb();
        try {
            String sql = "select exists(Select * from sql4409579.Clients where id = " + client_input.getText() + ");";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            rs.next();
            long i = (long) (rs.getObject(1));
            System.out.println(i);
            if (i == 1) {
                System.out.println("client found " + i);
                CheckedId = client_input.getText();
            } else {
                System.out.println("client not found " + i);
                CheckedId = null;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error in checking clients");
        }
    }

    private void CheckManagers() {


        Connection con = MYSQLconnect.ConnectDb();
        try {
            String sql = "select exists(Select * from sql4409579.employees where id = " + ManagerName_input.getText() + " and position='Manager');";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            rs.next();
            long i = (long) (rs.getObject(1));
            System.out.println(i);
            if (i == 1) {
                System.out.println("Manager found " + i);
                CheckedManager = ManagerName_input.getText();
            } else {
                System.out.println("Manager not found " + i);
                CheckedManager = null;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error in checking Managers");
        }
    }

    private void CheckData() {
        CheckClients();
        CheckManagers();
    }

    public void AddProject() {
        CheckData();
        con = MYSQLconnect.ConnectDb();

        String sql = "INSERT INTO sql4409579.Projects (title,projectDescription,date,type,client_name,Manager_name,cost)values(?,?,?,?,?,?,?)";
        if (CheckedId != null && CheckedManager != null) {
            try {
                pst = con.prepareStatement(sql);
                pst.setString(1, projectName_input.getText());
                pst.setString(2, describtion_input.getText());
                pst.setString(3, String.valueOf(date_input.getValue()));
                pst.setString(4, type_input.getText());
                pst.setString(5, CheckedId);
                pst.setString(6, CheckedManager);
                pst.setString(7, Cost_input.getText());
                pst.execute();

                JOptionPane.showMessageDialog(null, "Project add success");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "please fill all fields with appropriate data");
            }

        } else if (CheckedManager == null && CheckedId != null) {
            JOptionPane.showMessageDialog(null, "Manager doesn't exist");
        } else if (CheckedManager != null && CheckedId == null) {
            JOptionPane.showMessageDialog(null, "Client doesn't exist");
        } else {
            JOptionPane.showMessageDialog(null, "Both Manager and Client don't exist");
        }


    }

    public void UpdateTable() {


        projects_column.setCellValueFactory(new PropertyValueFactory<Projects, String>("projectTitle"));
        id_column0.setCellValueFactory(new PropertyValueFactory<Projects, Integer>("projectId"));
        listP = MYSQLconnect.getDataProjects();
        projects_table.setItems(listP);

    }

    public Projects getSelected() {
        return projects_table.getSelectionModel().getSelectedItem();

    }

    public void DeleteProject() {
        Projects selected = projects_table.getSelectionModel().getSelectedItem();
        con = MYSQLconnect.ConnectDb();
        String sql = "DELETE FROM sql4409579.Projects WHERE id=" + selected.getProjectId() + ";";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
            System.out.println("failed to delete");
        }

    }

    public void SeeInfoProject() {
        TableView<Projects> table = projects_table;
        table.setRowFactory(tv -> {
            TableRow<Projects> row = new TableRow<>();
            row.setOnMouseClicked(event -> {

                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    Projects selected = getSelected();
                    try {
                        // passing data to other controller
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("./singleProjectDetails/DetailPageUI.fxml"));
                        Parent root = loader.load();
                        DetailsController controller = loader.getController();
                        controller.initData(selected);
                        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                        Scene scene = new Scene(root);
                        stage.setTitle("project Detail");
                        stage.setScene(scene);
                        stage.show();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("controller initialized");
        UpdateTable();
        SeeInfoProject();


    }
}
