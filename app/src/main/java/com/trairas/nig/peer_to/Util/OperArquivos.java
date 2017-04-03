package com.trairas.nig.peer_to.Util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.trairas.nig.peer_to.Util.*;

/**
 * Created by nig on 03/04/17.
 */

public class OperArquivos {

    Util u = new Util();
    Context ctx;

    public OperArquivos(){}

    public void salvar(String palavra){

        FileOutputStream fos;

        try{

            fos = ctx.openFileOutput("/data/data/com.trairas.nig.peer_to/data/words.wd", Context.MODE_APPEND);
            fos.write(palavra.getBytes());
            fos.flush();
            fos.close();
            u.print("Deu certo");

        } catch (Exception e) {
            u.print("Falha");
        }


    }
    }


