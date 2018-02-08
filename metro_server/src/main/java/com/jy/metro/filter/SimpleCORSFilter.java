package com.jy.metro.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CORS过滤器
 * Created by Saber on 16/4/25.
 */
@Component
public class SimpleCORSFilter implements Filter {

    public final static String a = "a.taofairy.com";
    public final static String b = "b.taofairy.com";
    public final static String c = "s.taofairy.com";
    public final static String d = "yfl.taofairy.com";
    public final static String e = "yfl2.taofairy.com";

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

    public static void main(String[] args) {
        String a = "http://b.taofairy.com";
        String b = "http://b.taofairy.com:9988/wangbaDictionaryapi/getNameForEdit.json";
        System.out.println(a.matches(b));
        System.out.println(b.contains(a));
    }
}
