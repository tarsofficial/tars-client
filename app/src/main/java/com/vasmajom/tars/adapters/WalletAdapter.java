package com.vasmajom.tars.adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vasmajom.tars.R;
import com.vasmajom.tars.model.Wallet;

import java.util.List;

import static com.vasmajom.tars.util.Toaster.throwToast;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.WalletViewHolder> {

    private Context context;
    private List<Wallet> walletList;

    static class WalletViewHolder extends RecyclerView.ViewHolder {
        View recyclerElement;
        TextView currency;

        WalletViewHolder(View walletView) {
            super(walletView);
            this.recyclerElement = walletView;
            this.currency = walletView.findViewById(R.id.currency);
        }
    }

    public WalletAdapter(Context context, List<Wallet> walletList) {
        this.context = context;
        this.walletList = walletList;
    }

    @NonNull
    @Override
    public WalletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View name = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallet_list_element, parent, false);
        return new WalletViewHolder(name);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletViewHolder holder, final int position) {
        holder.currency.setText(walletList.get(position).getCurrency());
        holder.recyclerElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("address", walletList.get(position).getAddress());
                clipboard.setPrimaryClip(clip);
                throwToast(context, "Address copied!");
            }
        });
    }


    @Override
    public int getItemCount() {
        return walletList.size();
    }

}
