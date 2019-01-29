package controller;

import dao.MarcaDAO;
import dao.ModeloDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import model.Marca;
import model.Modelo;

public class TelaCadEquipamentosController {

    @FXML
    private TextField tfDescricao;

    @FXML
    private ComboBox<Marca> cbMarca;

    @FXML
    private Button btNOVOmarca;

    @FXML
    private ComboBox<Modelo> cbModelo;

    @FXML
    private Button btNOVOmodelo;

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btCancelar;
    
    @FXML
    void Cadastrar(MouseEvent event) {

    }

    @FXML
    void Cancelar(MouseEvent event) {

    }

    @FXML
    void novaMarca(MouseEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cadastrar Marca");
        dialog.setHeaderText("Insira o nome da Marca");
        dialog.setContentText("Marca:");
        String nome;
        nome = dialog.showAndWait().get();
        
        Marca marca = new Marca();
        marca.setDescricao(nome);
        
        MarcaDAO marcaDAO = new MarcaDAO();
        marcaDAO.incluir(marca);
        atualizaCBS();
    }

    @FXML
    void novoModelo(MouseEvent event) {
        
        if(cbMarca.getSelectionModel().isEmpty()){
            Mensagem.alerta(Alert.AlertType.ERROR, "Erro ao criar novo modelo", "Erro ao criar modelo!", "Por favor selecione a marca antes de criar um novo modelo.");
        }else{
            String nome = Mensagem.entrada("Cadastrar Modelo", "Insira o nome do Modelo", "Modelo:");
            Modelo modelo = new Modelo();
            modelo.setDescricao(nome);

            ModeloDAO modeloDAO = new ModeloDAO();
            modeloDAO.incluir(modelo);
            atualizaCBS();
        }

    }
    
    @FXML
    void initialize() {
        try {
            MarcaDAO marcadao = new MarcaDAO();
            ObservableList<Marca> obs = FXCollections.observableArrayList(marcadao.listarTodos());
            cbMarca.setItems(obs);
        } catch (Exception e) {
            System.err.println("erro ao inicializar");
        }
        
        cbMarca.valueProperty().addListener((ov, t, tl) -> {
            if(tl!=null){
                ModeloDAO modelodao = new ModeloDAO();
                ObservableList<Modelo> obs = FXCollections.observableArrayList(modelodao.listarPorMarca(cbMarca.getSelectionModel().getSelectedItem()));
                cbModelo.setItems(obs);
            }
//            cbModelo.setValue(null);
        });
        
    }
    
    void atualizaCBS(){
        cbMarca.setItems(null);
        cbModelo.setItems(null);
        try {
            MarcaDAO marcadao = new MarcaDAO();
            ObservableList<Marca> obs = FXCollections.observableArrayList(marcadao.listarTodos());
            cbMarca.setItems(obs);
        } catch (Exception e) {
            System.err.println("erro ao atualizar CBS");
        }
    }
    
    
        

}
