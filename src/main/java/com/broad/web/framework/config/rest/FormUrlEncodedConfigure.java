package com.broad.web.framework.config.rest;

import com.broad.web.framework.utils.JsonUtils;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.base.Charsets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author broad
 */
@EnableWebMvc
@Configuration
public class FormUrlEncodedConfigure extends WebMvcConfigurationSupport {


    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
    }


    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
    }


    /**
     * 支持cors
     *
     * @return
     */

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        //Date转为String
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Date.class, StringDateSerializer.INSTANCE);
        //反序列化时String转为Date
        simpleModule.addDeserializer(Date.class, StringDateDeserializer.INSTANCE);
        JsonUtils.MAPPER.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(JsonUtils.MAPPER);
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(jackson2HttpMessageConverter);
    }

    /**
     * @return
     */
    @Bean
    @Override
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        final RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
        List<HttpMessageConverter<?>> converters = adapter.getMessageConverters();
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        final MediaType textMedia = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charsets.UTF_8);
        supportedMediaTypes.add(textMedia);
        final MediaType jsonMedia = new MediaType(MediaType.APPLICATION_JSON, Charsets.UTF_8);
        supportedMediaTypes.add(jsonMedia);
        jsonConverter.setSupportedMediaTypes(supportedMediaTypes);
        converters.add(jsonConverter);
        adapter.setMessageConverters(converters);
        return adapter;
    }
}
