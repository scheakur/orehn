package com.scheakur.orehn;

import java.util.ArrayList;
import java.util.List;

public class Comment {

    public static class Builder {

        public String user;
        public List<Detail> details;
        public int level;

        public Builder() {
            this.details = new ArrayList<>();
        }

        public Builder add(String text) {
            details.add(new Detail(text));
            return this;
        }

        public Comment build() {
            return new Comment(user, details, level);
        }

    }

    public static class Detail {

        String text;

        public Detail(String text) {
            this.text = text;
        }

        public String toString() {
            return text;
        }

    }

    public final String user;
    public final List<Detail> details;
    public final int level;

    public Comment(String user, List<Detail> details, int level) {
        this.user = user;
        this.details = details;
        this.level = level;
    }

}
