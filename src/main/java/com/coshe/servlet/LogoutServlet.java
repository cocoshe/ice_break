package com.coshe.servlet;

import com.coshe.pojo.User;
import com.coshe.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user0 = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        if (user0==null) {//被移除或者注销了，或者未登录
            System.out.println("被移除或者注销了，或者未登录");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }


        //System.out.println("===========");
        req.getSession().removeAttribute(Constants.USER_SESSION);
        //System.out.println(req.getSession().getAttribute(Constants.USER_SESSION));
        //System.out.println("logout-----");
        req.getRequestDispatcher("/jsp/welcome.jsp").forward(req, resp);
    }
}
