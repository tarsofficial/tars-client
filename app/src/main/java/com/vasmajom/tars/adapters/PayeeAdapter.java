package com.vasmajom.tars.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vasmajom.tars.R;
import com.vasmajom.tars.activities.WalletList;

import java.util.List;

public class PayeeAdapter extends RecyclerView.Adapter<PayeeAdapter.PayeeViewHolder> {

    private List<String> payeeList;

    static class PayeeViewHolder extends RecyclerView.ViewHolder {
        View recyclerElement;
        TextView payeeName;

        PayeeViewHolder(View payeeView) {
            super(payeeView);
            this.recyclerElement = payeeView;
            this.payeeName = payeeView.findViewById(R.id.payee_name);
        }
    }

    public PayeeAdapter(Context context, List<String> payeeList) {
        this.payeeList = payeeList;
    }

    @NonNull
    @Override
    public PayeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View payeeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.payee_list_element, parent, false);
        return new PayeeViewHolder(payeeView);
    }

    @Override
    public void onBindViewHolder(@NonNull PayeeViewHolder holder, final int position) {
        holder.payeeName.setText(payeeList.get(position));
        holder.recyclerElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WalletList.class);
                intent.putExtra("payeeName", payeeList.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return payeeList.size();
    }

}
