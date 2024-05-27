package cn.xc.handmade.mybatis.dao;

public interface IUserDao {

    Object selectList();

    Object selectByUsername(String username);

}
