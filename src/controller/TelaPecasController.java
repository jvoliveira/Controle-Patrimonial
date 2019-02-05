package controller;

import dao.EquipamentoDAO;
import dao.MarcaDAO;
import dao.PecaDAO;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.Equipamento;
import model.Marca;
import model.Peca;

public class TelaPecasController {

    @FXML
    private TableView<Peca> tbPecas;

    @FXML
    private TableColumn<Peca, String> colPeca;

    @FXML
    private TableColumn<Peca, String> colEquip;

    @FXML
    private TableColumn<Peca, String> colMarca;

    @FXML
    private TableColumn<Peca, String> colModelo;

    @FXML
    private Button btAtualizar;

    @FXML
    private Button btNovo;

    @FXML
    private Button btEditar;

    @FXML
    private Button btExcluir;

    @FXML
    private TextField tfPesquisa;

    @FXML
    void AtualizaTable(ActionEvent event) {
        atualizar();
    }

    @FXML
    void Editar(ActionEvent event) {
        Peca peca = null;
        PecaDAO pdao = new PecaDAO();
        peca = tbPecas.getSelectionModel().getSelectedItem();
        
        EquipamentoDAO edao = new EquipamentoDAO();
        List<Equipamento> l =  edao.listarTodos();
        Equipamento equip = (Equipamento) Mensagem.ListaOpcoes(l, "Por favor selecione o equipamento desejado: \n");
        
        if(equip == null){
            Mensagem.Erro("Erro ao selecionar Equipamento", "Por favor selecione corretamente\no equipamento da peça.");
            return;
        }
        
        if(peca==null){
            Mensagem.Erro("Erro: Nenhuma peça selecionada.", "Por favor selecione uma e tente novamente.");
            return;
        }
        
        String novo = Mensagem.entradaDeDados("Editar Peça", "Editando: "+peca.getDescricao(), "Por favor insira o novo nome da marca.");
        
        Boolean certeza = Mensagem.ConfirmacaoSN("ATENÇÃO!"
                                                + "\nEditar uma peça afetará todos os equipamentos relacionados."
                                                + "\nApós editar, todos os equipamentos cadastrados com a peça: "+peca.getDescricao()
                                                + "\nserão relacionados com a peça: "+novo
                                                + "\nTem certeza que deseja continuar?");
        
        if(certeza){
            peca.setDescricao(novo);
            peca.setEquipamento(equip);
            pdao.atualizar(peca);
            Mensagem.Sucesso("Peça Editado com Sucesso!");
            atualizar();
        }
    }

    @FXML
    void Excluir(ActionEvent event) {
        Peca peca = null;
        peca = tbPecas.getSelectionModel().getSelectedItem();
        
        if(peca==null){
            Mensagem.Erro("Erro: Nenhuma peça selecionada.", "Por favor selecione uma e tente novamente.");
            return;
        }
        
        PecaDAO pdao = new PecaDAO();
        pdao.excluir(Peca.class, peca.getId());
        atualizar();
    }

    @FXML
    void Novo(ActionEvent event) {
        EquipamentoDAO edao = new EquipamentoDAO();
        Equipamento e = (Equipamento) Mensagem.ListaOpcoes(edao.listarTodos(), "Selecione o equipamento a qual essa peça pertence.");
        String desc = Mensagem.entradaDeDados("Cadastro de Peças", "Qual o nome da Peça?", "Peça: ");
        
        if(desc==null){
            Mensagem.Erro("Erro ao cadastrar Peça.", "Por favor insira os dados corretamente.");
            return;
        }
        
        Peca p = new Peca();
        p.setDescricao(desc);
        p.setEquipamento(e);
        
        try {
            PecaDAO pdao = new PecaDAO();
            pdao.incluir(p);
            Mensagem.Sucesso("Peça cadastrada com sucesso!");
            atualizar();
        } catch (Exception ex) {
            System.out.println(ex);
            Mensagem.Erro("Não foi possível registrar essa peça", "Por favor tente novamente.");
        }
        
    }

    @FXML
    void PesquisaEquipamentos(KeyEvent event) {
        String filtro = tfPesquisa.getText();
        PecaDAO pdao = new PecaDAO();
        ObservableList<Peca> obs = FXCollections.observableArrayList(pdao.listarTodos());
        if (filtro.equals("")) {
            tbPecas.setItems(obs);
        } else {
            ObservableList<Peca> equips = FXCollections.observableArrayList();
            for (Peca e : obs) {
                //NOME DA PEÇA
                if (e.getDescricao().toLowerCase().contains(filtro.toLowerCase())) {
                    equips.add(e);
                }else if(e.getEquipamento().getDescricao().toLowerCase().contains(filtro.toLowerCase())) {
                    equips.add(e);
                }else if(e.getEquipamento().getModelo().getDescricao().toLowerCase().contains(filtro.toLowerCase())) {
                    equips.add(e);
                }else if (e.getEquipamento().getModelo().getMarca().getDescricao().toLowerCase().contains(filtro.toLowerCase())) {
                    equips.add(e);
                }
            }
            tbPecas.setItems(equips);
        }
    }
    
    @FXML
    void initialize(){
        atualizar();
    }
    
    void atualizar() {
        PecaDAO pdao = new PecaDAO();
        ObservableList<Peca> obs = null;
        try {
            colPeca.setCellValueFactory(new PropertyValueFactory("descricao"));
            colPeca.setStyle("-fx-alignment: CENTER;");

            colEquip.setCellValueFactory(cellData
                    -> new SimpleStringProperty(cellData.getValue().getEquipamento().getDescricao()));
            colEquip.setStyle("-fx-alignment: CENTER;");

            colMarca.setCellValueFactory(cellData
                    -> new SimpleStringProperty(cellData.getValue().getEquipamento().getModelo().getMarca().getDescricao()));
            colMarca.setStyle("-fx-alignment: CENTER;");
            
            colModelo.setCellValueFactory(cellData
                    -> new SimpleStringProperty(cellData.getValue().getEquipamento().getModelo().getDescricao()));
            colModelo.setStyle("-fx-alignment: CENTER;");

            obs = FXCollections.observableArrayList(pdao.listarTodos());
            
            tbPecas.setItems(obs);
            tbPecas.refresh();
        } catch (Exception e) {
            Mensagem.Erro("Não foi possível atualizar a tabela de peças", "Por favor tente novamente.");
            System.out.println("ERRO AO ATUALIZAR A TABELA DE PEÇAS" + e);
        }
    }

}
