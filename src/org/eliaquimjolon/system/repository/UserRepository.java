/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.eliaquimjolon.system.repository;

import org.eliaquimjolon.system.model.User;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.eliaquimjolon.system.config.ConexionDB;
/**
 *
 * @author jolon
 */
public class UserRepository implements UserInterface{
    private CallableStatement callSP;
    private PreparedStatement statement;
    private ConexionDB conexionDB = ConexionDB.getInstanciaConexionDB();
    
    
    @Override
    public void create(User user){
        try {
            callSP = conexionDB.getConnection().prepareCall("{call sp_create_users(?,?,?,?,?)}");
            callSP.setString(1, user.getName());
            callSP.setString(2, user.getLastname());
            callSP.setString(3, user.getEmail());
            callSP.setString(4, user.getUser());
            callSP.setString(5, user.getPassword());
            callSP.execute();
            callSP.close(); //libera el almacenamiento
        } catch (SQLException e) {
            System.out.println("Error al crear usuario repository");
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }

    @Override
    public User findByUserOrEmail(String userOrEmail){
        User user = null;
        String sql = "SELECT * FROM users WHERE email = ? OR user = ?";
        try {
            statement = conexionDB.getConnection().prepareStatement(sql);
            statement.setString(1, userOrEmail);
            statement.setString(2, userOrEmail);
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
            System.out.println("Error al buscar usuario por usuario/email");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

}