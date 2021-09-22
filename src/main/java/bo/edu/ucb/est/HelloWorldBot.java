/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est;

import java.util.List;
import javax.swing.JOptionPane;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;/**
*
*/
public class HelloWorldBot extends TelegramLongPollingBot {
    private int b=0;
    private Update mensajeAnterior=null;
    @Override
    public String getBotToken() {
        return "1988345512:AAGEgtLnolaKanOTJmigJg5LS0FzqvtQ1Zk";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("Llego mensaje: " + update.toString());
        if(mensajeAnterior!=null){
            if(update.hasMessage()) { // Verificamos que tenga mensaje
                String c=update.getMessage().getText();
                boolean d = false;
                if(d==Verificarcaracteres(c)==false){
                    int a=Integer.parseInt(c);
                    if(a==1 || b>=1){
                        b++;
                        suma(update);
                        if(b==3){
                            b=0;
                            menu();
                        }
                    }else if(a==2){
                            mandarMensaje("funcionalidad no implementada, intente otro día");
                            menu();
                    }
                }else{
                    menu();
                }
                
            }
            mensajeAnterior=update;
        }else{
            mensajeAnterior=update;
            menu();
        }
    
    }
    
    public void menu(){
        mandarMensaje("Bienvenido al bot Calculadora.\nSeleccione una de las siguientes opciones:\n"
                + "1.Sumar Dos numeros.\n2.Calcular serie de Fibonacci");
    }
    
    public void suma(Update update){
        int x=0,y=0;
        if(b>=1){ 
            if(b==1){
                mandarMensaje("ingrese el primer numero");
            } else {
                if(b==2){
                    mandarMensaje("ingrese el segundo numero");
                } else{ 
                    if(b==3){
                        x=Integer.parseInt(update.getMessage().getText());
                        y=Integer.parseInt(mensajeAnterior.getMessage().getText());
                        mandarMensaje("La suma es: "+(x+y));
                    }
                }
            }
        }
    }
    
    public void mandarMensaje(String mensaje){
        SendMessage message = new SendMessage();
        message.setChatId(mensajeAnterior.getMessage().getChatId().toString());
        message.setText(mensaje);
        try {
            execute(message); // Envia el mensaje
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
       
    public boolean Verificarcaracteres(String c){
        for( int i = 0; i < c.length(); i++ )
            if( !Character.isDigit( c.charAt( i ) ) )
                return false;
        return true;
    }
    
    @Override
    public String getBotUsername() {
        return "ucb_estt_bot";
    }
    
    
    
}