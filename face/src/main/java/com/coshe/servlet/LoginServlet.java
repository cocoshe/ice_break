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

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserByName(username);


        if (user != null){
            if (user.getPwd().equals(password)){
                //登录成功
                //System.out.println("登录成功");
                req.getSession().setAttribute(Constants.USER_SESSION, user);
                req.getRequestDispatcher("/jsp/welcome.jsp").forward(req, resp);
            }else{
                //密码错误
                req.setAttribute("error", "密码错误");
                req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            }
        }else{//user==null
            req.setAttribute("error", "用户不存在");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }


        sqlSession.close();

    }
}
