package demo.yang.com.baisi.MainActivity.View;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import demo.yang.com.baisi.Essence.View.Essence_Fragment;
import demo.yang.com.baisi.MainActivity.Adapter.GridViewAdapter;
import demo.yang.com.baisi.PersonalCenter.View.PersonalCenter_Fragment;
import demo.yang.com.baisi.Posts.View.Posts_Fragment;
import demo.yang.com.baisi.R;
import demo.yang.com.baisi.Release.View.Release_Fragment;
import demo.yang.com.baisi.Shopping.View.Shopping_Fragment;
import demo.yang.com.baisi.Utils.Screen;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener,View.OnClickListener {

    private LayoutInflater layoutInflater;
    private FragmentTabHost fragmentTabHost;
    private Screen screen;
    private GridView gridView;
    private PopupWindow popupWindow;
    private GridViewAdapter gridViewAdapter;
    private ImageView left,right,center,night;
    private TextView toolbar_text,popup_text;

    //存放Fragment的界面
    private Class fragmentArray[] = {Essence_Fragment.class, Posts_Fragment.class, Release_Fragment.class, Shopping_Fragment.class, PersonalCenter_Fragment.class};
    //Tab选项卡的文字
    private String mTabTextArray[] = {"精华","新帖","发布","败家姐","我的"};
    //tab选项卡的图标id
    private int mTabImageResIDArray[] = {R.drawable.square_frament_item_imagebackground,R.drawable.grabwork_fragment_item_imagebackground,R.drawable.release,R.drawable.mywork_fragment_item_imagebackground,R.drawable.personnalcenter_fragment_item_imagebackground};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutInflater = LayoutInflater.from(this);
        initView();
    }

    //初始化组件
    public void initView(){
        left = (ImageView) findViewById(R.id.toolbar_gongxian);
        right = (ImageView) findViewById(R.id.toolbar_chuanyue);
        center = (ImageView) findViewById(R.id.toolbar_img);
        night = (ImageView) findViewById(R.id.toolbar_yejian);
        toolbar_text = (TextView) findViewById(R.id.toolbar_text);
        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(MainActivity.this,getSupportFragmentManager(),R.id.realtabcontent);
        int count = fragmentArray.length;
        fragmentTabHost.getTabWidget().setDividerDrawable(null);
        for (int i = 0;i<count;i++){
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(mTabTextArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中,并绑定对应碎片
            fragmentTabHost.addTab(tabSpec,fragmentArray[i],null);
        }
        //碎片tab切换监听
        fragmentTabHost.setOnTabChangedListener(this);
        //初始化发布菜单
        screen = new Screen(MainActivity.this);
        View view = layoutInflater.inflate(R.layout.popupwindow_main,null);
        gridView = (GridView) view.findViewById(R.id.popupwindow_gridview);
        popup_text = (TextView) view.findViewById(R.id.popupwindow_textview);
        gridViewAdapter = new GridViewAdapter();
        gridView.setAdapter(gridViewAdapter);
        popupWindow = new PopupWindow(view,screen.getWidth(),screen.getHeight()-getStatusBarHeight(),true);
        Log.e("+++", "initView: "+getStatusBarHeight() );
        popupWindow.setTouchable(true);
        ColorDrawable dw = new ColorDrawable(0xffe9e7ef);
        popupWindow.setBackgroundDrawable(dw);
        view.findViewById(R.id.popupwindow_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow.isShowing()){
                    gridView.setLayoutAnimation(getAnimationControllerOut());
                    gridView.startLayoutAnimation();
                    AnimationSet set = new AnimationSet(true);
                    Animation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_SELF, -1.0f,
                            Animation.RELATIVE_TO_SELF, 20.0f);
                    animation.setDuration(300);
                    animation.setInterpolator(new AccelerateInterpolator());
                    set.addAnimation(animation);
                    popup_text.startAnimation(set);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            popupWindow.dismiss();
                        }
                    },350);

                }
            }
        });
        fragmentTabHost.getTabWidget().getChildTabViewAt(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow(view);
            }
        });
        fragmentTabHost.setOnTabChangedListener(this);
    }

    private LayoutAnimationController getAnimationControllerOut(){
        AnimationSet set = new AnimationSet(true);
        Animation animation1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -0.5f,
                Animation.RELATIVE_TO_SELF, 10.0f);
        animation1.setDuration(300);
        animation1.setInterpolator(new AccelerateInterpolator());
        set.addAnimation(animation1);
