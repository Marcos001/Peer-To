package com.trairas.nig.peer_to.Fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.trairas.nig.peer_to.R;
import com.trairas.nig.peer_to.Util.Cliente;
import com.trairas.nig.peer_to.Util.Servidor;


public class PesquisarPalavra extends Fragment {


    Servidor servidor;

    TextView tv_1;
    TextView tv_2;

    EditText ed_1;
    EditText ed_2;

    Button bt_1;

    Cliente cliente;




    public PesquisarPalavra() {
        // Required empty public constructor
    }

    private void pesquidarPalavra(String palavra){

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pesquisar_palavra, container, false);
        // Inflate the layout for this fragment

        tv_1 = (TextView) view.findViewById(R.id.pt_tv_1);
        tv_1.setText(R.string.pt_tv_1);

        tv_2 = (TextView) view.findViewById(R.id.pt_tv_2);
        tv_2.setText(R.string.pt_tv_2);

        ed_1 = (EditText) view.findViewById(R.id.pt_ed_1);
        ed_1.setText(R.string.pt_ed_1);


        bt_1 = (Button) view.findViewById(R.id.pt_bt_1);
        bt_1.setText(R.string.pt_bt_1);

        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             // cliente.sendData(ed_2.getText().toString());
            }
        });

        return view;
    }
}
