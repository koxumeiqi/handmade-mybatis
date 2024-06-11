package cn.xc.handmade.mybatis.dao;

import cn.xc.handmade.mybatis.entity.User;

public interface IUserDao {

    Object selectList();

    User selectByUsername(String username);

}
