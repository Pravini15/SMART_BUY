//package com.example.mad_project.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.mad_project.R;
//import com.example.mad_project.models.Products;
//
//import java.util.List;
//
//public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
//    Context mContext;
//    List<Products> mData;
//    private ProductViewHolder.RecyclerViewClickListner clickListner;
//
//    public ProductAdapter(Context mContext, List<Products> mData, ProductViewHolder.RecyclerViewClickListner clickListner) {
//        this.mContext = mContext;
//        this.mData = mData;
//        this.clickListner = clickListner;
//    }
//
//    @NonNull
//    @Override
//    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
//        View layout;
//        layout = LayoutInflater.from(mContext).inflate(R.layout.product_item, viewGroup, false);
//        return new ProductViewHolder(layout, mContext, mData, clickListner);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ProductAdapter holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//
//}
