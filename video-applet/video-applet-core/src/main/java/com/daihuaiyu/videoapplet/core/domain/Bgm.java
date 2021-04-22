package com.daihuaiyu.videoapplet.core.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;

/**
 * 背景音乐实体类
 *
 * @author :daihuaiyu
 * @Description: 背景音乐实体类
 * @create 2021/4/1 21:10
 */
@Entity
@ApiModel(value = "背景音乐")
public class Bgm {

    @javax.persistence.Id
    @ApiModelProperty(hidden = true)
    private String id;

    /**
     * 视屏作者
     */
    @ApiModelProperty(value = "作者",name = "author",example = "123",required = true)
    private String author;

    /**
     * 视屏名称
     */
    @ApiModelProperty(value = "视屏名称",name = "name",example = "123",required = true)
    private String name;

    /**
     * 视屏路径
     */
    @ApiModelProperty(value = "视屏路径",name = "path",example = "123",required = true)
    private String path;

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(final String path) {
        this.path = path;
    }
}
