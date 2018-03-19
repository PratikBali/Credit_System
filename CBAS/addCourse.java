import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

class addCourse extends JFrame implements ActionListener
{	
	// 1) Declaration
	
	JPanel pnl_course_entry,pnl_btn,pnl_table_model;
	
	JLabel lbl_dept_id,lbl_dept_name,lbl_course_name,lbl_no_of_sem;
	
	JTextField txt_dept_id,txt_dept_name,txt_course_name,txt_no_of_sem;
	
	JButton btn_add_to_course,btn_reset,btn_back;
	
    DefaultTableModel model ;
    DefaultTableModel mdl;
    JTable tab;
	 int cnt_sr;
	 int id;
	 
	 // sql  Declaration
	 
	 Connection cn = null;
	 ResultSet rs = null;
	 Statement stm;
	 PreparedStatement prstm;
	 String sql;
	 
	 String dept_No,dept_name;
	
	addCourse(String dno,String dname)
	{
		//super("Departmental Course Entry form");
		setSize(600,400);
		setLocation(150,100);
		setLayout(null);
		// 2) memory allocation
		
		dept_No=dno;
		dept_name=dname;
		  
		
		// sql Connection
		try
		{
			cn = DriverManager.getConnection("jdbc:mysql:///CBAS","root","");
			stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		}
		catch(Exception ex1)
		{
			JOptionPane.showMessageDialog(null,"Database Connection Error"+ex1);
		}
			
		
			// for panel pnl_course_entry Component
			
			 pnl_course_entry = new JPanel();
			  pnl_course_entry.setBackground(Color.white);
			  pnl_course_entry.setBorder(BorderFactory.createTitledBorder("Add Course"));
			  
			  pnl_course_entry.setLayout(null);
			  add(pnl_course_entry);
			  pnl_course_entry.setBounds(40,20,370,190);
			  
			  lbl_dept_id = new JLabel("Department ID");
			  lbl_dept_name = new JLabel("Department Name");
			  lbl_course_name = new JLabel("Course Name");
			  lbl_no_of_sem = new JLabel("No.Of.Sem");
			  
			  txt_dept_id = new JTextField(dept_No);
			  txt_dept_id.setEditable(false);
			  txt_dept_name = new JTextField(dept_name);
			  txt_dept_name.setEditable(false);
			  txt_course_name = new JTextField();
			  txt_no_of_sem = new JTextField();
			  txt_course_name.requestFocus();
			  
					
					// add Component
					
			  pnl_course_entry.add(lbl_dept_id);
			  pnl_course_entry.add(lbl_dept_name);
			  pnl_course_entry.add(lbl_course_name);
			  pnl_course_entry.add(lbl_no_of_sem);
			  
			  pnl_course_entry.add(txt_dept_id);
			  pnl_course_entry.add(txt_dept_name);
			  pnl_course_entry.add(txt_course_name);
			  pnl_course_entry.add(txt_no_of_sem);
			  
			  
					// setBounds to component
			  
			  lbl_dept_id.setBounds(20,20,120,30);
			  lbl_dept_name.setBounds(20,60,120,30);
			  lbl_course_name.setBounds(20,100,120,30);
			  lbl_no_of_sem.setBounds(20,140,120,30);
		
			  txt_dept_id.setBounds(150,20,200,30);
			  txt_dept_name.setBounds(150,60,200,30);
			  txt_course_name.setBounds(150,100,200,30);
			  txt_no_of_sem.setBounds(150,140,200,30);
			  
			  
			  
			  // button panel
			  
			   pnl_btn = new JPanel();
			   pnl_btn.setBackground(Color.white);
			  pnl_btn.setBorder(BorderFactory.createEtchedBorder(Color.gray,Color.white));
			  pnl_btn.setLayout(null);
			  add(pnl_btn);
			  pnl_btn.setBounds(410,20,140,190);
			  
			  btn_add_to_course = new JButton("Add Course");
			  btn_reset = new JButton("Reset");
			  btn_back = new JButton("Back");
			  
			  pnl_btn.add(btn_add_to_course);		
			  pnl_btn.add(btn_reset);
			  pnl_btn.add(btn_back);
			  
			  btn_add_to_course.setBounds(20,20,100,30);
			  btn_reset.setBounds(20,70,100,30);
			  btn_back.setBounds(20,120,100,30);
			  
			  btn_add_to_course.addActionListener(this);
			  btn_reset.addActionListener(this);
			  btn_back.addActionListener(this);
			  
			  // for pnl_table_model
			  
			  pnl_table_model = new JPanel();
			  pnl_table_model.setBackground(Color.white);
			  pnl_table_model.setBorder(BorderFactory.createEtchedBorder(Color.gray,Color.white));
			  pnl_table_model.setLayout(null);
			  add(pnl_table_model);
			  pnl_table_model.setBounds(40,220,510,100);
			  
			  
			  // ******************************************************************
		   
				final String[] colHead = {"Department Number","Department Name","Course Name","No of Sem"};
				Object data[][]={{"","","",""}};
				model= new DefaultTableModel(data,colHead);
				tab = new JTable(model);;
				tab.setBackground(Color.PINK);
				int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
				int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
				JScrollPane jsp = new JScrollPane(tab, v, h);
				pnl_table_model.add(jsp);
				jsp.setBounds(0,0,510,100);
		 
		 
		 //********************************************************************
			  
		
		// 3) add Component on frame

		// 4) setBounds
		
		// 5) addListenere
	/*	
	mdl = new DefaultTableModel(data,colHead);
	
	tab = new JTable(mdl)
	{
	  public Class getColumnClass(int column)
      {
	    switch (column)
		{
		   case 0:  return String.class;
		   case 1:  return String.class;
		   default :  return Integer.class;
		}
	  }	  
	};
	*/
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btn_add_to_course)
		{
			
			
			if(txt_dept_id.getText().length()==0 ||  txt_dept_name.getText().length()==0 ||
            txt_course_name.getText().length()==0 ||  txt_no_of_sem.getText().length()==0)
			{
			   JOptionPane.showMessageDialog(null,"All Field are Neccesary");
			}
			
			else
			{
	 
	 
	            try
				{
				sql = "insert into courseInfo values ('"+txt_dept_id.getText()+"','"+txt_dept_name.getText()+"','"+
						txt_course_name.getText()+"','"+txt_no_of_sem.getText()+"')";
						
				prstm = cn.prepareStatement(sql);
				prstm.execute();
				prstm.close();
				
				JOptionPane.showMessageDialog(null,"Course Added SuccessFully....");
				
				txt_course_name.requestFocus();
				}
				catch(SQLException ex2)
				{
					JOptionPane.showMessageDialog(null,"Sql Error : "+ex2);
				}
	 
	        
	     // to get total value
		   int rno = model.getRowCount();
		 //  int tot_val = Integer.parseInt((mdl.getValueAt(rno-1,7)).toString());
		   final String[] colHead = {"","","","",""};
		   
		   
		   // set/ over write new values to last row/ total cost row
		   model.setValueAt(txt_dept_id.getText(),rno-1,0);
		   model.setValueAt(txt_dept_name.getText(),rno-1,1);
		   model.setValueAt(txt_course_name.getText(),rno-1,2);
		   model.setValueAt(txt_no_of_sem.getText(),rno-1,3);


		  // t1.requestFocus();
		   //ass new row to for total cost
		   
		   cnt_sr++;
		   model.addRow(colHead);
		   rno = model.getRowCount();
		   model.setValueAt("",rno-1,0);
		   model.setValueAt("",rno-1,1);
		   model.setValueAt("",rno-1,2); 
		   model.setValueAt("",rno-1,3); 
 
		   
		    txt_course_name.setText("");
		    txt_no_of_sem.setText("");
		   }
			
		}
		if(e.getSource()==btn_reset)
		{
			txt_dept_id.setText("");
			txt_dept_name.setText("");
			txt_course_name.setText("");
			txt_no_of_sem.setText("");
			txt_dept_id.requestFocus();
			
	    }		
		if(e.getSource()==btn_back)
		{
			dispose();
	    }
		
	}	
	/*
	public static void main(String args[])
	{
		new addCourse();
	}
	*/
}