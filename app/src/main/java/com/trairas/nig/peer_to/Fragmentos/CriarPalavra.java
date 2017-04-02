package com.trairas.nig.peer_to.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trairas.nig.peer_to.R;
import com.trairas.nig.peer_to.Util.Util;


public class CriarPalavra extends Fragment {

    Util ut = new Util();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_criar_palavra, container, false);
    }



}
