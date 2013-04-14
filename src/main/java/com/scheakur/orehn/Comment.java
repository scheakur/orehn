package com.scheakur.orehn;

import java.util.ArrayList;
import java.util.List;

public class Comment {

    public static class Builder {

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
            return new Comment(details, level);
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

    public final List<Detail> details;
    public final int level;

    public Comment(List<Detail> details, int level) {
        this.details = details;
        this.level = level;
    }

}
