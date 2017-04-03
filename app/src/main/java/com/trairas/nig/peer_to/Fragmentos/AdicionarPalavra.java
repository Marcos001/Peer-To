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


    public AdicionarPalavra() {
        // Required empty public constructor

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
                String res = "DIGITADO = "+ed.getText().toString();
                u.print(res);
                op.salvar(res+"\n", c);

            }
        });


        return (view);
    }

}
