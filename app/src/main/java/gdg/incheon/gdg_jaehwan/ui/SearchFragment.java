package gdg.incheon.gdg_jaehwan.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;

import gdg.incheon.gdg_jaehwan.R;
import gdg.incheon.gdg_jaehwan.adapter.SearchImageAdapter;
import gdg.incheon.gdg_jaehwan.data.Define;
import gdg.incheon.gdg_jaehwan.data.SearchItem;
import gdg.incheon.gdg_jaehwan.data.SearchResult;
import gdg.incheon.gdg_jaehwan.network.ApiClient;
import gdg.incheon.gdg_jaehwan.network.NetworkManager;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class SearchFragment extends Fragment {


    FloatingActionButton btnSearch;
    EditText keywordView;
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;
    SearchImageAdapter mAdapter;

    boolean isUpdate = false;
    boolean isLastItem = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_search, container, false);

        keywordView = (EditText) v.findViewById(R.id.edit_keyword);
        refreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String keyword = mAdapter.getKeyword();
                if(!TextUtils.isEmpty(keyword)) {
                    searchMovie(keyword);
                }
            }
        });
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));

        mAdapter = new SearchImageAdapter(getContext());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isLastItem && newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    getMoreItem();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = recyclerView.getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                //int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                int[] firstVisibleItems = null;
                int pastVisibleItems = 0;
                firstVisibleItems = ((StaggeredGridLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPositions(firstVisibleItems);
                if (firstVisibleItems != null && firstVisibleItems.length > 0) {
                    pastVisibleItems = firstVisibleItems[0];
                }


                if (totalItemCount > 0 && (pastVisibleItems + visibleItemCount >= totalItemCount - 1)) {
                    isLastItem = true;
                } else {
                    isLastItem = false;
                }
            }
        });

        btnSearch = (FloatingActionButton) v.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(keywordView.getText().toString())) {
                    Snackbar snackbar = Snackbar
                            .make(v, "검색어를 입력해 주세요", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    searchMovie(keywordView.getText().toString());
                }
            }
        });

        return v;
    }

    private void getMoreItem() {
        if (!isUpdate) {
            String keyword = mAdapter.getKeyword();
            int startIndex = mAdapter.getStartIndex();
            if (!TextUtils.isEmpty(keyword) && startIndex != -1) {
                isUpdate = true;

                ApiClient apiClient = NetworkManager.getIntance().getRetrofit(ApiClient.class);

                Call<SearchResult> call = apiClient.searchImageList(Define.KEY, keyword, 10, startIndex, Define.FORMAT);

                call.enqueue(new Callback<SearchResult>() {
                    @Override
                    public void onResponse(Response<SearchResult> response, Retrofit retrofit) {
                        Log.d("bookTest", "network success");

                        for (SearchItem item : response.body().channel.item) {
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

            Call<SearchResult> call = apiClient.searchImageList(Define.KEY, keyword, 10, 1, Define.FORMAT);

            call.enqueue(new Callback<SearchResult>() {
                @Override
                public void onResponse(Response<SearchResult> response, Retrofit retrofit) {
                    Log.d("imageTest", "network success");
                    Log.d("message", response.message());

                    mAdapter.setKeyword(keyword);
                    mAdapter.setTotalCount(Integer.parseInt(response.body().channel.totalCount));
                    Log.d("카운트 : ", "카운트" + response.body().channel.totalCount);
                    mAdapter.clear();
                    for (SearchItem item : response.body().channel.item) {
                        mAdapter.add(item);
                        Log.d("아이템이름 : ", item.title);
                    }
                    refreshLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            refreshLayout.setRefreshing(false);
                        }
                    }, 2000);
                    Log.d("응답끝", "end");
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
