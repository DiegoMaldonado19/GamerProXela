/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dmaldonado.gamerproxela.database;

/**
 *
 * @author Diego Maldonado
 */
public class DatabaseUser {
    private String user;
    private String password;
    private String db;
    private String ip;
    private String port;

    public DatabaseUser(String user, String password) {
        this.user = user;
        this.password = password;
        this.db = "gamerproxela";
        this.ip = "localhost";
        this.port = "5432";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
    
    
}
