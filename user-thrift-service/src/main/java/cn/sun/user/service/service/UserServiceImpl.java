package cn.sun.user.service.service;

import cn.sun.user.api.UserInfo;
import cn.sun.user.api.UserService;
import cn.sun.user.service.mapper.UserMapper;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService.Iface
{
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo getUserById(int id) throws TException
    {
        return userMapper.getUserById(id);
    }

    @Override
    public UserInfo getUserByName(String username) throws TException
    {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public void registerUser(UserInfo userInfo) throws TException
    {
        userMapper.registerUser(userInfo);
    }

    @Override
    public UserInfo getTeacherById(int id) throws TException
    {
        return userMapper.getTeacherById(id);
    }
}
