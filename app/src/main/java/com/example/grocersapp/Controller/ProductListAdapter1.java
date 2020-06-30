/*
package com.example.grocersapp.Controller;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.grocersapp.R;
import com.example.grocersapp.Response.GetProductResponse;

import java.util.ArrayList;

public class ProductListAdapter1 extends BaseAdapter {

    ArrayList<GetProductResponse.GetProducts> getProducts;
    private Context mcontext;
    public ProductListAdapter1(ArrayList<GetProductResponse.GetProducts> getProducts, Context context) {
        this.getProducts=getProducts;
        mcontext=context;
    }

    @Override
    public int getCount() {
        return getProducts.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1;
        if(view==null)
        {
            view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.productlist_item,null);
            view1.setBackgroundColor(Color.parseColor("#ffffff"));


            TextView prodName,prodPrice;
            ImageView btnSave,btn_fav;

            prodName=view1.findViewById(R.id.prod_name);
            prodPrice=view1.findViewById(R.id.prod_price);
            btnSave=view1.findViewById(R.id.btn_add);
            btn_fav=view1.findViewById(R.id.favourites);


        }
        else{
            view1=view;
        }


        return view1;
    }
}
*/
