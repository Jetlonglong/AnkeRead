package cn.njcit.ankeread.ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.njcit.ankeread.R;

/**
 * Create by ankele
 * <p>
 * 2020/1/19 - 21:01
 */
public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {

    private Context context;
    private List<Integer> nums;
    private List<String> names;

    public HotAdapter(Context context, List<Integer> nums, List<String> names) {
        this.context = context;
        this.nums = nums;
        this.names = names;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hotsearch,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.hot_num.setText(position+1+"");
        holder.hot_num.setBackgroundResource(nums.get(position));
        holder.hot_name.setText(names.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.HotClick(names.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView hot_num;
        TextView hot_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hot_num = (TextView)itemView.findViewById(R.id.item_hot_num);
            hot_name = (TextView)itemView.findViewById(R.id.item_hot_bookName);

        }
    }

    public static OnHotClickListener mListener;

    public static void setmListener(OnHotClickListener onHotClickListener) {
        HotAdapter.mListener = onHotClickListener;
    }

    public interface OnHotClickListener{
        void HotClick(String bookName);
    }
}
