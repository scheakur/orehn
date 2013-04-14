package com.scheakur.orehn;

import com.sun.jersey.api.view.Viewable;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Path("/comments/{id}")
@Singleton
public class CommentList {

    private final DataStore ds;

    public CommentList(@Inject DataStore ds) {
        this.ds = ds;
    }

    public static String getHackerNewsUrl(String id) {
        return Orehn.HACKER_NEWS_URL + "/item?id=" + id;
    }

    public static String getOrehnUrl(String id) {
        return "/orehn/comments/" + id;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable getComments(@PathParam("id") final String id) throws ExecutionException {
        List<Comment> comments = ds.getCommentList(id);
        Map<String, Object> it = new HashMap<>();
        it.put("originalUrl", getHackerNewsUrl(id));
        it.put("comments", comments);
        return new Viewable("comments", it);
    }

}
