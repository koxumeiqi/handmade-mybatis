package cn.xc.handmade.mybatis.t2;

import cn.xc.handmade.mybatis.binding.MapperRegistry;
import cn.xc.handmade.mybatis.dao.IUserDao;
import cn.xc.handmade.mybatis.session.SqlSession;
import cn.xc.handmade.mybatis.session.SqlSessionFactory;
import cn.xc.handmade.mybatis.session.impl.DefaultSqlSessionFactory;
import org.junit.Test;

import java.util.logging.Logger;

public class SqlSessionFactoryTest {

    private final Logger log = Logger.getLogger("SqlSessionFactoryTest");

    @Test
    public void test_createSqlSessionFactory() throws Exception {
        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMappers("cn.xc.handmade.mybatis.dao");
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        log.info(userDao.selectByUsername("myz").toString());
    }

}
