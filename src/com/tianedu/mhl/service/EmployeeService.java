package com.tianedu.mhl.service;

import com.tianedu.mhl.dao.EmployeeDAO;
import com.tianedu.mhl.domain.Employee;

/**
 * @author tian
 * 该类完成对employee 表的操作（通过调用employeeDAO对象完成）
 *
 */
public class EmployeeService {
    //定义一个EmployeeDAO 属性
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    //方法 根据empId 和 pwd 返回一个Employee对象
    //如果查询不到返回一个null
    public Employee getEmployeeByIdAndPwd(String empId,String pwd){
        Employee employee =
                employeeDAO.querySingle
                ("select * from employee where empId=? and pwd=md5(?)", Employee.class, empId, pwd);
        return employee;
    }
}
