package com.niuzj;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.niuzj.model.DatasourceTypeEnum;
import com.niuzj.model.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class DatasourceConfig {

    private Logger logger = LoggerFactory.getLogger(DatasourceConfig.class);

    //读数据源
    @Bean
    @ConfigurationProperties(prefix = "mydatasource.read")
    public DataSource readDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    //写数据源
    @Bean
    @ConfigurationProperties(prefix = "mydatasource.write")
    public DataSource writeDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    //自定义数据源
    @Bean
    public DataSource dynamicSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> datasources = new HashMap<>();
        datasources.put(DatasourceTypeEnum.READ.getType(), readDataSource());
        datasources.put(DatasourceTypeEnum.WRITE.getType(), writeDataSource());
        dynamicDataSource.setTargetDataSources(datasources);
        dynamicDataSource.setDefaultTargetDataSource(readDataSource());
        return dynamicDataSource;
    }

    //mybatis连接工厂
    @Bean
    public SqlSessionFactory sqlSessionFactory(){
        try {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dynamicSource());
            sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("configuration.xml"));
            sqlSessionFactoryBean.setMapperLocations(new ClassPathResource[]{new ClassPathResource("mybatis/user.xml")});
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    //sqlSessionTemplate模板
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }

    //平台事务管理器
    @Bean
    public PlatformTransactionManager platformTransactionManager(DynamicDataSource dynamicDataSource){
        return new DataSourceTransactionManager(dynamicDataSource);
    }






}
