package com.example.fcs.rx_research;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hanhmai1203 on 11/8/16.
 */

public class ExampleLocalActivity extends AppCompatActivity {
    @BindView(R.id.edArrayInput1)
    EditText edInput1;
    @BindView(R.id.edArrayInput2)
    EditText edInput2;
    @BindView(R.id.edArrayOutputPublish)
    EditText edPublish;
    @BindView(R.id.edArrayOutputBehavior)
    EditText edBehavior;
    @BindView(R.id.edArrayOutputReplay)
    EditText edReplay;
    @BindView(R.id.btn)
    Button btn;
    CompositeSubscription compositeSubscription;
    Observable<List<String>> obListString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_activity);
        ButterKnife.bind(this);
//        PublishSubject.create();
        compositeSubscription = new CompositeSubscription();
        RxView.clicks(btn)
                .subscribe(aVoid -> {

                });
    }

    private void getDataStr1() {
        String text = edInput1.getText().toString();
        List<String> list = Arrays.asList(text.split(","));
        Observable<List<String>> ob = Observable.just(list);
//        ob.flatMap(strings -> {
//
//        })
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
