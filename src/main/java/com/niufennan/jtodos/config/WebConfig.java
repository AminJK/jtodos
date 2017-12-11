package com.niufennan.jtodos.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.niufennan.jtodos.controller")
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");//对视图进行统一管理
        viewResolver.setSuffix(".jsp");//统一使用jsp文件作为视图
        viewResolver.setExposeContextBeansAsAttributes(true);//设置可直接访问上下文bena
        return  viewResolver;
    }

    public void  configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();;//配置静态资源的处理
    }
}
