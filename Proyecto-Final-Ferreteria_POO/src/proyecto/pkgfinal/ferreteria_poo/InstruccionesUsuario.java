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
public class InstruccionesUsuario {
    Conexion objConexion = new Conexion();

    /**
     *
     * @param Usuario a registrar
     * @param textoEncriptadoConMD5 encargado de encriptar contraseña
     */
    public void RegistrarUsuario(String Usuario, String textoEncriptadoConMD5) {
        int filasAfectadas = 0;
        try {
            Connection conex = objConexion.conectar();
            PreparedStatement psInsertar = null;

            psInsertar = conex.prepareStatement("INSERT INTO usuarios values (?,?,?)");
            psInsertar.setString(1, null);
            psInsertar.setString(2, Usuario);
            psInsertar.setString(3, textoEncriptadoConMD5);
            filasAfectadas = psInsertar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se insertó " + filasAfectadas + " usuario", "Resultados", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | SQLException err) {
            JOptionPane.showMessageDialog(null, "Error SQL" + err.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     *
     * @param Usuario a buscar
     * @return datos del usuario
     */
    public ResultSet BuscarUsuario(String Usuario) {
       
        ResultSet user = null;

        try{
            Connection conex = objConexion.conectar();
            Statement stmt = conex.createStatement();
            user = stmt.executeQuery("select Usuario from Usuarios where (Usuario = '"+Usuario+"')");
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null, "Error SQL " + err.toString());
        }
        return user;
    }
    
    /**
     *
     * @param Contraseña que se desea buscar
     * @return la contraseña encriptada
     */
    public ResultSet BuscarContraseña(String Contraseña) {

        ResultSet contra = null;
        
        try{
            Connection conex = objConexion.conectar();
            Statement stmt = conex.createStatement();
            contra = stmt.executeQuery("select Contraseña from usuarios where (Contraseña = '"+Contraseña+"')");
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null, "Error SQL " + err.toString());
        }
        return contra;
    }
}
