/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.eliaquimjolon.system.repository;

import org.eliaquimjolon.system.model.User;

/**
 *
 * @author jolon
 */
public interface AuthenticationInterface {
    User login(String email, String password);
}
