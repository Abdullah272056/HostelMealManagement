package com.example.hostelmealmanagement.calculation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelmealmanagement.R;
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
        View view = LayoutInflater.from(context).inflate(R.layout.calculation_recycler_view_item,parent,false);
        return new AllCalculationCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.memberNameTextView.setText(getAllCalculationDataList.get(position).getName());
        holder.totalMealTextView.setText(String.valueOf(getAllCalculationDataList.get(position).getTotalMeal()));
        holder.mealRateTextView.setText(String.valueOf(getAllCalculationDataList.get(position).getMealRate()));
        holder.totalCostTextView.setText(String.valueOf(getAllCalculationDataList.get(position).getTotalCost()));
        holder.depositAmountTextView.setText(String.valueOf(getAllCalculationDataList.get(position).getDepositAmount()));
        holder.dueTextView.setText(String.valueOf(getAllCalculationDataList.get(position).getDueOrGetReturn()));
    }

    @Override
    public int getItemCount() {
        return getAllCalculationDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView memberNameTextView,totalMealTextView,mealRateTextView,
                totalCostTextView,depositAmountTextView,dueTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            memberNameTextView=itemView.findViewById(R.id.memberNameTextViewId);
            totalMealTextView=itemView.findViewById(R.id.totalMealTextViewId);
            mealRateTextView=itemView.findViewById(R.id.mealRateTextViewId);
            totalCostTextView=itemView.findViewById(R.id.totalCostTextViewId);
            depositAmountTextView=itemView.findViewById(R.id.depositAmountTextViewId);
            dueTextView=itemView.findViewById(R.id.dueTextViewId);

        }
    }
}
