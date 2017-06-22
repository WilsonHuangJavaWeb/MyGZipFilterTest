package com.gzip;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Created by ki264 on 2017/6/21.
 */
public class GZipResponseWrapper extends HttpServletResponseWrapper {

    private HttpServletResponse response;

    private GZipOutputStream gZipOutputStream;

    private PrintWriter writer;

    public GZipResponseWrapper(HttpServletResponse response) {
        super(response);
        this.response = response;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (gZipOutputStream == null) {
            gZipOutputStream = new GZipOutputStream(response);
        }
        return gZipOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {


        if (writer == null) {
            writer = new PrintWriter(new OutputStreamWriter(new GZipOutputStream(response), "UTF-8"));
        }
        return writer;
    }


    @Override
    public void setContentLength(int len) {
    }

    @Override
    public void flushBuffer() throws IOException {
        gZipOutputStream.flush();
    }

    public void finishResponse() throws IOException {
        if (gZipOutputStream != null) {
            gZipOutputStream.close();
        }
        if (writer != null) {
            writer.close();
        }
    }
}

