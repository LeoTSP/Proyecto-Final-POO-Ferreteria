/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.pkgfinal.ferreteria_poo;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author raul_
 */
public class InstruccionesVentas {

    Conexion objConexion = new Conexion();

    /**
     *
     * @param Clave la busca en la tabla Empleados de la base de datos
     * @return información del empleado
     */
    public ResultSet BuscarEmpleado(String Clave) {

        ResultSet user = null;

        try {
            Connection conex = objConexion.conectar();
            Statement stmt = conex.createStatement();
            user = stmt.executeQuery("select Nombre from Empleados where (Clave = '" + Clave + "')");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL " + err.toString());
        }
        return user;
    }

    /**
     *
     * @param Clave la busca en la tabla Clientes de la base de datos.
     * @return información del empleado.
     */
    public ResultSet BuscarCliente(String Clave) {

        ResultSet user = null;

        try {
            Connection conex = objConexion.conectar();
            Statement stmt = conex.createStatement();
            user = stmt.executeQuery("select Nombre, Direccion from Clientes where (Clave = '" + Clave + "')");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL " + err.toString());
        }
        return user;
    }

}
