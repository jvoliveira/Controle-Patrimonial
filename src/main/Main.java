/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



/**
 *
 * @author joliveira
 */
public class Main extends Application {
    private String user = "root"; //Nome de usuário
    private String password = ""; //Senha do mysql
    private String banco = "controleequip";
    private String ip = "localhost";
    private String porta = "3306";
    public static Image e;

    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaPrincipal.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        stage.setTitle("Controle Patrimonial");
        stage.initStyle(StageStyle.DECORATED);
        gerarBD();
        alterarEncoding();
        stage.resizableProperty().set(false);
         e = new Image("/imagens/icon.png");
        stage.getIcons().add(e);
     
        stage.show();
    }
    
    private void gerarBD() {
        try{
            
            String url = "jdbc:mysql://"+ip+":"+porta+"/mysql?zeroDateTimeBehavior=convertToNull"; //URL de comunicação com o MYSQL

            Connection connection = DriverManager.getConnection(url, user, password); //Criando a comunicação com o banco
            String sql = "CREATE DATABASE IF NOT EXISTS "+banco; //Criando a query de criação do bancos caso ele não exista na máquina
            
            Statement statement = (Statement) connection.createStatement();
            statement.executeUpdate(sql); //Executando a query
            statement.close();
            connection.close();
            
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    private void alterarEncoding(){
         try{
            
            String url = "jdbc:mysql://"+ip+":"+porta+"/mysql?zeroDateTimeBehavior=convertToNull"; //URL de comunicação com o MYSQL

            Connection connection = DriverManager.getConnection(url, user, password); //Criando a comunicação com o banco
            String sql = "ALTER DATABASE "+banco+" CHARSET = UTF8 COLLATE = utf8_general_ci;"; //Criando a query de criação do bancosgp caso ele não exista na máquina
            
            Statement statement = (Statement) connection.createStatement();
            statement.executeUpdate(sql); //Executando a query
            statement.close();
            connection.close();
            
        } catch(SQLException ex){
            ex.printStackTrace();
        }
}
    
}
