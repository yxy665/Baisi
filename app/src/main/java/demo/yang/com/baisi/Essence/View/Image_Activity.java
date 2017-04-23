package demo.yang.com.baisi.Essence.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import demo.yang.com.baisi.R;
import demo.yang.com.baisi.Widget.MyPhotoViewPager;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 用于图片预览的界面
 */
public class Image_Activity extends AppCompatActivity {
    private static final String TAG = "Image_Activity调试信息》》》";
    public static final String INTENT_IMGURLS = "imgurls";
    public static final String INTENT_POSITION = "position";
    public static final String INTENT_IMAGESIZE = "imagesize";

    private List<View> guideViewList = new ArrayList<View>();
    private LinearLayout guideGroup;
    public ImageSize imageSize;
    private int startPos;
    private ArrayList<String> imgUrls;

    public static void startImagePagerActivity(Context context, List<String> imgUrls, int position, ImageSize imageSize){
        Intent intent = new Intent(context, Image_Activity.class);
        intent.putStringArrayListExtra(INTENT_IMGURLS, new ArrayList<String>(imgUrls));
        intent.putExtra(INTENT_POSITION, position);
        intent.putExtra(INTENT_IMAGESIZE, imageSize);
        context.startActivity(intent);
    }

/**
 * 把缩略图的url转换为原图的url
 */
    private List<String> getDetailImgUrls(List<String> strings){
        List<String> list =new ArrayList<>();
        for (int i=0;i<strings.size();i++){
            if ((strings.get(i).substring(0,4)).equals("http")){
                String s=strings.get(i).substring(0,strings.get(i).length()-1);
                s+="0";
//                Log.e("图片的url是", "getDetailImgUrls: >>>>>>>>>>>>>>>>>"+s );
                list.add(s);
            }else {
                list.add(strings.get(i));
            }
        }
        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        MyPhotoViewPager viewPager = (MyPhotoViewPager) findViewById(R.id.pager);
        guideGroup = (LinearLayout) findViewById(R.id.guideGroup);
        getIntentData();
        ImageAdapter mAdapter = new ImageAdapter(this);
        mAdapter.setDatas(imgUrls);
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0; i<guideViewList.size(); i++){
                    guideViewList.get(i).setSelected(i==position ? true : false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(startPos);

        addGuideView(guideGroup, startPos, imgUrls);

    }



    private void addGuideView(LinearLayout guideGroup, int startPos, ArrayList<String> imgUrls) {
        if(imgUrls!=null && imgUrls.size()>0){
            guideViewList.clear();
            for (int i=0; i<imgUrls.size(); i++){
                View view = new View(this);
                view.setBackgroundResource(R.drawable.selector_guide_bg);
                view.setSelected(i==startPos ? true : false);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(6,6);
                layoutParams.setMargins(10, 0, 0, 0);
                guideGroup.addView(view, layoutParams);
                guideViewList.add(view);
            }
        }
    }

    private void getIntentData() {
        startPos = getIntent().getIntExtra(INTENT_POSITION, 0);
//        imgUrls = (ArrayList<String>) getDetailImgUrls(getIntent().getStringArrayListExtra(INTENT_IMGURLS));
        imgUrls = (ArrayList<String>) getIntent().getStringArrayListExtra(INTENT_IMGURLS);
        imageSize = (ImageSize) getIntent().getSerializableExtra(INTENT_IMAGESIZE);
    }

    private class ImageAdapter extends PagerAdapter {


        private List<String> datas = new ArrayList<String>();
        private LayoutInflater inflater;
        private Context context;
        private ImageSize imageSize;
        private ImageView smallImageView = null;

        public void setImageSize(ImageSize imageSize){
            this.imageSize = imageSize;
        }

        public void setDatas(List<String> datas) {
            if(datas != null )
                this.datas = datas;
        }


        public ImageAdapter(Context context){
            this.context = context;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            if(datas == null) return 0;
            return datas.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = inflater.inflate(R.layout.item_pager_image, container, false);
            final PhotoView imageView = (PhotoView) view.findViewById(R.id.Image_Activity_image);
            imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    if (context instanceof Activity){
                        ((Activity) context).finish();
                    }
                }

                @Override
                public void onOutsidePhotoTap() {

                }
            });
            if(imageSize!=null){
                //预览imageView
                smallImageView = new ImageView(context);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(imageSize.getWidth(), imageSize.getHeight());
                layoutParams.gravity = Gravity.CENTER;
                smallImageView.setLayoutParams(layoutParams);
                smallImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                ((FrameLayout)view).addView(smallImageView);
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    dialog(datas.get(position));
                    return false;
                }
            });

            //loading
            final ProgressBar loading = new ProgressBar(context);
            FrameLayout.LayoutParams loadingLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            loadingLayoutParams.gravity = Gravity.CENTER;
            loading.setLayoutParams(loadingLayoutParams);
            ((FrameLayout)view).addView(loading);

            final String imgurl = datas.get(position);
            if (IsGIF(imgurl)){
                Glide.with(context)
                        .load(imgurl)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.mipmap.ic_launcher)
                        .dontAnimate()
                        .into(new GlideDrawableImageViewTarget(imageView){
                            @Override
                            public void onLoadStarted(Drawable placeholder) {
                                super.onLoadStarted(placeholder);
                                loading.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                super.onLoadFailed(e, errorDrawable);
                                loading.setVisibility(View.GONE);
                            }

                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                                super.onResourceReady(resource, animation);
                                loading.setVisibility(View.GONE);
                            }
                        });
            }else {
                Glide.with(context)
                        .load(imgurl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存多个尺寸
                        .thumbnail(0.1f)//先显示缩略图  缩略图为原图的1/10
                        .error(R.mipmap.ic_launcher)
                        .dontAnimate()
                        .into(new GlideDrawableImageViewTarget(imageView){
                            @Override
                            public void onLoadStarted(Drawable placeholder) {
                                super.onLoadStarted(placeholder);
                                loading.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                super.onLoadFailed(e, errorDrawable);
                                loading.setVisibility(View.GONE);
                            }

                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                                super.onResourceReady(resource, animation);
                                loading.setVisibility(View.GONE);
                            }
                        });
            }
            container.addView(view, 0);
            return view;
        }

        /**
         * 弹出保存图片的对话框
         */
        private void dialog(final String url){
            //先new出一个监听器，设置好监听
            DialogInterface.OnClickListener dialogOnclicListener=new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch(which){
                        case Dialog.BUTTON_POSITIVE:
                            Glide.with(context).load(url).asBitmap().into(new SimpleTarget<Bitmap>(){
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    saveImageToGallery(Image_Activity.this,resource,url);
                                    Log.e(TAG, "onResourceReady: "+"success" );
                                }
                            });
                            break;
                        case Dialog.BUTTON_NEGATIVE:
                            break;
                    }
                }
            };
            //dialog参数设置
            AlertDialog.Builder builder=new AlertDialog.Builder(Image_Activity.this);  //先得到构造器
            builder.setTitle("保存图片"); //设置标题
