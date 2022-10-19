/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Koneksi;

import java.sql.*;
import java.sql.Connection;
import javax.swing.JOptionPane;
/**
 *
 * @author ACER
 */
public class DB_Koneksi {
    private static Connection conn;
    public static Connection getKoneksi(){
         String host = "jdbc:mysql://localhost:3306/db_karyawan",
                user = "root",
                pass = "";
         try {
             conn = (Connection) DriverManager.getConnection(host, user, pass);
         } catch (SQLException err){
             JOptionPane.showMessageDialog(null, err.getMessage());
         }
         return conn;
    }
    public static void main(String[] args){
        
    }
}
