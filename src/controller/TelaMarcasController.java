package controller;

import dao.EquipamentoDAO;
import dao.MarcaDAO;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.Equipamento;
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
    

    @FXML
    void Editar(ActionEvent event) {
        Marca marca = null;
        MarcaDAO mdao = new MarcaDAO();
        marca = tbMarcas.getSelectionModel().getSelectedItem();
        
        if(marca==null){
            Mensagem.Erro("Erro: Nenhuma marca selecionada.", "Por favor selecione uma e tente novamente.");
            return;
        }
        
        String novo = Mensagem.entradaDeDados("Editar Marca", "Editando: "+marca.getDescricao(), "Por favor insira o novo nome da marca.");
        
        Boolean certeza = Mensagem.ConfirmacaoSN("ATENÇÃO!"
                                                + "\nEditar uma marca afetará todos os equipamentos relacionados."
                                                + "\nApós editar, todos os equipamentos cadastrados com a marca: "+marca.getDescricao()
                                                + "\nserão relacionados com a marca: "+novo
                                                + "\nTem certeza que deseja continuar?");
        
        if(certeza){
            marca.setDescricao(novo);
            mdao.atualizar(marca);
            Mensagem.Sucesso("Marca Editado com Sucesso!");
        }
        
        
    }

    @FXML
    void Excluir(ActionEvent event) {
        Marca marca = null;
        marca = tbMarcas.getSelectionModel().getSelectedItem();
        
        if(marca==null){
            Mensagem.Erro("Erro: Nenhuma marca selecionada.", "Por favor selecione uma e tente novamente.");
            return;
        }
        
        EquipamentoDAO edao = new EquipamentoDAO();
        List<Equipamento> listaEquip = new ArrayList();
        listaEquip = edao.listarTodos();
        
        for (Equipamento e : listaEquip) {
            if(e.getModelo().getMarca().equals(marca)){
                Mensagem.Erro("Erro ao excluir marca.", "Não é possível excluir uma marca atrelada\na um equipamento.");
                return;
            }            
        }
        
        MarcaDAO mdao = new MarcaDAO();
        mdao.excluir(Marca.class, marca.getId());
        atualizaTableM();

    }


    @FXML
    void Novo(ActionEvent event) {
        
        String nome = Mensagem.entradaDeDados("Cadastrar Marca", "Insira o nome da Marca", "Marca:");
        if(!nome.isEmpty()){
            Marca M = new Marca();
            M.setDescricao(nome);

            MarcaDAO marcaDAO = new MarcaDAO();
            marcaDAO.incluir(M);
            atualizaTableM();
        }else{
            Mensagem.Erro("Erro ao criar marca.","Por favor insira os dados corretamente.");
        }

    }

    @FXML
    void Pesquisa(KeyEvent event) {
        String filtro = tfPesquisa.getText();
        MarcaDAO mdao = new MarcaDAO();
        ObservableList<Marca> obs = FXCollections.observableArrayList(mdao.listarTodos());
        if (filtro.equals("")) {
            tbMarcas.setItems(obs);
        } else {
            ObservableList<Marca> equips = FXCollections.observableArrayList();
            for (Marca e : obs) {
                //NOME DA MARCA
                if (e.getDescricao().toLowerCase().contains(filtro.toLowerCase())) {
                    equips.add(e);
                }
            }
            tbMarcas.setItems(equips);
        }
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
            tbMarcas.refresh();
        } catch (Exception e) {
            Mensagem.Erro("Não foi possível atualizar a tabela", "Por favor tente novamente.");
            System.out.println("ERRO AO ATUALIZAR A TABELA DE MARCAS" + e);
        }
    }
    
 
}
