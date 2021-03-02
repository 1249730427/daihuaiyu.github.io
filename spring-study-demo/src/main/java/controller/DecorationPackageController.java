package controller;

import service.Matter;
import service.impl.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 输入房租面积和对应套餐，即可算出价格
 *
 * @author daihuaiyu
 * @create: 2021-03-02 16:10
 **/
public class DecorationPackageController {

    public String getMatterList(BigDecimal area,Integer level){

        List<Matter> matterList = new ArrayList<>();  //装修清单
        BigDecimal price = BigDecimal.ZERO;          //装修价格
        //豪华欧式
        if(1==level){
            LevelTwoCeiling levelTwoCeiling = new LevelTwoCeiling(); // 吊顶，二级顶
            DuluxCoat duluxCoat = new DuluxCoat();                   // 涂料，多乐士
            ShengXiangFloor shengXiangFloor = new ShengXiangFloor(); // 地板，圣象
            price =price.add(area.multiply(new BigDecimal(0.2)).multiply(levelTwoCeiling.price()));
            price =price.add(area.multiply(new BigDecimal(1.4)).multiply(duluxCoat.price()));
            price =price.add(area.multiply(shengXiangFloor.price()));
        }
        // 轻奢田园
        if (2 == level) {

            LevelTwoCeiling levelTwoCeiling = new LevelTwoCeiling(); // 吊顶，二级顶
            LiBangCoat liBangCoat = new LiBangCoat();                // 涂料，立邦
            MarcoPoloTile marcoPoloTile = new MarcoPoloTile();       // 地砖，马可波罗

            matterList.add(levelTwoCeiling);
            matterList.add(liBangCoat);
            matterList.add(marcoPoloTile);

            price = price.add(area.multiply(new BigDecimal("0.2")).multiply(levelTwoCeiling.price()));
            price = price.add(area.multiply(new BigDecimal("1.4")).multiply(liBangCoat.price()));
            price = price.add(area.multiply(marcoPoloTile.price()));

        }

        // 现代简约
        if (3 == level) {

            LevelOneCeiling levelOneCeiling = new LevelOneCeiling();  // 吊顶，二级顶
            LiBangCoat liBangCoat = new LiBangCoat();                 // 涂料，立邦
            DongPengTile dongPengTile = new DongPengTile();           // 地砖，东鹏

            matterList.add(levelOneCeiling);
            matterList.add(liBangCoat);
            matterList.add(dongPengTile);

            price = price.add(area.multiply(new BigDecimal("0.2")).multiply(levelOneCeiling.price()));
            price = price.add(area.multiply(new BigDecimal("1.4")).multiply(liBangCoat.price()));
            price = price.add(area.multiply(dongPengTile.price()));
        }
        StringBuilder detail = new StringBuilder("\r\n-------------------------------------------------------\r\n" +
                "装修清单" + "\r\n" +
                "套餐等级：" + level + "\r\n" +
                "套餐价格：" + price.setScale(2, BigDecimal.ROUND_HALF_UP) + " 元\r\n" +
                "房屋面积：" + area.doubleValue() + " 平米\r\n" +
                "材料清单：\r\n");

        for (Matter matter: matterList) {
            detail.append(matter.sence()).append("：").append(matter.brand()).append("、").append(matter.model()).append("、平米价格：").append(matter.price()).append(" 元。\n");
        }
        return detail.toString();
    }
}

