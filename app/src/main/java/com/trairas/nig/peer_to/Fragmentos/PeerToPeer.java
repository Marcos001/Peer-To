package com.trairas.nig.peer_to.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.trairas.nig.peer_to.R;
import com.trairas.nig.peer_to.Util.OperArquivos;
import com.trairas.nig.peer_to.Util.Util;

import java.util.ArrayList;


public class PeerToPeer extends Fragment {


    EditText ed_1;
    Button bt_1;
    ListView lv_1;
    OperArquivos opr;
    Util u;
    ArrayAdapter<String> array_adapter;

    String ips = "1234567890.";
    Context c;



    public PeerToPeer(){
         opr = new OperArquivos();
         u = new Util();
    }

    public void toast(String message){
        Toast t = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        t.show();
    }

    private void AdicionarIP(String ip, Context c){

        //valido o ip
        if(u.validarCaracters(ip, ips)){

            String resultado_2 = "";

            if(u.verificar_se_ja_tem(ip,c, "lista_ip.txt")){
                resultado_2 = "A palavra "+ip+" já contem!";
            }
            else{
                resultado_2 = "A palavra "+ip+" foi Salva!";
                opr.salvar(ip+"\n", c, "lista_ip.txt");
            }

            toast(resultado_2);
        }

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

    private void recarregarListaView(View view){

        String[] palavras = opr.Todas_palavras(opr.ler(getContext(), "lista_ip.txt"));

        /**-----------------------------------------------------------*/

        lv_1 = (ListView) view.findViewById(R.id.pe_lv_1);
        final ArrayList <String> pal = preencherDados(palavras);
        array_adapter =  new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1, pal);
        lv_1.setAdapter(array_adapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_peer_to_peer, container, false);
        // Inflate the layout for this fragment

        this.c = getContext();

        if (c==null){
            u.print("Contexto nulo");
        }


        ed_1 = (EditText) view.findViewById(R.id.pe_ed_1);
        bt_1 = (Button) view.findViewById(R.id.pe_bt_1);
        lv_1 = (ListView) view.findViewById(R.id.pe_lv_1);

        recarregarListaView(view);


        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //Adicionar o IP
                AdicionarIP(ed_1.getText().toString(), c);
                recarregarListaView(view);
            }
        });


        return view;
    }

}
