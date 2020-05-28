package myboot.config;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class MybatisConfig {
    private String defaultConfigLocations = "mybatis-config.xml";
    private List<String> mapperLocations = Arrays.asList("mapper/*.xml", "mapper/contact/*.xml");

//    @Bean
//    public SQLExecInterceptor sqlExecInterceptor() {
//        return new SQLExecInterceptor();
//
//    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource) throws IOException {
        return getSqlSessionFactoryBean(dataSource, mapperLocations, defaultConfigLocations);
    }

    protected SqlSessionFactoryBean getSqlSessionFactoryBean(DataSource dataSource, List<String> mapperLocations, String configLocations) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(getResourceLocations(mapperLocations));
        sqlSessionFactoryBean.setConfigLocation(getResourceLocation(configLocations));
//        Interceptor[] arr = {sqlExecInterceptor()};
//        sqlSessionFactoryBean.setPlugins(arr);
        return sqlSessionFactoryBean;
    }

    protected Resource[] getResourceLocations(List<String> location) throws IOException {

        List<Resource> list = new ArrayList<>();
        for (String path : location) {
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(path);
            list.addAll(Arrays.asList(resources));
        }
        return list.toArray(new Resource[0]);
    }

    protected Resource getResourceLocation(String location) {
        Resource resource = new PathMatchingResourcePatternResolver().getResource(location);
        return resource;
    }
}
