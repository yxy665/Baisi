package demo.yang.com.baisi.Essence.Model;

import demo.yang.com.baisi.Essence.Bean.Essence_bean_all;
import demo.yang.com.baisi.Essence.Bean.Essence_bean_duanzi;
import demo.yang.com.baisi.Essence.Bean.Essence_bean_picture;
import demo.yang.com.baisi.Essence.Bean.Essence_bean_video;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yxy on 2017/3/30.
 * email:1084625746@qq.com
 */

public interface Essence_server {
    @GET("api/api_open.php")
    Observable<Essence_bean_duanzi> getEssenceDuanzi(@Query("a") String a, @Query("c") String c, @Query("maxtime") String maxtime, @Query("type") int type);
    @GET("api/api_open.php")
    Observable<Essence_bean_duanzi> getEssenceDuanzi_First(@Query("a") String a, @Query("c") String c, @Query("type") int type);
    @GET("api/api_open.php")
    Observable<Essence_bean_picture> getEssencePicture(@Query("a") String a, @Query("c") String c, @Query("maxtime") String maxtime, @Query("type") int type);
    @GET("api/api_open.php")
    Observable<Essence_bean_picture> getEssencePicture_First(@Query("a") String a, @Query("c") String c, @Query("type") int type);
    @GET("api/api_open.php")
    Observable<Essence_bean_video> getEssenceShipin(@Query("a") String a, @Query("c") String c, @Query("maxtime") String maxtime, @Query("type") int type);
    @GET("api/api_open.php")
    Observable<Essence_bean_video> getEssenceShipin_First(@Query("a") String a, @Query("c") String c, @Query("type") int type);
    @GET("api/api_open.php")
    Observable<Essence_bean_all> getEssenceAll(@Query("a") String a, @Query("c") String c, @Query("maxtime") String maxtime, @Query("type") int type);
    @GET("api/api_open.php")
    Observable<Essence_bean_all> getEssenceAll_First(@Query("a") String a, @Query("c") String c, @Query("type") int type);
}
