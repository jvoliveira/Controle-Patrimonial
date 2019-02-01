package controller;

import dao.MarcaDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Marca;

public class TelaMarcasController {

     @FXML
    private TableView<Marca> tbMarcas;

    @FXML
    private TableColumn<Marca, String> colMarca;

    @FXML
    private Button btAtualizarTB;

    @FXML
    private Button btNovo;

    @FXML
    private Button btEdit;

    @FXML
    private Button btExclui;

    @FXML
    private TextField tfPesquisa;
    
    private static Marca marca;

    @FXML
    void Editar(ActionEvent event) {

    }

    @FXML
    void Excluir(ActionEvent event) {

    }

    @FXML
    void ExcluirMarca(MouseEvent event) {

    }

    @FXML
    void Novo(ActionEvent event) {

    }

    @FXML
    void Pesquisa(KeyEvent event) {

    }

    @FXML
    void atualizaTable(ActionEvent event) {
        atualizaTableM();
    }

    @FXML
    void initialize() {
        atualizaTableM();
    }
    
    void atualizaTableM() {
        MarcaDAO mdao = new MarcaDAO();
                
        try {
            colMarca.setCellValueFactory(cellData
                    -> new SimpleStringProperty(cellData.getValue().getDescricao()));
            colMarca.setStyle("-fx-alignment: CENTER;");
            ObservableList<Marca> obs = FXCollections.observableArrayList(mdao.listarTodos());
            tbMarcas.setItems(obs);
        } catch (Exception e) {
            Mensagem.alerta(Alert.AlertType.ERROR, "Erro ao atualizar a tabela", "Não foi possível atualizar a tabela de marcas", "Por favor tente novamente.");
            System.out.println("ERRO AO ATUALIZAR A TABELA DE MARCAS" + e);
        }
    }
    
    public static Marca retornaMarca(){
        return marca;
    }
}
