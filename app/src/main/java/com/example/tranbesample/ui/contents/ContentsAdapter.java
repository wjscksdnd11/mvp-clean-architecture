package com.example.tranbesample.ui.contents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.example.tranbesample.R;
import com.example.tranbesample.datas.HomeCategory;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ContentsContract.AdapterView {

    ArrayList<HomeCategory> mDatas =new ArrayList<>();
    private final RequestManager mRequestManager;
    private ContentsContract.AdapterView mViewListener;
    private ContentsContract.Presenter mPresenter;

    public ContentsAdapter(RequestManager mRequestManager,ContentsContract.Presenter presenter) {
        this.mRequestManager = mRequestManager;
        this.mPresenter = presenter;
        presenter.setAdapterView(this);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contents_item_view,parent,false);
        return new ItemViewHolder(view,mRequestManager,mPresenter);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

    }
    public void replaceData(ArrayList<HomeCategory> datas){
        this.mDatas = datas;
    }
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onExpandItemView(int position) {

    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView categoryImageView;
        RequestManager mRequestManager;
        ContentsContract.Presenter mPresenter;

        public ItemViewHolder(@NonNull View itemView,RequestManager requestManager,ContentsContract.Presenter presenter) {
            super(itemView);
            categoryImageView = itemView.findViewById(R.id.img);
            mRequestManager = requestManager;
            mPresenter = presenter;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.parentItemClick(getAdapterPosition());
                }
            });
        }
        public void bind(HomeCategory category){
            mRequestManager.load(category.images).centerCrop().into(categoryImageView);
        }
    }
}
