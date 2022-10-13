package proyecto.pkgfinal.ferreteria_poo;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author rober
 */
public class InstruccionesInventario {

    Conexion objConexion = new Conexion();

    /**
     *
     * @param nombre a insertar en tabla inventario
     * @param costo a insertar en tabla inventario
     * @param existencia a insertar en tabla inventario
     * @param descripcion a insertar en tabla inventario
     */
    public void InsertarProducto(String nombre, String costo, String existencia, String descripcion) {
        int filasAfectadas = 0;
        try {
            Connection conex = objConexion.conectar();

            PreparedStatement psInsertar = null;

            psInsertar = conex.prepareStatement("INSERT INTO inventario values (?,?,?,?,?)");
            psInsertar.setString(1, null);
            psInsertar.setString(2, nombre);
            psInsertar.setString(3, costo);
            psInsertar.setString(4, existencia);
            psInsertar.setString(5, descripcion);
            filasAfectadas = psInsertar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se insertó " + filasAfectadas + " producto", "Resultados", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL" + err.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     *
     * @param Buscar producto en la base de datos.
     * @return información del inventario.
     */
    public ResultSet BuscarProducto(String Buscar) {
        ResultSet registros = null;
        try {
            Connection conex = objConexion.conectar();
            Statement stmt = conex.createStatement();
            registros = stmt.executeQuery("select * from inventario where (idProducto = '" + Buscar + "')");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL " + err.toString());
        }
        return registros;
    }

    /**
     *
     * @param Buscar nombre de producto en la base de datos.
     * @return información del inventario.
     */
    public ResultSet BuscarNombreProducto(String Buscar) {
        ResultSet registros = null;
        try {
            Connection conex = objConexion.conectar();
            Statement stmt = conex.createStatement();
            registros = stmt.executeQuery("select Costo, Existencia from inventario where (Nombre = '" + Buscar + "')");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL " + err.toString());
        }
        return registros;
    }

    /**
     *
     * @param valor id del producto a actualizar
     * @param nombre
     * @param costo
     * @param existencia
     * @param descripcion
     */
    public void ActualizarInventario(String valor, String nombre, String costo, String existencia, String descripcion) {
        int filasAfectadas = 0;
        try {
            Connection conex = objConexion.conectar();

            PreparedStatement psActualizar = null;

            psActualizar = conex.prepareStatement("update inventario set Nombre=?, Costo=?, Existencia=?, Descripcion=?  where idProducto=?");

            psActualizar.setString(1, nombre);
            psActualizar.setString(2, costo);
            psActualizar.setString(3, existencia);
            psActualizar.setString(4, descripcion);
            psActualizar.setString(5, valor);
            filasAfectadas = psActualizar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se actualizó " + filasAfectadas + " registro", "Resultados", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL" + err.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     *
     * @param idProducto elimina producto de la base de datos
     */
    public void EliminarProducto(String idProducto) {
        int filasAfectadas = 0;
        try {
            Connection conex = objConexion.conectar();
            PreparedStatement psEliminar = null;

            psEliminar = conex.prepareStatement("delete from inventario  where idProducto=?");

            psEliminar.setString(1, idProducto);
            filasAfectadas = psEliminar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se eliminó " + filasAfectadas + " registro", "Resultados", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL" + err.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

}
