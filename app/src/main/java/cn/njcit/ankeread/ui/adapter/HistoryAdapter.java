package cn.njcit.ankeread.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.njcit.ankeread.db.SearchHistory;
import cn.njcit.ankeread.R;

/**
 * Create by ankele
 * <p>
 * 2020/1/23 - 13:12
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private List<SearchHistory> histories;

    public HistoryAdapter(Context context, List<SearchHistory> histories) {
        this.context = context;
        this.histories = histories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.bookName.setText(histories.get(position).getBookName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener!=null){
                    mListener.HistoryClick(histories.get(position).getBookName());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView bookName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookName = (TextView)itemView.findViewById(R.id.item_history_bookName);
        }
    }
    public static OnHistoryClickListener mListener;

    public static void setmListener(OnHistoryClickListener onHistoryClickListener) {
        HistoryAdapter.mListener = onHistoryClickListener;
    }

    public interface OnHistoryClickListener{
        void HistoryClick(String bookName);
    }
}
