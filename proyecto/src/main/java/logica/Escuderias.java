package logica;

import java.util.ArrayList;

public class Escuderias {
    private ArrayList<Escuderia> escuderias;
    private static Escuderias instancia;

    private Escuderias() {
        escuderias = new ArrayList<>();
    }

    public static Escuderias getInstance() {
        if (instancia == null) {
            instancia = new Escuderias();
        }
        return instancia;
    }

    public Escuderia getEscuderia(int id) {
        for (Escuderia escuderia : escuderias) {
            if (escuderia.getId() == id) {
                return escuderia;
            }
        }
        return null;
    }

    public void addEscuderia(Escuderia escuderia) {
        if (escuderia != null) {
            escuderias.add(escuderia);
        }
    }

    public void removeEscuderia(Escuderia escuderia) {
        escuderias.remove(escuderia);
    }
}