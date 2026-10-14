package logica;

import java.util.Date;

/**
 * @author Gabriel Correa
 */
public class GrandPrix {
    private int id;
    private String nombre;
    private Date fecha;
    private Circuito circuito;
    private Resultados resultados;

    public GrandPrix(int id, Date fecha, String nombre, Circuito circuito) {
        this.id = id;
        this.fecha = fecha;
        this.nombre = nombre;
        this.circuito = circuito;
        this.resultados = new Resultados();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Circuito getCircuito() {
        return circuito;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }

    public Resultados getResultados() {
        return resultados;
    }

    public void setResultados(Resultados resultados) {
        this.resultados = resultados;
    }

}
