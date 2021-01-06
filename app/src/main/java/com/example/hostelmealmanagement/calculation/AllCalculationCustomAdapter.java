package com.example.hostelmealmanagement.calculation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelmealmanagement.get_all_member.GetAllMemberData;
import com.example.hostelmealmanagement.get_all_member.MemberListCustomAdapter;

import java.util.List;

public class AllCalculationCustomAdapter extends RecyclerView.Adapter<AllCalculationCustomAdapter.MyViewHolder> {
    Context context;
    String token;
    List<GetAllCalculationData> getAllCalculationDataList;
    MemberListCustomAdapter.OnContactClickListener onContactClickListener;

    public AllCalculationCustomAdapter(Context context, String token, List<GetAllCalculationData> getAllCalculationDataList) {
        this.context = context;
        this.token = token;
        this.getAllCalculationDataList = getAllCalculationDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
