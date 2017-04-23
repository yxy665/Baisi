package demo.yang.com.baisi.Essence.View;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import demo.yang.com.baisi.Essence.Adapter.Essence_All_Adapter;
import demo.yang.com.baisi.Essence.Adapter.Essence_Duanzi_Adapter;
import demo.yang.com.baisi.Essence.Adapter.Essence_Picture_Adapter;
import demo.yang.com.baisi.Essence.Adapter.Essence_Video_Adapter;
import demo.yang.com.baisi.Essence.Bean.Essence_content_all;
import demo.yang.com.baisi.Essence.Bean.Essence_content_duanzi;
import demo.yang.com.baisi.Essence.Bean.Essence_content_picture;
import demo.yang.com.baisi.Essence.Bean.Essence_content_video;
import demo.yang.com.baisi.Essence.Presenter.Essence_content_Presenter;
import demo.yang.com.baisi.R;
import demo.yang.com.baisi.Widget.PullRefreshLayout;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;

public class Essence_content_Fragment extends Fragment implements Essence_SetData_ViewInterface {

    private RecyclerView recyclerView;
    private TextView baseText;
    private Essence_Duanzi_Adapter essence_duanzi_adapter;
    private Essence_Picture_Adapter essence_picture_adapter;
    private Essence_Video_Adapter essence_video_adapter;
    private Essence_All_Adapter essence_all_adapter;
    private LinearLayoutManager manager;
    private List<Essence_content_duanzi> list_duanzi;
    private List<Essence_content_picture> list_picture;
    private PullRefreshLayout pullRefreshLayout;
    private Essence_content_Presenter essence_content_presenter;
    private String maxtime = null;
    private int lastVisibleItem;
    private JCVideoPlayerStandard currPlayer;
    private RecyclerView.OnScrollListener onScrollListener;
    private int firstVisible;//当前第一个可见的item
    private int visibleCount;//当前可见的item个数


    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    public Essence_content_Fragment() {
        // Required empty public constructor
    }

    public static Essence_content_Fragment newInstance(String param1) {
        Essence_content_Fragment fragment = new Essence_content_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        essence_duanzi_adapter = new Essence_Duanzi_Adapter(getContext());
        essence_picture_adapter = new Essence_Picture_Adapter(getContext());
        essence_video_adapter = new Essence_Video_Adapter(getContext());
        essence_all_adapter = new Essence_All_Adapter(getContext());
//        initListener();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        essence_content_presenter = new Essence_content_Presenter(this);
        View view = inflater.inflate(R.layout.fragment_essence_content, container, false);
        baseText = (TextView) view.findViewById(R.id.base_text);
        baseText.setVisibility(View.VISIBLE);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_essence);
        pullRefreshLayout = (PullRefreshLayout) view.findViewById(R.id.essence_content_swiperefreshlayout);
        if (mParam1.equals("duanzi")){
            essence_content_presenter.fetchArticles_getDuanzi_First("list","data",29,0);
            Log.e("Essence_content", "onCreateView: "+"第一次请求！" );
        }else if (mParam1.equals("tupian")){
            essence_content_presenter.fetchArticles_getPicture_First("list","data",10,0);
        }else if (mParam1.equals("shipin")){
            essence_content_presenter.fetchArticles_getVideo_First("list","data",41,0);
        }else if (mParam1.equals("tuijian")){
            essence_content_presenter.fetchArticles_getAll_First("list","data",1,0);
        }
        pullRefreshLayout.setRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void refreshFinished() {
                if (mParam1.equals("duanzi")){
                    essence_content_presenter.fetchArticles_getDuanzi_First("list","data",29,0);//最后的0表示是下拉刷新
                }else if (mParam1.equals("tupian")){
                    essence_content_presenter.fetchArticles_getPicture_First("list","data",10,0);
                }else if (mParam1.equals("shipin")){
                    essence_content_presenter.fetchArticles_getVideo_First("list","data",41,0);
                }else if (mParam1.equals("tuijian")){
                    essence_content_presenter.fetchArticles_getAll_First("list","data",1,0);
                }
            }

