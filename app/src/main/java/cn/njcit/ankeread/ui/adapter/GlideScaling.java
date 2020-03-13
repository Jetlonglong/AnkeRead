package cn.njcit.ankeread.ui.adapter;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.request.target.ImageViewTarget;

/**
 * Create by ankele
 * <p>
 * 2020/1/27 - 19:24
 */
public class GlideScaling extends ImageViewTarget<Bitmap> {

    private ImageView imageView;

    public GlideScaling(ImageView imageView) {
        super(imageView);
        this.imageView = imageView;
    }

    @Override
    protected void setResource(@Nullable Bitmap resource) {
        view.setImageBitmap(resource);

        //获取原图的宽高
        int width = resource.getWidth();
        int height = resource.getHeight();

        //获取imageView的宽
        int imageViewWidth = imageView.getWidth();

        //计算缩放比例
        float sy = (float) (imageViewWidth * 0.1) / (float) (width * 0.1);

        //计算图片等比例放大后的高
        int imageViewHeight = (int) (height * sy);
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height = imageViewHeight;
        imageView.setLayoutParams(params);
    }
}
