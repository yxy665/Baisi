package demo.yang.com.baisi.PersonalCenter.Bean;

/**
 * Created by yxy on 2017/3/15.
 * email:1084625746@qq.com
 */

public class Recommend_Subscribe_bean {
    private int img_id;
    private String title_mySubscribe;
    private String num_mySubscribe;
    private String num_mySubscribe_essence;

    public Recommend_Subscribe_bean(int img_id, String title_mySubscribe, String num_mySubscribe, String num_mySubscribe_essence) {
        this.img_id = img_id;
        this.title_mySubscribe = title_mySubscribe;
        this.num_mySubscribe = num_mySubscribe;
        this.num_mySubscribe_essence = num_mySubscribe_essence;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getTitle_mySubscribe() {
        return title_mySubscribe;
    }

    public void setTitle_mySubscribe(String title_mySubscribe) {
        this.title_mySubscribe = title_mySubscribe;
    }

    public String getNum_mySubscribe() {
        return num_mySubscribe;
    }

    public void setNum_mySubscribe(String num_mySubscribe) {
        this.num_mySubscribe = num_mySubscribe;
    }

    public String getNum_mySubscribe_essence() {
        return num_mySubscribe_essence;
    }

    public void setNum_mySubscribe_essence(String num_mySubscribe_essence) {
        this.num_mySubscribe_essence = num_mySubscribe_essence;
    }
}
