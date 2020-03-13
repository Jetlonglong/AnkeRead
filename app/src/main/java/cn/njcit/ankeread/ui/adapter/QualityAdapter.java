package cn.njcit.ankeread.ui.adapter;

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

import cn.njcit.ankeread.bean.RankBean;
import cn.njcit.ankeread.R;
import cn.njcit.ankeread.ui.activity.SearchActivity;

/**
 * Create by ankele
 * <p>
 * 2020/1/28 - 14:50
 */
public class QualityAdapter extends RecyclerView.Adapter<QualityAdapter.ViewHolder> {

    private List<RankBean.BooksBean> books;
    private Context context;

    public QualityAdapter(List<RankBean.BooksBean> books, Context context) {
        this.books = books;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate((R.layout.item_qua),parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final RankBean.BooksBean book = books.get(position);
        Glide.with(context).load(book.getCover()).into(holder.qua_pic);
        holder.qua_name.setText(book.getTitle());
        holder.qua_author.setText(book.getAuthor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(context,SearchActivity.class);
                    intent.putExtra("Name",book.getTitle());
                    context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView qua_pic;
        TextView qua_name;
        TextView qua_author;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            qua_pic = (ImageView)itemView.findViewById(R.id.qua_pic);
            qua_name = (TextView)itemView.findViewById(R.id.qua_name);
            qua_author = (TextView)itemView.findViewById(R.id.qua_author);
        }
    }
}
