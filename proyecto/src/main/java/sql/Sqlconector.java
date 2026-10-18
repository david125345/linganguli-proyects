package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author Gabriel Correa
 */

public class Sqlconector {
    private static String url;
    private static String usuario;
    private static String clave;
 public static boolean conectar() {
    try {
				Class.forName("com.mysql.jdbc.Driver");
				return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public static Connection realizarConexion(String url, String usuario, String clave) {
		Connection conexion=null;
        if ((url == null || usuario == null || clave == null) && (Sqlconector.url == null || Sqlconector.usuario == null || Sqlconector.clave == null)) {
            return null;
        } else if (url != null && usuario != null && clave != null) {
            Sqlconector.url = url;
            Sqlconector.usuario = usuario;
            Sqlconector.clave = clave;
        }
		try {
		conexion = DriverManager.getConnection(url, usuario, clave);
		} catch (SQLException e) {
		System.err.println("Error en conexion MYSQL: " + e.getMessage());
		}
		return conexion;
		
	}

    public static Connection realizarConexion() {
		Connection conexion=null;
        //si los parametros son null, se usan los anteriores, si no, se actualizan los anteriores con los nuevos.
        //si ambos son null, se retorna null.
		try {
            if (Sqlconector.url == null || Sqlconector.usuario == null || Sqlconector.clave == null) {
                return null;
            }
            String urllocal = Sqlconector.url;
            String usuariolocal = Sqlconector.usuario;
            String clavelocal = Sqlconector.clave;
            
            conexion = DriverManager.getConnection(urllocal, usuariolocal, clavelocal);
		} catch (SQLException e) {
		System.err.println("Error en conexion MYSQL: " + e.getMessage());
		}
		return conexion;
		
	}
	public static void EjecutarSentencia (String tabla, String datosainsertar, String valores) {
		try {
            String sql = "INSERT INTO ? (?) VALUES (?)";
            Connection sqlbomboclat = realizarConexion();
            PreparedStatement ps = sqlbomboclat.prepareStatement(sql);
            ps.setString(1, tabla);
            ps.setString(2, datosainsertar);
            ps.setString(3, valores);
            ps.executeUpdate();
		} catch (SQLException ex) {
        System.out.println("Error con la sentencia MYSQL: " + ex);
        }
	}
	
    // datosabuscar es separado con una coma, para simplificar el codigo y hacer posible buscar cualquier cosa.
    // Muy orgulloso de esta función a pesar de ser O(n²) y no O(log n), pero es lo que hay.
	public static String[] Consultar(String datosabuscar, String tabla) {
        String[] datos = new String[datosabuscar.split(",").length];
		try {
            String sql = "SELECT ? FROM ?";
		    Connection sqlbomboclat = realizarConexion();
		    PreparedStatement ps = sqlbomboclat.prepareStatement(sql);
		    ps.setString(1, datosabuscar);    
		    ps.setString(2, tabla);
		    ResultSet rs = ps.executeQuery();
            String[] valores = datosabuscar.split(",");
            String cosas = "";
            int iteration = 0;
		    while (rs.next()) {
                for (String valor : valores) {
                    cosas += rs.getString(valor) + ":";
                }
                datos[iteration] = cosas;
                iteration++;
            }
		} catch (SQLException ex) {
		System.out.println("Error con la sentencia MYSQL: " + ex);
		}
		return datos;
	}
	
}
