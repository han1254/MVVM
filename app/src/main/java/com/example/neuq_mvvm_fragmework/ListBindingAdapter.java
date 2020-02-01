package com.example.neuq_mvvm_fragmework;

import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import androidx.core.text.HtmlCompat;
import androidx.databinding.BindingAdapter;

/**
 * Time:2020/1/14 21:31
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class ListBindingAdapter {

    //list_item_garden_planting
    @BindingAdapter(value = {"imageFromUrl"}, requireAll = false)
    public static void bindImageFromUrl(ImageView view, String imgUrl) {
        if (!"".equals(imgUrl)) {
            Glide.with(view.getContext())
                    .load(imgUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(view);
        }
    }

    //detail_fragment
    @BindingAdapter(value = {"renderHtml"}, requireAll = false)
    public static void bindRenderHtml(TextView textView, String description) {
        if (description != null) {
            textView.setText(HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT));
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            textView.setText("");
        }
    }

    @BindingAdapter(value = {"isBar"}, requireAll = false)
    public static void isBar(ProgressBar bar, boolean isShow) {
        bar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

}
