/**
 * �ļ�����orderDAO.java
 * ����������orderʵ���dao��Data Access Object���࣬����������ݿ��й��ں�ͬ�����ݣ�ʵ����ɾ��Ĳ�����
 * �������ڣ�2014-04-24
 * �����ߣ�������
 */

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import com.mysql.jdbc.PreparedStatement;
import model.*;

public class OrderDAO extends MySQLConnection implements Runnable{
	private int ordernum;
	public OrderDAO(){
	
		}
	public OrderDAO(int num){
	this.ordernum = num;
	}
	public int getOrderNum() {
		ResultSet rs1 = null;
		int orderNum = 0; 
		String sql="select count(*) from orders where ifDel=0";
		if(super.ConnectMySQL()){
			try {
		
			preStmt=(PreparedStatement) con.prepareStatement(sql);
		  rs1  = preStmt.executeQuery();
		  while(rs1.next())
		   orderNum = rs1.getInt(1);
		  
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			orderNum = -1;
			e.printStackTrace();
		}
		}	
		return orderNum;
		
	}

	public int AddOrder(String mealname,int num) {
		// TODO Auto-generated method stub
		 Order order;
		Timestamp time = new Timestamp(System.currentTimeMillis()); 
		 int  succ= 0;
		 int orderNum = this.getOrderNum();
		 if(orderNum >= 0) {
			 orderNum++;
       order = new Order(orderNum,0,mealname,num,0,0,100,200,time);
       	     MealDAO mealdao = new MealDAO();
				int errMeal = mealdao.checkMealNum(mealname,num);
		        if(errMeal < 0) {
					
			     return -1;
		        }
		 
		if(super.ConnectMySQL() && succ == 0){
			try {
				super.con.setAutoCommit(false);
				String sql="insert into orders values(?,?,?,?,?,?,?,?,?,0)";
				preStmt=(PreparedStatement) con.prepareStatement(sql);
				preStmt.setInt(1, order.GetId());
				preStmt.setInt(2, order.GetStatus());
				preStmt.setString(3, order.GetmealName());
				preStmt.setInt(4, order.GetmealNum());
				preStmt.setInt(5, order.GetCafeId());
				preStmt.setInt(6, order.GetUserId());
				preStmt.setFloat(7, order.GetOldTotalPrice());
				preStmt.setFloat(8, order.GetNewTotalPrice());
				preStmt.setString(9, order.GetTime().toString());
				preStmt.executeUpdate();
			    super.con.commit(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				succ= -1;
				e.printStackTrace();
				try {
				   super.con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else{
			succ= -1;
		}
		 } 
		return succ;
	}


	
	public boolean DeleteOrder(Order order) {
		// TODO Auto-generated method stub
		boolean succ=true;
		if(order.GetId()==0){
			return false;
		}
		if(super.ConnectMySQL()){
			String sql="update order set ifDel=1 where ifDel=0 and id=?";
			try {
				preStmt=(PreparedStatement) con.prepareStatement(sql);
				preStmt.setInt(1, order.GetId());
				if(preStmt.executeUpdate()==0){
					succ=false;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				succ=false;
				e.printStackTrace();
			}finally{
				super.CloseMySQL();
			}
			
		}else{
			succ=false;
		}
		
		return succ;
	}

	
	public boolean UpdateOrder(int id, int status) {
		// TODO Auto-generated method stub
		
		boolean succ=true;
		
		if(super.ConnectMySQL()){
			String sql="update order set ";
			try {
					sql+="status='"+status+"',";
				
				//ȥ�����ġ�,��
				sql=sql.substring(0, sql.length()-1);
				
				sql+=" where ifDel=0 and id="+id;
				if(stmt.executeUpdate(sql)==0){
					succ=false;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				succ=false;
				e.printStackTrace();
			}finally{
				super.CloseMySQL();
			}
			
		}else{
			succ=false;
		}
		
		return succ;
	}

	
	
	/**
	 * ����һ����ͬ��Ϣ
	 * @param id int �����ҵĶ����id
	 * @return entity IEntity ����ҵ�������ָ���Ķ�����Ϣ����û�У�����null
	 */
	
	public  Order GetOneOrder(int id) {
		// TODO Auto-generated method stub
		
		Order order=null;
		//id����Ϸ�,��Ȼ����nullֵ
			if(super.ConnectMySQL()){
				
				String sql="select *from order where del=0 and id=?";
				try {
					preStmt=(PreparedStatement) con.prepareStatement(sql);
					preStmt.setInt(1, id);
					ResultSet res=preStmt.executeQuery();
					if(res.next()){
						order=new Order(id,
								            res.getInt("status"),
								            res.getString("mealName"),
								            res.getInt("mealNum"),
								            res.getInt("cafeId"),
								            res.getInt("userId"),
											res.getFloat("oldTotalPrice"),
											res.getFloat("newTotalPrice"),
											res.getTimestamp("time"));
					}
					
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				finally {
					super.CloseMySQL();
				}
				
			}
		return order;
	}
	
	/**
	 * ����һ����ͬ��Ϣ
	 * @param entity IEntity �����ҵĶ����id
	 * @return entity IEntity ����ҵ�������ָ���ĺ�ͬ��Ϣ����û�У�����null
	 */
	@SuppressWarnings("null")
	public ArrayList<Order> GetOrders(Order order) {
		// TODO Auto-generated method stub
		
		ArrayList<Order> arr=null;
		//id����Ϸ�,��Ȼ����nullֵ
		if(order!=null){
			if(super.ConnectMySQL()){
				
				String sql="select *from order where del=0 and ";
				
				if(order.GetId()!=0){
					sql+="id="+order.GetId()+" and ";
				}
				if(order.GetTime()!=null){
					sql+="time='"+order.GetTime()+"' and ";
				}
				if(order.GetStatus()!=0){
					sql+="status="+order.GetStatus()+" and ";
				}
				if(order.GetmealName()!=null){
					sql+="mealName='"+order.GetmealName()+"' and ";
				}
				if(order.GetmealNum()!= 0){
					sql+="mealNum='"+order.GetmealNum()+"' and ";
				}
				if(order.GetCafeId()!= 0){
					sql+="cafeId='"+order.GetCafeId()+"' and ";
				}
				if(order.GetOldTotalPrice()!= 0){
					sql+="oldTotalPrice'"+order.GetOldTotalPrice()+"' and ";
				}
				if(order.GetNewTotalPrice()!=0){
					sql+="newTotalPrice="+order.GetNewTotalPrice()+" and ";
				}
				if(order.GetTime()!= null){
					sql+="newTotalPrice="+order.GetNewTotalPrice()+" and ";
				}
				//ȥ�����ġ�and��
				sql=sql.substring(0, sql.length()-4);
				
				try {
					ResultSet res=stmt.executeQuery(sql);
					while(res.next()){
						order=new Order(res.getInt("id"),
					            res.getInt("status"),
					            res.getString("mealName"),
					            res.getInt("mealNum"),
					            res.getInt("cafeId"),  
					            res.getInt("userId"),
								res.getFloat("oldTotalPrice"),
								res.getFloat("newTotalPrice"),
								res.getTimestamp("time"));
						arr.add(order);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					super.CloseMySQL();
				}
				
			}
		}
		return arr;
	}
	@Override
	public void run(){
		// TODO Auto-generated method stub
		AddOrder("cabbage",1);
	}
}
