package com.manning.gwtia.ch12.server;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;

@SuppressWarnings("serial")
public class XmlVideoProxyServlet extends HttpServlet
{
  private String url = "http://gdata.youtube.com/feeds/api/videos";

  @Override
  public void init (ServletConfig config) throws ServletException {
    System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
    System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
    System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire.header", "debug");
    System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "debug");
    super.init(config);
  }
    
  @Override
  protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpClient client = new HttpClient();
    GetMethod method = new GetMethod(url);
    method.setQueryString(new NameValuePair[]{
        new NameValuePair("q", req.getParameter("query")),
        new NameValuePair("max-results", req.getParameter("maxResults")),
        new NameValuePair("v", "2")
    });

    try {
      int statusCode = client.executeMethod(method);
      if (statusCode == HttpStatus.SC_OK) {
        resp.getWriter().write(method.getResponseBodyAsString());
      }
      else {
        throw new RuntimeException("bad response from remote server; " + statusCode);
      }
    }
    catch (Exception e) {
      throw new RuntimeException("client error: " + e);
    }
    finally {
      method.releaseConnection();
    }
  }
  
  @Override
  protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");

    HttpClient client = new HttpClient();
    GetMethod method = new GetMethod(url);
    method.setQueryString(new NameValuePair[]{
        new NameValuePair("q", req.getParameter("query")),
        new NameValuePair("max-results", req.getParameter("maxResults")),
        new NameValuePair("orderby", "published"),
        new NameValuePair("v", "2")
    });

    try {
      int statusCode = client.executeMethod(method);
      if (statusCode == HttpStatus.SC_OK) {
        resp.getWriter().write(URLEncoder.encode(method.getResponseBodyAsString(), "UTF-8"));
      }
      else {
        throw new RuntimeException("bad response from remote server; " + statusCode);
      }
    }
    catch (Exception e) {
      throw new RuntimeException("client error: " + e);
    }
    finally {
      method.releaseConnection();
    }
  }
  
}
