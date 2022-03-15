package com.example.memedex.dao;

import com.example.memedex.model.Usuario;
import com.example.memedex.utils.Constantes;

import java.sql.*;

public class JdbcConection {
    private Connection conexion;

    //Conexión y desconexión con la base de datos
    public void conectar() throws SQLException {
        String url = Constantes.Connection;
        String usuario = Constantes.Name;
        String contrasena = Constantes.Password;
        //Conexion
        conexion = DriverManager.getConnection(url, usuario, contrasena);
    }

    public void desconectar() throws SQLException {
        if(conexion != null) {
            conexion.close();
        }
    }

    public Usuario inicioSesion(String user, String pass) throws SQLException{
        Usuario u = null;
        try (PreparedStatement ps = conexion.prepareStatement(Constantes.Inicio_Sesion)) {
            ps.setString(1,user);
            ps.setString(2, pass);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));
                }
            }
        }
        return u;
    }

}
