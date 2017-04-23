package demo.yang.com.baisi.Essence.Presenter;

import android.util.Log;

import demo.yang.com.baisi.Essence.Bean.Essence_bean_all;
import demo.yang.com.baisi.Essence.Bean.Essence_bean_duanzi;
import demo.yang.com.baisi.Essence.Bean.Essence_bean_picture;
import demo.yang.com.baisi.Essence.Bean.Essence_bean_video;
import demo.yang.com.baisi.Essence.Model.Essence_server;
import demo.yang.com.baisi.Essence.View.Essence_SetData_ViewInterface;
import demo.yang.com.baisi.MyHttp.MyHttpHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yxy on 2017/3/30.
 * email:1084625746@qq.com
 */

public class Essence_content_Presenter {

    private Essence_SetData_ViewInterface essence_setData_viewInterface;

    public Essence_content_Presenter(Essence_SetData_ViewInterface essence_setData_viewInterface) {
        this.essence_setData_viewInterface = essence_setData_viewInterface;
    }

    public void fetchArticles_getDuanzi(String a, String c, String maxtime, int type, final int status){
        Subscriber<Essence_bean_duanzi> subscriber = new Subscriber<Essence_bean_duanzi>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("presenter", "onError: "+e.getMessage() );
            }

            @Override
            public void onNext(Essence_bean_duanzi essence_bean) {
                essence_setData_viewInterface.setData_Duanzi(essence_bean.getDuanzi(),status);
                essence_setData_viewInterface.getDuanzi_maxtime(essence_bean.getInfo().getMaxtime());
            }
        };
        MyHttpHelper.getInstance().getRetrofit()
                .create(Essence_server.class)
                .getEssenceDuanzi(a, c, maxtime, type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public void fetchArticles_getDuanzi_First(String a, String c,int type, final int status){
        Subscriber<Essence_bean_duanzi> subscriber = new Subscriber<Essence_bean_duanzi>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("presenter", "onError: "+e.getMessage() );
            }

            @Override
            public void onNext(Essence_bean_duanzi essence_bean) {
                essence_setData_viewInterface.setData_Duanzi(essence_bean.getDuanzi(),status);
                essence_setData_viewInterface.getDuanzi_maxtime(essence_bean.getInfo().getMaxtime());
            }
        };
        MyHttpHelper.getInstance().getRetrofit()
                .create(Essence_server.class)
                .getEssenceDuanzi_First(a, c, type)
//                .getTest()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void fetchArticles_getPicture(String a, String c,String maxtime,int type, final int status){
        Subscriber<Essence_bean_picture> subscriber = new Subscriber<Essence_bean_picture>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Essence_bean_picture essence_bean_picture) {
                essence_setData_viewInterface.setData_Picture(essence_bean_picture.getList(),status);
                essence_setData_viewInterface.getDuanzi_maxtime(essence_bean_picture.getInfo().getMaxtime());
            }
        };
        MyHttpHelper.getInstance().getRetrofit()
                .create(Essence_server.class)
                .getEssencePicture(a, c, maxtime, type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void fetchArticles_getPicture_First(String a, String c,int type, final int status){
        Subscriber<Essence_bean_picture> subscriber = new Subscriber<Essence_bean_picture>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Essence_bean_picture essence_bean_picture) {
                essence_setData_viewInterface.setData_Picture(essence_bean_picture.getList(),status);
                essence_setData_viewInterface.getDuanzi_maxtime(essence_bean_picture.getInfo().getMaxtime());
            }
        };
        MyHttpHelper.getInstance().getRetrofit()
                .create(Essence_server.class)
                .getEssencePicture_First(a, c, type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void fetchArticles_getVideo(String a, String c,String maxtime,int type, final int status){
        Subscriber<Essence_bean_video> subscriber = new Subscriber<Essence_bean_video>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Essence_bean_video essence_bean_picture) {
                essence_setData_viewInterface.setData_Video(essence_bean_picture.getList(),status);
                essence_setData_viewInterface.getDuanzi_maxtime(essence_bean_picture.getInfo().getMaxtime());
            }
        };
        MyHttpHelper.getInstance().getRetrofit()
                .create(Essence_server.class)
                .getEssenceShipin(a, c, maxtime, type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void fetchArticles_getVideo_First(String a, String c,int type, final int status){
        Subscriber<Essence_bean_video> subscriber = new Subscriber<Essence_bean_video>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Essence_bean_video essence_bean_picture) {
                essence_setData_viewInterface.setData_Video(essence_bean_picture.getList(),status);
                essence_setData_viewInterface.getDuanzi_maxtime(essence_bean_picture.getInfo().getMaxtime());
            }
        };
        MyHttpHelper.getInstance().getRetrofit()
                .create(Essence_server.class)
                .getEssenceShipin_First(a, c, type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void fetchArticles_getAll(String a, String c,String maxtime,int type, final int status){
        Subscriber<Essence_bean_all> subscriber = new Subscriber<Essence_bean_all>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Essence_bean_all essence_bean_picture) {
                essence_setData_viewInterface.setData_All(essence_bean_picture.getList(),status);
                essence_setData_viewInterface.getDuanzi_maxtime(essence_bean_picture.getInfo().getMaxtime());
            }
        };
        MyHttpHelper.getInstance().getRetrofit()
                .create(Essence_server.class)
                .getEssenceAll(a, c, maxtime, type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void fetchArticles_getAll_First(String a, String c,int type, final int status){
        Subscriber<Essence_bean_all> subscriber = new Subscriber<Essence_bean_all>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Essence_bean_all essence_bean_picture) {
                essence_setData_viewInterface.setData_All(essence_bean_picture.getList(),status);
                essence_setData_viewInterface.getDuanzi_maxtime(essence_bean_picture.getInfo().getMaxtime());
            }
        };
        MyHttpHelper.getInstance().getRetrofit()
                .create(Essence_server.class)
                .getEssenceAll_First(a, c, type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
