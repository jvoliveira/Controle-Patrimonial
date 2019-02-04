package controller;

import dao.EquipamentoDAO;
import dao.MarcaDAO;
import dao.ModeloDAO;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Equipamento;
import model.Marca;
import model.Modelo;

public class TelaModelosController {

    @FXML
    private TableView<Modelo> tbModelos;

    @FXML
    private TableColumn<Modelo, String> colModelo;

    @FXML
    private TableColumn<Modelo, String> colMarca;

    @FXML
    private Button btAtualiza;

    @FXML
    private Button btNovo;

    @FXML
    private Button btEditar;

    @FXML
    private Button btExcluir;

    @FXML
    private TextField tfPesquisa;
    
    public static Modelo mod = null;

    @FXML
    void Editar(ActionEvent event) {
        mod = tbModelos.getSelectionModel().getSelectedItem();
        
        if(mod==null){
            Mensagem.Erro("Erro: Nenhum modelo selecionado.", "Por favor selecione e tente novamente.");
            return;
        }
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/TelaModelosEditar.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.resizableProperty().set(false);
            stage.showAndWait();
            atualiza();
        } catch (Exception e) {
            System.out.println("Erro ao abrir tela de edição de modelos");
            Mensagem.Erro("Não foi possível abrir a tela de Edição de Modelos.",null);

        }
    }

    @FXML
    void Excluir(ActionEvent event) {
        Modelo modelo = null;
        modelo = tbModelos.getSelectionModel().getSelectedItem();
        
        if(modelo==null){
            Mensagem.Erro("Erro: Nenhum modelo selecionado.", "Por favor selecione e tente novamente.");
            return;
        }
        
        EquipamentoDAO edao = new EquipamentoDAO();
        List<Equipamento> listaEquip = new ArrayList();
        listaEquip = edao.listarTodos();
        
        for (Equipamento e : listaEquip) {
            if(e.getModelo().equals(modelo)){
                Mensagem.Erro("Erro ao excluir modelo.", "Não é possível excluir um modelo atrelada\na um equipamento.");
                return;
            }            
        }
        
        ModeloDAO mdao = new ModeloDAO();
        mdao.excluir(Modelo.class, modelo.getId());
        atualiza();
    }

    @FXML
    void Novo(ActionEvent event) {
        
        MarcaDAO mdao = new MarcaDAO();
        List<Marca> o =  mdao.listarTodos();
        Marca marca = (Marca) Mensagem.ListaOpcoes(o, "Por favor selecione a marca referente a esse modelo:\n");
        
        String descricao = Mensagem.entradaDeDados("Novo Modelo", "Por favor Insira a descrição do Modelo.", "Modelo: ");
        
        if(!descricao.isEmpty()){
            Modelo modelo = new Modelo();
            modelo.setMarca(marca);
            modelo.setDescricao(descricao);
            ModeloDAO moDAO = new ModeloDAO();
            moDAO.incluir(modelo);
            atualiza();
        }else{
            Mensagem.Erro("Erro ao criar modelo.","Por favor insira os dados corretamente.");
        }
        
        
    }

    @FXML
    void Pesquisa(KeyEvent event) {
        String filtro = tfPesquisa.getText();
        ModeloDAO mdao = new ModeloDAO();
        ObservableList<Modelo> obs = FXCollections.observableArrayList(mdao.listarTodos());
        if (filtro.equals("")) {
            tbModelos.setItems(obs);
        } else {
            ObservableList<Modelo> equips = FXCollections.observableArrayList();
            for (Modelo e : obs) {
                //DESCRIÇÃO DO MODELO
                if (e.getDescricao().toLowerCase().contains(filtro.toLowerCase())) {
                    equips.add(e);
                } else if (e.getMarca().getDescricao().toLowerCase().contains(filtro.toLowerCase())) {
                    //MARCA
                    equips.add(e);
                }
            }
            tbModelos.setItems(equips);
        }
    }

    @FXML
    void atualizaTable(ActionEvent event) {
        atualiza();
    }
    
    @FXML
    void initialize(){
        atualiza();
    }
    
    void atualiza() {
        ModeloDAO mdao = new ModeloDAO();
                
        try {
            colModelo.setCellValueFactory(cellData
                    -> new SimpleStringProperty(cellData.getValue().getDescricao()));
            colModelo.setStyle("-fx-alignment: CENTER;");
            
            colMarca.setCellValueFactory(cellData
                    -> new SimpleStringProperty(cellData.getValue().getMarca().getDescricao()));
            colMarca.setStyle("-fx-alignment: CENTER;");
            
            
            ObservableList<Modelo> obs = FXCollections.observableArrayList(mdao.listarTodos());
            tbModelos.setItems(obs);
            tbModelos.refresh();
        } catch (Exception e) {
            Mensagem.Erro("Não foi possível atualizar a tabela", "Por favor tente novamente.");
            System.out.println("ERRO AO ATUALIZAR A TABELA DE MARCAS" + e);
        }
    }
    
 
}
