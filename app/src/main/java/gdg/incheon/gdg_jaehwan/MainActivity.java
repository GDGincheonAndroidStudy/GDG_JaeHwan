package gdg.incheon.gdg_jaehwan;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText keywordView;
    ListView listView;
    SwipeRefreshLayout refreshLayout;
    MovieAdapter mAdapter;

    boolean isUpdate = false;
    boolean isLastItem = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        keywordView = (EditText) findViewById(R.id.edit_keyword);
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String keyword = mAdapter.getKeyword();
                searchMovie(keyword);
            }
        });
        listView = (ListView)findViewById(R.id.listView);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (isLastItem && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    getMoreItem();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (totalItemCount > 0 && (firstVisibleItem + visibleItemCount >= totalItemCount - 1)) {
                    isLastItem = true;
                } else {
                    isLastItem = false;
                }
            }
        });

        mAdapter = new MovieAdapter();
        listView.setAdapter(mAdapter);
        keywordView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchMovie(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getMoreItem() {
        if (!isUpdate) {
            String keyword = mAdapter.getKeyword();
            int startIndex = mAdapter.getStartIndex();
            if (!TextUtils.isEmpty(keyword) && startIndex != -1) {
                isUpdate = true;
                NetworkManager.getInstance().getMovies(MainActivity.this, keyword, startIndex, 10, new NetworkManager.OnResultListener<NaverMovies>() {
                    @Override
                    public void onSuccess(NaverMovies result) {
                        for (MovieItem item : result.item) {
                            mAdapter.add(item);
                        }
                        isUpdate = false;
                    }

                    @Override
                    public void onFail(int code) {
                        isUpdate = false;
                    }
                });
            }
        }
    }
    private void searchMovie(final String keyword) {
        if (!TextUtils.isEmpty(keyword)) {
            NetworkManager.getInstance().getMovies(this, keyword, 1, 10, new NetworkManager.OnResultListener<NaverMovies>() {
                @Override
                public void onSuccess(NaverMovies result) {
                    mAdapter.setKeyword(keyword);
                    mAdapter.setTotalCount(result.total);
                    mAdapter.clear();
                    for (MovieItem item : result.item) {
                        mAdapter.add(item);
                    }
                    refreshLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            refreshLayout.setRefreshing(false);
                        }
                    }, 2000);

                }

                @Override
                public void onFail(int code) {
                    Toast.makeText(MainActivity.this, "error : " + code, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            mAdapter.clear();
            mAdapter.setKeyword(keyword);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkManager.getInstance().cancelAll(this);
    }
}
