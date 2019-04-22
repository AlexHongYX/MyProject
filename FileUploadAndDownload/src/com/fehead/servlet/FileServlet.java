package com.fehead.servlet;

import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class FileServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws javax.servlet.ServletException, IOException {

//        //从request中获取流信息
//        InputStream fileSource = request.getInputStream();
//
//        //不要用request直接读取数据，会产生数据的丢失，所以使用Part对象，用里面的getInputStream就可以读取到完整的内容信息了
//        //后面的操作都可以不做
//
//
//        String tempFileName = "E:/tempFile";
//
//        //tempFile指向临时文件
//        File tempFile = new File(tempFileName);
//
//        //outputStream文件输出流指向这个临时文件
//        FileOutputStream outputStream = new FileOutputStream(tempFile);
//
//        //每次读取1024字节
//        byte b[] = new byte[1024];
//        int n;
//
//        while ((n=fileSource.read(b))!=-1){
//            outputStream.write(b,0,n);
//        }
//
//        //关闭输入流与输出流
//        outputStream.close();
//        fileSource.close();
//
//        //获取上传文件的名称
//        RandomAccessFile randomAccessFile = new RandomAccessFile(tempFile,"r");
//        //指针往下走一行，什么也不做
//        randomAccessFile.readLine();
//        //保存第二行的数据（Filename在第二行中）
//        //根据不同的浏览器，出来的filename格式不一样
//        String str = randomAccessFile.readLine();
//        //截取第二行中的Filename
//        int beginIndex = str.lastIndexOf("=")+2;
//        int endIndex = str.lastIndexOf("\"");
//        String filename = str.substring(beginIndex,endIndex);
//        System.out.println("filename:"+filename);
//
//        //获取文件内容
//            //重定位到文件头
//        randomAccessFile.seek(0);
//        long startPosition = 0;
//        int i=1;
//        //获取文件开始位置（第四行）
//        while ((n=randomAccessFile.readByte())!=-1&&i<=4){
//            if (n == '\n') {
//                startPosition = randomAccessFile.getFilePointer();
//                i++;
//            }
//        }
//        startPosition = startPosition-1;
//        //获取文件开结束位置（倒数第三行最后）
//            //定位到最后
//        randomAccessFile.seek(randomAccessFile.length());
//        long endPosition =randomAccessFile.getFilePointer();
//        int j=1;
//        while (endPosition>=0&&j<=2){
//            endPosition--;
//            randomAccessFile.seek(endPosition);
//            if (randomAccessFile.readByte()=='\n'){
//                j++;
//            }
//        }
//        endPosition = endPosition-1;
//
//        //设置保存上传文件的路径
//        String realPath = getServletContext().getRealPath("/")+"files";
//        File fileupload = new File(realPath);
//        if(!fileupload.exists()){
//            fileupload.mkdir();
//        }
//        File saveFile = new File(realPath,filename);
//        RandomAccessFile randomAccessFile1 = new RandomAccessFile(saveFile,"rw");
//
//        //从临时文件中读取文件的内容（根据起止位置获取）
//        randomAccessFile.seek(startPosition);
//        while (startPosition<endPosition){
//            randomAccessFile1.write(randomAccessFile.readByte());
//            startPosition = randomAccessFile.getFilePointer();
//        }
//
//        //关闭输入输出流，删除临时文件
//        randomAccessFile.close();
//        randomAccessFile1.close();
////        tempFile.delete();
//
//        request.setAttribute("filename",filename);

        Part part = request.getPart("myfile");
        InputStream inputStream = part.getInputStream();


        request.setAttribute("inputStream",inputStream);
        request.getRequestDispatcher("success.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
