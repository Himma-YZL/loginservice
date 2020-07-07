package com.api.login.apilogin.shiro.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
//@ApiModel(value = "返回说明")
public class ResultDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "返回状态码；200:成功")
    private String code;

    @ApiModelProperty(value = "成功标识；true：成功；false:失败")
    private String success;

    @ApiModelProperty(value = "描述信息")
    private String message;

    @ApiModelProperty(value = "返回实体")
    private T data;
}
