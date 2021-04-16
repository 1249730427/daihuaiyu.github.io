package com.daihuaiyu.videoapplet.core.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 热搜词实体类
 *
 * @author daihuaiyu
 * @create: 2021-04-16 09:42
 **/
@Entity
@Table(name = "hot")
public class Hot {

    @Id
    private String id;
    private String content;
    private Long num;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
}

