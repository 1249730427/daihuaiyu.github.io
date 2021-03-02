package service;

/**
 * IMenu接口
 *
 * @author daihuaiyu
 * @create: 2021-03-02 16:39
 **/
public interface IMenu {

    void appendCelling(Matter matter);

    void appendCoat(Matter matter);

    void appendFloor(Matter matter);

    void appendTile(Matter matter);

    String getDetail();
}
