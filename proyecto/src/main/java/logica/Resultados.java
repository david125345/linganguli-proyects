package logica;

import java.util.ArrayList;

/**
 * @author Gabriel Correa
 */
public class Resultados {

    private final ArrayList<Resultado> resultados;

    public Resultados() {
        resultados = new ArrayList<>();
    }

    public Resultado getResultado(int idpiloto) {
        for (Resultado resultado : resultados) {
            if (resultado.getPiloto().getId() == idpiloto) {
                return resultado;
            }
        }
        return null;
    }

    public void addResultado(Resultado resultado) {
        if (resultado != null) {
            resultados.add(resultado);
        }
    }

    public void removeResultado(int id) {
        Resultado resultadoToRemove = getResultado(id);
        if (resultadoToRemove != null) {
            resultados.remove(resultadoToRemove);
        }
    }
}
