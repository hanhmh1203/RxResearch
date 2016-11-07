package com.example.fcs.rx_research;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.fcs.rx_research.entity.BestSellerResults;
import com.example.fcs.rx_research.webservice.BookService;
import com.example.fcs.rx_research.webservice.ToStringConverterFactory;
import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BestSeller extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_seller);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        getData();
//        getDatatoString();
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.WS)
                .build();
        BookService bookService = retrofit.create(BookService.class);
        Observable<BestSellerResults> bestSeller = bookService.getBestSeller(Constant.API_KEY);
        bestSeller
                .doOnSubscribe(() -> {
                    progressBar.setVisibility(View.VISIBLE);
                })
                .doOnCompleted(() -> {

                })
                .doOnError(throwable -> {
                    Log.e("doOnError", throwable.getMessage());
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bestSellerResults -> {
                    Log.i("obBestSeller", bestSellerResults.getResults().get(0).getTitle().toString());
                    progressBar.setVisibility(View.GONE);
                });

    }

    private void getDatatoString() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(Constant.WS)
//                .build();
//        Retrofit retrofit = new Retrofit.Builder()
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(new ToStringConverterFactory())
//                .baseUrl(Constant.WS)
//                .build();
//        BookService bookService = retrofit.create(BookService.class);
//        Observable<String> bestSeller = bookService.getBestSeller(Constant.API_KEY);
//        bestSeller.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(bestSellerResults -> {
////                    Log.i("obBestSeller", bestSellerResults.getResults().get(0).getTitle().toString());
////                }
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.i("subscribe", "onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i("subscribe", "onError" + e.getLocalizedMessage());
//                    }
//
//                    @Override
//                    public void onNext(String bestSellerResults) {
//                    }
//                });

    }

    private void testRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.example.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

}
