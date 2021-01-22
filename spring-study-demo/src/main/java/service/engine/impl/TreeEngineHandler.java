package service.engine.impl;

import domain.EngineResult;
import domain.TreeNode;
import domain.TreeRich;
import service.engine.EngineBase;

import java.util.Map;

/**
 * @author daihuaiyu
 * @create: 2021-01-22 15:30
 **/
public class TreeEngineHandler extends EngineBase {
    @Override
    public EngineResult process(Long treeId, String userId, TreeRich treeRich, Map<String, String> decisionMatter) {
        TreeNode treeNode = engineDecisionMaker(treeRich, treeId, userId, decisionMatter);
        return new EngineResult(userId, treeId, treeNode.getTreeNodeId(), treeNode.getNodeValue());
    }
}

