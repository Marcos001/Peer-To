package com.trairas.nig.peer_to.Util;



import java.io.EOFException;
import java.io.IOException;;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class Cliente{

    Util u = new Util();

    private ObjectOutputStream output; //gera o fluxo de saida para o servidor
    private ObjectInputStream input; //gera o fluxo de entrada a partir do servidor
    private String message = "";//mensagem do servidor
    private String chatServer; //servidor de host para esse aplicativo
    private Socket client; //socket para comunicação

    //inicializa chatServer e configura a GUI
    public Cliente(String host){

        chatServer =  host;

       //sendData(event.getActionCommand());

    }

    public void runCliente(String mensagem){

        try{//config o servidor para receber conexões; processa as conexoes

            connectToServer();//cria um socket para fazer a conexao
            getStreams(); //obtem os fluxos de entrada e saida
            ProcessConection(mensagem);//processa a conexao
        }catch(EOFException e){
            u.print("\nClientd Terminou a conecção.");
        }
        catch(IOException oi){
            oi.printStackTrace();
        }
        finally{
            u.print("finally");
            closeConection(); //fecha a conexao
        }
    }

    //processa a conexão com o cliente
    private void  ProcessConection(String mensagem) throws IOException{


        //processa as menssagens enviadas pelo cliente

        do{
            u.print("---------Cliente------ : ");

            try {//lê e exibe a menssagem
                message = (String) input.readObject();//lê uma nova menssagem
                //displayMessage("\n" + message);
                u.print("Mensagem recebida do Servidor = "+message);
                sendData(mensagem);
            } catch (ClassNotFoundException c) {
                u.print("\nUnknowm object type received");
            }

        }while((!message.equals("SERVER>> ENCONTRADO em host")) || (!message.equals("SERVER>> NAO ENCONTRADO")));

        closeConection();
    }



    //ontém fluxos para enviar e receber dados
    private void getStreams() throws IOException{
        //configura o fluxo de saida de dados
        output = new ObjectOutputStream(client.getOutputStream());
        output.flush();//esvazia o buffer de saida para enviar as informações
        //configura o fluxo de entrada de dados
        input = new ObjectInputStream(client.getInputStream());

        u.print("\n I/O streams\n");
    }


    private void connectToServer()throws IOException{

        u.print("tentando conexão\n");
        //cria um socket para fazer a connexão
        client = new Socket(InetAddress.getByName(chatServer),12345);

        //exibe informções sobre a connexão
        u.print("Connected com "+client.getInetAddress().getHostName());

    }


    private void sendData(String message){

        try{
            output.writeObject(message);
            output.flush();//esvazia a saida para o cliente
            u.print("\nCLIENTE>>"+message);
        }catch(IOException io){
            u.print("\nError writing objetc");
        }

    }

    //fecha os fluxos e os sockets
    private void closeConection(){
        u.print("fechando a conexão com  cliente!");
        try{
            output.close();
            input.close();
            client.close();
        }catch(IOException io){}
        u.print("conexão fechada.");
    }


    /**--------------------------------------------**/


}
