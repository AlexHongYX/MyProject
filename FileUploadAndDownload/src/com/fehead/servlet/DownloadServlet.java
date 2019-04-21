package com.fehead.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "DownloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取文件下载路径
        String path = getServletContext().getRealPath("/")+"files/";
        String filename = request.getParameter("filename");
        File file = new File(path+filename);
        if(file.exists()){
            //设置相应类型(application/octet-stream)
            response.setContentType("application/x-msdownload");
            //设置头信息
            response.setHeader("Content-Disposition","attachment;filename=\""+filename+"\"");

            InputStream inputStream = new FileInputStream(file);
            ServletOutputStream outputStream = response.getOutputStream();
            byte b[] = new byte[1024];
            int n;
            while ((n=inputStream.read(b))!=-1){
                outputStream.write(b,0,n);
            }
            //关闭流，释放资源
            outputStream.close();
            inputStream.close();


        }else {
            request.setAttribute("errorResult","文件不存在，下载失败！");
            request.getRequestDispatcher("success.jsp").forward(request,response);
        }
    }
}
