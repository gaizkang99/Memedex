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

    public Usuario registro(String user, String pass, String mail, int coins, int lvl) throws SQLException{
        Usuario u = null;
        try (PreparedStatement ps = conexion.prepareStatement(Constantes.REGISTRAR)) {
            ps.setString(1,user);
            ps.setString(2, pass);
            ps.setString(3,mail);
            ps.setInt(4, coins);
            ps.setInt(5, lvl);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Usuario();
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));
                    u.setMail(rs.getString("mail"));
                    u.setCoins(rs.getInt("coins"));
                    u.setLvl(rs.getInt("lvl"));
                }
            }
        }
        return u;
    }

}
