package sample;
import java.sql.*;
import javafx.collections.*;
import javax.swing.JOptionPane;


public class MYSQLconnect {


    Connection con = null;

    public static Connection ConnectDb() {
        try {
            System.out.println("connetDb accessed successfully");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com:3306", "sql4409579", "zhc6fDgqd6");
            JOptionPane.showMessageDialog(null, "ConnectionEstablished");
            return con;

        } catch (Exception e) {
            System.out.println("connetDb failure");
            JOptionPane.showMessageDialog(null, e);

            return null;

        }

    }

    public static ObservableList<Projects> getDataProjects(){
        Connection con = ConnectDb();
        ObservableList<Projects> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM sql4409579.Projects");
            ResultSet rs = ps.executeQuery();
            System.out.println("getDataProjects success");

            while(rs.next()){
                list.add(new Projects(Integer.parseInt(

                        rs.getString("id"))
                        ,rs.getString("title")
                        ,rs.getString("projectDescription")
                        ,rs.getString("date")
                        ,rs.getString("type")
                        ,rs.getString("client_name")
                        ,rs.getString("Manager_name")
                        )

                );
            }
        }catch(Exception e){System.out.println("getDataProjects failure");}


        return list;
    }
}