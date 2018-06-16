package com.mario.newsapiexample.components.ui.main.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.mario.newsapiexample.R;
import com.mario.newsapiexample.components.base.BaseDialogFragment;
import com.mario.newsapiexample.components.ui.main.adapter.MainAdapter;
import com.mario.newsapiexample.components.ui.main.search.SearchFragment;
import com.mario.newsapiexample.data.model.news.Article;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

public class NewsFragment extends BaseDialogFragment<NewsContract.Presenter> implements NewsContract.View {

    @Inject
    NewsContract.Presenter presenter;

    @BindView(R.id.recyclerView_news)
    RecyclerView recyclerViewNews;
    @BindView(R.id.editText_search)
    EditText editTextNews;

    private MainAdapter mainAdapter;
    private LinearLayoutManager layoutManager;
    private Disposable disposable;

    @Inject
    public NewsFragment() {
    }

    @Override
    protected NewsContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter != null) {
            presenter.setView(this);
            presenter.fetchLatestNews();
            presenter.fetchTopHeadlines();
        }

        mainAdapter = new MainAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerViewNews.setLayoutManager(layoutManager);
        recyclerViewNews.setHasFixedSize(true);
        recyclerViewNews.setAdapter(mainAdapter);

        editTextNews.setFocusable(false);

        editTextNews.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(getId(), new SearchFragment())
                    .addToBackStack(null).commit();
        });
    }


    @Override
    public void showTopHeadlines(List<Article> headlinesList) {
        mainAdapter.setHeadlineItems(headlinesList);
    }

    @Override
    public void showLatestNews(List<Article> newsList) {
        mainAdapter.setNewsItems(newsList);
    }

}
