package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 品牌 Mapper 接口 — 对应 tb_brand 表的 CRUD 操作
 */
public interface BrandMapper {

    /**
     * 1. 查询所有品牌
     */
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /**
     * 2. 新增品牌
     */
    @Insert("insert into tb_brand values (null, #{brandName}, #{companyName}, #{ordered}, #{description}, #{status})")
    void add(Brand brand);

    /**
     * 3. 根据 ID 修改品牌
     */
    void update(Brand brand);

    /**
     * 4. 根据 ID 删除单个品牌
     */
    void deleteById(int id);

    /**
     * 5. 批量删除品牌（根据 ID 数组）
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 6. 分页查询
     * @param begin 起始索引（从第几条开始）
     * @param size  每页条数
     */
    @Select("select * from tb_brand limit #{begin}, #{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin, @Param("size") int size);

    /**
     * 查询总记录数（用于分页计算总页数）
     */
    @Select("select count(*) from tb_brand")
    int selectTotalCount();

    /**
     * 7. 条件查询 + 分页
     * @param begin 起始索引
     * @param size  每页条数
     * @param brand 查询条件（品牌名、企业名、状态）
     */
    List<Brand> selectByPageAndCondition(@Param("begin") int begin,
                                         @Param("size") int size,
                                         @Param("brand") Brand brand);

    /**
     * 按条件查询总记录数
     */
    int selectTotalCountByCondition(Brand brand);
}
