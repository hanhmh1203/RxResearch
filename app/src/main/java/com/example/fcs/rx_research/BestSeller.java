package com.example.fcs.rx_research;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;

import com.example.fcs.rx_research.adapter.BestSellerAdapter;
import com.example.fcs.rx_research.adapter.BookAdapter;
import com.example.fcs.rx_research.adapter.DividerItemDecoration;
import com.example.fcs.rx_research.entity.BestSellerResults;
import com.example.fcs.rx_research.entity.Result;
import com.example.fcs.rx_research.webservice.BookService;
import com.example.fcs.rx_research.webservice.ToStringConverterFactory;
import com.google.gson.Gson;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class BestSeller extends AppCompatActivity {
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recView;
    @BindView(R.id.btn)
    Button btnFilter;
    @BindView(R.id.cbCheckNull)
    CheckBox cbCheckNull;
    @BindView(R.id.cbSort)
    CheckBox cbSort;
    @BindView(R.id.cbEdit)
    CheckBox cbEdit;

    BestSellerAdapter mAdapter;
    List<Result> mList;
    Observable<BestSellerResults> bestSeller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_seller);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        recView = (RecyclerView) findViewById(R.id.recycler_view);
        btnFilter = (Button) findViewById(R.id.btn);
        cbEdit = (CheckBox) findViewById(R.id.cbEdit);
        cbCheckNull = (CheckBox) findViewById(R.id.cbCheckNull);
        cbSort = (CheckBox) findViewById(R.id.cbSort);

        recView.setLayoutManager(new LinearLayoutManager(this));
        init();
        getData();

    }

    private void init() {
        btnFilter.setOnClickListener(v -> filterNull());
        RxCompoundButton.checkedChanges(cbCheckNull)
                .subscribe(aBoolean -> filterNull());
        RxCompoundButton.checkedChanges(cbEdit)
                .subscribe(aBoolean -> filterNull());
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.WS)
                .build();
        BookService bookService = retrofit.create(BookService.class);
        bestSeller = bookService.getBestSeller(Constant.API_KEY);
        bestSeller
                .doOnSubscribe(() -> {
                    progressBar.setVisibility(View.VISIBLE);
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bestSellerResults -> {
                            Log.i("subscribe", "onNext ");
                            initView(bestSellerResults.getResults());
                        },
                        throwable -> {
                            Log.e("subscribe", "onError " + throwable.getMessage());
                        },
                        () -> {
                            progressBar.setVisibility(View.GONE);
                            Log.i("subscribe", "onComplete ");
                        });

    }

    private void initView(List<Result> list) {
        mList = list;
        mAdapter = new BestSellerAdapter(list, this);
        recView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recView.setAdapter(mAdapter);
    }

    private void filterNull() {
        Observable<List<Result>> obserFilter = Observable.just(mList);
        obserFilter
                .debounce(500, TimeUnit.MILLISECONDS)
                .flatMap(
                        results ->
                                Observable.from(results)
                                        .filter(result -> {
                                            if (cbCheckNull.isChecked()) {
                                                if (result.getDescription() == null || result.getDescription().isEmpty()) {
                                                    return false;
                                                }
                                                return true;
                                            }
                                            return true;
                                        })
                                        .map(result -> {
                                            if (cbEdit.isChecked()) {
                                                result.setAuthor(result.getAuthor() + " -N- ");
                                            }
                                            return result;
                                        })
                                        .toList()
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> {
                    progressBar.setVisibility(View.VISIBLE);
                })
                .subscribe(results -> {
                            mAdapter.setItems(results);
                            mAdapter.notifyDataSetChanged();
                        },
                        throwable -> {
                            Log.e("filterNull", throwable.getLocalizedMessage());
                        },
                        () -> {
                            progressBar.setVisibility(View.GONE);
                        })
        ;
    }


}
