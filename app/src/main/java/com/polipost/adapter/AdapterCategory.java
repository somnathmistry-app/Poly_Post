package com.polipost.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polipost.R;
import com.polipost.models.FilterData;

import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.MyVH> {

    private static final String TAG = AdapterCategory.class.getSimpleName();
    List<FilterData> dataList;
    Context context;

    public AdapterCategory(List<FilterData> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new MyVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {
        holder.filtername.setText(dataList.get(position).getName());
        Toast.makeText(context, dataList.get(position).getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyVH extends RecyclerView.ViewHolder {
        TextView filtername;

        public MyVH(@NonNull View itemView) {
            super(itemView);
            filtername = itemView.findViewById(R.id.textid);
        }
    }
}
