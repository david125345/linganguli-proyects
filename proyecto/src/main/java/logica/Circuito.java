package logica;

public class Circuito {
    private int id;
    private String nombre;
    private String pais;
    private int longitud;
    private int numeroVueltas;
    public Circuito(int id, String nombre, int longitud, String pais, int numeroVueltas) {
        this.id = id;
        this.nombre = nombre;
        this.longitud = longitud;
        this.pais = pais;
        this.numeroVueltas = numeroVueltas;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getNumeroVueltas() {
        return numeroVueltas;
    }

    public void setNumeroVueltas(int numeroVueltas) {
        this.numeroVueltas = numeroVueltas;
    }

}
