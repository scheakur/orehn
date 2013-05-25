package com.scheakur.orehn;

import com.sun.jersey.api.core.PackagesResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/orehn")
public class Orehn extends PackagesResourceConfig {

    public static final String HACKER_NEWS_URL = "https://news.ycombinator.com";

    public Orehn() {
        super("com.scheakur.orehn");
    }

}
