package service.engine;

import service.logic.LogicFilter;
import service.logic.impl.UserAgeFilter;
import service.logic.impl.UserGenderFilter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author daihuaiyu
 * @create: 2021-01-22 15:12
 **/
public class EngineConfig {

    protected static Map<String, LogicFilter> logicFilterMap;

    static {
        logicFilterMap = new ConcurrentHashMap<>();
        logicFilterMap.put("userGender",new UserGenderFilter());
        logicFilterMap.put("userAge",new UserAgeFilter());
    }

    public static Map<String, LogicFilter> getLogicFilterMap() {
        return logicFilterMap;
    }

    public static void setLogicFilterMap(Map<String, LogicFilter> logicFilterMap) {
        EngineConfig.logicFilterMap = logicFilterMap;
    }
}

