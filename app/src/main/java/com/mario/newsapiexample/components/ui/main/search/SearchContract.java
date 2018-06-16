package com.mario.newsapiexample.components.ui.main.search;

import com.mario.newsapiexample.components.base.BaseDialogView;
import com.mario.newsapiexample.components.base.BasePresenter;
import com.mario.newsapiexample.data.model.news.Article;

import java.util.List;

/**
 * Created by mario on 14/06/18.
 */

public interface SearchContract {
    interface Presenter extends BasePresenter<View> {
        void searchNews(String keyword, int page);

        void loadNextPage(String keyword, int page);
    }

    interface View extends BaseDialogView<Presenter> {
        void showSearchResults(List<Article> newsList);

        void showNextResults(List<Article> newsList);

        void showNoResults();
    }
}