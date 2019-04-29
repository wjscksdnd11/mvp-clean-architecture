package com.example.tranbesample.ui.contents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.tranbesample.R;
import com.example.tranbesample.datas.HomeCategory;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ContentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
        ContentsContract.AdapterView,
        ContentsContract.AdapterModel {

    private ArrayList<HomeCategory> mDatas = new ArrayList<>();
    private final RequestManager mRequestManager;
    private ContentsContract.Presenter mPresenter;
    private static final Integer TOOGLE_MODE = 1;

    ContentsAdapter(RequestManager mRequestManager, ContentsContract.Presenter presenter) {
        this.mRequestManager = mRequestManager;
        this.mPresenter = presenter;
        presenter.setAdapterView(this);
        presenter.setAdapterModel(this);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contents_item_view, parent, false);
        return new ItemViewHolder(view, mRequestManager, mPresenter);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.bind(mDatas.get(position));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        for (Object payload : payloads) {
            if (payload instanceof Integer && payload == TOOGLE_MODE) {
                itemViewHolder.addChild(mDatas.get(position));
            }
        }
    }

    void replaceData(ArrayList<HomeCategory> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).type.ordinal();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onExpandItemView(int position) {
        notifyItemChanged(position, TOOGLE_MODE);
    }

    @Override
    public HomeCategory getChildCategories(int position) {
        if (mDatas.size() > 0 && mDatas.get(position) != null)
            return mDatas.get(position);
        return null;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImageView;
        RequestManager mRequestManager;
        ContentsContract.Presenter mPresenter;
        RecyclerView mChildRecyclerView;

        ItemViewHolder(@NonNull View itemView, RequestManager requestManager, ContentsContract.Presenter presenter) {
            super(itemView);

            categoryImageView = itemView.findViewById(R.id.img);
            mChildRecyclerView = itemView.findViewById(R.id.child_recycler);
            mChildRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            mRequestManager = requestManager;
            mPresenter = presenter;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.parentItemClick(getAdapterPosition());
                }
            });
        }

        void bind(HomeCategory category) {

            mRequestManager.load(category.images.getLow())
                    .transition(new DrawableTransitionOptions()
                            .crossFade())
                    .into(categoryImageView);
            mChildRecyclerView.setAdapter(new ChildContentsAdapter(mRequestManager, mPresenter, getAdapterPosition()));

        }

        void addChild(HomeCategory category) {
            category.isOpen = !category.isOpen;
            ChildContentsAdapter childContentsAdapter;
            if (mChildRecyclerView.getAdapter() != null) {
                childContentsAdapter = ((ChildContentsAdapter) mChildRecyclerView.getAdapter());
            } else {
                childContentsAdapter = new ChildContentsAdapter(mRequestManager, mPresenter, getAdapterPosition());
                mChildRecyclerView.setAdapter(childContentsAdapter);
            }
            runLayoutAnimation(mChildRecyclerView);
            childContentsAdapter.toogleView(category.childCategories, category.isOpen);

        }
        private void runLayoutAnimation(final RecyclerView recyclerView) {
            final Context context = recyclerView.getContext();
            final LayoutAnimationController controller =
                    AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation);

            recyclerView.setLayoutAnimation(controller);
            recyclerView.scheduleLayoutAnimation();
        }
    }


}
