package com.example.elevenuser.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.elevenuser.R;
import com.mikhaellopez.circularimageview.CircularImageView;

public class Tools {


     Context context;
     Dialog dialog;

    public Tools() {
    }

    public Tools(Context context) {
        this.context = context;
        dialog = new Dialog(context);
    }


    public static void setSystemBarColor(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(act.getResources().getColor(R.color.colorPrimary));
        }
    }


    public static void setSystemBarColor(Activity act, @ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(act.getResources().getColor(color));
        }
    }


    public static void setSystemBarLight(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View view = act.findViewById(android.R.id.content);
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }
    }

    public static int dpToPx(Context c, int dp) {
        Resources r = c.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    public static void displayImageRound(final Context ctx, final ImageView img, @DrawableRes int drawable) {
        try {
            Glide.with(ctx).asBitmap().load(drawable).into(new BitmapImageViewTarget(img) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(ctx.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    img.setImageDrawable(circularBitmapDrawable);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public static void displayImageRoundUrl(final Context ctx, final ImageView img, String urlimg) {
        try {
            Glide.with(ctx).asBitmap().load(urlimg).apply(new RequestOptions().placeholder(R.drawable.logotransperent).error(R.drawable.logotransperent))
                    .into(new BitmapImageViewTarget(img) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(ctx.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    img.setImageDrawable(circularBitmapDrawable);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    public static void displayImageOriginal(Context ctx, ImageView img, String drawable) {
        try {
            Glide.with(ctx).load(drawable)
                    .into(img);
        } catch (Exception e) {
        }
    }


    public static void displayImageOriginal(Context ctx, ImageView img, @DrawableRes int drawable) {
        try {
            Glide.with(ctx).load(drawable)
                    .into(img);
        } catch (Exception e) {
        }
    }
   /* public static void displayImage(Context ctx, ImageView img, String url) {
        try {
            Glide.with(ctx).load(url).apply(new RequestOptions().placeholder(R.drawable.logotransperent).error(R.drawable.logotransperent))
                    .into(img);
        } catch (Exception e) {

        }
    }*/

   /* public static void displayImageURL(Context ctx, CircularImageView img, String url) {
        try {
            Glide.with(ctx).load(url).apply(new RequestOptions().placeholder(R.drawable.logotransperent).error(R.drawable.logotransperent))
                    .into(img);
        } catch (Exception e) {
        }
    }*/

    public static void nestedScrollTo(final NestedScrollView nested, final View targetView) {
        nested.post(new Runnable() {
            @Override
            public void run() {
                nested.scrollTo(500, targetView.getBottom());
            }
        });
    }


    public static void displayImage(Context ctx, CircularImageView img, int url) {
        try {
            Glide.with(ctx).load(url)
                    .into(img);
        } catch (Exception e) {
        }
    }

    public static void displayImageViewURL(Context ctx,ImageView img, String url) {
        try {
            Glide.with(ctx).load(url)
                    .into(img);
        } catch (Exception e) {
        }
    }


    public static void changeMenuIconColor(Menu menu, @ColorInt int color) {
        for (int i = 0; i < menu.size(); i++) {
            Drawable drawable = menu.getItem(i).getIcon();
            if (drawable == null) continue;
            drawable.mutate();
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        }
    }

    public static void toast(Context ctx,String msg,int type){
        //type 0 info, 1 for Error ,2 for Sucess,3 for warning
       if(type==0){
           //Toasty.info(ctx, msg, Toast.LENGTH_SHORT, true).show();
       }else if(type==1){
          // Toasty.error(ctx,msg, Toast.LENGTH_SHORT, true).show();
       }else if(type==2){
          // Toasty.success(ctx, msg, Toast.LENGTH_SHORT, true).show();
       }else if(type==3){
           //Toasty.warning(ctx,msg, Toast.LENGTH_SHORT, true).show();
       }else{
           Toast.makeText(ctx, ""+msg, Toast.LENGTH_SHORT).show();
       }

    }

    public static void log(String tag,String msg){
        Log.e(tag,""+msg);
    }

   /* public void showLoading() {
        dialog.setContentView(R.layout.loadingdailog);
        dialog.setCancelable(false);
        dialog.show();
    }*/

    public void stopLoading() {
        dialog.dismiss();
    }




}
