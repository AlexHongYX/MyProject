package com.fehead.Util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static final String URL="jdbc:mysql://47.92.194.26:3306/websustdx?useUnicode=true&" +
            "characterEncoding=utf-8";
    private static final String USER="root";
    private static final String PASSWORD="Qq1234!@#$";

    private static Connection conn = null;

    static {
        try {
            //1、加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2、获得数据库连接
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return conn;
    }
}
