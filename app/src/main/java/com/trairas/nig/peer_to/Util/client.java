package com.trairas.nig.peer_to.Util;

import java.io.*;
import java.net.Socket;


    public class client extends Thread {

        private ObjectOutputStream output; //gera o fluxo de saida para o servidor
        private ObjectInputStream input; //gera o fluxo de entrada a partir do servidor
        private String message = "";//mensagem do servidor
        private String palavra_consulta;
        private Util u;


        public void rodarCliente(String ip, String palavra){
            this.palavra_consulta = palavra;

            try{
                // Cria um Socket conexao passando como parâmetro o ip e a porta do servidor
                Socket conexao = new Socket(ip,12345);
                //cria uma thread que envia a conexao
                Thread t = new client(conexao);
                //inicia o thread
                t.start();
            }catch(IOException e){
                u.print("IOException"+e);
            }
        }

        public client(){
            u = new Util();
        }

        private Socket conexao;
        public client(Socket s){//recebe o valor do socket enviado na thread
            conexao = s;
        }

        private void sendData(String message){


            try{
                output.writeObject(message);
                output.flush();//esvazia a saida para o cliente
                u.print("\nCLIENTE>>"+message);
            }catch(IOException io){
                u.print("\nError writing objetc");}

        }

        public void run(){
            try{//cria uma printstream para pegar as informacoes enviadas do servidor

                output = new ObjectOutputStream(conexao.getOutputStream());
                output.flush();
                input = new ObjectInputStream(conexao.getInputStream());

                try{//lê e exibe a menssagem
                    sendData(palavra_consulta);
                    message = (String) input.readObject();//lê uma nova menssagem
                    u.print("\n"+message);
                }catch(ClassNotFoundException c){
                    u.print("\nUnknowm object type received");
                }

            }catch(IOException e){
                u.print("IOException"+e);
            }finally{
                try {
                    conexao.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

