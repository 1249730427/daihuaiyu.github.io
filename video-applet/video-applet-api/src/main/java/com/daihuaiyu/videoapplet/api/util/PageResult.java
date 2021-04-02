package com.daihuaiyu.videoapplet.api.util;

import lombok.Data;

import java.util.List;

/**
 * 分页结果类
 *
 * @author :daihuaiyu
 * @Description: 分页实体类
 * @create 2021/4/2 21:56
 */
@Data
public class PageResult {

    //当前页数
    private Integer page;

    //总页数
    private Integer totalPage;

    //总记录数
    private Long totalElements;

    private List<?> rows;

}
