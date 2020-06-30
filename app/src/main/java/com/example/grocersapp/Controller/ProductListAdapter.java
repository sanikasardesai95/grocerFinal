package com.example.grocersapp.Controller;

import android.content.Context;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.grocersapp.Model.CartData;
import com.example.grocersapp.R;
import com.example.grocersapp.Response.GetProductResponse;
import com.example.grocersapp.database.DatabaseClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.sql.CommonDataSource;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {

    private ArrayList<GetProductResponse.GetProducts> getProductsList;
    Context context;
    String prodName,prodPrice;
    String dupliparam;
    List<CartData> taskList;


    public ProductListAdapter(ArrayList<GetProductResponse.GetProducts> getProductsList, Context context) {
        this.getProductsList = getProductsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productlist_item, parent, false);
        return new ProductListAdapter.ProductListViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        GetProductResponse.GetProducts getProductResponse=getProductsList.get(position);

        holder.prodPrice.setText(getProductResponse.getPrice()+"");
        holder.prodName.setText(getProductResponse.getName());

        Log.d("imgurl",getProductResponse.getImgurl());
        Picasso.get().load(getProductResponse.getImgurl()).fit().into(holder.prodimg);


        prodName = getProductResponse.getName();
        prodPrice =  String.valueOf(getProductResponse.getPrice());



        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prodName = getProductResponse.getName();
                prodPrice = String.valueOf(getProductResponse.getPrice());
                dupliparam = prodName;
                checkDuplicateItem(dupliparam);
            }
        });



    }

    @Override
    public int getItemCount() {
        return getProductsList.size();
    }

    class ProductListViewHolder extends RecyclerView.ViewHolder{

        TextView prodName,prodPrice;
        ImageView btnSave,btn_fav,prodimg;
        public ProductListViewHolder(@NonNull
                                             View itemView) {
            super(itemView);

            prodName=itemView.findViewById(R.id.prod_name);
            prodPrice=itemView.findViewById(R.id.prod_price);
            btnSave=itemView.findViewById(R.id.btn_add);
            btn_fav=itemView.findViewById(R.id.favourites);
            prodimg=itemView.findViewById(R.id.prod_img);
        }
    }

    public void updateList(ArrayList<GetProductResponse.GetProducts> getProductsList){
        this.getProductsList=new ArrayList<>();
        this.getProductsList.addAll(getProductsList);
        notifyDataSetChanged();
    }

    private void saveTask() {

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                CartData task = new CartData();
                task.setName(prodName);
                task.setPrice(prodPrice);
                Log.d("PRODPRICE", prodPrice);
                task.setQty("1");
                DatabaseClient.getInstance(context).getAppDatabase()
                        .taskDao()
                        .insert(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context, "Added to cart", Toast.LENGTH_LONG).show();
            }
        }
        SaveTask s = new SaveTask();
        s.execute();


    }

    private void checkDuplicateItem(final String dupliparam2) {

        class CheckDuplicate extends AsyncTask<Void, Void, List<CartData>> {

            @Override
            protected List<CartData> doInBackground(Void... voids) {
                taskList = DatabaseClient
                        .getInstance(context)
                        .getAppDatabase()
                        .taskDao()
                        .checkDuplicate(dupliparam2);

                return taskList;
            }

            @Override
            protected void onPostExecute(List<CartData> tasks) {
                super.onPostExecute(tasks);
                taskList = tasks;
                Log.d("SIZELIST", tasks.size()+"");
                if(taskList.size() == 0)
                {
                    saveTask();

                }
                else
                {
                    Toast.makeText(context,"Product is already present in cart",Toast.LENGTH_LONG).show();
                }

            }
        }
        CheckDuplicate s = new CheckDuplicate();
        s.execute();

    }


}


