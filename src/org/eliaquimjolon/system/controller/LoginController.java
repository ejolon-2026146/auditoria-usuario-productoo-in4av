/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.eliaquimjolon.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.eliaquimjolon.system.service.AuthenticationService;
import org.eliaquimjolon.system.service.AuthenticationStatus;
import org.eliaquimjolon.system.utils.AlertInformation;
import org.eliaquimjolon.system.utils.ViewFactory;

public class LoginController implements Initializable{

    @FXML private TextField txtUserOrEmail;
    @FXML private PasswordField pwdPassword;

    private AuthenticationService authService = new AuthenticationService();
    private AlertInformation alertInfo = new AlertInformation();

    @Override
    public void initialize(URL url, ResourceBundle rb){
    
    }
    
    @FXML
    public void onRegister(MouseEvent event){
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewRegister();
    }

    @FXML
    public void onLogin(MouseEvent event){
        String email = txtUserOrEmail.getText().trim();
        String password = pwdPassword.getText().trim();

        if(email.isEmpty() || password.isEmpty()){
            alertInfo.viewAlert("ERROR", "ERROR DE CAMPOS", "CAMPOS VACIOS",
                    "DEBES INGRESAR TU USUARIO/CORREO Y CONTRASENIA");
            return;
        }

        AuthenticationStatus status = authService.login(email, password);

        switch(status){
            case LOGIN_SUCCES -> {
                ViewFactory viewFacto = new ViewFactory();
                viewFacto.viewDashboard();
            }
            case NOT_EXIST_USER ->
                alertInfo.viewAlert("ERROR", "CUENTA NO EXISTE", "USUARIO NO ENCONTRADO",
                        "NO EXISTE UNA CUENTA CON ESE USUARIO O CORREO, DEBES REGISTRARTE");
            case WRONG_PASSWORD ->
                alertInfo.viewAlert("ERROR", "CONTRASENIA INCORRECTA", "ERROR AL INICIAR SESION",
                        "LA CONTRASENIA INGRESADA ES INCORRECTA");
            case ERROR_LOGIN ->
                alertInfo.viewAlert("ERROR", "ERROR", "ERROR AL INICIAR SESION",
                        "OCURRIO UN ERROR AL INTENTAR INICIAR SESION");
        }
    }
}
