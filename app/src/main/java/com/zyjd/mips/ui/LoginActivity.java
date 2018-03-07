package com.zyjd.mips.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zyjd.mips.R;
import com.zyjd.mips.retrofit.Client;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Client.getApiService().getProgram()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
