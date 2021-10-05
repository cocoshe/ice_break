package com.coshe.dao;

import com.coshe.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User getUserByName(String name);

    void updateUserByName(@Param("show") int show,@Param("name") String name);

    List<User> getUserNotShow();

}
