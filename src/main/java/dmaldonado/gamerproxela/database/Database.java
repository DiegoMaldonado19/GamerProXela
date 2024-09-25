/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dmaldonado.gamerproxela.database;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import dmaldonado.gamerproxela.domain.Rol;

/**
 *
 * @author Diego Maldonado
 */
public class Database {

    Connection conectar = null;

    DatabaseUser usuarioAdministrador = new DatabaseUser("administrador1", "admin123");
    DatabaseUser usuarioCajero = new DatabaseUser("cajero1", "cajero123");
    DatabaseUser usuarioBodega = new DatabaseUser("bodega1", "bodega123");
    DatabaseUser usuarioInventario = new DatabaseUser("inventario1", "inventario123");

    String connect = "jdbc:postgresql://" + usuarioAdministrador.getIp() + ":" + usuarioAdministrador.getPort() + "/" + usuarioAdministrador.getDb();

    public Connection establecerConexion(String role) {

        if (role.equals(String.valueOf(Rol.Administrador))) {
            try {
                Class.forName("org.postgresql.Driver");
                conectar = DriverManager.getConnection(connect, usuarioAdministrador.getUser(), usuarioAdministrador.getPassword());
                JOptionPane.showMessageDialog(null, "SE CONECTO CORRECTAMENTE A LA BASE DE DATOS");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, " ERROR AL CONECTAR A LA BASE DE DATOS, ERRO:" + e.toString());
            }
            return conectar;
        } else if (role.equals(String.valueOf(Rol.Bodega))) {
            try {
                Class.forName("org.postgresql.Driver");
                conectar = DriverManager.getConnection(connect, usuarioBodega.getUser(), usuarioBodega.getPassword());
                JOptionPane.showMessageDialog(null, "SE CONECTO CORRECTAMENTE A LA BASE DE DATOS");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, " ERROR AL CONECTAR A LA BASE DE DATOS, ERRO:" + e.toString());
            }
            return conectar;
        } else if (role.equals(String.valueOf(Rol.Cajero))) {
            try {
                Class.forName("org.postgresql.Driver");
                conectar = DriverManager.getConnection(connect, usuarioCajero.getUser(), usuarioCajero.getPassword());
                JOptionPane.showMessageDialog(null, "SE CONECTO CORRECTAMENTE A LA BASE DE DATOS");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, " ERROR AL CONECTAR A LA BASE DE DATOS, ERRO:" + e.toString());
            }
            return conectar;
        } else if (role.equals(String.valueOf(Rol.Inventario))) {
            try {
                Class.forName("org.postgresql.Driver");
                conectar = DriverManager.getConnection(connect, usuarioInventario.getUser(), usuarioInventario.getPassword());
                JOptionPane.showMessageDialog(null, "SE CONECTO CORRECTAMENTE A LA BASE DE DATOS");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, " ERROR AL CONECTAR A LA BASE DE DATOS, ERRO:" + e.toString());
            }
            return conectar;
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no pertenece al sistema");
            return null;
        }

    }
}
