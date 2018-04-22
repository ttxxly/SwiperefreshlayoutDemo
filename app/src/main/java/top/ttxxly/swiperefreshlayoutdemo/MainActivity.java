package top.ttxxly.swiperefreshlayoutdemo;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeLayout;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> data;
    private boolean isRefresh = false;//是否刷新中

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        //设置SwipeRefreshLayout
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);

        //设置刷新动画切换颜色
        mSwipeLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED);

        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        mSwipeLayout.setDistanceToTriggerSync(300);
        // 设定下拉圆圈的背景
        mSwipeLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        // 设置圆圈的大小
        mSwipeLayout.setSize(SwipeRefreshLayout.LARGE);

        //设置下拉刷新的监听
        mSwipeLayout.setOnRefreshListener(this);
    }

    /*
     * 初始化  设置ListView
     */
    private void init() {
        mListView = (ListView) findViewById(R.id.list_view);

        data = new ArrayList<String>();


        data.add("语文");
        data.add("数学");
        data.add("英语");

        //初始化adapter
        mAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);

        mListView.setAdapter(mAdapter);
    }

    /*
     * 监听器SwipeRefreshLayout.OnRefreshListener中的方法，当下拉刷新后触发
     */
    public void onRefresh() {
        //检查是否处于刷新状态
        if (!isRefresh) {
            isRefresh = true;
            //模拟加载网络数据，这里设置4秒，正好能看到4色进度条
            new Handler().postDelayed(new Runnable() {
                public void run() {

                    //显示或隐藏刷新进度条
                    mSwipeLayout.setRefreshing(false);
                    //修改adapter的数据
                    data.add("化学");
                    data.add("生物");
                    data.add("历史");
                    data.add("政治");
                    mAdapter.notifyDataSetChanged();
                    isRefresh = false;
                }
            }, 3000);
        }
    }
}