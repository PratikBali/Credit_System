import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class addSubject extends JFrame implements ActionListener,ItemListener
{	
	// 1) Declaration
	
	JPanel pnl_title,pnl_info,pnl_button,pnl_search;
	
	JLabel lbl_title,lbl_sub_code,lbl_sub_name,lbl_sem_no,lbl_dept_name,lbl_course;
	JLabel lbl_search_sub_name;
	JTextField txt_sub_code,txt_sub_name,txt_sub_semNo;
	JTextField txt_search_sub_name;
	
	List lst;
	
	JComboBox cb_dept_name,cb_course_name;
	
	JButton btn_save,btn_reset,btn_update,btn_delete,btn_search,btn_cancel;
	
	// sql  Declaration
	 
	 Connection cn = null;
	 ResultSet rs = null;
	 Statement stm;
	 PreparedStatement prstm;
	 String sql ;
	 String dname1;
	
	
	addSubject()
	{
		//super("Add Subject on Course");
		setSize(700,500);
		setLocation(150,100);
		setLayout(null);
		
		
		
		
		// 2) memory allocation
		
		            	pnl_title = new JPanel();
						pnl_title.setBackground(Color.pink);
						pnl_title.setBorder(BorderFactory.createEtchedBorder(Color.GRAY,Color.red));
						
						lbl_title = new JLabel(" Add Subject on Course From ");
                        lbl_title.setFont(new Font("Algerian",Font.BOLD,30));						
						pnl_title.add(lbl_title);
						lbl_title.setBounds(40,30,450,30);
						add(pnl_title);
						pnl_title.setBounds(40,30,590,50);
						
						
			// panel for component
			
			  pnl_info = new JPanel();
			  pnl_info.setBackground(Color.white);
			  pnl_info.setBorder(BorderFactory.createEtchedBorder(Color.gray,Color.white));
			  pnl_info.setLayout(null);
			  add(pnl_info);
			  pnl_info.setBounds(40,90,380,230);

				lbl_sub_code = new JLabel("Subject Code");
				lbl_sub_name = new JLabel("Subject Name");
				lbl_sem_no = new JLabel("Enter Sem No.");
				lbl_dept_name = new JLabel("Select Department");
				lbl_course = new JLabel("Select Course");
				
				txt_sub_code = new JTextField();
				txt_sub_name = new JTextField();
				txt_sub_semNo = new JTextField();
				
				cb_dept_name = new JComboBox();	
				cb_dept_name.setFont(new Font("Times New Roman",Font.BOLD,16));
				cb_dept_name.addItem("< = = Select = = >");
				
						
				
				cb_course_name = new JComboBox();
				cb_course_name.setFont(new Font("Times New Roman",Font.BOLD,16));
				cb_course_name.addItem("< = = Select Class = = >");
				
				cb_dept_name.addItemListener(this);			
			    cb_course_name.addItemListener(this);				
				
				pnl_info.add(lbl_sub_code);		pnl_info.add(lbl_sub_name);		pnl_info.add(lbl_sem_no);
				pnl_info.add(lbl_dept_name);		pnl_info.add(lbl_course);		
				
				pnl_info.add(txt_sub_code);		pnl_info.add(txt_sub_name);		pnl_info.add(txt_sub_semNo);
				pnl_info.add(cb_dept_name);		pnl_info.add(cb_course_name);
				
				
				lbl_sub_code.setBounds(20,20,120,30);		txt_sub_code.setBounds(150,20,200,30);
				lbl_sub_name.setBounds(20,60,120,30);		txt_sub_name.setBounds(150,60,200,30);
				lbl_sem_no.setBounds(20,100,120,30);			txt_sub_semNo.setBounds(150,100,200,30);
				lbl_dept_name.setBounds(20,140,120,30);		cb_dept_name.setBounds(150,140,200,30);
				lbl_course.setBounds(20,180,120,30);		cb_course_name.setBounds(150,180,200,30);
				
				
				
				
				// sql Connection
				
				try
				{
					cn = DriverManager.getConnection("jdbc:mysql:///CBAS","root","");
					stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					rs = stm.executeQuery("select * from deptInfo");
					
					while(rs.next())
					{
						cb_dept_name.addItem(rs.getString(2));
					}
					
					//rs.close();
				}
				catch(Exception ex1)
				{
						JOptionPane.showMessageDialog(null,"Database Connection Error"+ex1);
				}
				

				
			// panel for pnl_button
			
			  pnl_button = new JPanel();
			  pnl_button.setBackground(Color.white);
			  pnl_button.setBorder(BorderFactory.createEtchedBorder(Color.gray,Color.white));
			  pnl_button.setBackground(Color.pink);
			  pnl_button.setLayout(null);
			  add(pnl_button);
			  pnl_button.setBounds(40,330,590,50);
			  
			  btn_save = new JButton("Save");
			  btn_reset = new JButton("Reset");
			  btn_update = new JButton("Update");
			  btn_delete = new JButton("Delete");
			  btn_search = new JButton("Search");
			  btn_cancel = new JButton("Cancel");
			  
			 btn_update.setEnabled(false);
			 btn_delete.setEnabled(false);
			  
			  pnl_button.add(btn_save);		pnl_button.add(btn_reset);		pnl_button.add(btn_update);
			  pnl_button.add(btn_delete);		pnl_button.add(btn_search);		pnl_button.add(btn_cancel);
			  
			  btn_save.setBounds(10,10,70,30);
			  btn_reset.setBounds(90,10,80,30);
			  btn_update.setBounds(180,10,80,30);
			  btn_delete.setBounds(270,10,80,30);
			  btn_search.setBounds(360,10,80,30);
			  btn_cancel.setBounds(450,10,80,30);
			  
			  btn_save.addActionListener(this);
			  btn_reset.addActionListener(this);
			  btn_update.addActionListener(this);
			  btn_delete.addActionListener(this);
			  btn_search.addActionListener(this);
			  btn_cancel.addActionListener(this);
			  
			// panel for search

			  pnl_search = new JPanel();
			  pnl_search.setBackground(Color.pink);
			  pnl_search.setBorder(BorderFactory.createEtchedBorder(Color.gray,Color.white));
			  pnl_search.setBackground(Color.pink);
			  pnl_search.setLayout(null);
			  add(pnl_search);
			  pnl_search.setBounds(430,90,200,230);
			
			  lbl_search_sub_name = new JLabel("Enter the Subject Name");
			  txt_search_sub_name = new JTextField();
			  txt_search_sub_name.setEditable(false);
			  lst = new List();
			  lst.setEnabled(false);
			  
			  pnl_search.add(lbl_search_sub_name);
			  pnl_search.add(txt_search_sub_name);
			  pnl_search.add(lst);
			  
			  lbl_search_sub_name.setBounds(10,10,160,20);			  
			  txt_search_sub_name.setBounds(10,40,160,20);			  
			  lst.setBounds(10,70,160,150);			

			 
			   lst.addItemListener(this);
		
		// 3) add Component on frame
		
		
		txt_search_sub_name.addKeyListener(new KeyAdapter()
		  {
		     public void keyPressed(KeyEvent e)
			 {
			   if(e.getKeyChar()==KeyEvent.VK_ENTER)
			   {
			     try
				 {
				   lst.clear();
				   rs=stm.executeQuery("select * from subjectInfo where sub_name like '%"+txt_search_sub_name.getText()+"%'");
				   while(rs.next())
				   lst.addItem(rs.getString(2));
				 }
				 catch(SQLException ex2)
				 {
				    System.out.println(ex2);
					
				 }
			   }
			 }
		  });  
		

		// 4) setBounds
		
		// 5) addListenere
		
		txt_sub_code.requestFocus();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btn_save)
		{
			if(txt_sub_code.getText().length()==0 ||  txt_sub_name.getText().length()==0 ||
            txt_sub_semNo.getText().length()==0)
			{
			   JOptionPane.showMessageDialog(null,"All Field are Neccesary");
			}
			
			else
			{
	 
	 
	            try
				{
					  int a = Integer.parseInt(txt_sub_semNo.getText());
				sql = "insert into subjectInfo values ('"+txt_sub_code.getText()+"','"+txt_sub_name.getText()+"','"+
						a+"','"+cb_dept_name.getSelectedItem().toString()+
						"','"+cb_course_name.getSelectedItem().toString()+"')";
						
				prstm = cn.prepareStatement(sql);
				prstm.execute();
				prstm.close();
				
				JOptionPane.showMessageDialog(null,"Course Added SuccessFully....");
				
				All_Clear();
				txt_sub_code.requestFocus();
				}
				catch(SQLException ex2)
				{
					JOptionPane.showMessageDialog(null,"Sql Error : "+ex2);
				}
				
			}
				
		}
		if(e.getSource()==btn_reset)
		{
			All_Clear();
			
			txt_search_sub_name.setText("");
			lst.clear();
			
			txt_search_sub_name.setEditable(false);
			lst.setEnabled(false);
		}
		if(e.getSource()==btn_update)
		{
			if(txt_sub_code.getText().length()==0 ||  txt_sub_name.getText().length()==0 ||
            txt_sub_semNo.getText().length()==0)
			{
			   JOptionPane.showMessageDialog(null,"All Field are Neccesary");
			}
			
			else
			{
	 
	 
	            try
				{
					  int a = Integer.parseInt(txt_sub_semNo.getText());
						sql = "update subjectInfo set  sub_name='"+txt_sub_name.getText()+"',no_of_sem ='"+
						a+"',dept_name='"+cb_dept_name.getSelectedItem().toString()+
						"',dept_course='"+cb_course_name.getSelectedItem().toString()+"' where sub_code='"+txt_sub_code.getText()
						+"'";
						
				prstm = cn.prepareStatement(sql);
				prstm.execute();
				prstm.close();
				
				JOptionPane.showMessageDialog(null,"Course Upadated SuccessFully....");
				
				All_Clear();
				
				txt_sub_code.requestFocus();
				}
				catch(SQLException ex2)
				{
					JOptionPane.showMessageDialog(null,"Sql Error : "+ex2);
				}
				
			}
		}
		if(e.getSource()==btn_delete)
		{
			if(txt_sub_code.getText().length()==0)
			   {
				   JOptionPane.showMessageDialog(null,"Enter the Subject Cod to be deleted ...");
		
			
			   } 
			   else
			   {
					try
					{
						
						sql = "delete from subjectInfo where sub_code='"+txt_sub_code.getText()+"'";
					
						prstm = cn.prepareStatement(sql);
						
						prstm.execute();
				
						prstm.close();
			
				JOptionPane.showMessageDialog(null,"Record Deleted SuccessFully...");
				
                  All_Clear();
										
				}
			catch(SQLException ex3)
			{
				JOptionPane.showMessageDialog(null,"Sql Error : "+ex3);
			}
			   }
			
		}
		if(e.getSource()==btn_search)
		{
			btn_update.setEnabled(true);
			btn_delete.setEnabled(true);
			btn_save.setEnabled(false);
			btn_search.setEnabled(false);		
			txt_search_sub_name.setEditable(true);
			lst.setEnabled(true);

			
		}
		if(e.getSource()==btn_cancel)
		{
			dispose();
		}

		
	}
	
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource()==lst)
		{
			/*
		    txt_sub_code.setEditable(true);
			txt_sub_name.setEditable(true);
			txt_sub_semNo.setEnabled(true);
			cb_course_name.setEnabled(true);
			cb_dept_name.setEnabled(true);
			*/
	    try
	    {
	 
	 
      rs = stm.executeQuery("select * from subjectInfo where sub_name='"+lst.getSelectedItem().toString()+"'");
	 
	 rs.next();
	 txt_sub_code.setText(rs.getString(1));
	 txt_sub_name.setText(rs.getString(2));
	 txt_sub_semNo.setText(rs.getString(3));
	 cb_dept_name.setSelectedItem(rs.getString(4));
	 cb_course_name.setSelectedItem(rs.getString(5));

	 }
	 catch(Exception ex1)
	 {
	    System.out.println(ex1);
		
		//JOptionPane.showMessageDialog(null,ex1);
	 }
		}
		
		
		
		if(ie.getSource()==cb_dept_name)
		{   
	
			cb_course_name.removeItemListener(this);
            cb_course_name.removeAllItems() ;
            cb_course_name.addItem("< = = Select Class = = >");
			
			try
				{ 
				  
				   dname1=cb_dept_name.getSelectedItem().toString(); 
					System.out.println("Dept Name : "+dname1);
					rs = stm.executeQuery("select distinct courseInfo.course_name from courseInfo where courseInfo.dept_name='"+dname1+"'");
			       //  cb_course_name.removeAllItems();
				while(rs.next())
					{
						cb_course_name.addItem(rs.getString(1));
						
						System.out.println(" course :"+rs.getString(1));
					}
					
					//rs.close();
					
					
				}
				catch(SQLException exx1)
				{
					JOptionPane.showMessageDialog(null,"Sql Error : "+exx1);
				}
				
		}
		
	}
	
	public void All_Clear()
	{
		txt_sub_code.setText("");
		txt_sub_name.setText("");
		txt_sub_semNo.setText("");
		cb_dept_name.setSelectedIndex(0);
		cb_course_name.setSelectedItem("< = = Select = = >");
		
		btn_save.setEnabled(true);
		btn_search.setEnabled(true);
		btn_update.setEnabled(false);
		btn_delete.setEnabled(false);
		
		txt_sub_code.requestFocus();
	}
	
	public static void main(String args[])
	{
		new addSubject();
	}
}