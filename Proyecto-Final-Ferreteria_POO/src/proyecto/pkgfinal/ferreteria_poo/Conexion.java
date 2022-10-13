package proyecto.pkgfinal.ferreteria_poo;

import java.sql.*;

/**
 *
 * @author rober
 */
public class Conexion {

    /**
     *
     * @return coneccion con la base de datos
     */
    public Connection conectar() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost/ferreteria"
                + "?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        String pass = "12345";

        Connection conex = null;

        try {
            System.out.println("Conectando a la base de datos");
            Class.forName(driver);

            conex = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión exitosa!!");

        } catch (SQLException ex) {
            System.out.println("Error de mysql " + ex);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Se ha encontrado el siguiente error " + ex);
        }
        return conex;
    }

}
