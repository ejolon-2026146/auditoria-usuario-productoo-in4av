/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.eliaquimjolon.system.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.eliaquimjolon.system.config.ConexionDB;
import org.eliaquimjolon.system.model.User;

public class AuthenticationRepository implements AuthenticationInterface {

    private ConexionDB conexionDB = ConexionDB.getInstanciaConexionDB();

    @Override
    public User login(String email, String password){
        User user = null;
        String sql = "SELECT * FROM users WHERE (email = ? OR user = ?) AND password = ?";

        try {
            PreparedStatement statement = conexionDB.getConnection().prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, email);
            statement.setString(3, password);
            ResultSet result = statement.executeQuery();

            if(result.next()){
                user = new User(
                        result.getString("name"),
                        result.getString("lastname"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("user"),
                        result.getString("id_user")
                );
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error al iniciar sesion repository");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return user;
    }

}
