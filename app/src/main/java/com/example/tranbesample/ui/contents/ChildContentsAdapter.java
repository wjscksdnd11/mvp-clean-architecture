package com.example.tranbesample.ui.contents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.example.tranbesample.R;
import com.example.tranbesample.datas.HomeCategory;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.google.common.base.Preconditions.checkNotNull;

public class ChildContentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ContentsContract.ChildAdapterView {

    private ArrayList<HomeCategory> mChildDatas = new ArrayList<>();
    private ContentsContract.Presenter mPresenter;
    private RequestManager mRequestManager;
    private final int mParentPosition;

    ChildContentsAdapter(
            @Nonnull RequestManager requestManager,
            @Nonnull ContentsContract.Presenter contentsPresenter, int parentPositon) {
        this.mPresenter = checkNotNull(contentsPresenter);
        this.mPresenter.setChildAdapterView(this);
        this.mRequestManager = requestManager;
        this.mParentPosition = parentPositon;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contents_child_item_view, parent, false);
        return new ChildViewHolder(view, mRequestManager, mPresenter,mParentPosition);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChildViewHolder childViewHolder = (ChildViewHolder) holder;
        childViewHolder.bind(mChildDatas.get(position));
    }

    private void addDatas(ArrayList<HomeCategory> childDatas) {
        this.mChildDatas = childDatas;
    }

    private void removeDatas() {
        mChildDatas.clear();
    }

    @Override
    public int getItemCount() {
        return mChildDatas.size();
    }

    @Override
    public void toogleView(List<HomeCategory> childDatas, boolean isOpen) {
        if (isOpen) {
            addDatas((ArrayList<HomeCategory>) childDatas);
        } else {
            removeDatas();
        }
    }

    public static class ChildViewHolder extends RecyclerView.ViewHolder {

        ContentsContract.Presenter mPresenter;
        RequestManager mRequestManager;
        ImageView mMidCategoryIconView;
        TextView mCategoryNameTextView;

        ChildViewHolder(@NonNull View itemView,
                        @Nonnull RequestManager requestManager,
                        @Nonnull final ContentsContract.Presenter presenter,
                        final int parentPosition) {
            super(itemView);
            mMidCategoryIconView = itemView.findViewById(R.id.category_icon);
            mCategoryNameTextView = itemView.findViewById(R.id.category_name);
            this.mRequestManager = checkNotNull(requestManager);
            this.mPresenter = checkNotNull(presenter);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.childItemClick(parentPosition,getAdapterPosition());
                }
            });
        }

        public void bind(HomeCategory homeCategory) {
            mRequestManager.load(homeCategory.images.getHigh()).into(mMidCategoryIconView);
            mCategoryNameTextView.setText(homeCategory.name);
        }
    }
}
