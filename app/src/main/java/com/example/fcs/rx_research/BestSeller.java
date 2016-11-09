package com.example.fcs.rx_research;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;

import com.example.fcs.rx_research.adapter.BestSellerAdapter;
import com.example.fcs.rx_research.adapter.DividerItemDecoration;
import com.example.fcs.rx_research.entity.history.BestSellerResults;
import com.example.fcs.rx_research.entity.history.Result;
import com.example.fcs.rx_research.entity.names.NamesResult;
import com.example.fcs.rx_research.webservice.BookService;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

import java.util.List;

import butterknife.BindView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class BestSeller extends AppCompatActivity {
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recView;
    @BindView(R.id.btn)
    Button btnFilter;
    @BindView(R.id.cbCheckNull)
    CheckBox cbCheckNull;
    @BindView(R.id.cbEdit)
    CheckBox cbEdit;
    @BindView(R.id.btnClear)
    Button btnClear;

    BestSellerAdapter mAdapter;
    List<Result> mList;
    Observable<BestSellerResults> bestSeller;
    Observable<NamesResult> namesResult;
    //    private Subscription subscription;
    CompositeSubscription compositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_seller);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        recView = (RecyclerView) findViewById(R.id.recycler_view);
        btnFilter = (Button) findViewById(R.id.btn);
        cbEdit = (CheckBox) findViewById(R.id.cbEdit);
        cbCheckNull = (CheckBox) findViewById(R.id.cbCheckNull);
        btnClear = (Button) findViewById(R.id.btnClear);
        recView.setLayoutManager(new LinearLayoutManager(this));
        compositeSubscription = new CompositeSubscription();
        init();
        getDataNames();

    }

    private void init() {
        compositeSubscription.add(RxView.clicks(btnFilter).subscribe(aVoid -> getData()));
        compositeSubscription.add(RxView.clicks(btnClear).subscribe(aVoid -> {
            mList.clear();
            initView(mList);
        }));

        compositeSubscription.add(RxCompoundButton.checkedChanges(cbCheckNull)
                .subscribe(aBoolean -> filterNull()));
        compositeSubscription.add(RxCompoundButton.checkedChanges(cbEdit)
                .subscribe(aBoolean -> filterNull()));
    }

    private Observable<BestSellerResults> getBestSeller() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.WS)
                .build();
        BookService bookService = retrofit.create(BookService.class);
        return bookService.getBestSeller(Constant.API_KEY);
    }

    private Observable<NamesResult> getNames() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.WS)
                .build();
        BookService bookService = retrofit.create(BookService.class);
        return bookService.getNamese(Constant.API_KEY);
    }

    private void getDataNames() {
        namesResult = getNames();
        Subscription subscription = namesResult.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(namesResult1 -> {
                            Log.e("subscribe", "onNext");
                            Log.i("result",namesResult1.toString());
                        }, throwable -> {

                        },
                        () -> {
                            Log.e("subscribe", "OnComplete");
                        });
        compositeSubscription.add(subscription);
    }

    private void getData() {
        bestSeller = getBestSeller();
        Subscription subscription = bestSeller
                .doOnSubscribe(() -> {
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bestSellerResults -> {
                            Log.e("subscribe", "onNext");
                            initView(bestSellerResults.getResults());
                        },
                        throwable -> {
                            Log.e("subscribe", "onError " + throwable.getMessage());
                        },
                        () -> {
                            Log.e("subscribe", "OnComplete");
                        });
        compositeSubscription.add(subscription);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
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
                .subscribe(results -> {
                            mAdapter.setItems(results);
                            mAdapter.notifyDataSetChanged();
                        },
                        throwable -> {
                            Log.e("subscribe", "OnError " + throwable.getLocalizedMessage());
                        })
        ;
    }


}
