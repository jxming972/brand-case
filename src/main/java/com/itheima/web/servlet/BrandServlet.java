package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.Impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {

    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.调用service查询
        List<Brand> brands = brandService.selectAll();

        // 2.转为JSON
        String jsonString = JSON.toJSONString(brands);

        // 3.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 0. 设置编码（防止中文乱码）
        req.setCharacterEncoding("UTF-8");
        // 1. 接受品牌数据
        BufferedReader br = req.getReader();
        String params = br.readLine();

        // 2. 转为brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        // 3.调用service添加
        brandService.add(brand);

        // 4.响应成功的标识
        resp.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 0. 设置编码
        req.setCharacterEncoding("UTF-8");
        // 1. 接受品牌数据
        BufferedReader br = req.getReader();
        String params = br.readLine();

        // 2. 转为brand对象
        int[] ids = JSON.parseObject(params, int[].class);

        // 3.调用service添加
        brandService.deleteByIds(ids);

        // 4.响应成功的标识
        resp.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        // 2.转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        // 3.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 0. 设置编码
        req.setCharacterEncoding("UTF-8");

        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        BufferedReader br = req.getReader();
        String params = br.readLine();

        Brand brand = JSON.parseObject(params, Brand.class);

        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize, brand);

        // 2.转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        // 3.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }
}
