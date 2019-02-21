package controller;

import dao.PatrimonioDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static main.Main.e;
import model.Patrimonio;

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
    private TableView<Patrimonio> tbPat;
    
    @FXML
    private TableColumn<Patrimonio, String> colPat;

    @FXML
    private TableColumn<Patrimonio, String> colLocal;

    @FXML
    private TableColumn<Patrimonio, String> colEquip;

    @FXML
    private TableColumn<Patrimonio, String> colMarca;

    @FXML
    private TableColumn<Patrimonio, String> colModelo;

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
    void clickMiLocais(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaLocais.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            
            stage.setTitle("Gerenciar Locais");
            
             stage.initOwner(menuBar.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.resizableProperty().set(false);
            stage.getIcons().add(e);
            stage.show();
        } catch (Exception e) {
            System.out.println("Erro ao abrir tela de Locais: \n"+e);
            Mensagem.Erro("Não Foi possível abrir a tela \nde Gerenciamento de Locais",null);
        }
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
            Mensagem.Erro("Não Foi possível abrir a tela \nde Gerenciamento de Equipamentos",null);
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
            Mensagem.Erro("Não Foi possível abrir a tela \nde Gerenciamento de Marcas",null);
        }
    }

    @FXML
    void clickMiModelos(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaModelos.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            
            stage.setTitle("Gerenciar Modelos");
            
             stage.initOwner(menuBar.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.resizableProperty().set(false);
            stage.getIcons().add(e);
            stage.show();
        } catch (Exception e) {
            System.out.println("Erro ao abrir tela de Modelos: \n"+e);
            Mensagem.Erro("Não Foi possível abrir a tela \nde Gerenciamento de Modelos",null);
        }
    }

    @FXML
    void clickMiPecas(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaPecas.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            
            stage.setTitle("Gerenciar Peças");
            
             stage.initOwner(menuBar.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.resizableProperty().set(false);
            stage.getIcons().add(e);
            stage.show();
        } catch (Exception e) {
            System.out.println("Erro ao abrir tela de Pelas: \n"+e);
            Mensagem.Erro("Não Foi possível abrir a tela \nde Gerenciamento de Peças",null);
        }
    }

    @FXML
    void clickSair(ActionEvent event) {
        tbManut.getScene().getWindow().hide();
    }

    @FXML
    void novoPatrimonio(ActionEvent event) {

    }
    
    @FXML
    void initialize(){
        atualizaPat();
    }
    
    void atualizaPat() {
        PatrimonioDAO pdao = new PatrimonioDAO();
        ObservableList<Patrimonio> obs = null;
        try {
            colEquip.setCellValueFactory(cellData
                    -> new SimpleStringProperty(cellData.getValue().getEquipamento().getDescricao()));
            colEquip.setStyle("-fx-alignment: CENTER;");

            colMarca.setCellValueFactory(cellData
                    -> new SimpleStringProperty(cellData.getValue().getEquipamento().getModelo().getMarca().getDescricao()));
            colMarca.setStyle("-fx-alignment: CENTER;");

            colModelo.setCellValueFactory(cellData
                    -> new SimpleStringProperty(cellData.getValue().getEquipamento().getModelo().getDescricao()));
            colModelo.setStyle("-fx-alignment: CENTER;");
            
            colLocal.setCellValueFactory(cellData
                    -> new SimpleStringProperty(cellData.getValue().getLocall().getDescricao()));
            colLocal.setStyle("-fx-alignment: CENTER;");
            
            colPat.setCellValueFactory(cellData
                    -> new SimpleStringProperty(cellData.getValue().getSNUMPAT()));
            colPat.setStyle("-fx-alignment: CENTER;");

            obs = FXCollections.observableArrayList(pdao.listarTodos());

            
            tbPat.setItems(obs);
            tbPat.refresh();
        } catch (Exception e) {
            Mensagem.Erro("Não foi possível atualizar a tabela de Patrimonios", "Por favor tente novamente.");
            System.out.println("ERRO AO ATUALIZAR A TABELA DE Patrimonios" + e);
        }
    }

}
