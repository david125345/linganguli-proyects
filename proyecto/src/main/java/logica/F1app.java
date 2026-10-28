package logica;

import java.sql.Connection;
import java.util.Date;
import sql.Sqlconector;

/**
 * @author Gabriel Correa
 */
public class F1app {

    private static final F1app INSTANCE = new F1app();

    private final Autos autos;
    private final Circuitos circuitos;
    private final Escuderias escuderias;
    private final Pilotos pilotos;
    private final Proveedores proveedores;
    private final Temporadas temporadas;

    private F1app() {
        this.autos = new Autos();
        this.circuitos = new Circuitos();
        this.escuderias = new Escuderias();
        this.pilotos = new Pilotos();
        this.proveedores = new Proveedores();
        this.temporadas = new Temporadas();
    }

    public static F1app getInstance() {
        return INSTANCE;
    }

    public Auto getAuto(int id) {
        return autos.getAuto(id);
    }

    public Auto addAuto(int id, String modelo, Date anoFabricacion) {
        Auto auto = new Auto(id, modelo, anoFabricacion);
        autos.addAuto(auto);
        return auto;
    }

    public void removeAuto(int id) {
        autos.removeAuto(id);
    }

    public Circuito getCircuito(int id) {
        return circuitos.getCircuito(id);
    }

    public Circuito addCircuito(int id, String nombre, int longitud, String pais, int numeroVueltas) {
        Circuito circuito = new Circuito(id, nombre, longitud, pais, numeroVueltas);
        circuitos.addCircuito(circuito);
        return circuito;
    }

    public void removeCircuito(int id) {
        circuitos.removeCircuito(id);
    }

    public Escuderia getEscuderia(int id) {
        return escuderias.getEscuderia(id);
    }

    public Escuderia addEscuderia(int id, String nombre, Auto auto) {
        Escuderia escuderia = new Escuderia(id, nombre, auto);
        escuderias.addEscuderia(escuderia);
        return escuderia;
    }

    public void removeEscuderia(int id) {
        escuderias.removeEscuderia(id);
    }

    public Piloto getPiloto(int id) {
        return pilotos.getPiloto(id);
    }

    public Piloto addPiloto(int id, String nombre, String nacionalidad, Date fechaNacimiento,
            Proveedor proveedor, Auto auto) {
        Piloto piloto = new Piloto(id, nombre, nacionalidad);
        piloto.setFechaNacimiento(fechaNacimiento);
        piloto.setProveedor(proveedor);
        piloto.setAuto(auto);
        pilotos.addPiloto(piloto);
        return piloto;
    }

    public void removePiloto(int id) {
        pilotos.removePiloto(id);
    }

    public Proveedor getProveedor(int id) {
        return proveedores.getProveedor(id);
    }

    public Proveedor addProveedor(int id, String nombre) {
        Proveedor proveedor = new Proveedor(id, nombre);
        proveedores.addProveedor(proveedor);
        return proveedor;
    }

    public void removeProveedor(int id) {
        proveedores.removeProveedor(id);
    }

    public Temporada getTemporada(int anoTemporada) {
        return temporadas.getTemporada(anoTemporada);
    }

    public Temporada addTemporada(int anoTemporada, GrandPrix grandPrix) {
        Temporada temporada = new Temporada(anoTemporada, grandPrix);
        temporadas.addTemporada(temporada);
        return temporada;
    }

    public GrandPrix addGrandPrix(int id, Date fecha, String nombre, Circuito circuito, int anoTemporada) {
        GrandPrix grandPrix = new GrandPrix(id, fecha, nombre, circuito);
        addTemporada(anoTemporada, grandPrix);
        return grandPrix;
    }

    public void removeTemporada(int anoTemporada) {
        temporadas.removeTemporada(anoTemporada);
    }

    public Resultado getResultado(GrandPrix grandPrix, int idPiloto) {
        return grandPrix.getResultados().getResultado(idPiloto);
    }

    public Resultado addResultado(GrandPrix grandPrix, Piloto piloto, Auto auto, int posicion,
            int cantVueltas, String tiempoVueltas, int puntos) {
        Resultado resultado = new Resultado(piloto, auto);
        resultado.setPosicion(posicion);
        resultado.setCantVueltas(cantVueltas);
        resultado.setTiempoVueltas(tiempoVueltas);
        resultado.setPuntos(puntos);
        grandPrix.getResultados().addResultado(resultado);
        return resultado;
    }

    public void removeResultado(GrandPrix grandPrix, int idPiloto) {
        grandPrix.getResultados().removeResultado(idPiloto);
    }

    public void Insertintodatabase(String tabla, String datosainsertar, String valores) {
        Sqlconector.EjecutarSentencia(tabla, datosainsertar, valores);

    }

    public String[] Consultardatabase(String datosabuscar, String tabla) {
        return Sqlconector.Consultar(datosabuscar, tabla);
    }

    public boolean realizarprimeraconexiondatabase(String url, String usuario, String clave) {
        if (Sqlconector.conectar()) {
            Connection sql = Sqlconector.realizarConexion(url, usuario, clave);
            return sql != null;
        } else {
            return false;
        }
    }

    public int registeruserdatabase(String usuario, String clave, String confirmarClave) {
        return Sqlconector.registeruser(usuario, clave, confirmarClave);
    }

    public int loginuserdatabase(String usuario, String clave) {
        if (Sqlconector.login(usuario, clave)) {
            String bombo = Sqlconector.Consultar("isadmin", "usuario")[0];
            if (bombo != null && bombo.equals("1:")) {
                return 2;
            }
            return 1;
        } else {
            return 0;
        }
    }
}
