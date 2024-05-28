package cn.xc.handmade.mybatis.dao;

public interface IUserDao {

    Object selectList();

    String selectByUsername(String username);

}
