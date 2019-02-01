package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static main.Main.e;

public class TelaPrincipalController {

    @FXML
    private TableView<?> tbManut;

    @FXML
    private Button btAtualizaM;

    @FXML
    private Button btNovoM;

    @FXML
    private Button btEditarM;

    @FXML
    private TextField tfPesquisaManut;

    @FXML
    private TableView<?> tbPat;

    @FXML
    private Button btAtualizaP;

    @FXML
    private Button btNovoP;

    @FXML
    private Button btEditarP;

    @FXML
    private Button btExcluiP;

    @FXML
    private TextField tfPesquisaPat;
    
    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem miSair;

    @FXML
    private MenuItem miEquip;

    @FXML
    private MenuItem miMarcas;

    @FXML
    private MenuItem miModelos;

    @FXML
    private MenuItem miPecas;

    @FXML
    private MenuItem miChamado;

    @FXML
    void AtualizaTableP(ActionEvent event) {

    }

    @FXML
    void EditarManutencao(ActionEvent event) {

    }

    @FXML
    void EditarPatrimonio(ActionEvent event) {

    }

    @FXML
    void ExcluirPatrimonio(ActionEvent event) {

    }

    @FXML
    void NovaManutencao(ActionEvent event) {

    }

    @FXML
    void PesquisaManutencoes(KeyEvent event) {

    }

    @FXML
    void PesquisaPatrimonios(KeyEvent event) {

    }

    @FXML
    void atualizaTableM(ActionEvent event) {

    }

    @FXML
    void clickMiChamado(ActionEvent event) {

    }

    @FXML
    void clickMiEquip(ActionEvent event) {
        
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaEquipamentos.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            
            stage.setTitle("Gerenciar Equipamentos");
            
            //Aqui fazemos o uso do modal, assim evitando que seja aberto outro Stage,
            // sem antes fechar o stage que está  em evidência  
             stage.initOwner(menuBar.getScene().getWindow());
//            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.resizableProperty().set(false);
            stage.getIcons().add(e);
            stage.show();
        } catch (Exception e) {
            System.out.println("Erro ao abrir tela de cadastro: \n"+e);
        }

    }

    @FXML
    void clickMiMarcas(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaMarcas.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            
            stage.setTitle("Gerenciar Marcas");
            
             stage.initOwner(menuBar.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.resizableProperty().set(false);
            stage.getIcons().add(e);
            stage.show();
        } catch (Exception e) {
            System.out.println("Erro ao abrir tela de marcas: \n"+e);
        }
    }

    @FXML
    void clickMiModelos(ActionEvent event) {

    }

    @FXML
    void clickMiPecas(ActionEvent event) {

    }

    @FXML
    void clickSair(ActionEvent event) {

    }

    @FXML
    void novoPatrimonio(ActionEvent event) {

    }

}
