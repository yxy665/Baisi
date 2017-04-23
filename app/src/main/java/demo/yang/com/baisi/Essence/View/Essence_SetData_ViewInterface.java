package demo.yang.com.baisi.Essence.View;

import java.util.List;

import demo.yang.com.baisi.Essence.Bean.Essence_content_all;
import demo.yang.com.baisi.Essence.Bean.Essence_content_duanzi;
import demo.yang.com.baisi.Essence.Bean.Essence_content_picture;
import demo.yang.com.baisi.Essence.Bean.Essence_content_video;

/**
 * Created by yxy on 2017/3/30.
 * email:1084625746@qq.com
 */

public interface Essence_SetData_ViewInterface {
    void setData_Duanzi(List<Essence_content_duanzi> list,int status);
    void setData_Picture(List<Essence_content_picture> list, int status);
    void setData_Video(List<Essence_content_video> list, int status);
    void setData_All(List<Essence_content_all> list,int status);
    void getDuanzi_maxtime(String maxtime);
}
