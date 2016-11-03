package com.example.fcs.rx_research;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.fcs.rx_research.adapter.BookAdapter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookActivity extends AppCompatActivity {
    //    @BindView(R.id.recycler_view)
    RecyclerView recView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    List<String> mArray;
    BookAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ButterKnife.bind(this);
        recView = (RecyclerView) findViewById(R.id.recycler_view);
        recView.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(toolbar);

        initData();
    }

    private void initData() {
        mArray = Arrays.asList(getResources().getStringArray(R.array.book_api));
        mAdapter = new BookAdapter(mArray, this);
        recView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, String item) {
                startActivity(new Intent(BookActivity.this, BestSeller.class));
            }
        });
    }
}
