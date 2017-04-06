package com.trairas.nig.peer_to.Util;

import android.content.Context;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


public class client {

        private ObjectOutputStream output; //gera o fluxo de saida para o servidor
        private ObjectInputStream input; //gera o fluxo de entrada a partir do servidor
        private String message = "";//mensagem do servidor
        Util u = new Util();
        private Socket conexao;
        private String comando;




        public client(String ip, String comando){//recebe o valor do socket enviado na thread

            this.comando = comando;

            try {
                conexao = new Socket(ip, 12345);
            }catch (Exception erro){
                u.print("Erro ao insranciar o socket cliente.\n"+erro);
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        processarConexao();
                    }
                    catch (Exception erro){
                     u.print("Errro ao processarConexao = "+erro);
                    }

                }
            }).start();


        }

        private void processarConexao(){
            try{//cria uma printstream para pegar as informacoes enviadas do servidor

                output = new ObjectOutputStream(conexao.getOutputStream());
                output.flush();
                input = new ObjectInputStream(conexao.getInputStream());

                try{//lê e exibe a menssagem
                    u.print("Palavra a ser enviada a aservidor = "+comando);
                    sendData(comando);
                    message = (String) input.readObject();//lê uma nova menssagem
                    u.print("\n"+message);
                }catch(ClassNotFoundException c){
                    u.print("\nUnknowm object type received");
                }

            }catch(IOException e){
                u.print("IOException"+e);
            }finally{
                try {
                    u.print("fechando socket");
                    conexao.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        private void sendData(String message){

            try{
                output.writeObject(message);
                output.flush();//esvazia a saida para o cliente
                u.print("\nMensagem enviada = "+message);
            }catch(IOException io){
                u.print("\nError writing objetc");}

        }


    }

