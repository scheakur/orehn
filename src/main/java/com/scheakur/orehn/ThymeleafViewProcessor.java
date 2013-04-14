package com.scheakur.orehn;

import com.sun.jersey.api.view.Viewable;
import com.sun.jersey.spi.template.ViewProcessor;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Provider
public class ThymeleafViewProcessor implements ViewProcessor<String> {

    @Context
    ServletContext context;

    @Context
    ThreadLocal<HttpServletRequest> request;

    @Context
    ThreadLocal<HttpServletResponse> response;

    TemplateEngine tplEngine;

    public ThymeleafViewProcessor() {
        TemplateResolver resolver = new ServletContextTemplateResolver();

        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheTTLMs(3600000L);

        tplEngine = new TemplateEngine();
        tplEngine.setTemplateResolver(resolver);
    }


    @Override
    public String resolve(final String path) {
        return path;
    }


    @Override
    public void writeTo(
            String resolvedPath, Viewable viewable, OutputStream out)
            throws IOException {
        out.flush();

        HttpServletRequest req = request.get();
        HttpServletResponse res = response.get();
        res.setCharacterEncoding("UTF-8");

        WebContext ctx = new WebContext(req, res, context, req.getLocale());

        Map<String, Object> variables = new HashMap<>();
        variables.put("it", viewable.getModel());
        ctx.setVariables(variables);
        tplEngine.process(viewable.getTemplateName(), ctx, res.getWriter());
    }

}