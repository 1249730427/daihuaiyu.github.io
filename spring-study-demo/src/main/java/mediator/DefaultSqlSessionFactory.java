package mediator;

/**
 * SqlSessionFactory 的默认实现
 *
 * @author daihuaiyu
 * @create: 2021-03-01 17:27
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {

        return new DefaultSqlSession(configuration.connection,configuration.mapperElement);
    }
}

