package com.niufennan.jtodos.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter( filterName = "encodingFilter",urlPatterns="/*")
public class EncodingFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        System.out.println("Bef_EncodingFilter");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Aft_EncodingFilter");
    }

    public void destroy() {

    }
}
