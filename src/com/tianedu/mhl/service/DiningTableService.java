package com.tianedu.mhl.service;


import com.tianedu.mhl.dao.DiningTableDAO;
import com.tianedu.mhl.domain.DiningTable;

import java.util.List;

/**
 * @author tian
 */
public class DiningTableService {  //业务层
    //定义一个DiningTableDAO对象
   private DiningTableDAO diningTableDAO = new DiningTableDAO();
   //返回所 有餐桌的一个信息
    public List<DiningTable> list(){
  return   diningTableDAO.queryMulti("select id,state from diningTable",DiningTable.class);
    }
    //根据id 查询对应的餐桌 DiningTable 对象
    // 如果 DiningTable 对象为空 表示id编号对应的餐桌不存在
    public DiningTable getDiningTableById(int id) {
        return diningTableDAO.querySingle("select * from diningTable where id = ?",DiningTable.class,id);
    }
    //如果餐桌可以预定 调用方法对其状态进行更新 包括预定人的名字和电话
    public boolean orderDiningTable(int id,String orderName,String orderTel){
        int update =
                diningTableDAO.update("update diningTable set state = '已经预定',orderName = ?,orderTable = ?  where id = ?", orderName, orderTel,id);
        return update>0;
    }
    // 需要提供一个更新餐桌状态的方法
    public boolean updateDiningTableState(int id,String state){
        int update = diningTableDAO.update("update diningTable set state = ? where id = ?", state, id);
        return update > 0;
    }
    //提供方法 将指定的餐桌设置为空闲状态
    public boolean updateDiningTableToFree(int id,String state){
        int update = diningTableDAO.update("update diningTable set state = ?,orderName= '',orderTel='',where id = ?", state, id);
        return update > 0;
    }
}
