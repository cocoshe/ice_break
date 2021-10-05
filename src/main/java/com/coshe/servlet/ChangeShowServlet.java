package com.coshe.servlet;

import com.coshe.dao.UserMapper;
import com.coshe.pojo.User;
import com.coshe.utils.Constants;
import com.coshe.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        String name = user.getName();

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);


        user = mapper.getUserByName(name);
        System.out.println("user show:"+user.getShow());
        int show = (int) (1 - user.getShow());
        mapper.updateUserByName(show, name);


        sqlSession.commit();
        sqlSession.close();

        req.getRequestDispatcher("init.do").forward(req, resp);

    }
}
