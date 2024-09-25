/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dmaldonado.gamerproxela.domain;

/**
 *
 * @author DiegoMaldonado
 */
public class Cliente {

    private String nit;
    private String nombre;
    private String apellido;
    private String Direccion;
    private Tarjeta tarjeta;

    public Cliente(String nit, String nombre, String apellido, String Direccion, Tarjeta tarjeta) {
        this.nit = nit;
        this.nombre = nombre;
        this.apellido = apellido;
        this.Direccion = Direccion;
        this.tarjeta = tarjeta;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setIdTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

}
