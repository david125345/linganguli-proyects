package logica;

import java.util.ArrayList;

/**
 * @author Gabriel Correa
 */
public class Escuderias {

    private final ArrayList<Escuderia> escuderias;

    public Escuderias() {
        escuderias = new ArrayList<>();
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

    public void removeEscuderia(int id) {
        Escuderia escuderiaToRemove = getEscuderia(id);
        if (escuderiaToRemove != null) {
            escuderias.remove(escuderiaToRemove);
        }
    }
}
