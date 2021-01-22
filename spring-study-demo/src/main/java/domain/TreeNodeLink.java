package domain;

/**
 * @author daihuaiyu
 * @create: 2021-01-22 14:13
 **/
public class TreeNodeLink {

    private Long nodeIdFrom;

    private Long nodeIdTo;

    private Integer ruleLimitType;

    private String RuleLimitValue;

    public TreeNodeLink() {
    }

    public Long getNodeIdFrom() {
        return nodeIdFrom;
    }

    public void setNodeIdFrom(Long nodeIdFrom) {
        this.nodeIdFrom = nodeIdFrom;
    }

    public Long getNodeIdTo() {
        return nodeIdTo;
    }

    public void setNodeIdTo(Long nodeIdTo) {
        this.nodeIdTo = nodeIdTo;
    }

    public Integer getRuleLimitType() {
        return ruleLimitType;
    }

    public void setRuleLimitType(Integer ruleLimitType) {
        this.ruleLimitType = ruleLimitType;
    }

    public String getRuleLimitValue() {
        return RuleLimitValue;
    }

    public void setRuleLimitValue(String ruleLimitValue) {
        RuleLimitValue = ruleLimitValue;
    }
}

