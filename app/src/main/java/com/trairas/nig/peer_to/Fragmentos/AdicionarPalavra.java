package com.trairas.nig.peer_to.Fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.trairas.nig.peer_to.R;

import com.trairas.nig.peer_to.Util.*;

public class AdicionarPalavra extends Fragment {

    Util u = new Util();
    OperArquivos op = new OperArquivos();

    TextView tv;
    Button bt;
    EditText ed;

    TextView tv_2;
    EditText ed_2;

    String letras = "aáãâbcçdeéẽêfghiíjklmnopqrstuúûũvxyz";
    String letrasM = "AÁÃÂBCÇDEÉẼÊFGHIÍJKLMNOPQRSTUÚÛŨVXYZ";

    public void toast(String message){
        Toast t = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        t.show();
    }

    public AdicionarPalavra() {
        // Required empty public constructor

    }

    private boolean valida_um_carater(char c){

        boolean encontrou = false;

        for(int i=0;i<letras.length();i++){
            if(c == letras.charAt(i)){
                encontrou = true;
            }
        }

        for(int i=0;i<letrasM.length();i++){
            if(c == letrasM.charAt(i)){
                encontrou = true;
            }
        }

        return encontrou;
    }

    private boolean validarCaracters(String entrada){

        boolean ver_palavra = true;

        //verificando se os caracters sao validos

        for(int i=0;i<entrada.length();i++){

             if(!valida_um_carater(entrada.charAt(i))){
                 u.print("entrada invalida.");
                 ver_palavra = false;
             }

        }

        return ver_palavra;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view =  inflater.inflate(R.layout.fragment_adicionar_palavra, container, false);

        tv = (TextView) view.findViewById(R.id.add_p_tv_1);
        tv.setText(R.string.cp_tv_title);
        ed = (EditText) view.findViewById(R.id.add_p_edt_1);
        ed.setText(R.string.cp_tv_palavra);
        bt = (Button) view.findViewById(R.id.add_p_bt_1);
        bt.setText(R.string.cp_bt_add);

        tv_2 = (TextView) view.findViewById(R.id.add_p_tv_2);
        tv_2.setText(R.string.cp_tv_sig);
        ed_2 = (EditText) view.findViewById(R.id.add_p_edt_2);
        ed_2.setText(R.string.cp_ed_sig);



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context c = view.getContext();

                //obter entrada do usuario
                String res = ed.getText().toString();
                String res_sig = ed_2.getText().toString();


                //-----------------Validando Resultados------------------------------------//

                String resultado = "";

                //validar entradas do usurario - ele sempre tem como fazer merda
                if(validarCaracters(res)){
                    resultado = "Palavra Valida";
                }
                else{
                    resultado = "Palavra Inalida";
                }

                toast(resultado);

                u.print(res);
                op.salvar(res+"\n", c);

                //------------verificar se a palavra ja existe no discionario--------------------//

                String[] palavras = op.Todas_palavras(op.ler(c));

                for(int i=0;i< palavras.length;i++){
                    u.print("palavra["+i+"] = "+palavras[i]);

                }

            }
        });


        return (view);
    }

}
