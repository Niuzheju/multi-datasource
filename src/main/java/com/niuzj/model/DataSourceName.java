package com.niuzj.model;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DataSourceName {
    //数据源名称
    DatasourceTypeEnum name();
}
