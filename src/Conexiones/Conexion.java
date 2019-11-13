package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;

public class Conexion {

    static Connection contacto = null; //primera libreria 
    public static String usuario;
    public static String password;
    public static boolean status=false;
    

    public static Connection GetConnection() {
        status=false;
        String url = "jdbc:sqlserver://LAPTOP-KFRJB1C7:1433;databaseName=Java_DB";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No es posible establecer la coneccion." + e.getMessage(), "Error de coneccion", JOptionPane.ERROR_MESSAGE);
        }
        try {
            contacto = DriverManager.getConnection(url, Conexion.usuario, Conexion.password);//esto va a comparar el paswor y usuario ingresado con el password y usuario utilizados en el gestor 
            status=true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No es posible establecer la coneccion." + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
        return contacto;
    }
    public static void setcuenta(String usuario,String password){
        Conexion.usuario=usuario;
        Conexion.password=password;
        
    }
    public static boolean getstatus(){
        return status;
    }
    public static ResultSet Consulta(String consulta) {
        Connection con = GetConnection();
        Statement declara;
        try {
            declara = con.createStatement();
            ResultSet respuesta = declara.executeQuery(consulta);
            return respuesta;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No es posible establecer la coneccion." + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
        return null;
    }
}
