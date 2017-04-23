package demo.yang.com.baisi.Widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.yang.com.baisi.R;


/**
 * 有全文和收起的TextView
 * Created by yxy on 2017/3/18.
 * email:1084625746@qq.com
 */

public class ExpandTextView extends LinearLayout {
    public static final int DEFAULT_MAX_LINES = 3;
    private TextView contentText;
    private TextView textPlus;
    private OnExpandTextViewClicklistener onExpandTextViewClicklistener;

    private int showLines;


    public ExpandTextView(Context context) {
        super(context);
        initView();
    }

    public ExpandTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        initView();
    }

    public ExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initView();
    }

    private void initView() {
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_magic_text, this);
        contentText = (TextView) findViewById(R.id.contentText);
        if(showLines > 0){
//            contentText.setMaxLines(showLines);//设定显示行数
            contentText.setMaxLines(showLines);
        }

        contentText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onExpandTextViewClicklistener.onExpandTextViewClick();
            }
        });

        textPlus = (TextView) findViewById(R.id.textPlus);//扩展点击事件
        textPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String textStr = textPlus.getText().toString().trim();
                if("全文".equals(textStr)){
                    contentText.setMaxLines(Integer.MAX_VALUE);
                    textPlus.setText("收起");
                }else{
                    contentText.setMaxLines(showLines);
                    textPlus.setText("全文");
                }
            }
        });
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ExpandTextView, 0, 0);//属性
        try {
            showLines = typedArray.getInt(R.styleable.ExpandTextView_showLines, DEFAULT_MAX_LINES);//显示属性行数
        }finally {
            typedArray.recycle();
        }
    }

    public void setText(final CharSequence content){
        contentText.post(new Runnable() {
            @Override
            public void run() {
                contentText.setText(content);
                int linCount = contentText.getLineCount();
                if(linCount > showLines){
                    contentText.setMaxLines(showLines);
                    textPlus.setVisibility(View.VISIBLE);
                    textPlus.setText("全文");
                }else{
                    textPlus.setVisibility(View.GONE);
                }
            }
        });

    }

    public OnExpandTextViewClicklistener getOnExpandTextViewClicklistener() {
        return onExpandTextViewClicklistener;
    }

    public void setOnExpandTextViewClicklistener(OnExpandTextViewClicklistener onExpandTextViewClicklistener) {
        this.onExpandTextViewClicklistener = onExpandTextViewClicklistener;
    }

    public interface  OnExpandTextViewClicklistener{
        public void onExpandTextViewClick();
    }

}
