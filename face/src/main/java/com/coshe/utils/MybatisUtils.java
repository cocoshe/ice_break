package com.coshe.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;

    static{
        String resources = "mybatis-config.xml";
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream(resources);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
