package cn.xc.handmade.mybatis.t4;


import cn.xc.handmade.mybatis.dao.IUserDao;
import cn.xc.handmade.mybatis.entity.User;
import cn.xc.handmade.mybatis.io.Resources;
import cn.xc.handmade.mybatis.session.SqlSession;
import cn.xc.handmade.mybatis.session.SqlSessionFactory;
import cn.xc.handmade.mybatis.session.SqlSessionFactoryBuilder;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.IOException;

public class ExecutorApiTest {

    @Test
    public void test_executor() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User user = userDao.selectByUsername("xmq");
        System.out.println(JSON.toJSONString(user));
    }

}
