package com.trairas.nig.peer_to.Fragmentos;

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
import com.trairas.nig.peer_to.Util.OperArquivos;
import com.trairas.nig.peer_to.Util.Util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class PesquisarPalavra extends Fragment {

    TextView tv_1;
    TextView tv_2;

    EditText ed_1;

    Button bt_1;


    OperArquivos opr;
    Util u;




    public PesquisarPalavra() {
        // Required empty public constructor
        opr = new OperArquivos();
        u = new Util();
    }

    public void toask(String m){
        Toast.makeText(getContext(), m, Toast.LENGTH_SHORT);
    }

    private void pesquisar_peer_por_peer(final String palavra, final String ip){



        try{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new client(ip, palavra);
                }
            }).start();
        }
        catch (Exception erro){
            u.print("Erro Cliente nao disponível - "+ip+" \n"+erro);
        }


    }

    private void pesquisarPalavra(String busca){

        String[] palavras = opr.Todas_palavras(opr.ler(getContext(), "lista_ip.txt"));

        for(int i=0;i<palavras.length;i++){
             u.print(palavras[i]);
            pesquisar_peer_por_peer(busca, palavras[i]);
        }

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
        ed_1.setText("");


        bt_1 = (Button) view.findViewById(R.id.pt_bt_1);
        bt_1.setText(R.string.pt_bt_1);

        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesquisarPalavra(ed_1.getText().toString());
            }
        });

        return view;
    }


    public class client{

        private ObjectOutputStream output; //gera o fluxo de saida para o servidor
        private ObjectInputStream input; //gera o fluxo de entrada a partir do servidor
        private String message = "";//mensagem do servidor
        Util u = new Util();
        private Socket conexao;
        private String comando;



        public client(final String ip, String comando){//recebe o valor do socket enviado na thread

            this.comando = comando;

            try {
                conexao = new Socket(ip, 12345);
            }catch (Exception erro){
                u.print("Erro ao insranciar o socket cliente.\n"+erro);
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        processarConexao(ip);
                    }
                    catch (Exception erro){
                        u.print("Errro ao processarConexao = "+erro);
                    }

                }
            }).start();


        }

    private void processarConexao(String ip){
        try{//cria uma printstream para pegar as informacoes enviadas do servidor

            output = new ObjectOutputStream(conexao.getOutputStream());
            output.flush();
            input = new ObjectInputStream(conexao.getInputStream());

            try{//lê e exibe a menssagem
                u.print("Palavra a ser enviada a aservidor = "+comando);
                sendData(comando);
                message = (String) input.readObject();//lê uma nova menssagem
                u.print("\n"+message);
                tv_2.setText("\n"+message);

            }catch(ClassNotFoundException c){
                u.print("\nUnknowm object type received");
            }

        }catch(IOException e){
            u.print("IOException"+e);
        }finally{
            try {
                u.print("fechando socket");
                conexao.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void sendData(String message){

        try{
            output.writeObject(message);
            output.flush();//esvazia a saida para o cliente
            u.print("\nMensagem enviada = "+message);
        }catch(IOException io){
            u.print("\nError writing objetc");}

    }
    }

}
