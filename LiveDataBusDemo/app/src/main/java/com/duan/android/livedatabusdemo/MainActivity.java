package com.duan.android.livedatabusdemo;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_send_message:
                break;
            case R.id.bt_send_message_sub_thread:
                break;
            case R.id.bt_send_message_new_page:
                break;
        }

    }
}
