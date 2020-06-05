package com.broad.web.framework.dozer;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author broad
 * @date 20200111
 **/
@Configuration
public class DozerConfiguration {


    @Bean
    public DozerUtils getDozerUtils() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        return new DozerUtils(mapper);
    }

}
