package com.trairas.nig.peer_to.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.trairas.nig.peer_to.R;
import com.trairas.nig.peer_to.Util.OperArquivos;


public class PeerToPeer extends Fragment {

    EditText ed_1;
    Button bt_1;
    ListView lv_1;
    OperArquivos opr = new OperArquivos();

    String ips = "1234567890.";

    private void AdicionarIP(String ip){

        //valido o ip

        //verifico se ja contem um ip

        //Adiciona no Arquivo

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_peer_to_peer, container, false);
        // Inflate the layout for this fragment

        ed_1 = (EditText) view.findViewById(R.id.pe_ed_1);
        bt_1 = (Button) view.findViewById(R.id.pe_bt_1);
        lv_1 = (ListView) view.findViewById(R.id.pe_lv_1);

        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //Adicionar o IP
                AdicionarIP(ed_1.getText().toString());
            }
        });


        return view;
    }

}
