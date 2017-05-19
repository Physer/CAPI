package com.valtech.amsterdam.valtechapipoc.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LoadListCommand<Product> mTask;

    private ProgressBar mProgressBar;
    private ListView mListView;
    private TextView mTextViewError;

    private boolean mHasLoadedApi;

    private static final String mProductsKey = "PRODUCT_KEY";
    private static final String mLogKey = "MAIN_ACTIVITY";

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
        mListView = (ListView)findViewById(R.id.listview_main);
        mTextViewError = (TextView)findViewById(R.id.textview_error);

        if(savedInstanceState != null) {
            List<Product> products = (List<Product>) savedInstanceState.get(mProductsKey);
            onLoadComplete(products);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        Log.d(mLogKey, "Initiating onStart method");
        super.onStart();

        if(mListView.getAdapter() == null) {
            Log.d(mLogKey, "No adapter found, calling async task");
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(mLogKey, "Initiating onSave State");
        super.onSaveInstanceState(outState);

        ProductAdapter productAdapter = (ProductAdapter)mListView.getAdapter();
        if(productAdapter != null) {
            Log.d(mLogKey, "Found product adapter, saving products to bundle");
            List<Product> products = productAdapter.getProducts();
            outState.putSerializable(mProductsKey, (Serializable)products);
        }
    }

    public void onLoadComplete(List<Product> result) {
        mTask = null;
        mProgressBar.setVisibility(View.GONE);
        ProductAdapter adapter = new ProductAdapter(this, result);
        mListView.setAdapter(adapter);
        mListView.setVisibility(View.VISIBLE);
    }

    private void onLoadError() {
        mTask = null;
        mProgressBar.setVisibility(View.GONE);
        mTextViewError.setVisibility(View.VISIBLE);
    }
}
