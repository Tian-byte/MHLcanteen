package com.tianedu.mhl.service;

import com.tianedu.mhl.dao.BillDAO;
import com.tianedu.mhl.dao.DiningTableDAO;
import com.tianedu.mhl.domain.Bill;
import com.tianedu.mhl.domain.DiningTable;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author tian
 *
 * 处理和账单相关的业务逻辑
 */
public class BillService {
    //定义BillDAO属性
    private BillDAO billDAO = new BillDAO();
    //定义一个MenuService 属性 也就是 BillService 协同合作
    private MenuService menuService = new MenuService();
    //定义 DiningTableService属性
    private DiningTableService diningTableService = new DiningTableService();
    // 编写点餐的方法
    //1.生成账单
    //2.需要更新对应餐桌的状态
    //如果成功返回true 否则返回 false
    public boolean orderMenu(int menuId,int nums,int diningTableId){
        //生成一个账单号
        String billId = UUID.randomUUID().toString();
        //将账单生成到bill表,要求直接计算订单的金额
        int update = billDAO.update("insert into bill values(null,?,?,?,?,?,now(),'未结账')",
                billId, menuId, nums, menuService.getMenuById(menuId).getPrice() * nums, diningTableId);

        if (update <= 0 ){
            return false;
        }
        //需要跟新对应菜单的状态
       return diningTableService.updateDiningTableState(diningTableId,"就餐中");
    }
    //返回所有的账单，提供给view 调用
    public List<Bill> list(){
        return billDAO.queryMulti("select * from bill",Bill.class);
    }
    //查看某个餐桌是否有未结账的账单
    public boolean hasPayBillByDiningTableId(int diningTableId){
        Bill bill = billDAO.querySingle("select * from bill where diningTableId = ? and state = '未结账' limit 0,1", Bill.class, diningTableId);
        return bill != null;

    }
    // 完成结账 如果餐桌存在 并且该餐桌有为结账的账单   如果成功返回一个布尔值
    public boolean payBill(int diningTableId, String payMode){
        //1.修该bill表
        int update = billDAO.update("update bill set state = ? where diningTableId = ? and state = '未结账' ", payMode, diningTableId);
        if (update <= 0){  //如果更新没有成功，则表示失败....
            return false;
        }
        //2.修改diningTable 表
        //注意不要在这里操作 而应该调用DiningTableService 方法，完成更新
        if (!diningTableService.updateDiningTableToFree(diningTableId,"空")){
            return false;
        }
        return true;
    }

}
