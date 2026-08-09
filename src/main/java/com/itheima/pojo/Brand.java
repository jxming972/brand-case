package com.itheima.pojo;

/**
 * 品牌实体类
 * 对应数据库表 tb_brand
 */
public class Brand {

    private Integer id;          // 主键
    private String brandName;    // 品牌名称
    private String companyName;  // 企业名称
    private Integer ordered;     // 排序
    private String description;  // 描述信息
    private Integer status;      // 状态：0=禁用，1=启用

    // ========== 构造方法 ==========

    public Brand() {
    }

    public Brand(Integer id, String brandName, String companyName,
                 Integer ordered, String description, Integer status) {
        this.id = id;
        this.brandName = brandName;
        this.companyName = companyName;
        this.ordered = ordered;
        this.description = description;
        this.status = status;
    }

    // ========== Getter & Setter ==========

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    // ========== toString ==========

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", ordered=" + ordered +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
