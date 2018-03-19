import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class addTeacher extends JFrame implements ActionListener,ItemListener
{	
	// 1) Declaration
	
	JPanel pnl_title,pnl_info,pnl_button,pnl_search;
	
	JLabel lbl_title,lbl_teach_id,lbl_teach_name,lbl_dept,lbl_contact,lbl_email;
	JLabel lbl_search_teach_name;
	JTextField txt_teach_id,txt_teach_name,txt_contact,txt_email;
	JTextField txt_search_teach_name;
	
	List lst;
	
	JComboBox cb_dept_name;
	
	JButton btn_save,btn_reset,btn_update,btn_delete,btn_search,btn_cancel;
	
	// sql  Declaration
	 
	 Connection cn = null;
	 ResultSet rs = null;
	 Statement stm;
	 PreparedStatement prstm;
	 String sql;
	
	
	addTeacher()
	{
		super("New Teacher Registration Form");
		setSize(700,500);
		setLocation(150,100);
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
		
		            	pnl_title = new JPanel();
						pnl_title.setBackground(Color.pink);
						pnl_title.setBorder(BorderFactory.createEtchedBorder(Color.GRAY,Color.red));
						
						lbl_title = new JLabel("New Teacher Registration Form ");
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

				lbl_teach_id = new JLabel("Teacher ID");
				lbl_teach_name = new JLabel("Teacher Name");
				lbl_dept = new JLabel("Department Name");
				lbl_contact = new JLabel("Contact Numbert");
				lbl_email = new JLabel("Email");
				
				txt_teach_id = new JTextField();
				txt_teach_name = new JTextField();
				txt_contact = new JTextField();
				txt_email = new JTextField();
				
				cb_dept_name = new JComboBox();
				cb_dept_name.setFont(new Font("Times New Roman",Font.BOLD,16));
				cb_dept_name.addItem("   <= = Select  = =>");
				try
				{
					rs = stm.executeQuery("select * from deptInfo");
					
					while(rs.next())
						 cb_dept_name.addItem(rs.getString(2));
				}
				catch(SQLException ex3)
				{
					JOptionPane.showMessageDialog(null,"SQL Error : "+ex3);
				}
				

				
				pnl_info.add(lbl_teach_id);		pnl_info.add(lbl_teach_name);		pnl_info.add(lbl_contact);
				pnl_info.add(lbl_dept);		pnl_info.add(lbl_email);		
				
				pnl_info.add(txt_teach_id);		pnl_info.add(txt_teach_name);		pnl_info.add(cb_dept_name);
				pnl_info.add(txt_contact);		pnl_info.add(txt_email);
				
				lbl_teach_id.setBounds(20,20,120,30);		txt_teach_id.setBounds(150,20,200,30);
				lbl_teach_name.setBounds(20,60,120,30);		txt_teach_name.setBounds(150,60,200,30);
				lbl_dept.setBounds(20,100,120,30);			cb_dept_name.setBounds(150,100,200,30);
				lbl_contact.setBounds(20,140,120,30);		txt_contact.setBounds(150,140,200,30);
				lbl_email.setBounds(20,180,120,30);		txt_email.setBounds(150,180,200,30);
				
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
			
			  lbl_search_teach_name = new JLabel("Enter the Teacher Name");
			  txt_search_teach_name = new JTextField();
			  lst = new List();
			  
			  txt_search_teach_name.setEditable(false);
			  lst.setEnabled(false);
			  
			  pnl_search.add(lbl_search_teach_name);
			  pnl_search.add(txt_search_teach_name);
			  pnl_search.add(lst);
			  
			  lbl_search_teach_name.setBounds(10,10,160,20);			  
			  txt_search_teach_name.setBounds(10,40,160,20);			  
			  lst.setBounds(10,70,160,150);			

			  lst.addItemListener(this);
			  
			  valid_tcontact(txt_contact);
			

					// seaching Subject
			  
		txt_search_teach_name.addKeyListener(new KeyAdapter()
		  {
		     public void keyPressed(KeyEvent e)
			 {
			   if(e.getKeyChar()==KeyEvent.VK_ENTER)
			   {
			     try
				 {
				   lst.clear();
				   rs=stm.executeQuery("select * from teacherInfo where teach_name like '%"+txt_search_teach_name.getText()+"%'");
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
		
		// 3) add Component on frame

		// 4) setBounds
		
		// 5) addListenere
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btn_save)
		{
			 if(txt_teach_id.getText().length()==0 || txt_teach_name.getText().length()==0 ||
			   cb_dept_name.getSelectedIndex()==0 || txt_contact.getText().length()==0 ||
			   txt_email.getText().length()==0 )
			   {
			      JOptionPane.showMessageDialog(null,"All Field Are Neccessary...");
			
			   }
				else
				{
						try
						{
							
				sql = "insert into teacherInfo values ('"+txt_teach_id.getText()+"','"+txt_teach_name.getText()+"','"+
				         cb_dept_name.getSelectedItem().toString()+"','"+
						txt_contact.getText()+"','"+txt_email.getText()+"')";
						
				prstm = cn.prepareStatement(sql);
				prstm.execute();
				prstm.close();
				
				JOptionPane.showMessageDialog(null,"Record Saved SuccessFully....");
				
				clear_All();
				txt_teach_id.requestFocus();
					}
					catch(SQLException ex2)
					{
						JOptionPane.showMessageDialog(null,"Sql Error : "+ex2);
					}
				}
		}
		if(e.getSource()==btn_reset)
		{
			
			clear_All();
			
			txt_search_teach_name.setText("");
			lst.clear();
			
			txt_search_teach_name.setEditable(false);
			lst.setEnabled(false);
			btn_save.setEnabled(true);
			btn_update.setEnabled(false);
			btn_delete.setEnabled(false);
			btn_search.setEnabled(true);
		}
		if(e.getSource()==btn_update)
		{
			if(txt_teach_id.getText().length()==0 ||  txt_teach_name.getText().length()==0 ||
			    cb_dept_name.getSelectedIndex()==0 ||
            txt_contact.getText().length()==0 || txt_email.getText().length()==0)
			{
			   JOptionPane.showMessageDialog(null,"All Field are Neccesary");
			}
			
			else
			{
	 
	 
	            try
				{
					 
				sql = "update teacherInfo set  teach_name='"+txt_teach_name.getText()+"',dept_name ='"+
						cb_dept_name.getSelectedItem().toString()+"',contact_no='"+txt_contact.getText()+
						"',contact_no='"+txt_contact.getText()+"',email='"+txt_email.getText()+
						"' where teach_id='"+txt_teach_id.getText()+"'";
						
				prstm = cn.prepareStatement(sql);
				prstm.execute();
				prstm.close();
				
				JOptionPane.showMessageDialog(null,"Teacher Info. Upadated SuccessFully....");
			
			     clear_All();
				
				}
				catch(SQLException ex2)
				{
					JOptionPane.showMessageDialog(null,"Sql Error : "+ex2);
				}
				
			}
			
		}
		if(e.getSource()==btn_delete)
		{
				if(txt_teach_id.getText().length()==0)
			   {
				   JOptionPane.showMessageDialog(null,"Please try Again","Teacher ID Are Neccesary",JOptionPane.WARNING_MESSAGE);
		
			
			   } // end of if 
			   else
			   {
					try
					{
						
						sql = "delete from teacherInfo where teach_id='"+txt_teach_id.getText()+"'";
					
						prstm = cn.prepareStatement(sql);
											
						prstm.execute();
				
						prstm.close();
									
				
				JOptionPane.showMessageDialog(null,"Record Deleted SuccessFully...");
				
				clear_All();
				}
				catch(SQLException ex3)
				{
					JOptionPane.showMessageDialog(null,"Sql Error : "+ex3);
				}
			  }
		}
		if(e.getSource()==btn_search)
		{  
	
			 txt_search_teach_name.setEditable(true);
			  lst.setEnabled(true);
			  btn_search.setEnabled(false);
	
			//btn_update.setEnabled(true);
			//btn_delete.setEnabled(true);
			//btn_save.setEnabled(false);
			/*
			txt_teach_id.setEditable(false);
			txt_teach_name.setEditable(false);
			txt_contact.setEnabled(false);
			txt_email.setEnabled(false);
			cb_dept_name.setEnabled(false);
			*/
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
			btn_save.setEnabled(false);
			btn_update.setEnabled(true);
			btn_delete.setEnabled(true);
			try
			{
	 
	 
			rs = stm.executeQuery("select * from teacherInfo where teach_name='"+lst.getSelectedItem().toString()+"'");
	  
			rs.next();
			txt_teach_id.setText(rs.getString(1));
			txt_teach_name.setText(rs.getString(2));
			cb_dept_name.setSelectedItem(rs.getString(3));
			txt_contact.setText(rs.getString(4));
			txt_email.setText(rs.getString(5));

	 }
	 catch(Exception ex1)
	 {
	    System.out.println(ex1);
	 }
	
		}
	}
	
	public void clear_All()
	{
		txt_teach_id.setText("");
		txt_teach_name.setText("");
		txt_contact.setText("");
		txt_email.setText("");
		cb_dept_name.setSelectedIndex(0);
		
		txt_teach_id.requestFocus();
	}
	
	void valid_tcontact(final JTextField tt)
	{
		tt.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				if(tt.getText().length()<10 && e.getKeyChar()>='0' && e.getKeyChar()<='9')

				super.keyTyped(e);    // optional

				else
				{
					e.consume();		// diascard the event
					Toolkit tk=Toolkit.getDefaultToolkit();;
					tk.beep();	// raise the sound
				}
            }
		});
	}
	
	public static void main(String args[])
	{
		new addTeacher();
	}
}