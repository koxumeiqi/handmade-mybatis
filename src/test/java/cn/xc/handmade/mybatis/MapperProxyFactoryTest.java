package cn.xc.handmade.mybatis;

import cn.xc.handmade.mybatis.binding.MapperProxyFactory;
import cn.xc.handmade.mybatis.dao.IUserDao;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class MapperProxyFactoryTest {

    Logger log = Logger.getLogger("MapperProxyFactoryTest");

    @Test
    public void test_mapperProxyFactory() {
        MapperProxyFactory<IUserDao> proxyFactory = new MapperProxyFactory<>(IUserDao.class);
        Map<String, String> sqlSessionMap = new HashMap<>();
        sqlSessionMap.put("cn.xc.handmade.mybatis.dao.IUserDao.selectList", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户所有信息");
        IUserDao userDao = proxyFactory.newInstance(sqlSessionMap);
        log.info(userDao.selectList().toString());
    }


}
