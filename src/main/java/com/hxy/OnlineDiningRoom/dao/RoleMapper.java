package com.hxy.OnlineDiningRoom.dao;

import com.hxy.OnlineDiningRoom.pojo.Role;
import com.hxy.OnlineDiningRoom.pojo.RoleExample;

import java.util.List;

public interface RoleMapper extends SysDao<Role> {

    List<Role> selectByExample(RoleExample example);

}