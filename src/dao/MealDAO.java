package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import model.*;
//public class MealDAO extends MySQLConnection implements IDAO{
	public class MealDAO extends MySQLConnection{
	/**
	 * 文件名：orderDAO.java
	 * 描述：这是order实体的dao（Data Access Object）类，负责访问数据库中关于合同的数据，实现增删查改操作。
	 * 创建日期：2014-04-24
	 * 创建者：李鹏翔
		private int recordNum=0;	//记录数
		private int pageNum=0;		//页面数
		
		/**
		 * orderDAO构造方法，初始化操作
		 */
		public MealDAO(){
		
		}
		public int getMealNum() {
			ResultSet rs1 = null;
			int mealNum = 0; 
			String sql="select count(*) from meal where ifDel=0";
			if(super.ConnectMySQL()){
				try {
			
				preStmt=(PreparedStatement) con.prepareStatement(sql);
			  rs1  = preStmt.executeQuery();
			  while(rs1.next())
			   mealNum = rs1.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				mealNum = -1;
				e.printStackTrace();
			}finally{
				super.CloseMySQL();
			}
			}	
			return mealNum;
			
		}

		public String[] getMealSet() {
	        int mealNum  = this.getMealNum();
	         String[] result = new String[mealNum];
	         int i = 0;
			if(super.ConnectMySQL()) {{
				try {
					String sql="select name from meal where ifDel=0 ";
					preStmt=(PreparedStatement) con.prepareStatement(sql);
					
					ResultSet res2 = null;
					res2=preStmt.executeQuery();
					while(res2.next())//该菜品可以点
					{   
						result[i++]= res2.getString(1);
					}
				}
				 catch (SQLException e) {
						// TODO Auto-generated catch block
						
						e.printStackTrace();
					}
					finally{
						super.CloseMySQL();
					}
			}
		}
			return result;
	}
		
		public int checkMealNum(String mealName, int mealNum) {
			int scc = 0;
			if(super.ConnectMySQL()){
				try {
					
					String sql = "select * from meal where ifDel=0 and name=? and num >= ?";
					preStmt=(PreparedStatement) con.prepareStatement(sql);
					preStmt.setString(1, mealName);
					preStmt.setInt(2, mealNum);
					ResultSet res=preStmt.executeQuery();
					if(res.next()){//有菜品信息
							int Id = res.getInt("id");
							int newNum = res.getInt("num") -mealNum;
							if(newNum < 0)
								{
								scc = -1;
								return scc;
								}
							Meal meal = new Meal(Id,mealName,(float)-1,"",-1,newNum,(float)-1,"");
							this.UpdateMeal(meal);
					}
					else {
						scc = -1;
					}
				}
				 catch (SQLException e) {
					// TODO Auto-generated catch block
					scc = -2;
					e.printStackTrace();
				}
			}
			
		return scc;
		}
		

		/**
		 * 插入一条合同信息到数据库
		 * @param Meal Meal 被插入的合同对象
		 * @return succ boolean 函数执行的结果
		 */
		
//		public boolean AddMeal(Meal Meal) {
//			// TODO Auto-generated method stub
//			boolean succ=true;
//		
//			if(super.ConnectMySQL()){
//				try {
//					super.con.setAutoCommit(false); 
//					String sql="insert into meal values(?,?,?,?,?,?,?,0)";
//					preStmt=(PreparedStatement) con.prepareStatement(sql);
//					preStmt.setInt(1, order.GetId());
//					preStmt.setInt(2, order.GetStatus());
//					preStmt.setString(3, order.GetMealList());
//					preStmt.setInt(4, order.GetCafeId());
//					preStmt.setFloat(5, order.GetOldTotalPrice());
//					preStmt.setFloat(6, order.GetNewTotalPrice());
//					preStmt.setTimestamp(7, order.GetTime());
//					preStmt.executeUpdate()
//					if(preStmt.executeUpdate()!=0 && ){
//						succ=false;
//					}
//					super.con.commit(); 
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					succ=false;
//					e.printStackTrace();
//				}finally{
//					super.CloseMySQL();
//				}
//				
//			}else{
//				succ=false;
//			}
//			
//			return succ;
//		}

		/**
		 * 从数据库删除合同信息，并非真正在数据库中删除，只需把del字段置为1
		 * @param Meal Meal 被删除的合同需符合的条件
		 * @return succ boolean 函数执行的结果
		 */
		//@Override
//		public boolean DeleteMeal(Meal Meal) {
//			// TODO Auto-generated method stub
//			boolean succ=true;
//			Order order=(Order)Meal;
//			
//			if(order.GetId()==0){
//				return false;
//			}
//			
//			if(super.ConnectMySQL()){
//				String sql="update order set ifDel=1 where ifDel=0 and id=?";
//				try {
//					preStmt=(PreparedStatement) con.prepareStatement(sql);
//					preStmt.setInt(1, order.GetId());
//					if(preStmt.executeUpdate()==0){
//						succ=false;
//					}
//					
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					succ=false;
//					e.printStackTrace();
//				}finally{
//					super.CloseMySQL();
//				}
//				
//			}else{
//				succ=false;
//			}
//			
//			return succ;
//		}
//
//		/**
//		 * 从数据库更新一条合同信息
//		 * @param Meal Meal 新的的对象，替换掉原来的对象
//		 * @return	succ Boolean 函数执行的结果
//		 */
//		@Override
		public boolean UpdateMeal(Meal meal) {
			// TODO Auto-generated method stub
			//直接传入整个实体？
			boolean succ=true;
		
			if(super.ConnectMySQL()){
				String sql="update meal set ";
				try {
					
				
					if(meal.GetCafeId()!= -1){
						sql+="cafeId='"+meal.GetCafeId()+"',";
					}
					if(meal.GetDesc()!=""){
						sql+="descs ="+meal.GetDesc()+",";
					}
					if(meal.GetName()!= ""){
						sql+="name='"+meal.GetName()+"',";
					}
					if(meal.GetEvaluation()!= ""){
						sql+="evaluation='"+meal.GetEvaluation()+"',";
					}
					if(meal.GetNum()!= -1){
						sql+="num='"+meal.GetNum()+"',";
					}
					if(meal.GetPrice()!= -1){
						sql+="price="+meal.GetPrice()+",";
					}
					//去掉最后的“,”
					sql=sql.substring(0, sql.length()-1);
					
					sql+=" where ifDel=0 and id="+meal.GetId();
					
					
					if(stmt.executeUpdate(sql)==0){
						succ=false;
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					succ=false;
					e.printStackTrace();
				}
			}else{
				succ=false;
			}
			
			return succ;
		}
		}
//
//		/**
//		 * 查找一条合同信息
//		 * @param id int 被查找的对象的id
//		 * @return Meal Meal 如果找到，返回指定的订单信息，若没有，返回null
//		 */
//		@Override
//		public Meal GetOneMeal(int id) {
//			// TODO Auto-generated method stub
//			
//			Order order=null;
//			//id必须合法,不然返回null值
//				if(super.ConnectMySQL()){
//					
//					String sql="select *from order where del=0 and id=?";
//					try {
//						preStmt=(PreparedStatement) con.prepareStatement(sql);
//						preStmt.setInt(1, id);
//						ResultSet res=preStmt.executeQuery();
//						if(res.next()){
//							order=new Order(id,
//									            res.getInt("status"),
//									            res.getString("mealList"),
//									            res.getInt("cafeId"),
//												res.getFloat("oldTotalPrice"),
//												res.getFloat("newTotalPrice"),
//												res.getTimestamp("time"));
//						}
//						
//					} 
//					catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} 
//					finally {
//						super.CloseMySQL();
//					}
//					
//				}
//			
//			
//			return order;
//		}
//		
//		/**
//		 * 查找一条合同信息
//		 * @param Meal Meal 被查找的对象的id
//		 * @return Meal Meal 如果找到，返回指定的合同信息，若没有，返回null
//		 */
//		public Meal GetOneMeal(Meal Meal) {
//			// TODO Auto-generated method stub
//			
//			Order order=(Order)Meal;
//			//id必须合法,不然返回null值
//			if(order!=null){
//				if(super.ConnectMySQL()){
//					
//					String sql="select *from order where del=0 and ";
//					
//					if(order.GetId()!=0){
//						sql+="id="+order.GetId()+" and ";
//					}
//					if(order.GetTime()!=null){
//						sql+="time='"+order.GetTime()+"' and ";
//					}
//					if(order.GetStatus()!=0){
//						sql+="status="+order.GetStatus()+" and ";
//					}
//					if(order.GetMealList()!=null){
//						sql+="mealList='"+order.GetMealList()+"' and ";
//					}
//					if(order.GetCafeId()!= 0){
//						sql+="cafeId='"+order.GetCafeId()+"' and ";
//					}
//					if(order.GetOldTotalPrice()!= 0){
//						sql+="oldTotalPrice'"+order.GetOldTotalPrice()+"' and ";
//					}
//					if(order.GetNewTotalPrice()!=0){
//						sql+="newTotalPrice="+order.GetNewTotalPrice()+" and ";
//					}
//					if(order.GetTime()!= null){
//						sql+="newTotalPrice="+order.GetNewTotalPrice()+" and ";
//					}
//					//去掉最后的“and”
//					sql=sql.substring(0, sql.length()-4);
//					
//					try {
//						ResultSet res=stmt.executeQuery(sql);
//						if(res.next()){
//							order=new Order(res.getInt("id"),
//						            res.getInt("status"),
//						            res.getString("mealList"),
//						            res.getInt("cafeId"),
//									res.getFloat("oldTotalPrice"),
//									res.getFloat("newTotalPrice"),
//									res.getTimestamp("time"));
//							
//						}
//						
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} finally {
//						super.CloseMySQL();
//					}
//					
//				}
//			}
//			return order;
//		}
//		
//		/**
//		 * 分页显示时，查询指定页的所有合同的集合
//		 * @param Meal 查询条件对象
//		 * @param pageNo 指定查询的页号
//		 * @param pageRecordNum 指定每页显示的记录数
//		 * @return arr ArrayList<Meal> 查询的结果集，若不符合查询条件，则返回null
//		 */

//		public ArrayList<Meal> GetMealSet(Meal Meal) {
//			// TODO Auto-generated method stub
//			
//			ArrayList<Meal> arr=null;
//			Order order=(Order)Meal;
//			if(super.ConnectMySQL()){
//				String sqlGetNum="select COUNT(id)";	//获取实体数量的sql语句前缀
//				String sqlGetMealSet="select *";		//获取实体集合的sql语句前缀
//				String sql=" from order where ifDel=0 and ";
//				if(order.GetId()!=0){
//					sql+="id="+order.GetId()+" and ";
//				}
//				if(order.GetStatus()!=0){
//					sql+="status="+order.GetStatus()+" and ";
//				}
//				if(order.GetMealList()!= null){
//					sql+="mealList="+order.GetMealList()+" and ";
//				}
//				if(order.GetCafeId()!=0){
//					sql+="cafeId="+order.GetCafeId()+" and ";
//				}
//				if(order.GetOldTotalPrice()!= 0){
//					sql+="oldTotalPrice='"+order.GetOldTotalPrice()+"' and ";
//				}
//				if(order.GetNewTotalPrice()!= 0){
//					sql+="newTotalPrice='"+order.GetNewTotalPrice()+"' and ";
//				}
//				if(order.GetTime()!=null){
//					sql+="time='"+order.GetTime()+"' and ";
//				}
//				
//				//去掉最后的“and”
//				sql=sql.substring(0, sql.length()-4);
//				
//				ResultSet res;
//				try {
//					
//					sqlGetNum+=sql;
//					res = stmt.executeQuery(sqlGetNum);
//					if(res.next()){
//						this.recordNum=res.getInt(1);
//					}
//					
////					//算出共有多少页
////					pageNum=recordNum/pageRecordNum;
////					if(recordNum%pageRecordNum!=0){
////						pageNum++;
////					}
////					
////					if(pageNo<=pageNum){
////						//设置limit值
////						int startNo=(pageNo-1)*pageRecordNum;
////						sqlGetMealSet+=sql+" limit "+startNo+","+pageRecordNum;
////						
////						arr=new ArrayList<Meal>();
////						res = stmt.executeQuery(sqlGetMealSet);
//						while(res.next()){
//							order=new Order(res.getInt("id"),
//						            res.getInt("status"),
//						            res.getString("mealList"),
//						            res.getInt("cafeId"),
//									res.getFloat("oldTotalPrice"),
//									res.getFloat("newTotalPrice"),
//									res.getTimestamp("time"));
//							
//							arr.add(order);
//						}
////					}
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}finally{
//					super.CloseMySQL();
//				}
//				
//			}
//			
//			return arr;
//		}
//		
//		/**
//		 * 返回查询条件下的合同总条数
//		 * @return int 查询条件下的合同总条数
//		 */
//		@Override
//		public int getRecordNum() {
//			// TODO Auto-generated method stub
//			return this.recordNum;
//		}
//
//		@Override
//		public boolean AddEntity(IEntity entity) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public boolean DeleteEntity(IEntity entity) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public boolean UpdateEntity(IEntity entity) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public IEntity GetOneEntity(int id) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public IEntity GetOneEntity(IEntity entity) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public ArrayList<IEntity> GetEntitySet(IEntity entity) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		
//		/**
//		 * 返回查询条件下的合同页数
//		 * @return int 查询条件下的合同页数
//		 */
////		@Override
////		public int GetPageNum() {
////			// TODO Auto-generated method stub
////			return this.pageNum;
////		}
//
//
//
//	}
//
//}
