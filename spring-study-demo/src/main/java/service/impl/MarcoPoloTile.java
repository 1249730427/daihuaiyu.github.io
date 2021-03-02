package service.impl;

import service.Matter;

import java.math.BigDecimal;

/**
 * 地砖：马可波罗
 *
 * @author daihuaiyu
 * @create: 2021-03-02 16:04
 **/
public class MarcoPoloTile implements Matter {
    @Override
    public String sence() {
        return "地砖";
    }

    @Override
    public String brand() {
        return "马可波罗";
    }

    @Override
    public String model() {
        return "缺省";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(140);
    }

    @Override
    public String desc() {
        return "马可波罗”品牌诞生于1996年，作为国内最早品牌化的建陶品牌，以“文化陶瓷”占领市场，享有“仿古砖至尊”的美誉";
    }
}

