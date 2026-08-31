/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.eliaquimjolon.system.service;

import org.eliaquimjolon.system.model.User;
import org.eliaquimjolon.system.repository.AuthenticationRepository;

/**
 *
 * @author jolon
 */
public class AuthenticationService {
    private AuthenticationRepository authRepo = new AuthenticationRepository();
    private UserService userService = new UserService();

    public AuthenticationStatus login(String email, String password){
        try {
            // 1. Antes de loguear, se busca el usuario con el UserService para
            //    validar si existe.
            User existingUser = userService.findUserByUserOrEmail(email);

            if(existingUser == null){
                return AuthenticationStatus.NOT_EXIST_USER;
            }

            // 2. Si existe, se valida el email y password contra la db.
            User user = authRepo.login(email, password);

            if(user == null){
                return AuthenticationStatus.WRONG_PASSWORD;
            }

            return AuthenticationStatus.LOGIN_SUCCES;
        } catch (Exception e) {
            return AuthenticationStatus.ERROR_LOGIN;
        }
    }
}
