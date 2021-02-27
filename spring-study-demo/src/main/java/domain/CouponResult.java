package domain;

/**
 * @author :daihuaiyu
 * @Description:
 * @create 2021/2/27 11:05
 */
public class CouponResult {

    private String code;

    private String info;

    public CouponResult(final String code, final String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(final String info) {
        this.info = info;
    }
}
