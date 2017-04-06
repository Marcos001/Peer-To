package com.trairas.nig.peer_to.Util;

import android.content.Context;

/**
 * Created by nig on 06/04/17.
 */

public class meuApp extends android.app.Application {


    private static meuApp instance;

    public meuApp(){
        instance = this;
    }

    public  static Context getContext(){
        return instance;
    }

}
