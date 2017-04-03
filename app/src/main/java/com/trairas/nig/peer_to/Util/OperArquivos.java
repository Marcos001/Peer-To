package com.trairas.nig.peer_to.Util;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


import static android.content.Context.MODE_APPEND;

/**
 * Created by nig on 03/04/17.
 */

public class OperArquivos {

    Util u = new Util();
    final String FILE = "words.wd";

    public OperArquivos(){}

    public String ler(Context contexto){

        try{
            File arquivo_lido = contexto.getFileStreamPath(FILE);

            if (arquivo_lido.exists()){
                u.print("Arquivo existe");
                FileInputStream arquivo_ler = contexto.openFileInput(FILE);
                int tamanho =  arquivo_ler.available();
                byte dadosBytesLidos[] = new byte[tamanho];
                arquivo_ler.read(dadosBytesLidos);
                String dadosLidos = new String(dadosBytesLidos);
                u.print("Dados LIdos >\n"+dadosLidos);
            return dadosLidos;
            }
            else{
                u.print("Arquivo nao existe.");
            }

        }catch (Exception erro){

        }
    return "NAO DEU";
    }

    public void salvar(String palavra, Context ctx){

        if (ctx == null){
            u.print("Contexto nulo.");
            return;
        }

        u.print("word = "+palavra);

        try{
            
            FileOutputStream arquivo = ctx.openFileOutput(FILE, MODE_APPEND);
            arquivo.write(palavra.getBytes());
            arquivo.close();
            u.print("Arquivo Gravado");
        }

        catch (FileNotFoundException erro){
            u.print("Arquivo nao encontrado");
        }

        catch (Exception e) {
            u.print("Erro ao Gravar");
        }


    }


}

