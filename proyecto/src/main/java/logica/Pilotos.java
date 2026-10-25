package logica;

import java.util.ArrayList;

/**
 * @author Gabriel Correa
 */
public class Pilotos {

    private final ArrayList<Piloto> pilotos;

    public Pilotos() {
        pilotos = new ArrayList<>();
    }

    public Piloto getPiloto(int id) {
        for (Piloto piloto : pilotos) {
            if (piloto.getId() == id) {
                return piloto;
            }
        }
        return null;
    }

    public void addPiloto(Piloto piloto) {
        if (piloto != null) {
            pilotos.add(piloto);
        }
    }

    public void removePiloto(int id) {
        Piloto pilotoToRemove = getPiloto(id);
        if (pilotoToRemove != null) {
            pilotos.remove(pilotoToRemove);
        }
    }
}
