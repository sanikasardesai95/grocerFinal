package com.example.grocersapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.grocersapp.Controller.CategoryAdapter;
import com.example.grocersapp.Controller.DataAdapter;
import com.example.grocersapp.Controller.GridProductLayoutAdapter;
import com.example.grocersapp.Controller.ProductItemAdapter;
import com.example.grocersapp.Model.ProductItem;
import com.example.grocersapp.R;
import com.example.grocersapp.Response.CategoriesResponse;
import com.example.grocersapp.Response.GetProductResponse;
import com.example.grocersapp.Views.Categories;
import com.example.grocersapp.Views.CustomListViewDialog;
import com.example.grocersapp.Views.Offers;
import com.example.grocersapp.api.Api;
import com.example.grocersapp.api.RetrofitC;
import com.example.grocersapp.api.RetrofitClient;
import com.example.grocersapp.storage.SharedPrefManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private RecyclerView categoryRecycle,recycledhayna,recyclecold;

    private CategoryAdapter categoryAdapter;
    private ArrayList<CategoriesResponse.Categories> categoryList;
    SliderLayout sliderLayout,sliderLayout1;
    private ProductItemAdapter productItemAdapter;
    private List<ProductItem> productList;
    Context context;
    CardView cardView;

    GridView gridView;
    GridProductLayoutAdapter gridProductLayoutAdapter;

    SharedPrefManager sharedPrefManager;
    private String decryptedToken;

    Button btnCat,btnOffer,btnOrders;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);
       /* ((Button) v.findViewById(R.id.btn_view_offers1)).setOnClickListener(this);
        ((Button) v.findViewById(R.id.btn_view_orders)).setOnClickListener(this);*/
        sliderLayout1=v.findViewById(R.id.slide);
//        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout1.setIndicatorAnimation(IndicatorAnimations.FILL);

      /*  add=v.findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickHere();
            }
        });*/
        categoryList=new ArrayList<>();

