package service.impl;

import service.Matter;

import java.math.BigDecimal;

/**
 * 吊顶：一级吊顶
 *
 * @author daihuaiyu
 * @create: 2021-03-02 15:43
 **/
public class LevelOneCeiling implements Matter {
    @Override
    public String sence() {
        return "吊顶";
    }

    @Override
    public String brand() {
        return "装修公司自带";
    }

    @Override
    public String model() {
        return "一级顶";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(260);
    }

    @Override
    public String desc() {
        return "造型只做低一级，只有一个层次的吊顶，一般离顶120-150mm";
    }
}

