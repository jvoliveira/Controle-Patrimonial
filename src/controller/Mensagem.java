package controller;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author joliveira
 */
public interface Mensagem {

    public static void alerta(Alert.AlertType tipo, String titulo, String cabecalho, String conteudo){
       
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
    public static String entrada(String titulo, String cabecalho, String conteudo){
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
            alerta(Alert.AlertType.ERROR, "Erro na entrada de dados", "Erro na entrada de dados", "Por favor verifique o conteúdo digitado e tente novamente.");
        }
        return null;
    }
    
       public static boolean Confirmacao(String mensagemConfirmacao){
       
            Alert mensagem = new Alert(Alert.AlertType.WARNING);

            mensagem.setTitle("ATENÇÃO");
            mensagem.setHeaderText(mensagemConfirmacao);
            mensagem.getOnCloseRequest();
            mensagem.getButtonTypes().removeAll(ButtonType.OK);
            mensagem.getButtonTypes().add(ButtonType.YES);
            mensagem.getButtonTypes().add(ButtonType.NO);
            Optional<ButtonType> result =  mensagem.showAndWait();
            
            if (result.get() == ButtonType.YES){
                return true;
            } else {
                return false;
            }
            
        
}
    
}
