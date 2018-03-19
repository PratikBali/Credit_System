import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class addDept extends JFrame implements ItemListener,ActionListener
{	
	// 1) Declaration
	
	JPanel p1,p2,p3,p4;
	
	JLabel lbl_frame_title;
	JLabel lbl_Dept_ID,lbl_Dept_name,lbl_Dept_Contact,lbl_Dept_HOD;
	JLabel lbl_search_dept,lbl_by;
	
	JTextField txt_Dept_Id,txt_Dept_name,txt_Dept_Contact,txt_Dept_HOD;
	
	JButton btn_save,btn_clear,btn_update,btn_delete,btn_search,btn_cancel,btn_add_course;
	
	JRadioButton rb_by_ID,rb_by_name;
	ButtonGroup bg;
	
	JComboBox cb_ID,cb_name;
	
	public int id;
	
		 // sql  Declaration
	 
	 Connection cn = null;
	 ResultSet rs = null;
	 Statement stm;
	 PreparedStatement prstm;
	 String sql;

	
	addDept()
	{
		super("Department Info. Entry Form");
		setSize(710,450);
		setLocation(150,120);
		setLayout(null);
		
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
		
		
		// 2) memory allocation
		
			// for panel p1 
			
			p1 = new JPanel();
			p1.setBackground(Color.pink);
			
			p1.setBorder(BorderFactory.createEtchedBorder(Color.gray,Color.red));
			add(p1);
			p1.setBounds(40,20,600,50);
			
			
			lbl_frame_title = new JLabel("New Department Register Form");
			lbl_frame_title.setFont(new Font("Algerian",Font.BOLD,30));
		//	lbl_frame_title.setForeground(Color.red);
			p1.add(lbl_frame_title);
			lbl_frame_title.setBounds(20,20,550,40);
			
			// for panel info dept
			
			p2 = new JPanel();
			p2.setBackground(Color.white);
			p2.setBorder(BorderFactory.createEtchedBorder(Color.gray,Color.gray));
			p2.setLayout(null);
			add(p2);
			p2.setBounds(40,80,400,200);
			
				// component on p2
			
			lbl_Dept_ID = new JLabel("Department ID");
			lbl_Dept_name = new JLabel("Department Name");
			lbl_Dept_Contact = new JLabel("Contact Number");
			lbl_Dept_HOD = new JLabel("Head Of Department");
			
			txt_Dept_Id = new JTextField();
			txt_Dept_name = new JTextField();
			txt_Dept_Contact = new JTextField();
			txt_Dept_HOD = new JTextField();
			
			  p2.add(lbl_Dept_ID);		p2.add(lbl_Dept_name);		p2.add(lbl_Dept_Contact);		p2.add(lbl_Dept_HOD);
			  p2.add(txt_Dept_Id);		p2.add(txt_Dept_name);		p2.add(txt_Dept_Contact);		p2.add(txt_Dept_HOD);
			  
			  lbl_Dept_ID.setBounds(10,10,120,30);         txt_Dept_Id.setBounds(170,10,200,30);
	    	  lbl_Dept_name.setBounds(10,50,120,30);		txt_Dept_name.setBounds(170,50,200,30);
			  lbl_Dept_Contact.setBounds(10,90,120,30);		txt_Dept_Contact.setBounds(170,90,200,30);
			  lbl_Dept_HOD.setBounds(10,130,120,30);		txt_Dept_HOD.setBounds(170,130,200,30);
			  
			  // for panel p3 button
			  
			  p3 = new JPanel();
			  p3.setBackground(Color.pink);
			  p3.setBorder(BorderFactory.createEtchedBorder(Color.red,Color.gray));
			  p3.setLayout(null);
			  add(p3);
			  p3.setBounds(40,290,600,50);
			  
			    
				btn_save = new JButton("Save");
				btn_clear = new JButton("Reset");
				btn_update = new JButton("Update");
				btn_delete = new JButton("Delete");
				btn_cancel = new JButton("Cancel");
				btn_add_course = new JButton("Add Course");
				btn_update.setEnabled(false);
				btn_delete.setEnabled(false);
				btn_add_course.setEnabled(false);
				
				p3.add(btn_save);		p3.add(btn_clear);		p3.add(btn_update);
				p3.add(btn_delete);		p3.add(btn_cancel);		add(btn_add_course);
				
				btn_save.setBounds(30,10,90,30);
				btn_clear.setBounds(130,10,90,30);
				btn_update.setBounds(230,10,90,30);
				btn_delete.setBounds(330,10,90,30);
				btn_cancel.setBounds(430,10,90,30);
				btn_add_course.setBounds(480,200,110,30);
				
			  
		// for frame
			
			lbl_search_dept = new JLabel("Seach Department");
			lbl_search_dept.setFont(new Font("times new roman",Font.BOLD,18));
			lbl_search_dept.setForeground(Color.blue);
			lbl_by = new JLabel("By");
			lbl_by.setFont(new Font("times new roman",Font.BOLD,18));
			lbl_by.setForeground(Color.blue);
			
			add(lbl_search_dept);		add(lbl_by);
			
			lbl_search_dept.setBounds(460,80,200,20);
			lbl_by.setBounds(460,110,30,20);
			
			  // for radio button
			rb_by_ID = new JRadioButton("ID",true);
			rb_by_ID.setFont(new Font("times new roman",Font.BOLD,16));
			rb_by_ID.setForeground(Color.red);
			rb_by_name = new JRadioButton("Name");
			rb_by_name.setFont(new Font("times new roman",Font.BOLD,16));
			rb_by_name.setForeground(Color.red);
			
			bg = new ButtonGroup();
			
			add(rb_by_ID);	add(rb_by_name);
			bg.add(rb_by_ID);		bg.add(rb_by_name);
			
			rb_by_ID.setBounds(490,110,60,20);
			rb_by_name.setBounds(550,110,80,20);
						
			   // for comboBox
			   
			   cb_ID = new JComboBox();
			   cb_ID.addItem("<-- Select ID -->");
			   
			   cb_name = new JComboBox();
			   cb_name.addItem("<-- Select name -->");
			   
			   try
			   {
				 rs = stm.executeQuery("select * from deptInfo");

					while(rs.next())
					{
						cb_ID.addItem(rs.getString(1));
						cb_name.addItem(rs.getString(2));
					}
					
			   }
			   catch(SQLException ex)
			   {
				   JOptionPane.showMessageDialog(null,"Sql Error : -"+ex);
			   }
			   
			   
			   add(cb_ID);		add(cb_name);
			   
			   cb_ID.setBounds(460,140,160,25);
			   cb_name.setBounds(460,140,160,25);
		
		// 3) add Component on frame

		// 4) setBounds
		
		// 5) addListenere
		
		cb_ID.addItemListener(this);
		cb_name.addItemListener(this);
		
		rb_by_ID.addActionListener(this);
		rb_by_name.addActionListener(this);
		
		btn_save.addActionListener(this);
		btn_clear.addActionListener(this);
		btn_update.addActionListener(this);
		btn_delete.addActionListener(this);
		btn_cancel.addActionListener(this);
		btn_add_course.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==rb_by_ID)
		{
			cb_ID.setVisible(true);
			cb_name.setVisible(false);
			

		}
		if(e.getSource()==rb_by_name)
		{
			cb_name.setVisible(true);
			cb_ID.setVisible(false);	
		}
		if(e.getSource()==btn_save)
		{
			//btn_add_course.setEnabled(true);
			if(txt_Dept_Id.getText().length()==0 || txt_Dept_name.getText().length()==0 ||
			   txt_Dept_Contact.getText().length()==0 || txt_Dept_HOD.getText().length()==0)
			   {
			      JOptionPane.showMessageDialog(null,"All Field Are Neccessary...");
			
			   }
				else
				{
						try
						{
				sql = "insert into deptInfo values ('"+txt_Dept_Id.getText()+"','"+txt_Dept_name.getText()+"','"+
						txt_Dept_Contact.getText()+"','"+txt_Dept_HOD.getText()+"')";
						
				prstm = cn.prepareStatement(sql);
				prstm.execute();
				prstm.close();
				
				JOptionPane.showMessageDialog(null,"Record Inserted SuccessFully....");
				
			//	clear_All();
				txt_Dept_Id.requestFocus();
				
				 btn_add_course.setEnabled(true);
					}
					catch(SQLException ex2)
					{
						JOptionPane.showMessageDialog(null,"Sql Error : "+ex2);
					}
				}
		}		
		if(e.getSource()==btn_clear)
		{
				clear_All();
		}		
		if(e.getSource()==btn_update)
		{
			
			if(txt_Dept_Id.getText().length()==0 || txt_Dept_name.getText().length()==0 ||
			   txt_Dept_Contact.getText().length()==0 || txt_Dept_HOD.getText().length()==0)
			   {
				   JOptionPane.showMessageDialog(null,"All Field Are Neccessary...");
		
			
			   } // end of if 
			   else
			   {
					try
					{
						sql = "update deptInfo set dept_name='"+txt_Dept_name.getText()+"',contact_no='"+
						txt_Dept_Contact.getText()+"',HOD_name='"+txt_Dept_HOD.getText()+"' where dept_id ='"+
						txt_Dept_Id.getText()+"'";
					
				prstm = cn.prepareStatement(sql);
				prstm.execute();
				
				prstm.close();
				JOptionPane.showMessageDialog(null,"Record Updated SuccessFully...");
				
			//	clear_All();
				btn_add_course.setEnabled(true);
				
				}
			catch(SQLException ex3)
			{
				JOptionPane.showMessageDialog(null,"Sql Error : "+ex3);
			}
			   }
		}		
		if(e.getSource()==btn_delete)
		{	
			if(txt_Dept_Id.getText().length()==0)
			   {
				   JOptionPane.showMessageDialog(null,"All Field Are Neccessary...");
		
			
			   } // end of if 
			   else
			   {
					try
					{
						
						sql = "delete from courseInfo where dept_id='"+txt_Dept_Id.getText()+"'";
					
						prstm = cn.prepareStatement(sql);
											
						prstm.execute();
				
						prstm.close();
						
				sql = "delete from deptInfo where dept_id='"+txt_Dept_Id.getText()+"'";
					
				prstm = cn.prepareStatement(sql);
				prstm.execute();
				
				prstm.close();
				
				
				
				JOptionPane.showMessageDialog(null,"Record Deleted SuccessFully...");
				
				clear_All();
				
				btn_add_course.setEnabled(true);
				
				}
			catch(SQLException ex3)
			{
				JOptionPane.showMessageDialog(null,"Sql Error : "+ex3);
			}
			   }
		}		
		if(e.getSource()==btn_cancel)
		{
			dispose();
		}	
		if(e.getSource()==btn_add_course)
		{   
		    
			String Did = txt_Dept_Id.getText();
			String Dname = txt_Dept_name.getText();
			new addCourse(Did,Dname);
			
		}
		
	}
	
	public void itemStateChanged(ItemEvent Ie)
	{
		if(Ie.getSource()==cb_ID)
		{
			
			try
			{
				String Did = cb_ID.getSelectedItem().toString();
				rs = stm.executeQuery("select * from deptInfo where dept_id='"+Did+"'");
				
				while(rs.next())
				{
					txt_Dept_Id.setText(rs.getString(1));
					txt_Dept_name.setText(rs.getString(2));
					txt_Dept_Contact.setText(rs.getString(3));
					txt_Dept_HOD.setText(rs.getString(4));
				}
				
			}
			catch(SQLException exx)
			{
				JOptionPane.showMessageDialog(null,"Sql Error :"+exx);
			}
			btn_save.setEnabled(false);
			btn_update.setEnabled(true);
			btn_delete.setEnabled(true);
			btn_add_course.setEnabled(true);
			
		}
		
		if(Ie.getSource()==cb_name)
		{
			try
			{
				String Dname = cb_name.getSelectedItem().toString();
				rs = stm.executeQuery("select * from deptInfo where dept_name='"+Dname+"'");
				
				while(rs.next())
				{
					txt_Dept_Id.setText(rs.getString(1));
					txt_Dept_name.setText(rs.getString(2));
					txt_Dept_Contact.setText(rs.getString(3));
					txt_Dept_HOD.setText(rs.getString(4));
				}
				
			}
			catch(SQLException exx)
			{
				JOptionPane.showMessageDialog(null,"Sql Error :"+exx);
			}
			
			btn_save.setEnabled(false);
			btn_update.setEnabled(true);
			btn_delete.setEnabled(true);
			btn_add_course.setEnabled(true);
			
		}
		
	}
	void clear_All()
	{
		txt_Dept_Id.setText("");
		txt_Dept_name.setText("");
		txt_Dept_Contact.setText("");
		txt_Dept_HOD.setText("");
				
		cb_ID.setSelectedIndex(0);
		cb_name.setSelectedIndex(0);
		
		txt_Dept_Id.requestFocus();
		
		btn_save.setEnabled(true);
		btn_update.setEnabled(false);
		btn_delete.setEnabled(false);
		btn_add_course.setEnabled(false);
	}
	
	public static void main(String args[])
	{
		new addDept();
	}
}