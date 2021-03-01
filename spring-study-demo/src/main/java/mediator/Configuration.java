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

    public Connection connection;

    public Map<String, XNode> mapperElement;
}

