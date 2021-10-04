package com.coshe.servlet;

import com.alibaba.fastjson.JSON;
import com.coshe.dao.DataMapper;
import com.coshe.pojo.Data;
import com.coshe.pojo.User;
import com.coshe.utils.Constants;
import com.coshe.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JudgeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");



        User user0 = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        if (user0==null) {//被移除或者注销了，或者未登录
            System.out.println("被移除或者注销了，或者未登录");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }



        SqlSession sqlSession = MybatisUtils.getSqlSession();
        DataMapper mapper = sqlSession.getMapper(DataMapper.class);

        String choose = req.getParameter("choose");
        String trueDataId = req.getParameter("trueDataId");
        System.out.println("choose: "+choose);
        System.out.println("trueDataId: "+trueDataId);
        Data data = mapper.getDataById(Integer.parseInt(trueDataId));

        if (!trueDataId.equals(choose)){
            String s = "Ta是"+data.getName();
            req.setAttribute("trueName", s);
        }else{
            req.setAttribute("trueName", "回答正确！");
        }
        String jsonString = JSON.toJSONString(data);
        req.setAttribute("jsonString", jsonString);
//        System.out.println(jsonString);

        sqlSession.close();

        req.getRequestDispatcher("init.do").forward(req, resp);


    }

}
