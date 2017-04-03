package com.trairas.nig.peer_to.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trairas.nig.peer_to.R;

import com.trairas.nig.peer_to.Util.*;

public class MinhasPavras extends Fragment {

    TextView text;
    OperArquivos opr;


    public MinhasPavras() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_minhas_pavras, container, false);

        opr = new OperArquivos();

        text = (TextView) view.findViewById(R.id.text_mv);

        Context c = view.getContext();

        text.setText(opr.ler(c));
        

        // Inflate the layout for this fragment
        return view;
    }


}
