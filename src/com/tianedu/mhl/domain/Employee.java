package com.tianedu.mhl.domain;

import com.mysql.jdbc.PingTarget;

/**
 * @author tian
 *
 * 这是一个Javabean  和 employee 对应
 */
public class Employee {
    private Integer id;//建议使用Integer 防止空指针异常
    private String empId;
    private String pwd;
    private String name;
    private String job;

    public Employee() {  //无参构造器，底层是apache-dbutils 反射需要
    }

    public Employee(Integer id, String empId, String pwd, String name, String job) {
        this.id = id;
        this.empId = empId;
        this.pwd = pwd;
        this.name = name;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
