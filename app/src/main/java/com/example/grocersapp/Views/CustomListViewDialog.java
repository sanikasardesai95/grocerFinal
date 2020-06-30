package com.example.grocersapp.Views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocersapp.R;

public class CustomListViewDialog extends Dialog implements View.OnClickListener {

    public CustomListViewDialog(@NonNull Context context) {
        super(context);
    }

    public CustomListViewDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CustomListViewDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public Context activity;
    public Dialog dialog;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    public CustomListViewDialog(Context activity, RecyclerView.Adapter adapter){
        super(activity);
        this.activity=activity;
        this.adapter=adapter;
        setupLAyout();
    }

    private void setupLAyout() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        recyclerView=findViewById(R.id.custom_recycle);
        layoutManager=new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {


    }
}
