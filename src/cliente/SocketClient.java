/**
 *
 * @author Hector Adan Morales Lugo
 */
package cliente;

import java.util.List;
import java.util.Vector;

import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class SocketClient {
    // Create socket connection and pass data to server
    public static List<empleados> SendDataToServer(Vector datatosend)
    {
        List<empleados> data = null;
        Socket clientSocket; //create socket
        try {
            clientSocket = new Socket("10.184.31.239", 1234); //initialize socket
            System.out.println("Conexion establecida");
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            oos.writeObject(datatosend);//write data
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            data = (List<empleados>)ois.readObject(); //read data
            //Close connections
            ois.close(); 
            oos.close();
            clientSocket.close();
            
            //Return data
            return data;
            
        } catch (IOException ex) {
//            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, "No hay conexión");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    } 
    
    public static Connection getReport(Vector datatosend)
    {
        List<empleados> data = null;
        Socket clientSocket; //create socket
        Connection conn;
        try {
            clientSocket = new Socket("10.184.31.239", 1234); //initialize socket
            System.out.println("Conexion establecida");
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            oos.writeObject(datatosend);//write data
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            conn = (Connection)ois.readObject(); //read data
            
            //Close connections
            ois.close(); 
            oos.close();
            clientSocket.close();
            
            //Return data
            return conn;
            
        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    } 
}
