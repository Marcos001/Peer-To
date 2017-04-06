package com.trairas.nig.peer_to.Util;

/**
 * Created by nig on 05/04/17.
 */
import android.content.Context;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    private ObjectOutputStream output; //gera o fluxo de saida para o cliente
    private ObjectInputStream input; // gera o fluxo de entrada a apartir do cliente
    private OperArquivos opr;
    private Util u = new Util();
    Context c;

    public void rodarServidor(Context ct){
        ServerSocket s = null;
        opr = new OperArquivos();


        try{
            s = new ServerSocket(12345);
            u.print("criado socket servidor");
            while(true){
                Socket conexao = s.accept();
                u.print("socket aceito");
                Thread t = new Server(conexao);
                t.start();
                u.print("thread iniciada");
            }
        }catch(IOException e){
            u.print("IOException "+e);
        }
    }



        private Socket conexao;

        public Server(){}

        public Server(Socket s){//recebe o valor do socket enviado na thread
            conexao = s;
        }

    private void sendData(String message){

        try{
            output.writeObject(message);
            output.flush();
            u.print("\nSERVER>> "+message);
        }catch(IOException io){
            u.print("\nError writing objetc");}
        }

    private String pesquisarPalavra(String palavra){

        meuApp mp =  new meuApp();

        String[] palavras = opr.Todas_palavras(opr.ler(mp.getBaseContext(), "words.wd"));

        boolean found =  false;

        for(int i=0;i<palavras.length;i++){
            u.print(palavra+" <> "+palavras[i]);
            if (palavra.equals(palavras[i])){
                found = true;
            }
        }

        if (found){
            return "SIM";
        }
            return "NAO";
    }


        public void run(){

            try{

               u.print("metodo run");

                //configura o fluxo de saida de dados
                output = new ObjectOutputStream(conexao.getOutputStream());
                //configura o fluxo de entrada de dados
                input = new ObjectInputStream(conexao.getInputStream());

                try{

                    String message = (String) input.readObject();//lê uma nova menssagem

                    u.print("mensagem lida do cliente"+message);

                    u.print("A palavra contem "+message+" ? = "+pesquisarPalavra(message));

                }catch (Exception erro){
                    u.print("erro ao obter fluxo de dado do cliente");
                }


            }catch(IOException e){
                u.print("IOException "+e);
            }
        }
    }

