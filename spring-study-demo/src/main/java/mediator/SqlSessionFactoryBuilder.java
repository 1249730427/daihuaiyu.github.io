package mediator;

import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Configuration parseConfiguration(Element rootElement) {
        Configuration configuration = new Configuration();
        configuration.setDataSource(dataSource(rootElement.selectNodes("//dataSource")));
        return null;
    }

    private Map<String,String> dataSource(List<Node> selectNodes) {
        Map<String, String> dataSource = new HashMap<>(4);
        Element element = selectNodes.get(0).getParent();
//        List<Element> content = element.content();
//        for(Node o:content){
//            String value = o.attributeValue("value");
//            dataSource.put(name, value);
//        }
        return null;
    }
}

