package ua.naiksoftware.measureshp.common;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by naik on 13.08.17.
 */

public class BindingConverters {

    private static final DrawableTransitionOptions DEFAULT_TRANSITION_OPTIONS = new DrawableTransitionOptions().dontTransition();

    @BindingAdapter(value = {"android:glideUrl", "android:glidePlaceholder", "android:glidePlaceholderId",
    "android:glideCrossFade"}, requireAll = false)
    public static void setImageUrl(ImageView imageView, String glideUrl, Drawable glidePlaceholder, int glidePlaceholderId,
                                   boolean glideCrossFade) {

        RequestOptions opts = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        if (glidePlaceholder != null) opts.placeholder(glidePlaceholder);
        else if (glidePlaceholderId != 0) opts.placeholder(glidePlaceholderId);

        DrawableTransitionOptions transitionOpts;
        if (glideCrossFade) {
            transitionOpts = new DrawableTransitionOptions().crossFade();
        } else {
            transitionOpts = DEFAULT_TRANSITION_OPTIONS;
        }

        Glide.with(imageView)
                .load(glideUrl)
                .apply(opts)
                .transition(transitionOpts)
                .into(imageView);
    }
}
