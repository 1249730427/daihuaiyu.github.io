package domain;

import java.util.List;

/**
 * 树节点信息
 *
 * @author daihuaiyu
 * @create: 2021-01-22 14:02
 **/
public class TreeNode {

    private long treeId;

    private long treeNodeId;

    private String nodeType;

    private  String nodeValue;

    private String ruleKey;

    private String ruleDesc;

    private List<TreeNodeLink> TreeNodeLinkList;

    public TreeNode() {
    }

    public long getTreeId() {
        return treeId;
    }

    public void setTreeId(long treeId) {
        this.treeId = treeId;
    }

    public long getTreeNodeId() {
        return treeNodeId;
    }

    public void setTreeNodeId(long treeNodeId) {
        this.treeNodeId = treeNodeId;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public List<TreeNodeLink> getTreeNodeLinkList() {
        return TreeNodeLinkList;
    }

    public void setTreeNodeLinkList(List<TreeNodeLink> treeNodeLinkList) {
        TreeNodeLinkList = treeNodeLinkList;
    }

    public String getRuleKey() {
        return ruleKey;
    }

    public void setRuleKey(String ruleKey) {
        this.ruleKey = ruleKey;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }
}

