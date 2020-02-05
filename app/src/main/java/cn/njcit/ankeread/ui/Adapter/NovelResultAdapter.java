package cn.njcit.ankeread.ui.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.njcit.ankeread.Bean.NovelsResult;
import cn.njcit.ankeread.R;
import cn.njcit.ankeread.ui.Activity.DetailsActivity;

/**
 * Create by ankele
 * <p>
 * 2020/1/21 - 12:41
 */
public class NovelResultAdapter extends RecyclerView.Adapter<NovelResultAdapter.ViewHolder> {

    private Context context;
    private List<NovelsResult.ListBean> results;

    public NovelResultAdapter(Context context, List<NovelsResult.ListBean> results) {
        this.context = context;
        this.results = results;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_result,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final NovelsResult.ListBean result = results.get(position);
        Glide.with(context).load(result.getCover()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.d("图片加载","失败");
                holder.book_pic.setImageResource(R.mipmap.nocover);
                return true;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(holder.book_pic);
        holder.book_name.setText(result.getName());
        holder.book_author.setText("作者："+result.getAuthor());
        holder.book_time.setText("最后更新时间："+result.getTime());
        holder.book_tag.setText("类别："+result.getTag());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DetailsActivity().startActivity(context,result);
            }
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView book_pic;
        TextView book_name;
        TextView book_author;
        TextView book_time;
        TextView book_tag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            book_pic = (ImageView)itemView.findViewById(R.id.book_pic);
            book_name = (TextView)itemView.findViewById(R.id.book_name);
            book_author = (TextView)itemView.findViewById(R.id.book_author);
            book_time = (TextView)itemView.findViewById(R.id.book_time);
            book_tag = (TextView)itemView.findViewById(R.id.book_tag);
        }
    }

}
