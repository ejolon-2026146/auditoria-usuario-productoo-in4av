package org.eliaquimjolon.system.controller;

import java.net.URL;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.eliaquimjolon.system.service.UserService;
import org.eliaquimjolon.system.service.UserStatus;
import org.eliaquimjolon.system.utils.AlertInformation;
import org.eliaquimjolon.system.utils.Validations;
import org.eliaquimjolon.system.utils.ViewFactory;

public class RegisterController implements Initializable {
     @FXML private TextField txtUser;
     @FXML private TextField txtName;
     @FXML private TextField txtLastName;
     @FXML private TextField txtEmail;
     @FXML private PasswordField pwdPassword;
     @FXML private PasswordField pwdConfirmPassword;
     @FXML private Button btnCancel;
     @FXML private Button btnCreateUser;
     private Validations validate = new Validations();
     private AlertInformation alertInfo = new AlertInformation();
     private UserService userService = new UserService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
    
    }
    
    @FXML
    public void onCancelRegister(MouseEvent event){
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewLogin();
    }
    
   @FXML
    public void onRegisterUser(MouseEvent event){
        String email = txtEmail.getText().trim();
        boolean validEmail = validate.validateEmail(email);
        if(validEmail == false){
            alertInfo.viewAlert("ERROR","ERROR DE EMAIL", "ERROR CAMPO EMAIL",
                        "INGRESASTE UN EMAIL INCORRECTO");
            return;
        }

        String user, name, lastName, password, confirmPassword;
        user = txtUser.getText().trim();
        name = txtName.getText().trim();
        lastName = txtLastName.getText();
        password = pwdPassword.getText().trim();
        
        confirmPassword = pwdConfirmPassword.getText().trim();
        if(validate.validateTextEmpty(user) == true ||
           validate.validateTextEmpty(name) == true ||
           validate.validateTextEmpty(email) == true ||
           validate.validateTextEmpty(lastName) == true ||
           validate.validateTextEmpty(password) == true ||
           validate.validateTextEmpty(confirmPassword) == true){
            
          alertInfo.viewAlert("ERROR",
                  "ERROR DE EMAIL", 
                  "ERROR CAMPOS VACIOS",
                  "NO LLENASTE TODOS LOS CAMPOS");
          return;
        }
        
        String msgField= "";
        if(validate.validateTextLength(user, 50) == false)
            msgField = "El campo USUARIO es mayor a 50 letras";
        if(validate.validateTextLength(name, 50) == false)
            msgField = "El campo NOMBRES es mayor a 50 letras";
        if(validate.validateTextLength(lastName, 50) == false)
            msgField = "El campo APELLIDOS es mayor a 50 letras";
        if(validate.validateTextLength(email, 50) == false)
            msgField = "El campo CORREO es mayor a 50 letras";
        if(validate.validateTextLength(password, 35) == false)
            msgField = "El campo CONTRASEÑA es mayor a 50 letras";
        if(validate.validateTextLength(confirmPassword, 35) == false)
            msgField = "El campo CONFRIMAR CONTRASEÑA es mayor a 50 letras";
        if(msgField.isEmpty() == false){
               alertInfo.viewAlert("ERROR",
                  "ERROR DE CAMPO", 
                  "ERROR LONGITUD DE CAMPO",
                  msgField);
               return;
        }
        
        if(validate.equalsText(password, confirmPassword) == false){
            
          alertInfo.viewAlert("ERROR",
                  "ERROR DE CONTRESEÑA", 
                  "ERROR AL CONFIRMAR CONTRASEÑA",
                  "NO CONTRASEÑAS NO COINCIDEN");
          return;
        }
        
        // Todo esto ahora está correctamente dentro del método
        UserStatus status = userService.createUser(user, name, lastName, email, password);
        
        switch(status){
            case USER_CREATED -> {
                alertInfo.viewAlert("INFO", "USUARIO CREADO", "REGISTRO EXITOSO",
                        "TU CUENTA SE CREO CORRECTAMENTE, INICIA SESION");
                ViewFactory viewFacto = new ViewFactory();
                viewFacto.viewLogin();
            }
            case ERROR_USER_CREATE ->
                System.out.println("No se pudo crear el usuario");
            default ->
                System.out.println("Error desconocido");
        }
    }
}