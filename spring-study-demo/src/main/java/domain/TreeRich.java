package domain;

import java.util.Map;

/**
 * 组织树信息<聚合对象>
 *
 * @author daihuaiyu
 * @create: 2021-01-22 13:58
 **/
public class TreeRich {

    private TreeRoot treeRoot;

    private Map<Long, TreeNode> treeNodeMap;

    public TreeRich(TreeRoot treeRoot, Map<Long, TreeNode> treeNodeMap) {
        this.treeRoot = treeRoot;
        this.treeNodeMap = treeNodeMap;
    }

    public TreeRoot getTreeRoot() {
        return treeRoot;
    }

    public void setTreeRoot(TreeRoot treeRoot) {
        this.treeRoot = treeRoot;
    }

    public Map<Long, TreeNode> getTreeNodeMap() {
        return treeNodeMap;
    }

    public void setTreeNodeMap(Map<Long, TreeNode> treeNodeMap) {
        this.treeNodeMap = treeNodeMap;
    }
}

