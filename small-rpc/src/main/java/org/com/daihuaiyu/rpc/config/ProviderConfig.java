package org.com.daihuaiyu.rpc.config;

/**
 * 生产者配置类
 * @Author: daihuaiyu
 * @Date: 2021/7/26 15:31
 * @Description:
 */
public class


ProviderConfig {

    private String nozzle; //接口
    private String ref; //映射
    private String alias;//别名

    protected void doExport(){
        System.out.format("生产者信息=> [接口：%s] [映射：%s] [别名：%s]\r\n",nozzle,ref,alias);
    }

    public String getNozzle() {
        return nozzle;
    }

    public void setNozzle(String nozzle) {
        this.nozzle = nozzle;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
