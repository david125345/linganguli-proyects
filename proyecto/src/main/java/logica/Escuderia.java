package logica;

public class Escuderia {
    private int id;
    private String nombre;
    private Auto auto;

    public Escuderia(int id, String nombre, Auto auto) {
        this.id = id;
        this.nombre = nombre;
        this.auto = auto;
    }

    public int getId() {
         return id; 
    }

    public String getNombre() { 
        return nombre;
    }
    
    public Auto getAuto() { 
        return auto; 
    }

    public void setId(int id) { 
        this.id = id; 
    }

    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public void setAuto(Auto auto) { 
        this.auto = auto; 
    }
}
