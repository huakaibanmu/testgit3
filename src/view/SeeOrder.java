package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;   //�����Ҫ�İ�
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.MySQLConnection;
import dao.OrderDAO;
import model.Order;
import dao.MealDAO;
 public class SeeOrder extends JFrame{
	 private DefaultTableModel model = null;
	   private JTable table = null;
   OrderDAO orderdao = new OrderDAO();
	   private JButton addBtn = null;
	public SeeOrder(){
    	
    	      super("TableDemo");
    	      String[][] datas = {};
    	      String[] titles = { "������", "״̬" };
    	      model = new DefaultTableModel(datas, titles);
    	      table = new JTable(model);
    	      String orderset 
    	      model.addRow(new String[] {});
    	      addBtn = new JButton("�����ʹ�");
    	      addBtn.addActionListener(new ActionListener() {
              int rowSelected = table.getSelectedRow();
              int orderSelected = (int) table.getValueAt(rowSelected, 0);
    	         @Override
    	         public void actionPerformed(ActionEvent e)
    	         {
    	          
    	           orderdao.UpdateOrder(orderSelected,1);
    	         }
    	      });

    	      add(addBtn, BorderLayout.NORTH);
    	      add(new JScrollPane(table));

    	      setSize(400, 300);
    	      setLocationRelativeTo(null);
    	      setDefaultCloseOperation(EXIT_ON_CLOSE);
    	      setVisible(true);
      
     }

 }

	
	
	
	
	
	
	
	
	
	
	
	

