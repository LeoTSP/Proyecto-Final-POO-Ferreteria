package proyecto.pkgfinal.ferreteria_poo;

import proyecto.pkgfinal.ferreteria_poo.Conexion;
import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author rober
 */
public class InstruccionesClientes {

    Conexion objConexion = new Conexion();

    /**
     *
     * @param nombre a insertar en tabla clientes
     * @param edad a insertar en tabla clientes
     * @param sexo a insertar en tabla clientes
     * @param telefono a insertar en tabla clientes
     * @param direccion a insertar en tabla clientes
     * @param clave a insertar en tabla clientes
     */
    public void InsertarClientes(String nombre, String edad, String sexo, String telefono, String direccion, String clave) {
        int filasAfectadas = 0;
        try {
            Connection conex = objConexion.conectar();

            PreparedStatement psInsertar = null;

            psInsertar = conex.prepareStatement("INSERT INTO Clientes values (?,?,?,?,?,?,?)");
            psInsertar.setString(1, null);
            psInsertar.setString(2, nombre);
            psInsertar.setString(3, edad);
            psInsertar.setString(4, sexo);
            psInsertar.setString(5, telefono);
            psInsertar.setString(6, direccion);
            psInsertar.setString(7, clave);
            filasAfectadas = psInsertar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se insertó " + filasAfectadas + " cliente", "Resultados", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL" + err.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     *
     * @param Buscar cliente en base de datos
     * @return los datos del cliente
     */
    public ResultSet BuscarClientes(String Buscar) {
        ResultSet registros = null;
        try {
            Connection conex = objConexion.conectar();
            Statement stmt = conex.createStatement();
            registros = stmt.executeQuery("select Nombre, Edad, Sexo, Telefono, Direccion, Clave from clientes where (idClientes = '" + Buscar + "')");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL " + err.toString());
        }
        return registros;
    }

    /**
     *
     * @param valor id del cliente a actualizar
     * @param nombre
     * @param edad
     * @param sexo
     * @param telefono
     * @param direccion
     * @param clave
     */
    public void ActualizarCliente(String valor, String nombre, String edad, String sexo, String telefono, String direccion, String clave) {
        int filasAfectadas = 0;
        try {
            Connection conex = objConexion.conectar();

            PreparedStatement psActualizar = null;

            psActualizar = conex.prepareStatement("update clientes set Nombre=?, Edad=?, Sexo=?, Telefono=?, Direccion=?, Clave=?  where idClientes=?");

            psActualizar.setString(1, nombre);
            psActualizar.setString(2, edad);
            psActualizar.setString(3, sexo);
            psActualizar.setString(4, telefono);
            psActualizar.setString(5, direccion);
            psActualizar.setString(6, clave);
            psActualizar.setString(7, valor);
            filasAfectadas = psActualizar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se actualizó " + filasAfectadas + " registro", "Resultados", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL" + err.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     *
     * @param idClientes elimina al cliente de la base de datos.
     */
    public void EliminarCliente(String idClientes) {
        int filasAfectadas = 0;
        try {
            Connection conex = objConexion.conectar();
            PreparedStatement psEliminar = null;

            psEliminar = conex.prepareStatement("delete from clientes  where idClientes=?");

            psEliminar.setString(1, idClientes);
            filasAfectadas = psEliminar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se eliminó " + filasAfectadas + " registro", "Resultados", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL" + err.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