//        customListViewDialog.show();
        cardView=v.findViewById(R.id.cardview1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(), Offers.class);
                startActivity(i);
            }
        });

        setSliderViews1();
       // setSliderViews1();

        return v;


    }

    CustomListViewDialog customListViewDialog;



  /*  private void setSliderViews() {
        for (int i=0;i<3;i++){
            DefaultSliderView sliderView=new DefaultSliderView(getContext());


            switch (i){
                case 0:
                    sliderView.setImageDrawable(R.drawable.banner1);

                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.beat);
                    break;
                case 2:
                sliderView.setImageDrawable(R.drawable.foodgrains);
                break;

            }

            sliderView.setImageScaleType(ImageView.ScaleType.FIT_CENTER);
            sliderLayout.addSliderView(sliderView);



        }
    }*/

    private void setSliderViews1() {
        for (int i=0;i<3;i++){
            DefaultSliderView sliderView1=new DefaultSliderView(getContext());


            switch (i){
                case 0:
                    sliderView1.setImageDrawable(R.drawable.bevarages);

                    break;
                case 1:
                    sliderView1.setImageDrawable(R.drawable.beat);
                    break;
                case 2:
                    sliderView1.setImageDrawable(R.drawable.banner1);
                    break;

            }

            sliderView1.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout1.addSliderView(sliderView1);



        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        categoryLoad(view);
        dhanyaItems(view);
        coldDrinksItems(view);

        btnCat=view.findViewById(R.id.btn_view_categories1);
        btnCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Categories.class);
                startActivity(intent);
            }
        });



    }

   private void categoryLoad(View view)
    {
        categoryRecycle = view.findViewById(R.id.recycler_category);
        categoryRecycle.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

//        categoryList = new ArrayList<CategoriesResponse.Categories>();
//        categoryList.add(new CategoryModel("https://www.heartfoundation.org.nz/media/images/nutrition/page-heros/are-whole-grains-good-for-you_737_553_c1.jpg", "Dhanya", 1));
//        categoryList.add(new CategoryModel("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/shopping-bag-full-of-fresh-vegetables-and-fruits-royalty-free-image-1128687123-1564523576.jpg?crop=0.669xw:1.00xh;0.300xw,0&resize=640:*", "Fruits", 2));
//        categoryList.add(new CategoryModel("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/shopping-bag-full-of-fresh-vegetables-and-fruits-royalty-free-image-1128687123-1564523576.jpg?crop=0.669xw:1.00xh;0.300xw,0&resize=640:*", "Vegetables", 3));
//        categoryList.add(new CategoryModel("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/shopping-bag-full-of-fresh-vegetables-and-fruits-royalty-free-image-1128687123-1564523576.jpg?crop=0.669xw:1.00xh;0.300xw,0&resize=640:*", "Vegetables", 3));
//        categoryAdapter = new CategoryAdapter(context, categoryList);
//        categoryRecycle.setAdapter(categoryAdapter);

        sharedPrefManager = new SharedPrefManager(this.getActivity());

        decryptedToken = sharedPrefManager.getToken();

        Retrofit retrofitClient =new RetrofitClient().getRetrofit();
        final Api api =retrofitClient.create(Api.class);
        Call<CategoriesResponse> call = api.getCategories(decryptedToken);


        call.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
//                if (response.isSuccessful() &&
//                        response.raw().networkResponse() != null
//
//                    ) {
//                    Log.e("Network", "response came from cache");
//                    // the response hasn't changed, so you do not need to do anything
//                    return;
//                }
                CategoriesResponse categoriesResponse = response.body();
                   categoryAdapter = new CategoryAdapter( getActivity(),categoriesResponse.getCategories());
                    categoryRecycle.setAdapter(categoryAdapter);
                    categoryList=categoriesResponse.getCategories();


                Log.d("category", "in here");
                if (response.raw().cacheResponse() != null) {
                    Log.e("Network", "response came from cache");
                }

                if (response.raw().networkResponse() != null) {
                    Log.e("Network", "response came from server");
                }
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
}

    private void dhanyaItems(View view){
        recycledhayna = view.findViewById(R.id.recycle_dhanya);
        recycledhayna.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

//        productList=new ArrayList<>();
//
//        productList.add(new ProductItem("https://previews.123rf.com/images/utima/utima1311/utima131100146/24077398-orange-fruit-isolated-on-white-background.jpg","संत्र","35"));
//        productList.add(new ProductItem("https://5.imimg.com/data5/NR/UP/MY-2/123-jpg-500x500.jpg","बोर बॉन बिस्किट","25"));
//        productList.add(new ProductItem("https://images-na.ssl-images-amazon.com/images/I/812o4EQXPKL._SX569_.jpg","मॅगी","20"));
//        productList.add(new ProductItem("https://i5.walmartimages.ca/images/Enlarge/094/514/6000200094514.jpg","सफरचंद","44"));
//        productItemAdapter= new ProductItemAdapter(context, productList,this);
//        productRecycle.setAdapter(productItemAdapter);



        sharedPrefManager = new SharedPrefManager(getActivity());

        decryptedToken = sharedPrefManager.getToken();

        Retrofit retrofitClient =new RetrofitC(getActivity()).getRetrofit();
        final Api api =retrofitClient.create(Api.class);
        Call<GetProductResponse> call = api.getProducts(decryptedToken,1);


        call.enqueue(new Callback<GetProductResponse>() {
            @Override
            public void onResponse(Call<GetProductResponse> call, Response<GetProductResponse> response) {
                GetProductResponse getProductResponse = response.body();
                productItemAdapter = new ProductItemAdapter(getActivity(),getProductResponse.getGetProducts(),HomeFragment.this);
                recycledhayna.setAdapter(productItemAdapter);
            }

            @Override
            public void onFailure(Call<GetProductResponse> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });


//        Retrofit retrofitClient =new RetrofitClient().getRetrofit();
//        final Api api =retrofitClient.create(Api.class);
//        Call<List<ProductItem>> call = api.getProducts();
//
//        call.enqueue(new Callback<List<ProductItem>>() {
//            @Override
//            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
//                productList = response.body();
//                Log.d("TAG","Response = "+productList);
//                productItemAdapter.setProductList(productList);
//            }
//
//            @Override
//            public void onFailure(Call<List<ProductItem>> call, Throwable t) {
//                Log.d("TAG","Response = "+t.toString());
//            }
//        });
    }



    private void coldDrinksItems(View view){
        recyclecold = view.findViewById(R.id.recycle_cold_drinks1);
        recyclecold.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

//        productList =new ArrayList<>();
//
//        productList.add(new ProductItem("https://previews.123rf.com/images/utima/utima1311/utima131100146/24077398-orange-fruit-isolated-on-white-background.jpg","संत्र","35"));
//        productList.add(new ProductItem("https://5.imimg.com/data5/NR/UP/MY-2/123-jpg-500x500.jpg","बोर बॉन बिस्किट","25"));
//        productList.add(new ProductItem("https://images-na.ssl-images-amazon.com/images/I/812o4EQXPKL._SX569_.jpg","मॅगी","20"));
//        productList.add(new ProductItem("https://i5.walmartimages.ca/images/Enlarge/094/514/6000200094514.jpg","सफरचंद","44"));
//        productItemAdapter= new ProductItemAdapter(context, productList,this);
//        productRecycle.setAdapter(productItemAdapter);

        sharedPrefManager = new SharedPrefManager(getActivity());

        decryptedToken = sharedPrefManager.getToken();

        Retrofit retrofitClient =new RetrofitC(getActivity()).getRetrofit();
        final Api api =retrofitClient.create(Api.class);
        Call<GetProductResponse> call = api.getProducts(decryptedToken,2);


        call.enqueue(new Callback<GetProductResponse>() {
            @Override
            public void onResponse(Call<GetProductResponse> call, Response<GetProductResponse> response) {
                GetProductResponse getProductResponse = response.body();
                productItemAdapter = new ProductItemAdapter(getActivity(),getProductResponse.getGetProducts(),HomeFragment.this);
                recyclecold.setAdapter(productItemAdapter);
            }

            @Override
            public void onFailure(Call<GetProductResponse> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });




//
//        Retrofit retrofitClient =new RetrofitClient().getRetrofit();
//        final Api api =retrofitClient.create(Api.class);
//        Call<List<ProductItem>> call = api.getProducts();
//
//        call.enqueue(new Callback<List<ProductItem>>() {
//            @Override
//            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
//                productList = response.body();
//                Log.d("TAG","Response = "+productList);
//                productItemAdapter.setProductList(productList);
//            }
//
//            @Override
//            public void onFailure(Call<List<ProductItem>> call, Throwable t) {
//                Log.d("TAG","Response = "+t.toString());
//            }
//        });
    }


    @Override
    public void onClick(View view) {
       /* switch (view.getId()) {
            case R.id.btn_view_offers1:
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                OfferFragment fragment =new OfferFragment();
                Bundle arguments = new Bundle();
                arguments.putInt("id",1);
                fragment.setArguments(arguments);
                ft.replace(R.id.content_home, fragment, "NewFragmentTag");
                ft.addToBackStack(null);
                ft.commit();
                break;
            case R.id.btn_view_orders:
                final FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                OfferFragment fragment1 =new OfferFragment();
                Bundle arguments1 = new Bundle();
                arguments1.putInt("id",2);
                fragment1.setArguments(arguments1);
                ft1.replace(R.id.content_home, fragment1, "NewFragmentTag");
                ft1.addToBackStack(null);
                ft1.commit();
                break;
        }
    }*/
    }


    /*public void ClickHere() {

        DataAdapter dataAdapter=new DataAdapter(getContext(),categoryList);

        customListViewDialog=new CustomListViewDialog(getActivity(),dataAdapter);

        customListViewDialog.show();
        customListViewDialog.setCanceledOnTouchOutside(false);
    }
    @Override
    public void clickOnItem(CategoriesResponse.Categories categories) {

        if(customListViewDialog!=null){
            customListViewDialog.dismiss();
        }*/

}


