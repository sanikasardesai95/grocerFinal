package com.example.grocersapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.grocersapp.R;

public class Offers extends AppCompatActivity {

    GridView gridView;
    String[] FruitNAmes = {"MONTHLY OFFERS", "HOME PRODUCTS", "HOUSEHOLD", "PERSONAL OFFERS", "BABYCARE OFFERS", "BEVERAGES", "WEEKLY OFFERS", "FOOD & GRAINS"};
    int[] img = {R.drawable.img2, R.drawable.drinks, R.drawable.img2, R.drawable.personal, R.drawable.baby, R.drawable.drinks, R.drawable.img2, R.drawable.food};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        gridView = findViewById(R.id.gridviewdata);

        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);

    }


    public class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return img.length;
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
            View v1 = getLayoutInflater().inflate(R.layout.row_data, null);
            TextView textView = v1.findViewById(R.id.veggies);
            Button btn =v1. findViewById(R.id.btnshop);
            ImageView img1=v1.findViewById(R.id.img);

            textView.setText(FruitNAmes[i]);
            img1.setImageResource(img[i]);


return  v1;
        }
    }
}