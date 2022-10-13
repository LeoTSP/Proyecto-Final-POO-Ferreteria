/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.pkgfinal.ferreteria_poo;

import proyecto.pkgfinal.ferreteria_poo.Conexion;
import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author rober
 */
public class InstruccionesEmpleados {

    Conexion objConexion = new Conexion();

    /**
     *
     * @param nombre a insertar en tabla empleados
     * @param edad a insertar en tabla empleados
     * @param sexo a insertar en tabla empleados
     * @param telefono a insertar en tabla empleados
     * @param direccion a insertar en tabla empleados
     * @param clave a insertar en tabla empleados
     */
    public void InsertarEmpleados(String nombre, String edad, String sexo, String telefono, String direccion, String clave) {
        int filasAfectadas = 0;
        try {
            Connection conex = objConexion.conectar();

            PreparedStatement psInsertar = null;

            psInsertar = conex.prepareStatement("INSERT INTO Empleados values (?,?,?,?,?,?,?)");
            psInsertar.setString(1, null);
            psInsertar.setString(2, nombre);
            psInsertar.setString(3, edad);
            psInsertar.setString(4, sexo);
            psInsertar.setString(5, telefono);
            psInsertar.setString(6, direccion);
            psInsertar.setString(7, clave);
            filasAfectadas = psInsertar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se insertó " + filasAfectadas + " empleado", "Resultados", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL" + err.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     *
     * @param Buscar empleado en la base de datos
     * @return información del empleado
     */
    public ResultSet BuscarEmpleados(String Buscar) {
        ResultSet registros = null;
        try {
            Connection conex = objConexion.conectar();
            Statement stmt = conex.createStatement();
            registros = stmt.executeQuery("select * from empleados where (idEmpleado = '" + Buscar + "') OR (Nombre = '" + Buscar + "')");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL " + err.toString());
        }
        return registros;
    }

    /**
     *
     * @param valor id del empleado a actualizar
     * @param nombre
     * @param edad
     * @param sexo
     * @param telefono
     * @param direccion
     * @param clave
     */
    public void ActualizarEmpleados(String valor, String nombre, String edad, String sexo, String telefono, String direccion, String clave) {
        int filasAfectadas = 0;
        try {
            Connection conex = objConexion.conectar();

            PreparedStatement psActualizar = null;

            psActualizar = conex.prepareStatement("update empleados set Nombre=?, Edad=?, Sexo=?, Telefono=?, Direccion=?, Clave=?  where idEmpleados=?");

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
     * @param idEmpleados del empleado que se desea eliminar
     */
    public void EliminarEmpleado(String idEmpleados) {
        int filasAfectadas = 0;
        try {
            Connection conex = objConexion.conectar();
            PreparedStatement psEliminar = null;

            psEliminar = conex.prepareStatement("delete from empleados  where idEmpleados=?");

            psEliminar.setString(1, idEmpleados);
            filasAfectadas = psEliminar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se eliminó " + filasAfectadas + " registro", "Resultados", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL" + err.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
