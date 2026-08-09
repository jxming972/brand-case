package com.itheima.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 通用父Servlet，负责方法分发
public class BaseServlet extends HttpServlet {

    // 重写service，所有get/post请求都会先进这个方法
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取请求URI  请求地址 /brand/selectAll
        String uri = req.getRequestURI();

        /*
        getRequestURI()：获取请求路径，例子：
        访问 http://localhost:8080/项目名/brand/selectAll
        uri = "/项目名/brand/selectAll"
        */

        // 2. 找到最后一个 / 的下标
        int index = uri.lastIndexOf("/");
        // 截取最后一段字符串，就是方法名 selectAll
        String methodName = uri.substring(index + 1);
        // substring(index+1)：从/的下一位截取到末尾
        // uri="/brand/selectAll"  lastIndexOf("/")=6 → index+1=7 → substring(7)得到 "selectAll"


        // =========反射部分：通过方法名字符串，调用对应的方法=========
        // this 就是子类对象 BrandServlet实例，getClass拿到它的字节码对象
        Class<? extends BaseServlet> cls = this.getClass();

        Method method = null;
        try {
            /*
            getMethod(方法名, 参数类型1,参数类型2)
            找 public void selectAll(HttpServletRequest req,HttpServletResponse resp)
            方法名字符串methodName，参数必须是 HttpServletRequest.class, HttpServletResponse.class
            */
            method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            // 找不到这个方法就抛出异常
            throw new RuntimeException(e);
        }

        try {
            // 执行方法：invoke(哪个对象执行，方法实参1，实参2)
            method.invoke(this, req, resp);
            // 等价调用 this.selectAll(req,resp);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
