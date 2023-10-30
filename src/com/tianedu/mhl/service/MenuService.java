package com.tianedu.mhl.service;

import com.tianedu.mhl.dao.MenuDAO;
import com.tianedu.mhl.domain.Menu;

import java.util.List;

/**
 * @author tian\
 * 完成对Menu 表的各种操作，通过调用MenuDAO完成的
 */
public class MenuService {
    //定义一个 MenuDAO 属性
    private MenuDAO menuDAO = new MenuDAO();

    //返回所有的菜品，返回给界面使用
    public List<Menu> list(){
        return menuDAO.queryMulti("select * from menu",Menu.class);
    }
    //需要方法，根据id 返回一个Menu对象
    public Menu getMenuById(int id){
        return menuDAO.querySingle("select *from menu where id = ?",Menu.class,id);
    }
}
