package com.valtech.amsterdam.valtechapipoc.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.valtech.amsterdam.valtechapipoc.R;
import com.valtech.amsterdam.valtechapipoc.model.Product;
import com.valtech.amsterdam.valtechapipoc.platform.StringResourcePreferenceManager;
import com.valtech.amsterdam.valtechapipoc.service.AsyncCommandExecutor;
import com.valtech.amsterdam.valtechapipoc.service.LoadListCommand;
import com.valtech.amsterdam.valtechapipoc.service.TaskListener;
import com.valtech.amsterdam.valtechapipoc.service.loader.implementation.network.BufferedStreamContentReader;
import com.valtech.amsterdam.valtechapipoc.service.loader.implementation.network.GsonDesynchronizer;
import com.valtech.amsterdam.valtechapipoc.service.loader.implementation.network.NetworkModelLoader;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LoadListCommand<Product> mTask;

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private TextView mTextViewError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mProgressBar = (ProgressBar)findViewById(R.id.progressbar_loading);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_main);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mTextViewError = (TextView)findViewById(R.id.textview_error);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        mTask = new LoadListCommand<>(new NetworkModelLoader<>(new BufferedStreamContentReader(), new GsonDesynchronizer<>(Product.class), new StringResourcePreferenceManager(this), Product.class));

        AsyncCommandExecutor<List<Product>> productsExecutor = new AsyncCommandExecutor<>(new TaskListener<List<Product>>() {
            @Override
            public void onComplete(List<Product> products) {
                onLoadComplete(products);
            }

            @Override
            public void onError(Exception exception) {
                onLoadError();
            }
        });

        productsExecutor.execute(mTask);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onLoadComplete(List<Product> result) {
        mTask = null;
        mProgressBar.setVisibility(View.GONE);
        ProductAdapter adapter = new ProductAdapter(result, this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void onLoadError() {
        mTask = null;
        mProgressBar.setVisibility(View.GONE);
        mTextViewError.setVisibility(View.VISIBLE);
    }
}
