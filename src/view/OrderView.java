package view;
import javax.swing.*;
 
import java.awt.*;   //�����Ҫ�İ�
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.MySQLConnection;
import dao.OrderDAO;
import dao.MealDAO;
 public class OrderView extends JFrame implements ActionListener{
    JTextField jTextField ;//�����ı������
     JLabel jLabel1,jLabel2;
     JPanel jp1,jp2,jp3;
     JButton jb1,jb2,jb3; //������ť
     JComboBox jcb;  //������ 
     OrderDAO orderdao = new OrderDAO();
     MealDAO mealdao = new MealDAO();
     ArrayList<String> ls = new ArrayList();
     @SuppressWarnings("unchecked")
	public OrderView(){
         jTextField = new JTextField(12);
         jLabel1 = new JLabel("��Ʒѡ��");
         String str1[] = mealdao.getMealSet(); 
         jLabel2 = new JLabel("��д����");
         jcb = new JComboBox(str1);  
         jb1 = new JButton("�ύ");
         jb2 = new JButton("ȡ��");
         jb3 = new JButton("�鿴����״̬");
         jp1 = new JPanel();
         jp2 = new JPanel();
         jp3 = new JPanel();
         jb1.addActionListener(this);
         jb2.addActionListener(this);
         jb3.addActionListener(this);
         //���ò���
        this.setLayout(new GridLayout(3,1));
         
         jp1.add(jLabel1); 
         jp1.add(jcb);
         jp1.add(jLabel2); 
         jp1.add(jTextField); 
        
         
         jp2.add(jb1);
         jp2.add(jb2); 
         jp2.add(jb3);
        //        jp3.setLayout(new FlowLayout());  ����//��ΪJPanelĬ�ϲ��ַ�ʽΪFlowLayout�����Կ���ע����δ���.
         this.add(jp1);
         this.add(jp2);
       //������ʾ
         this.setSize(400, 200);
         //this.pack();
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("��ȡ����");
     }
     public void actionPerformed(ActionEvent e) {
 		// TODO Auto-generated method stub
 		if(e.getActionCommand()=="�ύ")
 		{
 			String mealOrderNum = jTextField.getText(); 
 			String mealString = (String)jcb.getSelectedItem();  
 			int mOrderNum = Integer.parseInt(mealOrderNum);
 		 int resultMsg = orderdao.AddOrder(mealString,mOrderNum);
 	     if(resultMsg < 0) 
 	    	JOptionPane.showMessageDialog(null, "�ò�Ʒ�����ڻ��ۿ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
 	     else {
 	    	JOptionPane.showMessageDialog(null, "���ɶ����ɹ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
 	     }
 	    	 
 		}
 		else if(e.getActionCommand() =="�鿴����״̬") {
 			new SeeOrder();
 		}
 		 else if(e.getActionCommand()=="ȡ��")
 		{
 				//����ע�᷽��
 			this.dispose();
 			
 		}
 	
     }
     public static void main(String[] args){
    	 //创建线程池
    	 ThreadPoolExecutor executor = new ThreadPoolExecutor(3000, 3000, 200, TimeUnit.MILLISECONDS,  
          new ArrayBlockingQueue<Runnable>(3000));  
         long start = System.currentTimeMillis();  
         System.out.println("activeCountMain1 : " + Thread.activeCount()); 
 		for (int i = 1; i <= 3000; i++) {
 			OrderDAO newOrder = new OrderDAO(i);
 			executor.execute(newOrder);
 			System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" + executor.getQueue().size()
 					+ "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
 		}
 		executor.shutdown();
 		while (true) {
 			if (executor.getActiveCount() == 0)
 				break;
 		}
 		System.out.println("activeCountMain2 : " + Thread.activeCount());
 		long end = System.currentTimeMillis();
 		System.out.println("平均每秒可输出: " + 100000 / (end - start) + " 条");
       /* new OrderView();*/
     }
 }


	
	
	
	
	
	
	
	
	
	
	
	

