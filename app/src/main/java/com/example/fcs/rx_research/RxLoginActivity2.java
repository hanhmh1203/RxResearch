package com.example.fcs.rx_research;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import rx.Observable;


/**
 * Created by FCS on 10/28/16.
 */

public class RxLoginActivity2 extends AppCompatActivity {
    AutoCompleteTextView edUserName;
    EditText edPass;
    Button btnSignin;
    TextView tvText;
    String TAG = "RxLoginActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        edUserName = (AutoCompleteTextView) findViewById(R.id.email);
        edPass = (EditText) findViewById(R.id.password);
        btnSignin = (Button) findViewById(R.id.email_sign_in_button);
        tvText = (TextView) findViewById(R.id.tvEmail);

    }

    private void initRxLoginForm() {
//        Observable<>
//        Observable<> userNameText =
//                WidgetObservable.text((EditText) findViewById(R.id.edtUserName));

    }

}
