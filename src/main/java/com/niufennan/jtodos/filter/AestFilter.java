package com.niufennan.jtodos.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "AestFilter",urlPatterns = "/*")
public class AestFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Bef_TestFilter");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Aft_TestFilter");
    }
    public void destroy() {
    }
}
