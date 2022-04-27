package com.heima.travel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Documentation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author laofang
 * @description
 * @date 2021-06-08
 */
@Configuration
@EnableSwagger2
public class SwaggerApp {
    @Bean
    public Docket createRestApi(){
       return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        //指定api扫描的包
        .apis(RequestHandlerSelectors.basePackage("com.heima.travel"))
        .paths(PathSelectors.any())
        .build();
    }

    private ApiInfo apiInfo() {
        //页面标题
       return   new ApiInfoBuilder()
                //页面标题
                .title("黑马旅游Api")
                //描述
                .description("描述黑马旅游API")
                //作者信息
                .contact(new Contact("黑马旅游","http://localhost:8080","xx@163.com"))
                //版本
                .version("1.0.0")
                .build();
    }
}
