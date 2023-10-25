package com.tianedu.mhl.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author tian
 *
 * //测试文件  测试这两个工具类是否可以正常使用
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        //测试Utility 工具类
        System.out.println("请输入一个整数");
        int i = Utility.readInt();
        System.out.println("i="+ i);

        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println(connection);
    }
}
