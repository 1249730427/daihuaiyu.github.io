package service.logic.impl;

import service.logic.BaseLogic;

import java.util.Map;

/**
 * @author daihuaiyu
 * @create: 2021-01-22 14:48
 **/
public class UserAgeFilter extends BaseLogic {
    @Override
    public String matterValue(Long treeId, String userId, Map<String, String> decisionMatter) {
        return decisionMatter.get("age");
    }
}

