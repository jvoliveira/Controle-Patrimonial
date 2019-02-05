package controller;

import dao.LocallDAO;
import dao.PatrimonioDAO;
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
import model.Locall;
import model.Patrimonio;

public class TelaLocalController {

    @FXML
    private TableView<Locall> tbLocais;

    @FXML
    private TableColumn<Locall, String> colLocal;

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
        Locall locall = null;
        LocallDAO ldao = new LocallDAO();
        locall = tbLocais.getSelectionModel().getSelectedItem();
        
        if(locall==null){
            Mensagem.Erro("Erro: Nenhum Local selecionado.", "Por favor selecione e tente novamente.");
            return;
        }
        
        String novo = Mensagem.entradaDeDados("Editar Local", "Editando: "+locall.getDescricao(), "Por favor insira o novo nome do Local.");
        
        Boolean certeza = Mensagem.ConfirmacaoSN("ATENÇÃO!"
                                                + "\nEditar um local afetará todos os patrimônios relacionados."
                                                + "\nApós editar, todos os patrimonios cadastrados com o local: "+locall.getDescricao()
                                                + "\nserão relacionados com o local: "+novo
                                                + "\nTem certeza que deseja continuar?");
        
        if(certeza){
            locall.setDescricao(novo);
            ldao.atualizar(locall);
            Mensagem.Sucesso("Locals Editado com Sucesso!");
            atualizar();
        }
    }

    @FXML
    void Excluir(ActionEvent event) {
        Locall locall = null;
        locall = tbLocais.getSelectionModel().getSelectedItem();
        
        if(locall==null){
            Mensagem.Erro("Erro: Nenhum local selecionado.", "Por favor selecione e tente novamente.");
            return;
        }
        
        PatrimonioDAO pdao = new PatrimonioDAO();
        List<Patrimonio> listaPatrimonio = new ArrayList();
        listaPatrimonio = pdao.listarTodos();
        
        for (Patrimonio p : listaPatrimonio) {
            if(p.getLocall().equals(locall)){
                Mensagem.Erro("Erro ao excluir Local.", "Não é possível excluir um local atrelado\na um Patrimônio.");
                return;
            }            
        }
        
        LocallDAO ldao = new LocallDAO();
        ldao.excluir(Locall.class, locall.getId());
        atualizar();
    }

    @FXML
    void Novo(ActionEvent event) {
        
         String nome = Mensagem.entradaDeDados("Cadastrar Local", "Insira o nome do Local/Setor", "Local/Setor:");
        if(!nome.isEmpty()){
            Locall l = new Locall();
            l.setDescricao(nome);

            LocallDAO ldao = new LocallDAO();
            ldao.incluir(l);
            atualizar();
        }else{
            Mensagem.Erro("Erro ao criar marca.","Por favor insira os dados corretamente.");
        }

    }

    @FXML
    void Pesquisa(KeyEvent event) {
        String filtro = tfPesquisa.getText();
        LocallDAO ldao = new LocallDAO();
        ObservableList<Locall> obs = FXCollections.observableArrayList(ldao.listarTodos());
        if (filtro.equals("")) {
            tbLocais.setItems(obs);
        } else {
            ObservableList<Locall> equips = FXCollections.observableArrayList();
            for (Locall l : obs) {
                //NOME DA MARCA
                if (l.getDescricao().toLowerCase().contains(filtro.toLowerCase())) {
                    equips.add(l);
                }
            }
            tbLocais.setItems(equips);
        }
    }

    @FXML
    void atualizaTable(ActionEvent event) {
        atualizar();
    }
    
    @FXML
    void initialize(){
        atualizar();
    }
    
    void atualizar() {
        LocallDAO ldao = new LocallDAO();
                
        try {
            colLocal.setCellValueFactory(cellData
                    -> new SimpleStringProperty(cellData.getValue().getDescricao()));
            colLocal.setStyle("-fx-alignment: CENTER;");
            ObservableList<Locall> obs = FXCollections.observableArrayList(ldao.listarTodos());
            tbLocais.setItems(obs);
            tbLocais.refresh();
        } catch (Exception e) {
            Mensagem.Erro("Não foi possível atualizar a tabela", "Por favor tente novamente.");
            System.out.println("ERRO AO ATUALIZAR A TABELA DE Locais" + e);
        }
    }

}
