package com.example.config;

import com.example.valuetype.LocalDateValueType;
import com.miragesql.miragesql.SqlManager;
import com.miragesql.miragesql.SqlManagerImpl;
import com.miragesql.miragesql.dialect.Dialect;
import com.miragesql.miragesql.dialect.PostgreSQLDialect;
import com.miragesql.miragesql.integration.spring.SpringConnectionProvider;
import com.miragesql.miragesql.provider.ConnectionProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:jdbc.properties")
@ComponentScan({"com.example.service"})
public class AppConfig {

    @Bean
    DataSource dataSource(@Value("${jdbc.driver}") String driver,
                          @Value("${jdbc.url}") String url,
                          @Value("${jdbc.user}") String user,
                          @Value("${jdbc.password}") String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    SpringConnectionProvider connectionProvider(DataSourceTransactionManager transactionManager) {
        SpringConnectionProvider connectionProvider = new SpringConnectionProvider();
        connectionProvider.setTransactionManager(transactionManager);
        return connectionProvider;
    }

    @Bean
    Dialect dialect() {
        return new PostgreSQLDialect();
    }

    @Bean
    SqlManager sqlManager(ConnectionProvider connectionProvider, Dialect dialect) {
        SqlManagerImpl sqlManager = new SqlManagerImpl();
        sqlManager.setConnectionProvider(connectionProvider);
        sqlManager.setDialect(dialect);
        sqlManager.addValueType(new LocalDateValueType());
        return sqlManager;
    }

}
