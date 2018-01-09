package com.niufennan.jtodos.authority;


import com.niufennan.jtodos.utils.Token;
import com.niufennan.jtodos.utils.TokenUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SysPermissionInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //---------------test---------------------
        Enumeration<String> headers= request.getHeaderNames();
        while (headers.hasMoreElements()){
            String name=headers.nextElement();
            System.out.println(name+"__"+request.getHeader(name));
        }
        //得到请求的url和token
        //除登录, 注册，必须有token
        String url = request.getRequestURI();
        //无权限页面直接过去 不用拦截
        if(url.contains("/denied")){
            return true;
        }
        String token= request.getHeader("token");
        //判断失败 直接跳到无权限页
        if (checkToken(token)&&checkUrl(url)) {
            request.getRequestDispatcher("/denied").forward(request,response);
            return false;
        }
        if(checkUrl(url)) {
            long id = TokenUtil.volidateToken(token);
            if (id == -1) {
                request.getRequestDispatcher("/denied").forward(request, response);
                return false;
            }
            //防止id重复 将id注入到请求里
            request.setAttribute("tokenId", id);
        }
        return  true;
    }
    //在执行handler返回modelAndView之前来执行
    //如果需要向页面提供一些公用 的数据或配置一些视图信息，使用此方法实现 从modelAndView入手
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //System.out.println("HandlerInterceptor1...postHandle");

    }
    //执行handler之后执行此方法
    //作系统 统一异常处理，进行方法执行性能监控，在preHandle中设置一个时间点，在afterCompletion设置一个时间，两个时间点的差就是执行时长
    //实现 系统 统一日志记录
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
       //System.out.println("HandlerInterceptor1...afterCompletion");
    }
    //帮助方法
    private boolean checkToken(String token){
        return null==token||"".equals(token);
    }
    //帮助方法
    private boolean checkUrl(String url){
        if(url.contains("/login")) return false;
        if(url.contains("/denied")) return false;
        if(url.contains("/register")) return false;
        if(url.contains("/todos")) return false;
        return true;
    }
}
