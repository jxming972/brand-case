package com.itheima.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatis 工具类 —— 单例模式
 * 负责读取 mybatis-config.xml，创建 SqlSessionFactory，
 * 并提供获取 SqlSession 的方法。
 */
public class SqlSessionFactoryUtil {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 1. 加载 mybatis-config.xml（它会自动读取 druid.properties）
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);

            // 2. 构建 SqlSessionFactory
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 3. 关闭输入流
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("加载 mybatis-config.xml 失败，检查文件路径", e);
        }
    }

    /**
     * 获取一个新的 SqlSession（需要手动关闭）
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    /**
     * 获取 SqlSessionFactory（一般用不到，留着备用）
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
