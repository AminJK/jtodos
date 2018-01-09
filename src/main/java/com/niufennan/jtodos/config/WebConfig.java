package com.niufennan.jtodos.config;


import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.niufennan.jtodos.authority.SysPermissionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

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
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder().indentOutput(true).
                dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).modulesToInstall(new ParameterNamesModule());

        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SysPermissionInterceptor());
    }
    public void  configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();//配置静态资源的处理
    }
    //跨域
    public void addCorsMappings(CorsRegistry registry){
       registry.addMapping("/**");
    }
}
