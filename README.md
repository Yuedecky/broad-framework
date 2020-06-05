## 基础的framework封装介绍

1. WebResponse是一个web controller|facade结果的返回的封装，举例如下：


```

public WebResponse<CurTenantAccountLogResultDto> accountLogDetail(@RequestBody @Valid Request<SearchAccountLogDetailDto> request);


````

如果你想要拿到repose返回的结果Dto对象，建议使用hutool-core 提供的对象序列化工具


```

BeanUtil.toBean(tenantDtoWebResponse.getData(),
                CommonTenantDto.class);

```

另外，要说的一点是，不建议在WebResponse建立一个List|set|Map等类似的泛型数据，比如以下：



```

WebResponse<List<BaseDto>> selectDtoPage(@RequestBody @Valid PageRequest<SearchBaseDto> request);

```

因为远程http调用序列化结果可能不会是你想要的集合类型，谨记。


那该怎么做？

把你想要的集合放到单独的dto,比如：


```

@Data
public class ListBaseDto implements Serializable {

    private List<BaseDto> list;
}
```



2. Request，泛型类，包装了请求的数据（data）,支持@Valid注解，使用时只需要把你想要封装的请求参数Dto放到Request<?>中即可。

示例：


```
@RequestBody @Valid Request<SearchAccountLogDetailDto> request

```


3. PageRequest,泛型类，除了Request基础数据的包装，还提供了对分页参数的支持，实际上，该类拥有PageDto属性，该属性用于操作分页场景。


源码如下：

```

import lombok.Data;
import lombok.NoArgsConstructor;
import PageDto;


@Data
@NoArgsConstructor
public class PageRequest<T> extends Request<T> {
    private PageDto page = new PageDto();


    public PageRequest(T data) {
        super(data);
    }
}

```

4. PageDto,分页参数的包装类，拥有pageNum,pageSize属性，默认值分别是1,20



5.ReturnCode 基本web响应的封装类，可以在你的业务项目去继承该类，填充你想要的具体业务场景下的返回的code和msg



6. GlobalExceptionHandler 全局的异常处理器，可以处理基于不同场景下的异常处理。使用时，建议在facade模块下resources目录下建立META-INF目录，新增spring.factories文件，
把该处理器自动注入到spring context中。比如：


```

org.springframework.boot.autoconfigure.EnableAutoConfiguration=BaseDataSourceConfigure
```

7. BaseException基础的异常包装类，强烈建议所有的业务自定义异常继承该类，这样在全局异常处理时会判断异常类型，会抛出你自定义的returnCode.这样，
你就不必在每个service|controller使用臃肿的try{}catch{},减少模板代码。


8. BaseDataSourceConfigure ,基础的数据库配置文件工具类，该类会为你默认加载一个数据源到你的spring context，前提是你能够按照以下yaml配置，


```

spring:
    datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        username: root
        password: 123456
        url: jdbc:mysql://localhost:3306/atom_order?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false
        type: com.alibaba.druid.pool.DruidDataSource
        filters: stat,wall,log4j
        maxActive: 20
        initialSize: 1
        maxPoolPreparedStatementPerConnectionSize: 100
        maxWait: 60000
        minIdle: 1
        timeBetweenEvictionRunsMillis: 60000
        removeAbandoned: true
        throwException: true
        minEvictableIdleTimeMillis: 300000
        validationQuery: select 'x'
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        logAbandoned: true
        loginDruidUsername: broad
        loginDruidPassword: broad
        removeAbandonedTimeout: 60000
        poolPreparedStatements: true
        slowSqlMillis: 5000
        maxOpenPreparedStatements: 20
        logSlowSql: true
        logViolation: true
        mergeSql: false
        connection-properties: druid.stat.merggSql=ture;druid.stat.slowSqlMillis=5000
        
```

如果你不想写这种基础的数据配置代码，可以再spring.factories增加以下配置：


```

org.springframework.boot.autoconfigure.EnableAutoConfiguration=BaseDataSourceConfigure

```

9. 先到这，后续再补充。。。


