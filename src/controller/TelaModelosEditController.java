package controller;

import dao.MarcaDAO;
import dao.ModeloDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Marca;
import model.Modelo;

public class TelaModelosEditController {

    @FXML
    private TextField tfDescricao;

    @FXML
    private ComboBox<Marca> cbMarca;

    @FXML
    private Button btNOVOmarca;

    @FXML
    private Button btAtualizar;

    @FXML
    private Button btCancelar;

    private Modelo modelo;
    
    @FXML
    void Atualizar(MouseEvent event) {
        
        
        if (tfDescricao.getText() == null || "".equals(tfDescricao.getText()) || cbMarca.getSelectionModel().getSelectedItem() == null) {
            Mensagem.Erro("Erro ao atualizar Modelo", "Por favor preencha os campos corretamente.");
        } else {
            try {
                 //RECEBER OBJETO
                modelo.setDescricao(tfDescricao.getText());
                modelo.setMarca(cbMarca.getSelectionModel().getSelectedItem());

                ModeloDAO edao = new ModeloDAO();
                edao.atualizar(modelo);
                Mensagem.Sucesso("Modelo Atualizado com sucesso!");
                btCancelar.getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println("ERRO AO EDITAR Modelo: " + e);
                Mensagem.Erro("Erro ao atualizar Modelo", "Por favor Verifique os dados e tente novamente.");
                btCancelar.getScene().getWindow().hide();
            }
        }

    }

    @FXML
    void Cancelar(MouseEvent event) {
        btCancelar.getScene().getWindow().hide();
    }

    @FXML
    void novaMarca(MouseEvent event) {
        String nome = Mensagem.entradaDeDados("Cadastrar Marca", "Insira o nome da Marca", "Marca:");
        if(!nome.isEmpty()){
            Marca marca = new Marca();
            marca.setDescricao(nome);

            MarcaDAO marcaDAO = new MarcaDAO();
            marcaDAO.incluir(marca);
            Mensagem.Sucesso("Marca Criada com sucesso!");
            atuaizacbs();
        }else{
            Mensagem.Erro("Erro ao Criar Marca", "Por favor insira o nome da Marca corretamente");
        }
    }
    
    @FXML
    void initialize(){
        atuaizacbs();
    
        modelo = TelaModelosController.mod;
        
        tfDescricao.setText(modelo.getDescricao());
        cbMarca.getSelectionModel().select(modelo.getMarca());
    }
    
    void atuaizacbs(){
        try {
            MarcaDAO marcadao = new MarcaDAO();
            ObservableList<Marca> obs = FXCollections.observableArrayList(marcadao.listarTodos());
            cbMarca.setItems(obs);
        } catch (Exception e) {
            System.err.println("erro ao inicializar");
        }
    }
    

}
