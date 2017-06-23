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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.valtech.amsterdam.valtechapipoc.R;
import com.valtech.amsterdam.valtechapipoc.model.Product;
import com.jaspervz.www.recyclist.Recyclist;
import com.jaspervz.www.recyclist.Recyclistener;
import com.valtech.amsterdam.valtechapipoc.platform.injection.DaggerInjectionComponent;
import com.valtech.amsterdam.valtechapipoc.platform.injection.InjectionComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements Recyclistener {
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private TextView mTextViewError;

    @Inject
    Recyclist<Product> recyclist;

    private InjectionComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        component = DaggerInjectionComponent
                .builder()
                .build();
        component.inject(this); //This makes the members injected

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
        recyclist.startBind(this, getString(R.string.api_base_location), Product.class, new ProductViewBinder(this), R.layout.product, mRecyclerView);
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
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showResults() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideResults() {
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        mTextViewError.setText(message);
        mTextViewError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        mTextViewError.setVisibility(View.GONE);
    }
}

