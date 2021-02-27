package service;

import java.util.Map;

/**
 * 颁奖接口
 */
public interface ICommodity {

    void sendCommodity(String uId, String commodityId, String bizId, Map<String,String> extMap);
}
