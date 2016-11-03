package com.example.fcs.rx_research;

import android.database.Observable;
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
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

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
        initRxForButton();
    }

    private void initRxForButton() {
        Subscription subscription = RxView.clicks(btnSignin).subscribe(
                (view) -> {
//                     abc

                });
        rx.Observable<String> myObservable = rx.Observable.create(new rx.Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello moto");
                subscriber.onCompleted();
            }
        })
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i));
        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onnext " + s);
            }
        };
        myObservable.subscribe(mySubscriber);
    }

    private void initRxLoginForm() {
        rx.Observable<CharSequence> emailChangeObservable = RxTextView.textChanges(edUserName);
        rx.Observable<CharSequence> passwordChangeObservable = RxTextView.textChanges(edPass);
        btnSignin.setEnabled(false);


    }

    private void initRx1() {
        Subscription subscription = RxView.clicks(btnSignin)
                .subscribe(new Action1<Void>() {
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
//                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {

                        return charSequence.length() > 6;
                    }

                })
                .map(new Func1<CharSequence, String>() {
                    @Override
                    public String call(CharSequence charSequence) {
                        tvText.setText(new StringBuilder(charSequence).reverse().toString());
                        return (new StringBuilder(charSequence).reverse().toString()) + "- abcd ";
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
                        tvText.setText(charSequence);
                    }
                });
        Subscription subscriptionPass = RxTextView.textChanges(edPass).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                Log.i(TAG, "Pass " + charSequence.toString());
            }
        });

//        RxView.clicks(btnSignin)
//                .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//                .filter(integer -> integer % 2 == 0)
//                .subscribe(integer -> Log.d("String integer ", integer.toString()));
    }

    private void showLog() {

    }
}
