package com.scheakur.orehn;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommentScraper {

    private final String url;

    public CommentScraper(String id) {
        this.url = CommentList.getHackerNewsUrl(id);
    }

    public List<Comment> scrape() {
        try {
            Document doc = Jsoup.connect(url).get();

            Elements comments = doc.select(".default");

            List<Comment> list = new ArrayList<>();

            for (Element comment : comments) {
                Comment.Builder builder = new Comment.Builder();
                builder.level = getLevel(comment);

                Elements textEls = comment.select(".comment font");

                int len = textEls.size() - 1;
                if (len < 0) {
                    continue;
                }
                len = (len == 0) ? 1 : len;

                for (int i = 0; i < len; i++) {
                    Element textEl = textEls.get(i);
                    builder.add(textEl.toString());
                }

                Element user = comment.select(".comhead a").first();
                builder.user = user.text();

                list.add(builder.build());
            }

            return list;
        } catch (IOException ignore) {}

        return Collections.emptyList();
    }


    int getLevel(Element comment) {
        Element spacer = comment.parent().child(0);
        String levelStr = spacer.select("img").first().attr("width");
        try {
            int level = Integer.parseInt(levelStr) / 40;
            if (level > 9) {
                level = 9;
            }
            return level;
        } catch (NumberFormatException ignore) {}
        return 0;
    }

}
