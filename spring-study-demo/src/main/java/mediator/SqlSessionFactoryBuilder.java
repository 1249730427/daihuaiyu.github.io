package mediator;

import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;
import org.apache.ibatis.io.Resources;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SqlSession工厂builder
 *
 * @author daihuaiyu
 * @create: 2021-03-01 17:41
 **/
public class SqlSessionFactoryBuilder {

    public DefaultSqlSessionFactory build(Reader reader){
        try {
            //1.解析配置文件
            SAXReader saxReader = new SAXReader();
            saxReader.setEntityResolver(new XMLMapperEntityResolver());
            Document read = saxReader.read(new InputSource(reader));
            Configuration configuration = parseConfiguration(read.getRootElement());
            return new DefaultSqlSessionFactory(configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Configuration parseConfiguration(Element rootElement) throws SQLException, ClassNotFoundException, IOException, DocumentException {
        Configuration configuration = new Configuration();
        configuration.setDataSource(dataSource(rootElement.selectNodes("//dataSource")));
        configuration.setConnection(connection(configuration.dataSource));
        configuration.setMapperElement(mapperElement(rootElement.selectNodes("mappers")));
        return configuration;
    }

    private Map<String, XNode> mapperElement(List<Element> mappers) throws IOException, DocumentException {
        Map<String, XNode> map = new HashMap<>();
        Element element = mappers.get(0);
        List content = element.content();
        for(Object o:content){
            Element element1 = (Element) o;
            String resource = element1.attributeValue("resource");
            Reader resourceAsReader = Resources.getResourceAsReader(resource);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(resourceAsReader));
            Element rootElement = document.getRootElement();
            //命令空间namespace
            String namespace = rootElement.attributeValue("namespace");
            //获取select标签
            List<Element> select = rootElement.selectNodes("select");
            for(Element element2:select){
                //获取select标签下id字段的value
                //获取id
                String id = element2.attributeValue("id");
                //获取参数类型
                String parameterType = element2.attributeValue("parameterType");
                //获取结果类型
                String resultType = element2.attributeValue("resultType");
                //获取执行sql
                String sql = element2.getText();
                Map<Integer,String> parameter= new HashMap<>();
                //判断sql是否含有占位符{(.*?)}
                Pattern pattern =Pattern.compile("(#\\{(.*?)})");
                Matcher matcher = pattern.matcher(sql);
                for(int i=1;matcher.find();i++){
                    String g1 = matcher.group(1);
                    String g2 = matcher.group(2);
                    parameter.put(i, g2);
                    sql = sql.replace(g1, "?");
                }
                XNode xNode =new XNode();
                xNode.setNamespace(namespace);
                xNode.setId(id);
                xNode.setParameterType(parameterType);
                xNode.setResultType(resultType);
                xNode.setSql(sql);
                xNode.setParameter(parameter);
                map.put(namespace+"."+id,xNode);
            }
        }

        return map;
    }

    private Connection connection(Map<String, String> dataSource) throws ClassNotFoundException, SQLException {
        Class.forName(dataSource.get("driver"));
        Connection connection = DriverManager.getConnection(dataSource.get("url"), dataSource.get("username"), dataSource.get("password"));
        return connection;
    }

    private Map<String,String> dataSource(List<Element> selectNodes) {
        Map<String, String> dataSource = new HashMap<>(4);
        Element element = selectNodes.get(0);
        List content = element.content();
        for(Object o:content){
            Element element1 = (Element) o;
            String name= element1.attributeValue("name");
            String value = element1.attributeValue("value");
            dataSource.put(name, value);
        }
        return dataSource;
    }
}

