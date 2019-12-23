package com.zxy.springboot.config;

import com.zxy.springboot.component.LoginHandlerInterceptor;
import com.zxy.springboot.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: zhangxy
 * @Date: Created in 2019/12/18
 */

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

  // 注意：2.0以上版本需要实现 WebMvcConfigurer ，而不是WebMvcConfigurerAdapter
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        // 浏览器发送 /zxy请求来到success页面
        registry.addViewController("/zxy").setViewName("success");
    }

    /**
     * 注册拦截器
     *1
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 拦截所有的请求;  excludePathPatterns("xxx") 排除不需要拦截的请求
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/","/asserts/**","/webjars/**","/user/login");
    }

    // 所有的WebMvcConfigurer组件会一起起作用
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer confogurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");

            }
        };

        return confogurer;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
