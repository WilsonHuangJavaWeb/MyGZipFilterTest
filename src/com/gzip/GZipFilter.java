package com.gzip;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ki264 on 2017/6/21.
 */
public class GZipFilter implements javax.servlet.Filter {

    private Log log;

    public void destroy() {
    }

    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;


        String acceptEncoding = request.getHeader("Accept-Encoding");
        log.info("Accept-Encoding：" + acceptEncoding);
        System.out.println("Accept-Encoding：" + acceptEncoding);

        if (acceptEncoding != null && acceptEncoding.toLowerCase().indexOf("gzip") != -1) {
            GZipResponseWrapper responseWrapper = new GZipResponseWrapper(response);
            chain.doFilter(request, responseWrapper);
            responseWrapper.finishResponse();
            log.info("1");
        } else {

            chain.doFilter(req, resp);
            log.info("2");
        }


    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {
        log = LogFactory.getLog(this.getClass());
    }

}
