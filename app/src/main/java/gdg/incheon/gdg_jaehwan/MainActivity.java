package gdg.incheon.gdg_jaehwan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    EditText keywordView;
    ListView listView;
    SwipeRefreshLayout refreshLayout;
    ImageAdapter mAdapter;

    boolean isUpdate = false;
    boolean isLastItem = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        keywordView = (EditText) findViewById(R.id.edit_keyword);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String keyword = mAdapter.getKeyword();
                searchMovie(keyword);
            }
        });
        listView = (ListView) findViewById(R.id.listView);

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageItem mItem = (ImageItem) listView.getItemAtPosition(position);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mItem.link)));
            }
        });

        mAdapter = new ImageAdapter();
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

                ApiClient apiClient = NetworkManager.getIntance().getRetrofit(ApiClient.class);

                Call<SearchResult> call = apiClient.searchMovieList(Define.KEY, keyword, 10, startIndex, Define.FORMAT);

                call.enqueue(new Callback<SearchResult>() {
                    @Override
                    public void onResponse(Response<SearchResult> response, Retrofit retrofit) {
                        Log.d("bookTest", "network success");

                        for (ImageItem item : response.body().channel.item) {
                            mAdapter.add(item);
                        }
                        isUpdate = false;

                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.e("imageTest", "network failure");
                        Log.e("imageTest", t.getMessage());
                        Log.e("imageTest", t.toString());
                        t.printStackTrace();
                        isUpdate = false;
                    }
                });

            }
        }
    }

    private void searchMovie(final String keyword) {
        if (!TextUtils.isEmpty(keyword)) {

            ApiClient apiClient = NetworkManager.getIntance().getRetrofit(ApiClient.class);

            Call<SearchResult> call = apiClient.searchMovieList(Define.KEY, keyword, 10, 1, Define.FORMAT);

            call.enqueue(new Callback<SearchResult>() {
                @Override
                public void onResponse(Response<SearchResult> response, Retrofit retrofit) {
                    Log.d("imageTest", "network success");
                    Log.d("message",response.message());

                    mAdapter.setKeyword(keyword);
                    mAdapter.setTotalCount(Integer.parseInt(response.body().channel.totalCount));
                    Log.d("카운트 : ","카운트"+response.body().channel.totalCount);
                    mAdapter.clear();
                    for (ImageItem item : response.body().channel.item) {
                        mAdapter.add(item);
                        Log.d("아이템이름 : ",item.title);
                    }
                    refreshLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            refreshLayout.setRefreshing(false);
                        }
                    }, 2000);
                    Log.d("응답끝","end");
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e("imageTest", "network failure");
                    Log.e("imageTest", t.getMessage());
                    Log.e("imageTest", t.toString());
                    t.printStackTrace();
                    isUpdate = false;
                }
            });
        } else {
            mAdapter.clear();
            mAdapter.setKeyword(keyword);
        }
    }
}
