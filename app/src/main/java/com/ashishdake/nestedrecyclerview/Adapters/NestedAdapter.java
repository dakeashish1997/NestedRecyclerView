package com.ashishdake.nestedrecyclerview.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashishdake.nestedrecyclerview.Models.NestedModel;
import com.ashishdake.nestedrecyclerview.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class NestedAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<NestedModel> dataList;
    private Context context;

    public NestedAdapter(List<NestedModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false));
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

        ImageView studentphoto;
        TextView studentname;

        ViewHolder(final View itemView) {
            super(itemView);

            studentphoto=itemView.findViewById(R.id.imgviewstudentphoto);
            studentname=itemView.findViewById(R.id.txvstudentname);

            parent = itemView;
        }

        protected void clear(){}

        public void onBind(final int position){
            super.onBind(position);
            parent.setTag(this);
            NestedModel nestedModel = dataList.get(position);

            studentname.setText(nestedModel.getStudentname()+":"+position);

            Glide.with(context)
                    .load(nestedModel.getStudentphoto())
                    .apply(RequestOptions.circleCropTransform())
                    .into(studentphoto);
        }
    }
}
