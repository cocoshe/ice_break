package com.coshe.servlet;

import com.coshe.dao.DataMapper;
import com.coshe.dao.UserMapper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InitServlet extends HttpServlet {
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

        int num = 0;
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        DataMapper mapper = sqlSession.getMapper(DataMapper.class);
        List<Data> dataList = mapper.getDataList();
        num = dataList.size();

        Random random = new Random();
        List<Integer> randomList = new ArrayList<Integer>();
        boolean[] flag = new boolean[num + 1];



        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userNotShow = userMapper.getUserNotShow();
        for (User user : userNotShow) {
            flag[(int) user.getId()] = true;
        }

        if (userNotShow.size()+4>num){
            req.setAttribute("error", "开放照片人数不足4人！");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int t = random.nextInt(num) + 1;
            while (flag[t]) t = t % num + 1;
            flag[t] = true;
            randomList.add(t);
        }

        int randomPick = random.nextInt(randomList.size());
        randomPick = randomList.get(randomPick);
        for (int i = 0; i < randomList.size(); i++) {
            Data dataById = mapper.getDataById(randomList.get(i));
            String s = "p"+(i+1);
            req.setAttribute(s, dataById);
        }

        req.setAttribute("trueData", mapper.getDataById(randomPick));

        sqlSession.close();
        req.getRequestDispatcher("/jsp/choose.jsp").forward(req, resp);


    }
}
