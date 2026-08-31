/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.eliaquimjolon.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import org.eliaquimjolon.system.utils.ViewFactory;

/**
 *
 * @author jolon
 */
public class DashboardController implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }

    @FXML
    public void onLogout(MouseEvent event){
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewLogin();
    }
}
