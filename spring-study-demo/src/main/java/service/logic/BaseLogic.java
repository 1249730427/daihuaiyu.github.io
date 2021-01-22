package service.logic;

import domain.TreeNodeLink;

import java.util.List;

/**
 * @author daihuaiyu
 * @create: 2021-01-22 14:26
 **/
public abstract class BaseLogic implements LogicFilter {


    @Override
    public Long filter(String matterValue, List<TreeNodeLink> treeNodeLineInfoList) {
        for(TreeNodeLink treeNodeLink:treeNodeLineInfoList){
            if(decisionLogic(matterValue,treeNodeLink)){
                return treeNodeLink.getNodeIdTo();
            }
        }
        return 0L;
    }

    private boolean decisionLogic(String matterValue, TreeNodeLink treeNodeLink) {
        switch (treeNodeLink.getRuleLimitType()){
            case 1:
                return matterValue.equals(treeNodeLink.getRuleLimitValue());
            case 2:
                return Double.parseDouble(matterValue) > Double.parseDouble(treeNodeLink.getRuleLimitValue());
            case 3:
                return Double.parseDouble(matterValue) < Double.parseDouble(treeNodeLink.getRuleLimitValue());
            case 4:
                return Double.parseDouble(matterValue) <= Double.parseDouble(treeNodeLink.getRuleLimitValue());
            case 5:
                return Double.parseDouble(matterValue) >= Double.parseDouble(treeNodeLink.getRuleLimitValue());
            default:
                return false;

        }
    }
}