            @Override
            public void loadMoreFinished() {
                if (mParam1.equals("duanzi")){
                    essence_content_presenter.fetchArticles_getDuanzi("list","data",maxtime,29,1);//最后的1表示是上拉加载
                }else if (mParam1.equals("tupian")){
                    essence_content_presenter.fetchArticles_getPicture("list","data",maxtime,10,1);
                }else if (mParam1.equals("shipin")){
                    essence_content_presenter.fetchArticles_getVideo("list","data",maxtime,41,1);
                }else if (mParam1.equals("tuijian")){
                    essence_content_presenter.fetchArticles_getAll("list","data",maxtime,1,1);
                }
            }
        });
        manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        if (mParam1.equals("duanzi")){
            recyclerView.setAdapter(essence_duanzi_adapter);
        }else if (mParam1.equals("tupian")){
            recyclerView.setAdapter(essence_picture_adapter);
        }else if (mParam1.equals("shipin")){
            recyclerView.setAdapter(essence_video_adapter);
            initListener();
        }else if (mParam1.equals("tuijian")){
            recyclerView.setAdapter(essence_all_adapter);
            initListener();
        }
        return view;
    }

    /**
     * 滑动监听
     */
    private void initListener() {
        onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState){
                    case SCROLL_STATE_IDLE:
                        autoPlayVideo(recyclerView);
                        break;
                    case SCROLL_STATE_DRAGGING:
                        break;
                    case SCROLL_STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        };

        recyclerView.addOnScrollListener(onScrollListener);
    }

    /**
     * 滑动停止自动播放视频
     * @param view
     */
    private void autoPlayVideo(RecyclerView view) {

        for (int i = 0; i < visibleCount; i++) {
            if (view != null && view.getChildAt(i) != null && view.getChildAt(i).findViewById(R.id.player_list_video) != null) {
                currPlayer = (JCVideoPlayerStandard) view.getChildAt(i).findViewById(R.id.player_list_video);
                Rect rect = new Rect();
                //获取当前view 的 位置
                currPlayer.getLocalVisibleRect(rect);
                int videoheight = currPlayer.getHeight();
                if (rect.top == 0 && rect.bottom == videoheight) {
                    if (currPlayer.currentState == JCVideoPlayer.CURRENT_STATE_NORMAL
                            || currPlayer.currentState == JCVideoPlayer.CURRENT_STATE_ERROR) {
                        currPlayer.startButton.performClick();
                    }
                    return;
                }
            }
        }
        //释放其他视频资源
        JCVideoPlayer.releaseAllVideos();
    }


    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void setData_Duanzi(List<Essence_content_duanzi> list,int status) {
        Log.e("Essence_content", "setData_Duanzi: "+list.get(0).getScreen_name() );
        if (list != null){
            baseText.setVisibility(View.GONE);
        }
        if (status == 0){
            essence_duanzi_adapter.setData(list);
            pullRefreshLayout.setRefreshFinished();
        }else if (status == 1){
            essence_duanzi_adapter.addData(list);
            pullRefreshLayout.setLoadMoreFinished();
        }
    }

    @Override
    public void setData_Picture(List<Essence_content_picture> list, int status) {
        if (list != null){
            baseText.setVisibility(View.GONE);
        }
        if (status == 0){
            essence_picture_adapter.setData(list);
            pullRefreshLayout.setRefreshFinished();
        }else if (status == 1){
            essence_picture_adapter.addData(list);
            pullRefreshLayout.setLoadMoreFinished();
        }
    }

    @Override
    public void setData_Video(List<Essence_content_video> list, int status) {
        if (list != null){
            baseText.setVisibility(View.GONE);
        }
        if (status == 0){
            essence_video_adapter.setData(list);
            pullRefreshLayout.setRefreshFinished();
        }else if (status == 1){
            essence_video_adapter.addData(list);
            pullRefreshLayout.setLoadMoreFinished();
        }
    }

    @Override
    public void setData_All(List<Essence_content_all> list, int status) {
        if (list != null){
            baseText.setVisibility(View.GONE);
        }
        if (status == 0){
            essence_all_adapter.setData(list);
            pullRefreshLayout.setRefreshFinished();
        }else if (status == 1){
            essence_all_adapter.addData(list);
            pullRefreshLayout.setLoadMoreFinished();
        }
    }

    @Override
    public void getDuanzi_maxtime(String maxtime) {
        this.maxtime = maxtime;
    }

}
