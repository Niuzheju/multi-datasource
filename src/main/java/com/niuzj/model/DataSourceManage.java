package com.niuzj.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceManage {

    private static Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();


    public static void setDataSource(String datasource){
        logger.info("datasource is change to " + datasource);
        contextHolder.set(datasource);
    }

    public static String getDataSource(){
        return contextHolder.get();
    }

    public static void clear(){
        logger.info("datasource is set to default");
        contextHolder.remove();
    }
}
