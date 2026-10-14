package logica;

import java.util.ArrayList;

/**
 * @author Gabriel Correa
 */
public class Autos {
    private final ArrayList<Auto> autos;

    public Autos() {
        autos = new ArrayList<>();
    }

    public Auto getAuto(int id) {
        for (Auto auto : autos) {
            if (auto.getId() == id) {
                return auto;
            }
        }
        return null;
    }

    public void addAuto(Auto auto) {
        if (auto != null) {
            autos.add(auto);
        }
    }

    public void removeAuto(int id) {
        Auto autoToRemove = getAuto(id);
        if (autoToRemove != null) {
            autos.remove(autoToRemove);
        }
    }
}
