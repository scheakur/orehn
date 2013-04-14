package com.scheakur.orehn;

public class News {

    public final String id;
    public final String title;
    public final String url;
    public final String domain;
    public final int commentsNum;
    public final String commentsUrl;
    public final int points;


    public static class Builder {

        String id;
        String title;
        String url;
        String domain;
        int commentsNum;
        int points;

        public News build() {
            String url = CommentList.getOrehnUrl(id);
            return new News(id, title, url, domain, commentsNum, url, points);
        }

    }

    public News(
            String id, String title, String url, String domain, int commentsNum,
            String commentsUrl, int points) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.domain = domain;
        this.commentsNum = commentsNum;
        this.commentsUrl = commentsUrl;
        this.points = points;
    }

}
