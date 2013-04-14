package com.scheakur.orehn;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class NewsScraper {

    private static Pattern re(String str) {
        return Pattern.compile(str);
    }

    private static final Pattern COMMENTS = re("(\\d+)\\s+comments");
    private static final Pattern COMMENTS_URL = re("item\\?id=(\\d+)");
    private static final Pattern DOMAIN = re("^\\s*\\((.*)\\)\\s*$");
    private static final Pattern POINTS = re("(\\d+)\\s+points");

    private final String url;

    public NewsScraper(String url) {
        this.url = url;
    }

    public List<News> scrape() {

        try {
            Document doc = Jsoup.connect(url).get();

            Elements trs = doc.select(
                    "body > center > " +
                    "table > tbody > tr > td > " +
                    "table > tbody > tr");

            int num = 0;

            List<News> newsList = new ArrayList<>();

            News.Builder builder = null;

            out: for (Element tr : trs) {
                switch (num % 3) {
                    case 1:
                        Elements titles = tr.select(".title");
                        if (titles.size() < 2) {
                            break out;
                        }
                        builder = new News.Builder();

                        Element titleEl = titles.get(1);
                        Element a = titleEl.select("a").first();
                        builder.title = a.text();
                        builder.url = a.attr("href");
                        Elements comhead = titleEl.select(".comhead");
                        if (comhead.size() > 0) {
                            String domain = comhead.first().text();
                            builder.domain = extract(domain, DOMAIN);
                        }
                        break;

                    case 2:
                        assert builder != null;
                        Element subtext = tr.select(".subtext").first();
                        Elements els = subtext.select("a");

                        if (els.size() > 1) {
                            Element comments = els.get(1);
                            String id = getId(comments);
                            builder.id = id;
                            builder.points = getPoints(subtext);
                            builder.commentsNum = getCommentsNum(comments);
                        }

                        newsList.add(builder.build());
                        break;
                }
                num++;
            }

            return newsList;

        } catch (IOException e) {
        }

        return Collections.emptyList();
    }


    int getCommentsNum(Element comments) {
        String text = comments.text();
        if (text.equals("discuss")) {
            return 0;
        }

        String numStr = extract(text, COMMENTS);
        try {
            int commentsNum = Integer.parseInt(numStr);
            return commentsNum;
        } catch (NumberFormatException ignore) {}
        return 0;
    }


    String getId(Element comments) {
        String id = extract(comments.attr("href"), COMMENTS_URL);
        return id;
    }


    int getPoints(Element subtext) {
        try {
            String pointsStr = subtext.select("span").first().text();
            int points = Integer.parseInt(extract(pointsStr, POINTS));
            return points;
        } catch (NumberFormatException ignore) {}
        return 0;
    }


    String extract(String str, Pattern re) {
       return re.matcher(str).replaceAll("$1");
    }

}
