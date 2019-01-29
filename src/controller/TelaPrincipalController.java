package controller;

import java.awt.Color;
import java.awt.Paint;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaPrincipalController {

    @FXML
    private TableView<?> tbManut;

    @FXML
    private Button btATUALIZAmanut;

    @FXML
    private Button btNOVOmanut;

    @FXML
    private Button btEDITmanut;

    @FXML
    private TableView<?> tbPat;

    @FXML
    private Button btATUALIZApat;

    @FXML
    private Button btNOVOpat;

    @FXML
    private Button btEDITpat;

    @FXML
    private Button btEXCLUIpat;

    @FXML
    private TableView<?> tbEquipamentos;

    @FXML
    private Button btATUALIZAequip;

    @FXML
    private Button btNOVOequip;

    @FXML
    private Button btEDITequip;

    @FXML
    private Button btEXCLUIequip;

    @FXML
    void AtualizaTBEquipamento(MouseEvent event) {

    }

    @FXML
    void AtualizaTBPatrimonio(MouseEvent event) {

    }

    @FXML
    void AtualizarTBManutencao(MouseEvent event) {

    }

    @FXML
    void EditarEquipamento(MouseEvent event) {

    }

    @FXML
    void EditarManutencao(MouseEvent event) {

    }

    @FXML
    void EditarPatrimonio(MouseEvent event) {

    }

    @FXML
    void ExcluirEquipamento(MouseEvent event) {

    }

    @FXML
    void ExcluirPatrimonio(MouseEvent event) {

    }

    @FXML
    void NovoEquipamento(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaCadastroEquipamento.fxml"));
            Stage stage = new Stage();
            Scene sceneAdcL = new Scene(root);
            stage.setScene(sceneAdcL);

           //Aqui fazemos o uso do modal, assim evitando que seja aberto outro Stage,
           // sem antes fechar o stage que está  em evidência  
           stage.initOwner(((Node)event.getSource()).getScene().getWindow());
           stage.initModality(Modality.WINDOW_MODAL);
           stage.resizableProperty().set(false);
           stage.show();
    }

    @FXML
    void novaManutencao(MouseEvent event) {

    }

    @FXML
    void novoPatrimonio(MouseEvent event) {

    }
    
    
    

}
