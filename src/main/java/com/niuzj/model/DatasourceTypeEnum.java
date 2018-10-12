package com.niuzj.model;

public enum DatasourceTypeEnum {

    READ("mydatasource.read"), WRITE("mydatasource.write");

    private String type;

    DatasourceTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