//        builder.setMessage("是否确认退出?"); //设置内容
//        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
            builder.setPositiveButton("保存",dialogOnclicListener);
            builder.setNegativeButton("取消", dialogOnclicListener);
            builder.create().show();
        }

        /**
         * 将bitmap格式转换成byte格式
         * @param bm
         * @return
         */
        public byte[] Bitmap2Bytes(Bitmap bm){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
            return baos.toByteArray();
        }

        /**
         * 保存图片到图库
         * @param context
         * @param bmp
         */
        public void saveImageToGallery(Context context, Bitmap bmp,String url) {
            // 首先保存图片
            File appDir = new File(Environment.getExternalStorageDirectory(), "baisi");
            if (!appDir.exists()) {
                appDir.mkdir();
            }
            String fileName;
            if (IsGIF(url)){
                fileName = System.currentTimeMillis() + ".gif";
            }else {
                fileName = System.currentTimeMillis() + ".jpg";
            }
            File file = new File(appDir, fileName);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                if(IsGIF(url)){
                    byte[] bytes = Bitmap2Bytes(bmp);
                    fos.write(bytes);
                }else {
                    bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                }
                fos.close();
                Toast.makeText(context,"文件已保存到"+Environment.getExternalStorageDirectory()+"/baisi",Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 最后通知图库更新
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
        }

         /**
         * 判断图片是否为GIF图片
         */
        private boolean IsGIF(String url){
            if (url.substring(url.length()-3,url.length()).equals("gif")){
//            Log.e(TAG, "IsGIF: "+ url.substring(url.length()-3,url.length()));
                return true;
            }else {
//            Log.e(TAG, "IsNotGIF: "+ url.substring(url.length()-3,url.length()));
                return false;
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }

    public static class ImageSize implements Serializable {

        private int width;
        private int height;

        public ImageSize(int width, int height){
            this.width = width;
            this.height = height;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}
