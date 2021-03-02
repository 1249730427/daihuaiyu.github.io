package service.impl;

import service.IMenu;
import service.Matter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于填充各种物料
 *
 * @author daihuaiyu
 * @create: 2021-03-02 16:43
 **/
public class DecorationPackageMenu implements IMenu {

    private List<Matter> matterList = new ArrayList<>();
    private BigDecimal area=BigDecimal.ZERO;
    private  Integer grade;
    private BigDecimal price =BigDecimal.ZERO;

    public DecorationPackageMenu() {
    }

    public DecorationPackageMenu(Integer grade, BigDecimal area) {
        this.grade = grade;
        this.area = area;
    }

    @Override
    public void appendCelling(Matter matter) {
        price = price.add(area.multiply(new BigDecimal("0.2")).multiply(matter.price()));
        matterList.add(matter);
    }

    @Override
    public void appendCoat(Matter matter) {
        price = price.add(area.multiply(new BigDecimal("1.4")).multiply(matter.price()));
        matterList.add(matter);
    }

    @Override
    public void appendFloor(Matter matter) {
        price = price.add(area.multiply(matter.price()));
        matterList.add(matter);
    }

    @Override
    public void appendTile(Matter matter) {
        price = price.add(area.multiply(matter.price()));
        matterList.add(matter);
    }

    @Override
    public String getDetail() {

        StringBuilder detail = new StringBuilder("\r\n-------------------------------------------------------\r\n" +
                "装修清单" + "\r\n" +
                "套餐等级：" + grade + "\r\n" +
                "套餐价格：" + price.setScale(2, BigDecimal.ROUND_HALF_UP) + " 元\r\n" +
                "房屋面积：" + area.doubleValue() + " 平米\r\n" +
                "材料清单：\r\n");

        for (Matter matter: matterList) {
            detail.append(matter.sence()).append("：").append(matter.brand()).append("、").append(matter.model()).append("、平米价格：").append(matter.price()).append(" 元。\n");
        }
        return detail.toString();
    }
}

