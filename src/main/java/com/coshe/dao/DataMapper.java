package com.coshe.dao;

import com.coshe.pojo.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataMapper {
    List<Data> getDataList();

    Data getDataByName(String name);

    Data getDataById(int id);

    void updateDataPic(@Param("picName") String picName, @Param("userName") String userName);

}
