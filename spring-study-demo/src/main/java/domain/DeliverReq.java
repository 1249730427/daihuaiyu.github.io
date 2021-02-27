package domain;

import lombok.Data;

/**
 * @author :daihuaiyu
 * @Description:
 * @create 2021/2/27 11:11
 */
@Data
public class DeliverReq {

    private String userName;

    private String userPhone;

    private String sku;

    private String orderId;

    private String  consigneeUserName;

    private String consigneeUserPhone;

    private String consigneeUserAddress;

    public DeliverReq(final String userName, final String userPhone, final String sku, final String orderId, final String consigneeUserName, final String consigneeUserPhone, final String consigneeUserAddress) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.sku = sku;
        this.orderId = orderId;
        this.consigneeUserName = consigneeUserName;
        this.consigneeUserPhone = consigneeUserPhone;
        this.consigneeUserAddress = consigneeUserAddress;
    }

    public DeliverReq() {
    }


}
