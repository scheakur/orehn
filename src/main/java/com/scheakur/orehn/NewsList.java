package com.scheakur.orehn;

import com.sun.jersey.api.view.Viewable;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Path("/")
@Singleton
public class NewsList {

    private final DataStore ds;

    public NewsList(@Inject DataStore ds) {
        this.ds = ds;
    }


    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable view() throws ExecutionException {
        List<News> newsList = ds.getNewsList();
        return new Viewable("newslist", newsList);
    }

}
