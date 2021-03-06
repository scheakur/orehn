package com.scheakur.orehn;

import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Collections;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.*;

public class DataStore {

    private static final String NEWSLIST = "newslist";

    private final Cache<String, List<News>> news;

    private final Cache<String, List<Comment>> comment;


    public DataStore() {

        news = CacheBuilder.newBuilder()
                .initialCapacity(1)
                .maximumSize(1)
                .expireAfterWrite(7200, TimeUnit.SECONDS)
                .build();

        comment = CacheBuilder.newBuilder()
                .initialCapacity(60)
                .maximumSize(60)
                .expireAfterWrite(7200, TimeUnit.SECONDS)
                .build();

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        TimerTask scrape = new TimerTask() {
            @Override
            public void run() {
                List<News> newsList = new NewsScraper(Orehn.HACKER_NEWS_URL).scrape();
                if (newsList.isEmpty()) {
                    try {
                        newsList = DataStore.this.getNewsList();
                    } catch (ExecutionException ignore) {
                    }
                }
                news.put(NEWSLIST, newsList);
                for (News news : newsList) {
                    List<Comment> commentList = new CommentScraper(news.id).scrape();
                    comment.put(news.id, commentList);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignore) {
                    }
                }

            }
        };

        executor.scheduleWithFixedDelay(scrape, 1, 3600, TimeUnit.SECONDS);
    }


    public List<News> getNewsList() throws ExecutionException {
        return news.get(NEWSLIST, new Callable<List<News>>() {
            @Override
            public List<News> call() throws Exception {
                return new NewsScraper(Orehn.HACKER_NEWS_URL).scrape();
            }
        });
    }


    public List<Comment> getCommentList(final String id) throws ExecutionException {
        return comment.get(id, new Callable<List<Comment>>() {
            @Override
            public List<Comment> call() throws Exception {
                return new CommentScraper(id).scrape();
            }
        });
    }

}
