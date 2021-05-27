package Projects_Panel.singleProjectDetails;

import Projects_Panel.MYSQLconnect;
import Projects_Panel.Projects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DetailsController implements Initializable {
    @FXML
    private AnchorPane Type_details;

    @FXML
    private Button backToProjects_button;

    @FXML
    private TextArea Project_describtion_details;

    @FXML
    private TextField project_title_details;

    @FXML
    private TextField client_id_details;

    @FXML
    private TextField Managers_id_details;

    @FXML
    private TextField cost_details;

    @FXML
    private DatePicker DatePicker_Details;

    @FXML
    private MenuButton type_input;

    @FXML
    public void backToProjectsPanel(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../sample.fxml"));
            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            stage.setTitle("Projects panel");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("back to projects panel failed");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    int index = -1;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String CheckedId = null;
    String CheckedManager = null;
    int ProjectId ;

    private void CheckClients() {

        Connection con = MYSQLconnect.ConnectDb();
        try {
            String sql = "select exists(Select * from sql4409579.Clients where id = " + client_id_details.getText() + ");";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            rs.next();
            long i = (long) (rs.getObject(1));
            System.out.println(i);
            if (i == 1) {
                System.out.println("client found " + i);
                CheckedId = client_id_details.getText();
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
            String sql = "select exists(Select * from sql4409579.employees where id = " + Managers_id_details.getText() + " and position='Manager');";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            rs.next();
            long i = (long) (rs.getObject(1));
            System.out.println(i);
            if (i == 1) {
                System.out.println("Manager found " + i);
                CheckedManager = Managers_id_details.getText();
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

    public void UpdateProject() {
        CheckData();
        con = MYSQLconnect.ConnectDb();

        String sql =
                "UPDATE sql4409579.Projects set title=?,projectDescription=?,date =?,type=?,client_name=?,Manager_name=?,cost=?  WHERE id = ?; ";

        if (CheckedId != null && CheckedManager != null) {
            try {
                pst = con.prepareStatement(sql);
                pst = con.prepareStatement(sql);
                pst.setString(1, project_title_details.getText());
                pst.setString(2, Project_describtion_details.getText());
                pst.setString(3, String.valueOf(DatePicker_Details.getValue()));
                pst.setString(4, type_input.getText());
                pst.setString(5, CheckedId);
                pst.setString(6, CheckedManager);
                pst.setString(7, cost_details.getText());
                pst.setString(8, String.valueOf(ProjectId));
                pst.execute();
                JOptionPane.showMessageDialog(null, "Project updated successfully");

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

    public void initData(Projects project) {


        client_id_details.setText(String.valueOf(project.getClient_name()));
        cost_details.setText(String.valueOf(project.getCost()));
        project_title_details.setText(project.getProjectTitle());
        Project_describtion_details.setText(project.getProjectDescribtion());
        Managers_id_details.setText(String.valueOf(project.getManager()));
        LocalDate localDate = LocalDate.parse(project.getDateOfDelivery());
        DatePicker_Details.setValue(localDate);
        ProjectId=project.getProjectId();

    }

}
