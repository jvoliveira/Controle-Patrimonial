package controller;

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
            alerta(Alert.AlertType.ERROR, "Erro na entrada de dados", "Erro na entrada de dados", "Por favor verifique o conteúdo digitado e tente novamente. Caso o erro persista, entre em contato com o a T.I.");
        }
        return null;
    }
    
}
