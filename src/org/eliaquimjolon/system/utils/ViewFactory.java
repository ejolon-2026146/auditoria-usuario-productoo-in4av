/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.eliaquimjolon.system.utils;

import javafx.scene.Scene;
import java.io.IOException;
import java.io.UncheckedIOException;
import javafx.fxml.FXMLLoader;
import java.net.URL;
import javafx.fxml.JavaFXBuilderFactory;
import org.eliaquimjolon.system.ClasePrincipal;
/**
 *
 * @author informatica
 */
public class ViewFactory {
    private final String PATH_VIEWS = "/org/eliaquimjolon/system/view/";
    
    public Scene loadFileFXML(String nameFile, int width, int height){
        String pathOfFile = PATH_VIEWS + nameFile ;
        try {
            //Llamar al FXMLLOADER
            FXMLLoader loadFXML = new FXMLLoader();

            //Obtener la URL del archivo viende de la clase main
            URL urlFILE =  ClasePrincipal.class.getResource(pathOfFile);
            loadFXML.setBuilderFactory(new JavaFXBuilderFactory());
            loadFXML.setLocation(urlFILE);
            
            return new Scene(loadFXML.load(), width , height);
            
            
        } catch (IOException e) {
            throw new UncheckedIOException (e);
        }
    }
            
    public void loadScene(String nameFile){
        Scene scene = null;
        try {
            switch (nameFile) {
                case "login" -> scene = loadFileFXML("LoginView.fxml",400,500);
                    default -> scene = loadFileFXML("LoginView.fxml",0,0);
               
            }
            SceneManager.getInstanciaSceneManager().changeScene(scene);
                    
                    
        } catch (NullPointerException e) {
            System.out.println("Error load scene");
            //alert
        }

        
    }
    
    public void viewLogin(){
        loadScene("login");
    }
            
            
}
