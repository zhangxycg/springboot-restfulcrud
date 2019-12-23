package com.zxy.springboot.config;

import com.zxy.springboot.filter.MyFilter;
import com.zxy.springboot.listener.MyListener;
import com.zxy.springboot.servlet.MySevlet;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;

/**
 * @Description:
 * @Author: zhangxy
 * @Date: Created in 2019/12/23
 */
@Configuration
public class MyServerConfig {

    /**
     * 注册servlet组件
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean myServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MySevlet(), "/myServlet");
        // 设置启动顺序
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    /**
     * 注册filter组件
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        // 设置filter
        filterRegistrationBean.setFilter(new MyFilter());
        // 设置拦截uri的路径
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/hello", "/myServlet"));
        return filterRegistrationBean;
    }

    /**
     * 注册Listener
     *
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean myListener() {
        ServletListenerRegistrationBean<MyListener> registrationBean =
                new ServletListenerRegistrationBean<>(new MyListener());
        return registrationBean;
    }

    /**
     * 配置嵌入式的Servlet容器
     *
     * @return
     */
    @Bean //一定要将这个定制器加入到容器中
    public WebServerFactoryCustomizer webServerFactory() {
        return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>() {
            //定制嵌入式的Servlet容器相关的规则
            @Override
            public void customize(ConfigurableServletWebServerFactory factory) {
                factory.setPort(8088);
            }
        };
    }
}
