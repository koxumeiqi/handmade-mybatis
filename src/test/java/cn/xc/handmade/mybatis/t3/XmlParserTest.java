package cn.xc.handmade.mybatis.t3;

import cn.xc.handmade.mybatis.dao.IUserDao;
import cn.xc.handmade.mybatis.entity.User;
import cn.xc.handmade.mybatis.io.Resources;
import cn.xc.handmade.mybatis.session.SqlSession;
import cn.xc.handmade.mybatis.session.SqlSessionFactory;
import cn.xc.handmade.mybatis.session.SqlSessionFactoryBuilder;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.Reader;
import java.util.logging.Logger;

public class XmlParserTest {

    private Logger log = Logger.getLogger("XmlParserTest");

    @Test
    public void test_xml_parse() throws Exception {
        // 1. 从SqlSessionFactory中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User info = userDao.selectByUsername("myz");
        log.info(JSON.toJSONString(info));
    }

}
