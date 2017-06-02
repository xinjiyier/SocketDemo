package com.cxp.shopping.socketchatroom;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends Activity implements View.OnClickListener{
    private EditText etLinkIP,etSendText;
    private Button btLink,btSend;
    private TextView tvTalkText;
    private static final String SUCCESS_FLAG = "@Success";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        etLinkIP = (EditText) findViewById(R.id.et_link_ip);
        etSendText = (EditText) findViewById(R.id.et_send_text);

        findViewById(R.id.bt_link).setOnClickListener(this);
        findViewById(R.id.bt_send).setOnClickListener(this);

        tvTalkText = (TextView) findViewById(R.id.tv_talk);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_link:
                link();
                break;
            case R.id.bt_send:
                send();
                break;
        }
    }

    /**
     * 发送输入的消息到服务器
     */
    private void send() {
        try {
            writer.write(etSendText.getText().toString().getBytes("UTF-8")+"\n");
            writer.flush();
            etSendText.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Socket socket = null;
    BufferedReader reader;
    BufferedWriter writer;
    /**
     * 连接到服务器
     */
    private void link(){
        String ip = etLinkIP.getText().toString();
            new AsyncTask<String, String, Void>() {
                @Override
                protected Void doInBackground(String... strings) {
                    try {
                        socket = new Socket(strings[0],12345);
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
                        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
                        publishProgress(SUCCESS_FLAG);
                        String lines = null;
                        while((lines=reader.readLine())!=null){
                            publishProgress(lines);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onProgressUpdate(String... values) {
                    super.onProgressUpdate(values);
                    if(SUCCESS_FLAG.equals(values[0])){
                        Toast.makeText(MainActivity.this,"连接成功！",Toast.LENGTH_SHORT).show();
                    }
                    tvTalkText.append(values[0]);
                }
            }.execute(ip);
    }
}
