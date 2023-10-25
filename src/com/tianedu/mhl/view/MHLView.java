package com.tianedu.mhl.view;

import com.tianedu.mhl.utils.Utility;

import java.util.Scanner;

/**
 * @author tian
 *
 * 这是主界面
 */
public class MHLView {
    //控制是否为空
    private boolean loop = true;
    private String key = ""; //接受用户的选择

    public static void main(String[] args) {
        new MHLView().mainMenu();
    }
    //显示主菜单
    public void mainMenu(){
        while (loop){
            System.out.println("===============满汉楼=============");
            System.out.println("\t\t 1 登录满汉楼");
            System.out.println("\t\t 2 退出满汉楼");
            System.out.print("请输入你的选择：");
            key = Utility.readString(1);
            switch (key){
                case "1":
                    System.out.println("登录满汉楼");
                    System.out.print("请输入员工号：");
                    String empId = Utility.readString(50);
                    System.out.print("请输入密码：");
                    String pwd = Utility.readString(50);
                    //到数据库判断
                    break;
                case "2":
                  loop = false;
                    break;
                default:
                    System.out.println("你输入有误，请重新输入");
            }
        }
        System.out.println("你退出满汉楼系统~");
    }

}
