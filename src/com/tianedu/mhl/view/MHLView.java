package com.tianedu.mhl.view;

import com.tianedu.mhl.domain.DiningTable;
import com.tianedu.mhl.domain.Employee;
import com.tianedu.mhl.service.DiningTableService;
import com.tianedu.mhl.service.EmployeeService;
import com.tianedu.mhl.utils.Utility;

import java.util.List;
import java.util.Scanner;

/**
 * @author tian
 * 这是主界面
 */
public class MHLView {
    //控制是否为空
    private boolean loop = true;
    private String key = ""; //接受用户的选择
    //定义EmployeeService 属性
    private EmployeeService employeeService = new EmployeeService();
    //调用 DiningTable 的属性
    private DiningTableService diningTableService = new DiningTableService();

    public static void main(String[] args) {
        new MHLView().mainMenu();
    }
    // 完成订座
    public void orderDiningTable(){
        System.out.println("=============预定餐桌=================");
        System.out.print("请选择要预定的餐桌编号(-1退出):");
        int orderId  = Utility.readInt();
        if (orderId == -1){
            System.out.println("=============取消预定餐桌=================");
            return;
        }

        //该方法得到的是 Y 或者 n
        char key = Utility.readConfirmSelection();
        if (key == 'Y'){ //要预定
            DiningTable diningTable = diningTableService.getDiningTableById(orderId);
            if (diningTable == null){
                System.out.println("================该餐桌不存在===============");
                return;
                }
            //判断该餐桌状态是否为空
            if (!("空".equals(diningTable.getState()))){ //说明当前餐桌不等于空
                System.out.println("=============该餐桌已经预定或着就就餐中=================");
                return;
            }
            System.out.print("预定人的名字：");
            String orderName = Utility.readString(50);
            System.out.print("预定人的电话：");
            String orderTel = Utility.readString(50);
            //这是说明可以真的预定，更新餐桌状态
           if (diningTableService.orderDiningTable(orderId,orderName,orderTel)){
               System.out.println("=============预定餐桌成功=================");
           } else {
               System.out.println("=============预定餐桌失败=================");
           }
        }else {
            System.out.println("=============取消预定餐桌=================");
        }
    }

    //显示所有餐桌状态
    public void listDiningTable(){
        List<DiningTable> list = diningTableService.list();
        System.out.println("\n餐桌编号\t\t餐桌状态");
        for (DiningTable diningTable : list){
            System.out.println(diningTable);
        }
        System.out.println("=============显示完毕=================");
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
                    Employee employee = employeeService.getEmployeeByIdAndPwd(empId, pwd);
                    if(employee != null){  //说明存在用户
                        System.out.println("=================登录成功["+employee.getName()+"]=================\n");

                        //显示二级菜单,这里是循环操作
                        while (loop){
                            System.out.println("========== 满汉楼二级菜单=====");
                            System.out.println("\t\t 1 显示餐桌状态");
                            System.out.println("\t\t 2 预定餐桌");
                            System.out.println("\t\t 3 显示所有菜品");
                            System.out.println("\t\t 4 点餐服务");
                            System.out.println("\t\t 5 查看账单");
                            System.out.println("\t\t 6 结账");
                            System.out.println("\t\t 9 退出满汉楼 ");
                            System.out.println("请输入你所需要的服务:");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                listDiningTable();  //显示餐桌状态的方法666
                                    break;
                                case "2":
                                    orderDiningTable();
                                    //System.out.println("预定餐桌");
                                    break;
                                case "3":
                                    System.out.println("显示所有菜品");
                                    break;
                                case"4":
                                    System.out.println("点餐服务");
                                    break;
                                case"5":
                                    System.out.println("查看账单");
                                    break;
                                case"6":
                                    System.out.println("结账");
                                    break;
                                case"9":
                                   loop = false;
                                   break;
                                default:
                                    System.out.println("您的输入有误，清重新输入：");
                                    break;
                            }
                        }
                    }else {
                        System.out.println("=================登录失败=================");
                    }

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
