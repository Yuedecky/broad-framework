package com.broad.web.framework.config.swagger;

import com.broad.web.framework.bean.JwtInfo;
import com.broad.web.framework.utils.AuthUtils;
import com.google.common.base.Predicates;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@EnableSwagger2
@Slf4j
@ConditionalOnProperty(prefix = "swagger2", name = "enable", havingValue = "true")
public class Swagger2Config {

    @Value("${auth.user.token-header}")
    private String tokenHeader;

    @Resource
    private AuthUtils authUtils;

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        String jws = authUtils.generateDefault(JwtInfo.defaultInfo());
        log.info("全局token:{}", jws);
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name(tokenHeader).description("令牌").modelRef(new ModelRef("string"))
                .parameterType("header").defaultValue(jws).required(true).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(Predicates.or(RequestHandlerSelectors.withClassAnnotation(RestController.class),
                        RequestHandlerSelectors.withClassAnnotation(Controller.class)))
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .build().globalOperationParameters(pars)
                //隐藏所有Map类型的参数
                .ignoredParameterTypes(Map.class)
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("布鲁德--服务API")
                .description("API中心！")
                .contact(new Contact("禅道", "http://your.example.com", "bug-report@your.example.com"))
                .version("1.0")
                .build();
    }

}
