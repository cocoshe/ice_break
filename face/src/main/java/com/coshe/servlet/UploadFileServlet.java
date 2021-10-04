package com.coshe.servlet;

import com.coshe.dao.DataMapper;
import com.coshe.pojo.User;
import com.coshe.utils.Constants;
import com.coshe.utils.MybatisUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UploadFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        DataMapper mapper = sqlSession.getMapper(DataMapper.class);


        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        String userName = user.getName();

        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(req)) {
            // 如果不是则停止
            PrintWriter writer = resp.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");
        String uploadPath = "/home/coshe/HDU-helper/face/web/img";


        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(req);

            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {

                        String fileName = new File(item.getName()).getName();
                        int lastIndex = fileName.lastIndexOf(".");
                        String substring = fileName.substring(lastIndex + 1);//后缀
                        //System.out.println(substring);
                        if (!substring.equals("jpg") && !substring.equals("png") && !substring.equals("jpeg")){
                            req.setAttribute("message","文件格式错误！");
                            req.getRequestDispatcher("/jsp/uploadFile.jsp").forward(req, resp);
                            return;
                        }

                        String filePath = uploadPath + File.separator + userName +"."+substring;
                        System.out.println("后缀："+substring);

                        System.out.println("userName: "+userName);

                        mapper.updateDataPic(userName+"."+substring, userName);
                        sqlSession.commit();
                        sqlSession.close();


                        File storeFile = new File(filePath);
                        if (storeFile.exists()){
                            storeFile.delete();
                        }
                        //System.out.println(filePath);
                        // 保存文件
                        item.write(storeFile);
                        req.setAttribute("message","文件上传成功!");

                    }
                }
            }
        } catch (Exception ex) {
            req.setAttribute("message","错误信息: " + ex.getMessage());
        }
        req.getRequestDispatcher("/jsp/uploadFile.jsp").forward(req, resp);

    }
}
