package com.trairas.nig.peer_to.Fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.trairas.nig.peer_to.R;
import com.trairas.nig.peer_to.Util.*;

import java.util.ArrayList;


public class MinhasPavras extends Fragment {

    TextView text;
    ListView lista_lv;
    OperArquivos opr;
    Util u = new Util();
    ArrayAdapter<String> array_adapter;



    public MinhasPavras() {
    }


    private ArrayList<String> preencherDados(String[] dados_vetor){

        ArrayList <String> dados = new ArrayList<>();

        if(dados_vetor.length == 0){
            dados.add("Palavra");
        }
        else{
            for(int i=0;i<dados_vetor.length;i++){
                dados.add(dados_vetor[i]);
            }
        }

        return dados;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_minhas_pavras, container, false);
        Context c = view.getContext();
        opr = new OperArquivos();

        /**-----------Formatação de Strings do Arquivo------------*/


        String[] palavras = opr.Todas_palavras(opr.ler(c, "words.wd"));


        /**-----------------------------------------------------------*/



        lista_lv = (ListView) view.findViewById(R.id.lista_lv);
        final ArrayList <String> pal = preencherDados(palavras);
        array_adapter =  new ArrayAdapter <String> (c, android.R.layout.simple_list_item_1, pal);
        lista_lv.setAdapter(array_adapter);



        text = (TextView) view.findViewById(R.id.title_palavras);
        text.setText(R.string.cp_tv_palavras);




        return view;
    }


}
