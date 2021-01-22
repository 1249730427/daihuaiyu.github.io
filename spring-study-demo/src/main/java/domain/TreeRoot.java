package domain;

/**
 * 树根信息
 *
 * @author daihuaiyu
 * @create: 2021-01-22 14:02
 **/
public class TreeRoot {

    private long treeId;

    private long treeRootNodeId;

    private String treeName;


    public TreeRoot() {
    }

    public long getTreeId() {
        return treeId;
    }

    public void setTreeId(long treeId) {
        this.treeId = treeId;
    }

    public long getTreeRootNodeId() {
        return treeRootNodeId;
    }

    public void setTreeRootNodeId(long treeRootNodeId) {
        this.treeRootNodeId = treeRootNodeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }
}

