package com.lll.springboot.config;

import com.lll.springboot.component.LoginHandlerInterceptor;
import com.lll.springboot.servlet.MyFilter;
import com.lll.springboot.servlet.MyListener;
import com.lll.springboot.servlet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
public class MyMvcConfig implements WebMvcConfigurer {
    /**
     * 注册servlet三大组件
     * @return
     */
    @Bean
    public ServletRegistrationBean myServlet() {
         ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet(),"/helloworld");

         return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean filterRegistrationBean =  new FilterRegistrationBean(new MyFilter());
        filterRegistrationBean.addUrlPatterns("/helloworld","/hello");
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener() {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean(new MyListener());
        return servletListenerRegistrationBean;
    }

    @Bean
//  (1)、@Bean注解在返回实例的方法上，如果未通过@Bean指定bean的名称，则默认与标注的方法名相同；
//  (2)、@Bean注解默认作用域为单例singleton作用域，可通过@Scope(“prototype”)设置为原型作用域；
//  (3)、既然@Bean的作用是注册bean对象，那么完全可以使用@Component、@Controller、
//  @Service、@Ripository等注解注册bean，当然需要配置@ComponentScan注解进行自动扫描。
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> WebServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8888);
            }
        };
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("/login");
//        registry.addViewController("/login.html").setViewName("/login");
    }

    @Bean
    public WebMvcConfigurer toLogin() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
        };
        return webMvcConfigurer;
    }

    /**
     * 配置登录拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user/login");
    }
}
