package cn.njcit.ankeread.ui.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.njcit.ankeread.Bean.SortIcon;
import cn.njcit.ankeread.R;
import cn.njcit.ankeread.ui.Activity.SortBooksActivity;

/**
 * Create by ankele
 * <p>
 * 2020/1/29 - 21:29
 */
public class SortAdapter extends RecyclerView.Adapter<SortAdapter.ViewHolder> {

    private Context context;
    private List<String> sorts;
    private List<String> icons;
    private int index;

    public SortAdapter(Context context, List<String> sorts, List<String> icons, int index) {
        this.context = context;
        this.sorts = sorts;
        this.icons = icons;
        this.index = index;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sort,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.srotName.setText("-"+sorts.get(position)+"-");
        Glide.with(context).load(icons.get(position)).into(holder.sortPic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SortBooksActivity.class);
                intent.putExtra("sort",sorts.get(position));
                if (index==1){
                    intent.putExtra("index","male");
                }else if (index==2){
                    intent.putExtra("index","female");
                }else if (index==3){
                    intent.putExtra("index","press");
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sorts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView srotName;
        ImageView sortPic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            srotName = (TextView)itemView.findViewById(R.id.sortName);
            sortPic = (ImageView)itemView.findViewById(R.id.sortPic);
        }
    }
}
