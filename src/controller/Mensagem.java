package controller;

import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author joliveira
 */
public interface Mensagem {

    public static void alertaPersonalizado(Alert.AlertType tipo, String titulo, String cabecalho, String conteudo){
       
        Alert mensagem = new Alert(tipo);
        
        if(tipo == Alert.AlertType.NONE){

            mensagem.setTitle(titulo);
            mensagem.setHeaderText(cabecalho);
            mensagem.setContentText(conteudo);
            mensagem.getOnCloseRequest();
            mensagem.getButtonTypes().add(ButtonType.OK);
            mensagem.showAndWait();
        }else {
            
            mensagem.setTitle(titulo);
            mensagem.setHeaderText(cabecalho);
            mensagem.setContentText(conteudo);
            mensagem.showAndWait();
        }
        
}
    public static String entradaDeDados(String titulo, String cabecalho, String conteudo){
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle(titulo);
            dialog.setHeaderText(cabecalho);
            dialog.setContentText(conteudo);
            String textoDigitado;
            textoDigitado = dialog.showAndWait().get();
            return textoDigitado;
        } catch (Exception e) {
            System.out.println("ERRO AO INSERIR A ENTRADA | INTERFACE MENSAGEM");
            alertaPersonalizado(Alert.AlertType.ERROR, "Erro na entrada de dados", "Erro na entrada de dados", "Por favor verifique o conteúdo digitado e tente novamente.");
        }
        return null;
    }
    
       public static boolean ConfirmacaoSN(String mensagemConfirmacao){
       
            Alert mensagem = new Alert(Alert.AlertType.CONFIRMATION);

            mensagem.setTitle("Confirmação");
            mensagem.setHeaderText(mensagemConfirmacao);
            mensagem.getOnCloseRequest();
            mensagem.getButtonTypes().removeAll(ButtonType.OK);
            mensagem.getButtonTypes().removeAll(ButtonType.CANCEL);
            mensagem.getButtonTypes().add(ButtonType.YES);
            mensagem.getButtonTypes().add(ButtonType.NO);
            Optional<ButtonType> result =  mensagem.showAndWait();
            
            if (result.get() == ButtonType.YES){
                return true;
            } else {
                return false;
            }
}
             public static void Erro(String texto, String corpo){
       
            Alert mensagem = new Alert(Alert.AlertType.ERROR);

            if(corpo==null)
                corpo="";
            mensagem.setTitle("Ocorreu um erro!");
            mensagem.setHeaderText(texto);
            mensagem.setContentText(corpo);
            mensagem.getOnCloseRequest();
            Optional<ButtonType> result =  mensagem.showAndWait();
 
            }
              public static void Sucesso(String texto){
       
            Alert mensagem = new Alert(Alert.AlertType.INFORMATION);

            mensagem.setTitle("Sucesso!");
            mensagem.setHeaderText(texto);
            mensagem.getOnCloseRequest();
            Optional<ButtonType> result =  mensagem.showAndWait();
 
            }
              
            public static Object ListaOpcoes(List l,String cabecalho){
                ChoiceDialog dialog = new ChoiceDialog();
                dialog.getItems().setAll(l);
                dialog.setTitle("Seleção");
                dialog.setHeaderText(cabecalho);
                Object m = null;
                m = dialog.showAndWait().get();
                return m;
            }
    
}
