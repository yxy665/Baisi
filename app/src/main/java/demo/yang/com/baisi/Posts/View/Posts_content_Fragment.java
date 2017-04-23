package demo.yang.com.baisi.Posts.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.yang.com.baisi.R;

public class Posts_content_Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    public Posts_content_Fragment() {
        // Required empty public constructor
    }

    public static Posts_content_Fragment newInstance(String param1) {
        Posts_content_Fragment fragment = new Posts_content_Fragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts_content, container, false);
    }

}
