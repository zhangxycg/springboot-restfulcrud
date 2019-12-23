package com.zxy.springboot.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Description:
 * @Author: zhangxy
 * @Date: Created in 2019/12/23
 */
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter 执行了。。。");
        // 放行请求
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
