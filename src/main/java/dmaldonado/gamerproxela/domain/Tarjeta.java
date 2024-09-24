/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dmaldonado.gamerproxela.domain;

/**
 *
 * @author DiegoMaldonado
 */
public class Tarjeta {

    private int id;
    private String tipo;
    private Double puntosPorCada200;

    public Tarjeta(int id, String tipo, Double puntosPorCada200) {
        this.id = id;
        this.tipo = tipo;
        this.puntosPorCada200 = puntosPorCada200;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPuntosPorCada200() {
        return puntosPorCada200;
    }

    public void setPuntosPorCada200(Double puntosPorCada200) {
        this.puntosPorCada200 = puntosPorCada200;
    }

}
