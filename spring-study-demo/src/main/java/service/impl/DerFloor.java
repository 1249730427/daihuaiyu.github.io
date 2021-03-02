package service.impl;

import service.Matter;

import java.math.BigDecimal;

/**
 * 地板：德尔
 *
 * @author daihuaiyu
 * @create: 2021-03-02 15:56
 **/
public class DerFloor implements Matter {
    @Override
    public String sence() {
        return "地板";
    }

    @Override
    public String brand() {
        return "德尔";
    }

    @Override
    public String model() {
        return "A+";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(119);
    }

    @Override
    public String desc() {
        return "DER德尔集团是全球领先的专业木地板制造商，北京2008年奥运会家装和公装地板供应商";
    }
}

