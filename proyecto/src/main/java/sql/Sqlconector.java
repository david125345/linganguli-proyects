package sql;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HexFormat;

import org.kocakosm.jblake2.Blake2b;
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
            String sql = "INSERT INTO " + tabla +  " (" + datosainsertar + ") VALUES ("+ valores +")";
            Connection sqlbomboclat = realizarConexion();
            PreparedStatement ps = sqlbomboclat.prepareStatement(sql);
            ps.executeUpdate();
		} catch (SQLException ex) {
        System.out.println("Error con la sentencia MYSQL: " + ex);
        }
	}
	
    // datosabuscar es separado con una coma, para simplificar el codigo y hacer posible buscar cualquier cosa.
    // Muy orgulloso de esta función a pesar de ser O(n²) y no O(log n), pero es lo que hay.
	public static String[] Consultar(String datosabuscar, String tabla) {
		String[] datos = null;
        try {
            String sql = "SELECT " + datosabuscar + " FROM " + tabla;
		    Connection sqlbomboclat = realizarConexion();
		    PreparedStatement ps = sqlbomboclat.prepareStatement(sql);  
		    ResultSet rs = ps.executeQuery();
            String[] valores = datosabuscar.split(",");
            String cosas = "";
            int iteration = 0;
            datos = new String[rs.getFetchSize() + 1];
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
        System.out.println(datos[0]);
		return datos;
	}
	

    public static boolean login(String usuario, String clave) {
        if (usuario == null || clave == null) {
            return false;
        }

        String sql = "SELECT nombre, clave FROM usuario WHERE nombre = ? AND clave = ?";
        try (Connection connection = realizarConexion()) {
            if (connection == null) {
                System.err.println("Error en login: no hay conexión con MYSQL.");
                return false;
            }

            byte[] hash = new Blake2b(64).digest(clave.getBytes(StandardCharsets.UTF_8));
            String hashHex = HexFormat.of().formatHex(hash);

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, usuario);
                statement.setString(2, hashHex);
                try (ResultSet result = statement.executeQuery()) {
                    return result.next();
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error con la sentencia MYSQL: " + ex);
            return false;
        }
    }

    public static int registeruser(String usuario, String clave, String confirmarClave) {
        if (clave == null || !clave.equals(confirmarClave)) {
            return 2; // error, las claves no coinciden
        }
        if (usuario == null) {
            return 0; // error, usuario no puede ser nulo
        }

        String checkSql = "SELECT 1 FROM usuario WHERE nombre = ? LIMIT 1";
        String insertSql = "INSERT INTO usuario (nombre, clave) VALUES (?, ?)";
        try (Connection connection = realizarConexion()) {
            if (connection == null) {
                System.err.println("Error en registro: no hay conexión con MYSQL.");
                return 0; //error, no hay conexion con MYSQL
            }

            try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
                checkStatement.setString(1, usuario);
                try (ResultSet result = checkStatement.executeQuery()) {
                    if (result.next()) {
                        return 3; // error, el usuario ya existe
                    }
                }
            }

            byte[] hash = new Blake2b(64).digest(clave.getBytes(StandardCharsets.UTF_8));
            String hashHex = HexFormat.of().formatHex(hash);
            try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                insertStatement.setString(1, usuario);
                insertStatement.setString(2, hashHex);
                return insertStatement.executeUpdate() == 1 ? 1 : 0; //muy rara notación (no pensé en otra cosa), basicamente devuelve 1 si se insertó correctamente, 0 si no se insertó correctamente, como funciona por adentro es irrelevante, solo agradezco que existe stack overflow.
            }
        } catch (SQLException ex) {
            System.out.println("Error con la sentencia MYSQL: " + ex);
            return 0; //error, algo sql idk
        }
    }
}
