package domain;

/**
 * 库存信息实体类
 *
 * @author daihuaiyu
 * @create: 2021-03-02 13:26
 **/
public class Stock {

    private int total; // 库存总量
    private int used;  //

    public Stock(int total, int used) {
        this.total = total;
        this.used = used;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }
}

