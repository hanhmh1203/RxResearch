package com.example.fcs.rx_research;

import android.icu.util.TimeUnit;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxAutoCompleteTextView;
import com.jakewharton.rxbinding.widget.RxTextSwitcher;
import com.jakewharton.rxbinding.widget.RxTextView;

import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by FCS on 10/28/16.
 */

public class RxLoginActivity extends AppCompatActivity {
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
        Subscription subscription = RxView.clicks(btnSignin).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Log.i(TAG, "Email " + edUserName.getText().toString());
                Log.i(TAG, "Pass " + edPass.getText().toString());
                tvText.setText(edUserName.getText().toString());
            }
        });
        Subscription subscriptionButtonLongClick = RxView.longClicks(btnSignin).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Log.i(TAG, "Email longpress " + edUserName.getText().toString());
                Log.i(TAG, "Pass longpress " + edPass.getText().toString());
            }
        });
        Subscription subscriptionEmail = RxTextView.textChanges(edUserName)

//                .debounce(20000, java.util.concurrent.TimeUnit.MICROSECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {

                        return charSequence.length() > 6;
                    }
                })
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        Log.i(TAG, "Email " + charSequence.toString());
                        if (charSequence.length() <= 6) {
                            edUserName.setTextColor(RxLoginActivity.this.getResources().getColor(R.color.colorBlue));
                        } else {
                            edUserName.setTextColor(RxLoginActivity.this.getResources().getColor(android.R.color.black));
                        }
                    }
                });
        Subscription subscriptionPass = RxTextView.textChanges(edPass).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                Log.i(TAG, "Pass " + charSequence.toString());
            }
        });
    }
}
