package myboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.util.Loggers;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DataSouceConfig {
    private String driverName = "com.mysql.cj.jdbc.Driver";

    @Value("${maxActive:20}")
    private int maxActive;
    private static final String DEFAULT_VALIDATION_SQL = "select 1";

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;


    @Bean
    public DataSource dataSource() throws SQLException {
        return createDataSource(url, username, password, driverName);
    }

    protected DataSource createDataSource(String url, String userName, String password, String driverName) throws SQLException {
        DruidDataSource druidDataSource = new SpringAutoCloseSupportDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(driverName);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setValidationQuery(DEFAULT_VALIDATION_SQL);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setConnectionErrorRetryAttempts(2);
        druidDataSource.setTimeBetweenEvictionRunsMillis(120);
        // 监控统计用
        druidDataSource.setFilters("stat");
        try {
            druidDataSource.getConnection();
        } catch (Exception e) {
          System.out.println("连接数据库失败！");
        }
        return druidDataSource;
    }

    public static final class SpringAutoCloseSupportDataSource extends DruidDataSource implements DisposableBean {

        @Override
        public void destroy() {
            super.close();
        }
    }

}
