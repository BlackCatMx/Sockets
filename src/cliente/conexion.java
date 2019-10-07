/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import java.sql.Connection;

public class conexion {
 
    private static Connection Conexion= null;
    private Connection con = null;
    private final String base = "msc";
    private final String user = "reportes";
    private final String password = "reportes";
    private final String url = "jdbc:mysql://10.184.31.239:3306/" + base;
    
    
    static Frm_newEmpleado frm_nuevo;
    static Frm_empleados frm_empleados;
    static Frm_editEmpleado frm_editempleado; 
 
    public void MySQLConnection(String user, String pass, String db_name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_name, user, pass);
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public  Connection conectar() throws ClassNotFoundException  
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
             return con; 
        } catch(SQLException e)
        {
//            System.err.println(e);
            JOptionPane.showMessageDialog(null, "No hay conexión");
            return null; 
        }
                
    }
 
    public void closeConnection() {
        try {
            Conexion.close();
            System.out.println("Conexión Finalizada");
        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
 
    public static void insertData(String table_name, String CLAVE, String nombre, String sueldo, String dias_laborados, String total) {
        
        
        try {
             String Query = "INSERT INTO " + table_name + " VALUES("
                + "\"" + CLAVE + "\", "
                + "\"" + nombre + "\", "
                + "\"" + sueldo + "\", "
                + "\"" + dias_laborados + "\", "
                + "\"" + total + "\")";
             
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            
        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void updateEmpleado(String table_name, String clave, String nombre, String sueldo, String dias_laborados, String total) {
        
        try {
            String Query = "UPDATE "+table_name+" SET clave='"+clave+"', nombre='"+nombre+"', sueldo='"+sueldo+"', dias_laborados='"+dias_laborados+"', total='"+total+"' WHERE clave='"+clave+"'";
            Statement st = Conexion.createStatement();           
            st.executeUpdate(Query);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos");
        }
    }

    public static ResultSet getEmpleados(String table_name) {
        try {
            String Query = "SELECT * FROM " + table_name;
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            
            return resultSet;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en base de datos");
             return null;
        }
    }
    public static ResultSet getEmpleado(String clave) {
        try {
            String Query = "SELECT * FROM empleados WHERE clave='"+clave+"'";
            Statement st = Conexion.createStatement();
            ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            int rowcount = 0;
            
            if (resultSet.last()) {
              rowcount = resultSet.getRow();
              resultSet.beforeFirst();
            }
            
            if (rowcount>0){  
                return resultSet;
            }else{
                System.out.println("No se encuentra empleado");
                return null;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
 
    public static void deleteRecord(String table_name, String clave) {
        try {
            String Query = "DELETE FROM " + table_name + " WHERE clave = '" + clave + "'";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
   
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en la base de datos");
        }
    }
 
}
