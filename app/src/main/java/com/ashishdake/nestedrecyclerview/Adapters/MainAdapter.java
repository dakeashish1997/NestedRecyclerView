package com.ashishdake.nestedrecyclerview.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashishdake.nestedrecyclerview.Models.MainModel;
import com.ashishdake.nestedrecyclerview.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<MainModel> dataList;
    private Context context;

    private NestedAdapter horizontalAdapter;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public MainAdapter(List<MainModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;

        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends BaseViewHolder {
        private final View parent;

        RecyclerView recyclerView;
        TextView classname;

        ViewHolder(final View itemView) {
            super(itemView);

            recyclerView=itemView.findViewById(R.id.subRecyclerView);
            classname=itemView.findViewById(R.id.txvclassname);

            LinearLayoutManager horizontalManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setHasFixedSize(true);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setLayoutManager(horizontalManager);

            parent = itemView;
        }

        protected void clear(){}

        public void onBind(final int position){
            super.onBind(position);
            parent.setTag(this);
            MainModel mainModel = dataList.get(position);

            classname.setText("Class: "+mainModel.getClassname());

            horizontalAdapter = new NestedAdapter(mainModel.getList(),context);
            recyclerView.setAdapter(horizontalAdapter);
            recyclerView.setRecycledViewPool(recycledViewPool);
        }
    }
}
