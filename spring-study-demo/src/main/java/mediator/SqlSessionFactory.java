package mediator;

/**
 * SqlSession工厂
 *
 * @author daihuaiyu
 * @create: 2021-03-01 17:26
 **/
public interface SqlSessionFactory {

    SqlSession openSession();
}
