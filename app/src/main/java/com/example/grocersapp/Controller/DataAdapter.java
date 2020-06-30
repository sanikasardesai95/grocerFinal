package com.example.grocersapp.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocersapp.R;
import com.example.grocersapp.Response.CategoriesResponse;
import com.example.grocersapp.fragments.HomeFragment;
import com.example.grocersapp.interfaces.OnOrderDetailRecyclerViewItemClickListener;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private Context mCtx;
    private ArrayList<CategoriesResponse.Categories> categoryList;
    RecyclerViewItemClickListener recyclerViewItemClickListener;

    public DataAdapter(Context mCtx, ArrayList<CategoriesResponse.Categories> categoryList) {
        this.mCtx = mCtx;
        this.categoryList = categoryList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popup_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CategoriesResponse.Categories user = categoryList.get(position);

        holder.textViewName.setText(user.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public interface RecyclerViewItemClickListener {
        void clickOnItem(CategoriesResponse.Categories categories);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName;
        RecyclerViewItemClickListener recyclerViewItemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.cat_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        recyclerViewItemClickListener.clickOnItem(categoryList.get(this.getAdapterPosition()));
        }

    }
}
