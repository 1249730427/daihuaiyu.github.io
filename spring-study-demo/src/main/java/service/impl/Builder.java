package service.impl;

import java.math.BigDecimal;

/**
 * 建造器
 *
 * @author daihuaiyu
 * @create: 2021-03-02 16:57
 **/
public class Builder {

    public String levelOne(BigDecimal area){
        DecorationPackageMenu decorationPackageMenu = new DecorationPackageMenu(1,area);
        decorationPackageMenu.appendCelling(new LevelTwoCeiling());
        decorationPackageMenu.appendCoat(new DuluxCoat());
        decorationPackageMenu.appendFloor(new ShengXiangFloor());
        return decorationPackageMenu.getDetail();
    }
    public String levelTwo(BigDecimal area){
        DecorationPackageMenu decorationPackageMenu = new DecorationPackageMenu(2,area);
        decorationPackageMenu.appendCelling(new LevelTwoCeiling());
        decorationPackageMenu.appendCoat(new LiBangCoat());
        decorationPackageMenu.appendFloor(new MarcoPoloTile());
        return decorationPackageMenu.getDetail();
    }

    public String levelThree(BigDecimal area){
        DecorationPackageMenu decorationPackageMenu = new DecorationPackageMenu(3,area);
        decorationPackageMenu.appendCelling(new LevelOneCeiling());
        decorationPackageMenu.appendCoat(new LiBangCoat());
        decorationPackageMenu.appendFloor(new DongPengTile());
        return decorationPackageMenu.getDetail();
    }
}

