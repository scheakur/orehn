package com.scheakur.orehn;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

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
                .expireAfterWrite(360, TimeUnit.SECONDS)
                .build();

        comment = CacheBuilder.newBuilder()
                .initialCapacity(60)
                .maximumSize(60)
                .expireAfterWrite(600, TimeUnit.SECONDS)
                .build();

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        TimerTask scrape = new TimerTask() {
            @Override
            public void run() {
                List<News> newsList = new NewsScraper(Orehn.HACKER_NEWS_URL).scrape();
                news.put(NEWSLIST, newsList);
                for (News news : newsList) {
                    List<Comment> commentList = new CommentScraper(news.id).scrape();
                    comment.put(news.id, commentList);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }

            }
        };

        executor.scheduleWithFixedDelay(scrape, 1, 300, TimeUnit.SECONDS);
    }


    public List<News> getNewsList() throws ExecutionException {
        List<News> newsList = news.get(NEWSLIST, new Callable<List<News>>() {
            @Override
            public List<News> call() throws Exception {
                return new NewsScraper(Orehn.HACKER_NEWS_URL).scrape();
            }
        });

        return newsList;
    }


    public List<Comment> getCommentList(final String id) throws ExecutionException {
        List<Comment> commentList = comment.get(id, new Callable<List<Comment>>() {
            @Override
            public List<Comment> call() throws Exception {
                List<Comment> comments = new CommentScraper(id).scrape();
                return comments;
            }
        });

        return commentList;
    }

}