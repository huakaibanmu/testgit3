package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import model.*;
//public class MealDAO extends MySQLConnection implements IDAO{
	public class MealDAO extends MySQLConnection{
	/**
	 * �ļ�����orderDAO.java
	 * ����������orderʵ���dao��Data Access Object���࣬����������ݿ��й��ں�ͬ�����ݣ�ʵ����ɾ��Ĳ�����
	 * �������ڣ�2014-04-24
	 * �����ߣ�������
		private int recordNum=0;	//��¼��
		private int pageNum=0;		//ҳ����
		
		/**
		 * orderDAO���췽������ʼ������
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
					while(res2.next())//�ò�Ʒ���Ե�
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
					if(res.next()){//�в�Ʒ��Ϣ
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
		 * ����һ����ͬ��Ϣ�����ݿ�
		 * @param Meal Meal ������ĺ�ͬ����
		 * @return succ boolean ����ִ�еĽ��
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
		 * �����ݿ�ɾ����ͬ��Ϣ���������������ݿ���ɾ����ֻ���del�ֶ���Ϊ1
		 * @param Meal Meal ��ɾ���ĺ�ͬ����ϵ�����
		 * @return succ boolean ����ִ�еĽ��
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
//		 * �����ݿ����һ����ͬ��Ϣ
//		 * @param Meal Meal �µĵĶ����滻��ԭ���Ķ���
//		 * @return	succ Boolean ����ִ�еĽ��
//		 */
//		@Override
		public boolean UpdateMeal(Meal meal) {
			// TODO Auto-generated method stub
			//ֱ�Ӵ�������ʵ�壿
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
					//ȥ�����ġ�,��
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
//		 * ����һ����ͬ��Ϣ
//		 * @param id int �����ҵĶ����id
//		 * @return Meal Meal ����ҵ�������ָ���Ķ�����Ϣ����û�У�����null
//		 */
//		@Override
//		public Meal GetOneMeal(int id) {
//			// TODO Auto-generated method stub
//			
//			Order order=null;
//			//id����Ϸ�,��Ȼ����nullֵ
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
//		 * ����һ����ͬ��Ϣ
//		 * @param Meal Meal �����ҵĶ����id
//		 * @return Meal Meal ����ҵ�������ָ���ĺ�ͬ��Ϣ����û�У�����null
//		 */
//		public Meal GetOneMeal(Meal Meal) {
//			// TODO Auto-generated method stub
//			
//			Order order=(Order)Meal;
//			//id����Ϸ�,��Ȼ����nullֵ
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
//					//ȥ�����ġ�and��
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
//		 * ��ҳ��ʾʱ����ѯָ��ҳ�����к�ͬ�ļ���
//		 * @param Meal ��ѯ��������
//		 * @param pageNo ָ����ѯ��ҳ��
//		 * @param pageRecordNum ָ��ÿҳ��ʾ�ļ�¼��
//		 * @return arr ArrayList<Meal> ��ѯ�Ľ�������������ϲ�ѯ�������򷵻�null
//		 */

//		public ArrayList<Meal> GetMealSet(Meal Meal) {
//			// TODO Auto-generated method stub
//			
//			ArrayList<Meal> arr=null;
//			Order order=(Order)Meal;
//			if(super.ConnectMySQL()){
//				String sqlGetNum="select COUNT(id)";	//��ȡʵ��������sql���ǰ׺
//				String sqlGetMealSet="select *";		//��ȡʵ�弯�ϵ�sql���ǰ׺
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
//				//ȥ�����ġ�and��
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
////					//������ж���ҳ
////					pageNum=recordNum/pageRecordNum;
////					if(recordNum%pageRecordNum!=0){
////						pageNum++;
////					}
////					
////					if(pageNo<=pageNum){
////						//����limitֵ
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
//		 * ���ز�ѯ�����µĺ�ͬ������
//		 * @return int ��ѯ�����µĺ�ͬ������
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
//		 * ���ز�ѯ�����µĺ�ͬҳ��
//		 * @return int ��ѯ�����µĺ�ͬҳ��
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
