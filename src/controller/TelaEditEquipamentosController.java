package controller;

import dao.EquipamentoDAO;
import dao.MarcaDAO;
import dao.ModeloDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Equipamento;
import model.Marca;
import model.Modelo;

public class TelaEditEquipamentosController {

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
    private Button btAtualizar;

    @FXML
    private Button btCancelar;
    
    private Equipamento equipamento;

    @FXML
    void Atualizar(MouseEvent event) {
        if (tfDescricao.getText() == null || "".equals(tfDescricao.getText()) || "".equals(tfDescricao.getText()) || cbMarca.getSelectionModel().getSelectedItem() == null || cbModelo.getSelectionModel().getSelectedItem() == null) {
            Mensagem.alerta(Alert.AlertType.ERROR, "Erro ao atualizar Equipamento", "Erro ao atualizar Equipamento", "Por favor preencha os campos corretamente.");

        } else {
            try {
                 //RECEBER OBJETO
                equipamento.setDescricao(tfDescricao.getText());
                equipamento.setModelo(cbModelo.getSelectionModel().getSelectedItem());

                EquipamentoDAO edao = new EquipamentoDAO();
                edao.atualizar(equipamento);
                Mensagem.alerta(Alert.AlertType.INFORMATION, "Atualização de Equipamento", "Equipamento Atualizado com sucesso!", "");
                btCancelar.getScene().getWindow().hide();
            } catch (Exception e) {
                System.out.println("ERRO AO EDITAR EQUIPAMENTO: " + e);
                Mensagem.alerta(Alert.AlertType.ERROR, "Erro ao Atualizar Equipamento", "Erro ao atualizar Equipamento", "Por favor Verifique os dados e tente novamente.");
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

        String nome = Mensagem.entrada("Cadastrar Marca", "Insira o nome da Marca", "Marca:");

        Marca marca = new Marca();
        marca.setDescricao(nome);

        MarcaDAO marcaDAO = new MarcaDAO();
        marcaDAO.incluir(marca);
        atualizaCBS();
    }

    @FXML
    void novoModelo(MouseEvent event) {

        if (cbMarca.getSelectionModel().isEmpty()) {
            Mensagem.alerta(Alert.AlertType.ERROR, "Erro ao criar novo modelo", "Erro ao criar modelo!", "Por favor selecione a marca antes de criar um novo modelo.");
        } else {
            String nome = Mensagem.entrada("Cadastrar Modelo", "Insira o nome do Modelo", "Modelo:");
            Modelo modelo = new Modelo();
            modelo.setDescricao(nome);
            modelo.setMarca(cbMarca.getSelectionModel().getSelectedItem());
            ModeloDAO modeloDAO = new ModeloDAO();
            modeloDAO.incluir(modelo);
            atualizaCBS();
        }

    }

    @FXML
    void initialize(Equipamento equip) {
        this.equipamento = equip;
        try {
            MarcaDAO marcadao = new MarcaDAO();
            ObservableList<Marca> obs = FXCollections.observableArrayList(marcadao.listarTodos());
            cbMarca.setItems(obs);
        } catch (Exception e) {
            System.err.println("erro ao inicializar");
        }

        cbMarca.valueProperty().addListener((ov, t, tl) -> {
            if (tl != null) {
                ModeloDAO modelodao = new ModeloDAO();
                ObservableList<Modelo> obs = FXCollections.observableArrayList(modelodao.listarPorMarca(cbMarca.getSelectionModel().getSelectedItem()));
                cbModelo.setItems(obs);
            }
//            cbModelo.setValue(null);

        tfDescricao.setText(equip.getDescricao());
        cbMarca.getSelectionModel().select(equip.getModelo().getMarca());
        cbModelo.getSelectionModel().select(equip.getModelo());
        });

    }

    void atualizaCBS() {
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
