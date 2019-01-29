package controller;

import dao.EquipamentoDAO;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Equipamento;

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
    private TableView<Equipamento> tbEquipamentos;

    @FXML
    private TableColumn<Equipamento, String> colEquip;

    @FXML
    private TableColumn<Equipamento, String> colMarca;

    @FXML
    private TableColumn<Equipamento, String> colModelo;

    @FXML
    private Button btATUALIZAequip;

    @FXML
    private Button btNOVOequip;

    @FXML
    private Button btEDITequip;

    @FXML
    private Button btEXCLUIequip;

    @FXML
    private TextField tfPesquisaManut;

    @FXML
    private TextField tfPesquisaPat;

    @FXML
    private TextField tfPesquisaEquip;

    @FXML
    void initialize() {
        iniciaTodos();
    }

    @FXML
    void AtualizaTBEquipamento(MouseEvent event) {
        atualizaEquipTable();
    }

    @FXML
    void AtualizaTBPatrimonio(MouseEvent event) {

    }

    @FXML
    void AtualizarTBManutencao(MouseEvent event) {

    }

    @FXML
    void EditarEquipamento(MouseEvent event) {

        try {
            Equipamento equipamento = tbEquipamentos.getSelectionModel().getSelectedItem();

            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaEditaEquipamento.fxml"));
            Stage stage = new Stage();
            Scene sceneAdcL = new Scene(root);
            stage.setScene(sceneAdcL);

            //Aqui fazemos o uso do modal, assim evitando que seja aberto outro Stage,
            // sem antes fechar o stage que está  em evidência  
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.resizableProperty().set(false);
            stage.show();
        } catch (Exception e) {
        }

    }

    @FXML
    void EditarManutencao(MouseEvent event) {

    }

    @FXML
    void EditarPatrimonio(MouseEvent event) {

    }

    @FXML
    void ExcluirEquipamento(MouseEvent event) {

        try {
            Equipamento equipamento = tbEquipamentos.getSelectionModel().getSelectedItem();

            EquipamentoDAO edao = new EquipamentoDAO();
            boolean certeza = Mensagem.Confirmacao("Tem certeza que deseja excluir o equipamento?");
            if (certeza) {
                edao.excluir(Equipamento.class, equipamento.getId());
                Mensagem.alerta(Alert.AlertType.INFORMATION, "CONFIRMAÇÃO", "Equipamento excluido com sucesso!", "");
                atualizaEquipTable();
            }
        } catch (Exception e) {
            System.out.println("erro excluir equipamento: " + e);
            Mensagem.alerta(Alert.AlertType.ERROR, "ERRO AO EXCLUIR", "Não foi possível excluir o equipamento.", "");
        }

    }

    @FXML
    void ExcluirPatrimonio(MouseEvent event) {

    }

    @FXML
    void NovoEquipamento(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaCadastroEquipamento.fxml"));
            Stage stage = new Stage();
            Scene sceneAdcL = new Scene(root);
            stage.setScene(sceneAdcL);

            //Aqui fazemos o uso do modal, assim evitando que seja aberto outro Stage,
            // sem antes fechar o stage que está  em evidência  
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.resizableProperty().set(false);
            stage.show();
        } catch (Exception e) {
            System.out.println("Erro ao abrir tela de cadastro");
        }

    }

    @FXML
    void novaManutencao(MouseEvent event) {

    }

    @FXML
    void novoPatrimonio(MouseEvent event) {

    }

    @FXML
    void PesquisaEquipamentos(KeyEvent event) {
        String filtro = tfPesquisaEquip.getText();
        EquipamentoDAO edao = new EquipamentoDAO();
        ObservableList<Equipamento> obs = FXCollections.observableArrayList(edao.listarTodos());
        if (filtro.equals("")) {
            tbEquipamentos.setItems(obs);
        } else {
            ObservableList<Equipamento> equips = FXCollections.observableArrayList();
            System.out.println("eeeeeeeeeeee: " + filtro);
            for (Equipamento e : obs) {
                //DESCRIÇÃO DO EQUIPAMENTO
                if (e.getDescricao().toLowerCase().contains(filtro.toLowerCase())) {
                    equips.add(e);
                } else if (e.getModelo().getDescricao().toLowerCase().contains(filtro.toLowerCase())) {
                    //MODELO
                    equips.add(e);
                } else if (e.getModelo().getMarca().getDescricao().toLowerCase().contains(filtro.toLowerCase())) {
                    //MARCA
                    equips.add(e);
                }
            }
            tbEquipamentos.setItems(equips);
        }
    }

    @FXML
    void PesquisaManutencoes(KeyEvent event) {

    }

    @FXML
    void PesquisaPatrimonios(KeyEvent event) {

    }

    void atualizaEquipTable() {
        EquipamentoDAO edao = new EquipamentoDAO();
        try {
            colEquip.setCellValueFactory(new PropertyValueFactory("descricao"));
            colEquip.setStyle("-fx-alignment: CENTER;");

            colMarca.setCellValueFactory(cellData
                    -> new SimpleStringProperty(cellData.getValue().getModelo().getMarca().getDescricao()));
            colMarca.setStyle("-fx-alignment: CENTER;");

            colModelo.setCellValueFactory(cellData
                    -> new SimpleStringProperty(cellData.getValue().getModelo().getDescricao()));
            colModelo.setStyle("-fx-alignment: CENTER;");

            ObservableList<Equipamento> obs = FXCollections.observableArrayList(edao.listarTodos());

            tbEquipamentos.setItems(obs);

        } catch (Exception e) {
            Mensagem.alerta(Alert.AlertType.ERROR, "Erro ao atualizar a tabela", "Não foi possível atualizar a tabela de equipamentos", "Não foi possível atualizar a tabela de equipamentos, por favor tente novamente.");
            System.out.println("ERRO AO ATUALIZAR A TABELA DE EQUIPAMENTOS" + e);
        }
    }

    public void iniciaTodos() {
        atualizaEquipTable();
    }

}