//        Animation animation2 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
//                Animation.RELATIVE_TO_SELF, 0.0f,
//                Animation.RELATIVE_TO_SELF, -0.5f,
//                Animation.RELATIVE_TO_SELF, 5.0f);
//        animation2.setDuration(150);
//        set.addAnimation(animation2);
        LayoutAnimationController controller = new LayoutAnimationController(set,0.2f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        return controller;
    }

    private LayoutAnimationController getAnimationControllerIn(){
        AnimationSet set = new AnimationSet(true);
        Animation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -3.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(250);
        animation.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(animation);
        LayoutAnimationController controller = new LayoutAnimationController(set,0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        return controller;
    }

    //给TabItem设置图标和文字
    private View getTabItemView(int index){
        View itemView;
        TextView itemTextView;
        ImageView itemImageView;
        itemView = layoutInflater.inflate(R.layout.fragmenthost_tab_item,null);
        itemImageView = (ImageView) itemView.findViewById(R.id.fragmentHost_tab_item_imageView);
        itemTextView = (TextView) itemView.findViewById(R.id.fragmentHost_tab_item_textView);
        itemImageView.setImageResource(mTabImageResIDArray[index]);
        itemTextView.setText(mTabTextArray[index]);
        if (index == 2){//这里是项目需求，设置成了只显示图标
            itemTextView.setVisibility(View.GONE);
        }
        return itemView;
    }

    public void showPopupWindow(View view){
        if (popupWindow != null){
            popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,0,getStatusBarHeight());
            gridView.setLayoutAnimation(getAnimationControllerIn());
            gridView.startLayoutAnimation();
            AnimationSet set = new AnimationSet(true);
            Animation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF,-3.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(250);
            set.addAnimation(animation);
            popup_text.startAnimation(set);
        }else {
            Toast.makeText(MainActivity.this,"正在初始化...",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取状态栏高度
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onTabChanged(String s) {
        if (s.equals("精华")){
            left.setImageResource(R.drawable.essence_left);
            right.setImageResource(R.drawable.essence_right);
            night.setVisibility(View.GONE);
            toolbar_text.setText("百思不得姐");
            center.setVisibility(View.VISIBLE);
        }else if (s.equals("新帖")){
            left.setImageResource(R.drawable.posts_left);
            right.setImageResource(R.drawable.posts_right);
            night.setVisibility(View.GONE);
            toolbar_text.setText("百思不得姐");
            center.setVisibility(View.VISIBLE);
        }else if (s.equals("败家姐")){
            left.setImageResource(R.drawable.shopping_left);
            right.setImageResource(R.drawable.shopping_right);
            night.setVisibility(View.GONE);
            toolbar_text.setVisibility(View.VISIBLE);
            toolbar_text.setText("败家姐");
            center.setVisibility(View.GONE);
        }else if (s.equals("我的")){
            left.setImageResource(R.drawable.personal_left);
            right.setImageResource(R.drawable.personal_right_two);
            night.setVisibility(View.VISIBLE);
            night.setImageResource(R.drawable.personal_right_one);
            toolbar_text.setText("我的");
            center.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            AlertDialog isExit = new AlertDialog.Builder(this).create();
            isExit.setTitle("小逸提示");
            isExit.setMessage("真的要退出了吗？");
            isExit.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            isExit.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            isExit.show();

//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("小逸提示");
//            builder.setMessage("真的要退出了吗？");
//            builder.setNegativeButton("取消",null);
//            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    finish();
//                }
//            });
//            builder.create().show();
        }
        return false;
    }
}
