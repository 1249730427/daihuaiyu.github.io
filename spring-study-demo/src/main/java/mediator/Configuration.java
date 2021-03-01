package mediator;

import java.sql.Connection;
import java.util.Map;

/**
 * 配置类，负责读取配置文件
 *
 * @author daihuaiyu
 * @create: 2021-03-01 17:30
 **/
public class Configuration {

    protected Connection connection;
    protected Map<String, String> dataSource;
    protected Map<String, XNode> mapperElement;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Map<String, String> getDataSource() {
        return dataSource;
    }

    public void setDataSource(Map<String, String> dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, XNode> getMapperElement() {
        return mapperElement;
    }

    public void setMapperElement(Map<String, XNode> mapperElement) {
        this.mapperElement = mapperElement;
    }
}

