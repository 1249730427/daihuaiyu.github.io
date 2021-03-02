package service;

import java.math.BigDecimal;

/**
 * Matter 接口
 *
 * @author daihuaiyu
 * @create: 2021-03-02 15:41
 **/
public interface Matter {

    String sence(); //场景

    String brand(); //名牌

    String model(); //型号

    BigDecimal price(); //价格

    String desc(); //描述
}
