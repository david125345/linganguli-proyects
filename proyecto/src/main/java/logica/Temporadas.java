package logica;

import java.util.ArrayList;

/**
 * @author Gabriel Correa
 */
public class Temporadas {
    private final ArrayList<Temporada> temporadas;

    public Temporadas() {
        temporadas = new ArrayList<>();
    }

    public Temporada getTemporada(int anoTemporada) {
        for (Temporada temporada : temporadas) {
            if (temporada.getAnoTemporada() == anoTemporada) {
                return temporada;
            }
        }
        return null;
    }

    public void addTemporada(Temporada temporada) {
        if (temporada != null) {
            temporadas.add(temporada);
        }
    }

    public void removeTemporada(int id) {
        Temporada temporadaToRemove = getTemporada(id);
        if (temporadaToRemove != null) {
            temporadas.remove(temporadaToRemove);
        }
    }
}