package controller;

import dao.EquipamentoDAO;
import dao.PatrimonioDAO;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Equipamento;
import model.Patrimonio;

public class TelaEquipamentosController {

    @FXML
    private TableView<Equipamento> tbEquipamentos;

    @FXML
    private TableColumn<Equipamento, String> colEquip;

    @FXML
    private TableColumn<Equipamento, String> colMarca;

    @FXML
    private TableColumn<Equipamento, String> colModelo;

    @FXML
    private Button btAtualizar;

    @FXML
    private Button btNovo;

    @FXML
    private Button btEditar;

    @FXML
    private Button btExcluir;

    @FXML
    private TextField tfPesquisaE;
    
    private static Equipamento equipamento = null;

    @FXML
    void AtualizaTBEquipamento(ActionEvent event) {
        System.out.println("aaaa");
        atualizaTable();
    }

    @FXML
    void EditarEquipamento(ActionEvent event) {
            this.equipamento = tbEquipamentos.getSelectionModel().getSelectedItem();
            
            if(equipamento == null){
                Mensagem.alerta(Alert.AlertType.ERROR, "Erro ao Editar Equipamento", "Por favor selecione um equipamento para editar", "");
                return;
            }

            try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaEquipamentoEditar.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.resizableProperty().set(false);
            stage.showAndWait();
            atualizaTable();
        } catch (Exception e) {
            System.out.println("Erro ao abrir tela de cadastro");
        }
            
    }

    @FXML
    void ExcluirEquipamento(ActionEvent event) {
        boolean continua = true;
        Equipamento equipamento = null;
        
        equipamento = tbEquipamentos.getSelectionModel().getSelectedItem();
        
        //VERIFICA SE O EQUIPAMENTO FOI SELECIONADO
        if (equipamento == null) {
            Mensagem.alerta(Alert.AlertType.ERROR, "ERRO AO EXCLUIR EQUIPAMENTO", "Nenhum equipamento selecionado.", "Por favor selecione e tente novamente");
            return;
        }
        
        //VERIFICA SE HÁ ALGUM PATRIMONIO COM ESSE EQUIPAMENTO
        PatrimonioDAO pdao = new PatrimonioDAO();
        ObservableList<Patrimonio> obsPat = null;
        obsPat = FXCollections.observableArrayList(pdao.listarTodos());
        for (Patrimonio ob : obsPat) {
            if (ob.getEquipamento().equals(equipamento)) {
                Mensagem.alerta(Alert.AlertType.ERROR, "ERRO AO EXCLUIR EQUIPAMENTO", "Existem patrimônios registrados com esse equipamento.", "Não é possível excluir um equipamento atrelado a um patrimonio.");
                return;
            }
        }

        //EFETIVAMENTE EXCLUI CASO NÃO PARE NOS RETURNS ACIMA
        try {
            EquipamentoDAO edao = new EquipamentoDAO();
            boolean certeza = Mensagem.Confirmacao("Tem certeza que deseja excluir o equipamento?");
            if (certeza) {
                edao.excluir(Equipamento.class, equipamento.getId());
                Mensagem.alerta(Alert.AlertType.INFORMATION, "Excluir Equipamento", "Equipamento Excluido com Sucesso!", "");
                atualizaTable();
            }
        } catch (Exception e) {
            System.out.println("erro excluir equipamento: " + e);
            Mensagem.alerta(Alert.AlertType.INFORMATION, "Excluir Equipamento", "Não foi possível excluir realizar essa operação", "");
        }

    }

    @FXML
    void NovoEquipamento(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaEquipamentoCadastro.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            //Aqui fazemos o uso do modal, assim evitando que seja aberto outro Stage,
            // sem antes fechar o stage que está  em evidência  
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.resizableProperty().set(false);
            stage.showAndWait();
            atualizaTable();
        } catch (Exception e) {
            System.out.println("Erro ao abrir tela de cadastro");
        }

    }

    @FXML
    void Pesquisa(KeyEvent event) {
        String filtro = tfPesquisaE.getText();
        EquipamentoDAO edao = new EquipamentoDAO();
        ObservableList<Equipamento> obs = FXCollections.observableArrayList(edao.listarTodos());
        if (filtro.equals("")) {
            tbEquipamentos.setItems(obs);
        } else {
            ObservableList<Equipamento> equips = FXCollections.observableArrayList();
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
    void initialize() {
        atualizaTable();
    }

    void atualizaTable() {
        EquipamentoDAO edao = new EquipamentoDAO();
        ObservableList<Equipamento> obs = null;
        try {
            colEquip.setCellValueFactory(new PropertyValueFactory("descricao"));
            colEquip.setStyle("-fx-alignment: CENTER;");

            colMarca.setCellValueFactory(cellData
                    -> new SimpleStringProperty(cellData.getValue().getModelo().getMarca().getDescricao()));
            colMarca.setStyle("-fx-alignment: CENTER;");

            colModelo.setCellValueFactory(cellData
                    -> new SimpleStringProperty(cellData.getValue().getModelo().getDescricao()));
            colModelo.setStyle("-fx-alignment: CENTER;");

            obs = FXCollections.observableArrayList(edao.listarTodos());

            
            tbEquipamentos.setItems(obs);
            tbEquipamentos.refresh();
        } catch (Exception e) {
            Mensagem.alerta(Alert.AlertType.ERROR, "Erro ao atualizar a tabela", "Não foi possível atualizar a tabela de equipamentos", "Por favor tente novamente.");
            System.out.println("ERRO AO ATUALIZAR A TABELA DE EQUIPAMENTOS" + e);
        }
    }
    
    public static Equipamento retornaEquipamento(){
        return equipamento;
    }

}
