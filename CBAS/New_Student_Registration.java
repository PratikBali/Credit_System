import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class New_Student_Registration extends JFrame implements ActionListener,ItemListener
{	
	// 1) Declaration
	
	JPanel panel_title,panel_general_info,panel_other_info,panel_button,panel_search;
	
	JLabel lbl_title,
			lbl_rollno,lbl_sname,lbl_class,lbl_addr,lbl_contact_no,lbl_email,lbl_DOB,lbl_gender,lbl_university_no,lbl_academic_year;
			
	JTextField txt_rollno,txt_sname,txt_addr,txt_contact_no,txt_email,txt_DOB,txt_university_no,txt_academic_year;
		
	JComboBox cb_class;	
	
	JButton btn_save,btn_view,btn_clear,btn_update,btn_delete,btn_search,btn_cancel;
	
	JRadioButton rb_male,rb_female,rb_other;
	ButtonGroup bg;
	
	 JTextField txt_search;
	 List lst;
	 
	 // sql  Declaration
	 
	 Connection cn = null;
	 ResultSet rs = null;
	 Statement stm;
	 PreparedStatement prstm;
	String sql;
      
	 String gender; 
	
	New_Student_Registration()
	{
		super(" New Student Resistration Form");
		setSize(1200,600);
		setLocation(40,70);
		setLayout(null);
		
		  // sql Connection
		  
		  try
		{
			cn = DriverManager.getConnection("jdbc:mysql:///CBAS","root","");
			stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		}
		catch(Exception e2)
		{
			e2.printStackTrace();
		}
		
		
		// 2) memory allocation
		
		                // For JPanel 1
						
						panel_title = new JPanel();
						panel_title.setBackground(Color.pink);
						panel_title.setBorder(BorderFactory.createEtchedBorder(Color.GRAY,Color.red));
						
						lbl_title = new JLabel(" New Student Registration From ");
                        lbl_title.setFont(new Font("Algerian",Font.BOLD,40));						
						panel_title.add(lbl_title);
						lbl_title.setBounds(50,30,600,30);
						add(panel_title);
						panel_title.setBounds(80,40,820,60);
						
						
						 
						
						// For JPanel 2 ................................................................
						
						panel_general_info = new JPanel();
						panel_general_info.setBackground(Color.WHITE);
						panel_general_info.setBorder(BorderFactory.createTitledBorder("General Information"));	
						panel_general_info.setLayout(null);
						panel_general_info.setBounds(80,120,400,300);
						
						  // memory allocation for component
						  
						  JLabel lbl_star1 = new JLabel("*");
						 lbl_star1.setFont(new Font("Arial Black",Font.BOLD,14));
						 lbl_star1.setForeground(Color.red);
						 panel_general_info.add(lbl_star1);
						 
						 JLabel lbl_star2 = new JLabel("*");
						 lbl_star2.setFont(new Font("Arial Black",Font.BOLD,14));
						 lbl_star2.setForeground(Color.red);
						 panel_general_info.add(lbl_star2);
						 
						 JLabel lbl_star3 = new JLabel("*");
						 lbl_star3.setFont(new Font("Arial Black",Font.BOLD,14));
						 lbl_star3.setForeground(Color.red);
						 panel_general_info.add(lbl_star3);
						 
						 JLabel lbl_star4 = new JLabel("*");
						 lbl_star4.setFont(new Font("Arial Black",Font.BOLD,14));
						 lbl_star4.setForeground(Color.red);
						 panel_general_info.add(lbl_star4);
						 
						 
						 
						  
						  
						  lbl_rollno = new JLabel(" Roll No");
						  lbl_sname = new JLabel(" Student Name");
						  lbl_class = new JLabel(" Student Class");
						  lbl_addr = new JLabel(" Local Address ");
						  lbl_contact_no = new JLabel(" Contact No");

						  
						  txt_rollno = new JTextField();
						  txt_sname = new JTextField();
						  cb_class = new JComboBox();
						  cb_class.setFont(new Font("Times New Roman",Font.BOLD,16));
                            cb_class.addItem("<== Select Class ==>");
							
							try
							{
								rs=stm.executeQuery("select * from courseInfo");
								
								while(rs.next())
								{	
									cb_class.addItem(rs.getString(3));
								}
							}// end of try
							catch(SQLException e2)
							{ 
								System.out.println(e2);
							}
							
						  
						  txt_addr = new JTextField();
						  txt_contact_no = new JTextField();
						  
						  // adding Component to panel 
						  
						  panel_general_info.add(lbl_rollno);		panel_general_info.add(txt_rollno);
						  panel_general_info.add(lbl_sname);		panel_general_info.add(txt_sname);
						  panel_general_info.add(lbl_class);		panel_general_info.add(cb_class);
						  panel_general_info.add(lbl_addr);		    panel_general_info.add(txt_addr);
						  panel_general_info.add(lbl_contact_no);		panel_general_info.add(txt_contact_no);
						  
						  // setBounds
						  
						 lbl_rollno.setBounds(30,30,100,30);		txt_rollno.setBounds(160,30,200,30);
																	lbl_star1.setBounds(150,30,10,30);
						 lbl_sname.setBounds(30,70,100,30);		    txt_sname.setBounds(160,70,200,30);
																	lbl_star2.setBounds(150,70,10,30);
						 lbl_class.setBounds(30,110,100,30);		 cb_class.setBounds(160,110,200,30);
																	lbl_star3.setBounds(150,110,10,30);
						 lbl_addr.setBounds(30,160,100,30);		    txt_addr.setBounds(160,160,200,30);
																	lbl_star4.setBounds(150,160,10,30);
						 lbl_contact_no.setBounds(30,200,100,30);    txt_contact_no.setBounds(160,200,200,30);
														  
						
						add(panel_general_info);
						
						// For Jpanel 3  ................................................................
						
						panel_other_info = new JPanel();
						panel_other_info.setBackground(Color.WHITE);
						panel_other_info.setBorder(BorderFactory.createTitledBorder("Other Information"));
						panel_other_info.setLayout(null);
						
						panel_other_info.setBounds(500,120,400,300);
						
						// memory allocation
						
						  JLabel lbl_star5 = new JLabel("*");
						 lbl_star5.setFont(new Font("Arial Black",Font.BOLD,14));
						 lbl_star5.setForeground(Color.red);
						 panel_other_info.add(lbl_star5);
						 
						  JLabel lbl_star6 = new JLabel("*");
						 lbl_star6.setFont(new Font("Arial Black",Font.BOLD,14));
						 lbl_star6.setForeground(Color.red);
						 panel_other_info.add(lbl_star6);
						
						  lbl_email = new JLabel(" Email Id");
						  lbl_DOB = new JLabel(" Birth Date");
						  lbl_gender = new JLabel(" Gender");
						  lbl_university_no = new JLabel(" University Seat No");
						  lbl_academic_year = new JLabel("Academic Year");
						  
						  
						  txt_email = new JTextField();
						  txt_DOB = new JTextField();
						  
						  rb_male = new JRadioButton("Male",true);
						  rb_male.setBackground(Color.white);
						  rb_female = new JRadioButton("Female");
						  rb_female.setBackground(Color.white);
						  rb_other = new JRadioButton("Other");
						  rb_other.setBackground(Color.white);
						  bg = new ButtonGroup();
						  
						  txt_university_no = new JTextField();
						  txt_academic_year = new JTextField();
						
						add(panel_other_info);			
						
						
						  // adding Component to panel 
						  
						  panel_other_info.add(lbl_email);		panel_other_info.add(txt_email);
						  panel_other_info.add(lbl_DOB);		panel_other_info.add(txt_DOB);
						  panel_other_info.add(lbl_gender);		// radio button 
						  panel_other_info.add(lbl_university_no);		    panel_other_info.add(txt_university_no);
						  panel_other_info.add(lbl_academic_year);		panel_other_info.add(txt_academic_year);
						  
						  panel_other_info.add(rb_male);		panel_other_info.add(rb_female);
						  panel_other_info.add(rb_other);
						  
						  bg.add(rb_male);	bg.add(rb_female);		bg.add(rb_other);
			
						  
						  // setBounds
						  
						 lbl_email.setBounds(30,30,100,30);		txt_email.setBounds(160,30,200,30);
						 lbl_DOB.setBounds(30,70,100,30);		    txt_DOB.setBounds(160,70,200,30);
						 lbl_gender.setBounds(30,110,100,30);		    //setBounds for radio button
						 lbl_university_no.setBounds(30,160,120,30);		    txt_university_no.setBounds(160,160,200,30);
						 lbl_academic_year.setBounds(30,200,100,30);    txt_academic_year.setBounds(160,200,200,30);	
																		lbl_star6.setBounds(150,200,10,30);
						
						lbl_star5.setBounds(150,110,10,30);
						  rb_male.setBounds(160,110,55,30);
						  rb_female.setBounds(230,110,70,30);
						  rb_other.setBounds(310,110,70,30);
							
						// For Jpanel 4	.................................................................
						
						panel_button = new JPanel();
						panel_button.setBackground(Color.pink);
						panel_button.setBorder(BorderFactory.createEtchedBorder(Color.gray,Color.red));		
						panel_button.setLayout(null);
						panel_button.setBounds(80,440,820,70);						
						add(panel_button);	
						
						   // memory allocation
						   
						   btn_save = new JButton("Save");
						 //  btn_save.setIcon(new javax.swing.ImageIcon("E:\\mcs_project\\CBAS\\Images\\save.png"));
						   btn_view = new JButton("View");
						   btn_clear = new JButton("Reset");
						   btn_update = new JButton("Update");
						   btn_update.setEnabled(false);
						   btn_delete= new JButton("Delete");
						   btn_delete.setEnabled(false);
						   btn_search = new JButton("Search");
						   btn_cancel = new JButton("Cancel");
						   
						   // add on panel
						   
						   panel_button.add(btn_save);		panel_button.add(btn_view);		panel_button.add(btn_clear);
						   panel_button.add(btn_update);		panel_button.add(btn_delete);		panel_button.add(btn_search);
						   panel_button.add(btn_cancel);
						   
						   // setBounds
						   
						   btn_save.setBounds(70,20,80,30);
						   btn_view.setBounds(160,20,80,30);
						   btn_clear.setBounds(250,20,80,30);
						   btn_update.setBounds(340,20,80,30);
						   btn_delete.setBounds(430,20,80,30);
						   btn_search.setBounds(520,20,80,30);
						   btn_cancel.setBounds(610,20,80,30);
						   
						   // addListener
						   
						   btn_save.addActionListener(this);
						   btn_view.addActionListener(this);
						   btn_clear.addActionListener(this);
						   btn_update.addActionListener(this);
						   btn_delete.addActionListener(this);
						   btn_search.addActionListener(this);
						   btn_cancel.addActionListener(this);
						   
			 // panel for search
				
					panel_search = new JPanel();
					panel_search.setBackground(Color.pink);
					panel_search.setBorder(BorderFactory.createEtchedBorder(Color.red,Color.white));
					panel_search.setLayout(null);
					add(panel_search);
						
						panel_search.setBounds(910,120,200,390);
						
					txt_search= new JTextField();
					txt_search.setEditable(false);
					lst = new List();
					lst.setEnabled(false);
					
					panel_search.add(txt_search);
					
					panel_search.add(lst);
					
					txt_search.setBounds(10,20,180,30);
					lst.setBounds(10,60,180,320);
					
		 txt_search.addKeyListener(new KeyAdapter()
		  {
		     public void keyPressed(KeyEvent e)
			 {
			   if(e.getKeyChar()==KeyEvent.VK_ENTER)
			   {
			     try
				 {
				   lst.clear();
				   rs=stm.executeQuery("select * from studInfo where sname like '%"+txt_search.getText()+"%'");
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
					
					
					lst.addItemListener(this);
					
						
						// ...............................................................................
						
		
	    // calling valider methods
			
			  valid_contactNo(txt_contact_no);
			  valid_uniSeatNo(txt_university_no);
			  valid_rollNo(txt_rollno);
			 		
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{	
	
		if(e.getSource()==btn_save)
		{
			if((txt_rollno.getText()).length()==0 ||  (txt_sname.getText()).length()==0 ||
               cb_class.getSelectedIndex()==0 || (txt_addr.getText()).length()==0 ||
               (txt_academic_year.getText()).length()==0)
				{
					JOptionPane.showMessageDialog(null," This (*) Field are Necessary To Fill");
				} 
			else
			{		 
				try
				{
	 
	             
				 if(rb_male.isSelected())
				 {
					  gender =rb_male.getText();
				 }
				 else if(rb_female.isSelected())
				 {
					 gender = rb_female.getText();
				 }
				 else
				 {
					 gender=rb_other.getText();
				 }
			sql="insert into studInfo values('"+txt_rollno.getText()+"','"+txt_sname.getText()+"','"+
			cb_class.getSelectedItem().toString()+"','"+
	        txt_addr.getText()+"','"+txt_contact_no.getText()+"','"+txt_email.getText()+"','"+txt_DOB.getText()+
			"','"+gender+"','"+
			txt_university_no.getText()+"','"+txt_academic_year.getText()+"')";
			
	        prstm=cn.prepareStatement(sql);
            prstm.execute();	prstm.close();
		    JOptionPane.showMessageDialog(null,"Record added Successfully");
			
			clear_All();
			
            //	System.out.println("\n\t**** record successfully inserted:*****");
				}
				catch(SQLException e1)
				{
					//System.out.println(e1);  
					JOptionPane.showMessageDialog(null,e1);
				}
			} 
			
		 }
	
		
		if(e.getSource()==btn_view)
		{
				new View_All_Student_Details();
		}
		
		if(e.getSource()==btn_clear)
		{
		   clear_All();
		}
		
		if(e.getSource()==btn_update)
		{
			
			String gender2=null;
			try
		    {
		     //gender = "Male";
			 
			  
			 if(rb_male.isSelected()==true)
			 {
				  gender2="Male";
			 }
			 else if(rb_female.isSelected()==true)
			 {
				  gender2="Female";
			 }
		  
		   sql="update studInfo set sname='"+txt_sname.getText()+"',sclass='"+cb_class.getSelectedItem().toString()+
		        "',addr='"+txt_addr.getText()
		   +"',contact='"+txt_contact_no.getText()+"',email='"+txt_email.getText()+"',DOB='"+txt_DOB.getText()
		   +"',gender='"+gender2+"',uniSeat='"+txt_university_no.getText()+"',academicYr='"+txt_academic_year.getText()
		   +"' where roll="+txt_rollno.getText()+"";
		   prstm=cn.prepareStatement(sql);
          	   prstm.execute();	prstm.close();
         
		 JOptionPane.showMessageDialog(null,"Record Updated Successfully");
		 
		// refresh();
		 clear_All();
		 
				btn_save.setEnabled(true);
				btn_search.setEnabled(true);
				btn_update.setEnabled(false);
				btn_delete.setEnabled(false);
				txt_search.setEditable(false);
				lst.setEnabled(false);
		 // System.out.println("\n\t**** record successfully updated:*****");
		  }
	   catch(SQLException e1)
		{
		   // System.out.println(e1);    
			JOptionPane.showMessageDialog(null,e1);		  
		}
		}
		
		if(e.getSource()==btn_delete)
		{
			
			try
			{
				if(txt_rollno.getText().length()==0)
				{
					JOptionPane.showMessageDialog(null,"Please Enter the Student Roll No To Be Deleted.."); 
				}
				else
				{
	
				sql="delete from internalMarks where roll_no="+txt_rollno.getText()+"";
				prstm=cn.prepareStatement(sql);
				prstm.execute();	                    prstm.close();
			//	JOptionPane.showMessageDialog(null,"Record Deleted Successfully");
	
				sql="delete from studInfo where roll="+txt_rollno.getText()+"";
				prstm=cn.prepareStatement(sql);
				prstm.execute();	                    prstm.close();
				JOptionPane.showMessageDialog(null,"Record Deleted Successfully"); 
				
				
						
				clear_All();
				
				btn_save.setEnabled(true);
				btn_search.setEnabled(true);
				btn_update.setEnabled(false);
				btn_delete.setEnabled(false);
				txt_search.setEditable(false);
				lst.setEnabled(false);
				
			//   System.out.println("\n\t**** record successfully deleted:*****");
	        }
	      }
             catch(SQLException e1)
		     {
		        System.out.println(e1); 
				 JOptionPane.showMessageDialog(null,"mysql error"); 
		     }
		}
		
		if(e.getSource()==btn_search)
		{
				btn_update.setEnabled(true);
				btn_delete.setEnabled(true);
				btn_save.setEnabled(false);
				btn_search.setEnabled(false);
				txt_search.setEditable(true);
				lst.setEnabled(true);
				
				txt_search.requestFocus();
		}
		
		if(e.getSource()==btn_cancel)
		{
			dispose();	
		}
		
		
	}
	
	public void itemStateChanged(ItemEvent el)
	{
		try
	 {
	 
	 
      rs = stm.executeQuery("select * from studInfo where sname='"+lst.getSelectedItem().toString()+"'");
	  
	 rs.next();
	 txt_rollno.setText(rs.getString(1));
	 txt_sname.setText(rs.getString(2));
	 txt_search.setText(rs.getString(2));
	 cb_class.setSelectedItem(rs.getString(3));
	 txt_addr.setText(rs.getString(4));
	 txt_contact_no.setText(rs.getString(5));
	 txt_email.setText(rs.getString(6));
	 txt_DOB.setText(rs.getString(7));
	 
	    String gender1 = rs.getString(8);
		
	//	System.out.println("Gender ="+gender1);
		if(gender1.equals("Male"))
		{
			rb_male.setSelected(true);
		}
		else if(gender1.equals("Female"))
		{
			rb_female.setSelected(true);
		}
		
	 //.setText(rs.getString(8));
	 txt_university_no.setText(rs.getString(9));
	 txt_academic_year.setText(rs.getString(10));

	 }
	 catch(Exception ex1)
	 {
	    System.out.println(ex1);
	 }
	 
	}
	
	void clear_All()
	{
		txt_rollno.setText("");
		   txt_sname.setText("");
		   cb_class.setSelectedIndex(0);
		   txt_addr.setText("");
		   txt_contact_no.setText("");
		   txt_email.setText("");
		   txt_DOB.setText("");
		  // rb_male.setSelectedI();
		   txt_university_no.setText("");
		   txt_academic_year.setText("");
		 
		   txt_rollno.requestFocus();
		   
		   txt_search.setText("");
		   lst.clear();
		   
		   btn_search.setEnabled(true);
		   btn_save.setEnabled(true);
		   txt_search.setEditable(false);
		   btn_update.setEnabled(false);
		   btn_delete.setEnabled(false);
		   lst.setEnabled(false);
	}
	
	// text_field validator function
		 
    void valid_contactNo(final JTextField tt)
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
	
		
	void valid_uniSeatNo(final JTextField tt)
	{
		tt.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				if(tt.getText().length()<15 && e.getKeyChar()>='0' && e.getKeyChar()<='9')

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
	
	void valid_rollNo(final JTextField tt)
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
	
	     public void refresh()
     {
        try
        {
           rs = stm.executeQuery("select * from studInfo order by roll");
          
        }
        catch(Exception ex)
          {ex.printStackTrace();
          }
        
     }
	
	public static void main(String args[])
	{
		new New_Student_Registration();
			
	}
}