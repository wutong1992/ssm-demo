package com.zhu.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author ZHUTAO
 */
@SpringBootApplication
@MapperScan("com.zhu.demo.dao")
public class DemoApplication extends SpringBootServletInitializer {

    /**
     * 启动方式一
     * @param args
     */
//    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);
//    }

    /**
     * 启动方式二
     * @param args
     */
//    public static void main(String[] args) {
//        SpringApplication app = new SpringApplication(DemoApplication.class);
//        app.setBannerMode(Banner.Mode.OFF);
//        app.run(args);
//    }

    /**
     * 启动方式三
     * @param args
     */
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(DemoApplication.class)
                //设置关闭banner
//                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    /**
     * 如此配置打包后可以war包才可在tomcat下使用
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoApplication.class);
    }
}
