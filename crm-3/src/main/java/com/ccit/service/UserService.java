package com.ccit.service;

import com.ccit.mappers.RoleMapper;
import com.ccit.mappers.UserLogMapper;
import com.ccit.mappers.UserMapper;
import com.ccit.pojo.Role;
import com.ccit.pojo.User;
import com.ccit.utils.ShiroUtil;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class UserService {
    @Inject
    private UserMapper userMapper;
    @Inject
    private UserLogMapper logMapper;
    @Inject
    private RoleMapper roleMapper;
    public List<User> findAll(){
        return userMapper.findAll();
    }
    public void alterPw(String password) {
        User user = ShiroUtil.getPrincipal();
        user.setPassword(password);
        userMapper.alterPw(user);
    }
    public boolean addLog(String ip) {
        User user = ShiroUtil.getPrincipal();
        String logintime = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
        int i = logMapper.insert(user.getId(),ip,logintime);
        if(i == 1){
            return true;
        }
        return false;
    }
    public List<User> finByParam(Map<String,Object>param){
        return userMapper.findByParam(param);
    }
    public Long getTotal(){
        return userMapper.getTotal();
    }
    public List<Role> findAllRole(){
        return roleMapper.findAllRole();
    }
    public void addUser(User user){
        userMapper.addUser(user);
    }
    public void deleteById(Integer id){
        userMapper.deleteById(id);
    }
    public User findById(Integer id){
        return userMapper.findById(id);
    }
    public boolean alterUser(User user){
        if(userMapper.alterUser(user) == 1){
            return true;
        }
        return false;
    }
    public User findByUserName(String username){
        return userMapper.findByUserName(username);
    }
    public Long findByParamCount(Map<String,Object>param){
        return userMapper.findByParamCount(param);
    }
}
