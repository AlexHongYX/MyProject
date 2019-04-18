package com.fehead.servlet;

import com.fehead.service.GetNameService;

import java.io.IOException;
import java.io.PrintWriter;

public class getNameServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        //设置字符（没作用）
        request.setCharacterEncoding("utf-8");
        //设置字符
        response.setCharacterEncoding("utf-8");

        String id = request.getParameter("id");
        System.out.println("接收到前台请求...");
        System.out.println(id);

        GetNameService getNameService = new GetNameService();
        String name = getNameService.getNameServiceById(id);

        System.out.println(name);

        PrintWriter out = response.getWriter();
        out.println(name);

    }
}
