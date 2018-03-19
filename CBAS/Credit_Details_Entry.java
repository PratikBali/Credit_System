import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class Credit_Details_Entry extends JFrame implements ActionListener,ItemListener
{	
	// 1) Declaration
	
	JPanel panel_title,panel_info,panel_button;
	JLabel lbl_title;
	
	JLabel lbl_select_course,lbl_select_semNo,lbl_select_subcode,lbl_select_subName,lbl_select_required_credit_partition;
	
	JComboBox cb_select_course,cb_select_semNo,cb_select_subNo,cb_select_subName,cb_select_required_credit_partition;
	
	JButton btn_save,btn_reset,btn_cancel;
	
	// sql  Declaration
	 
	 Connection cn = null;
	 ResultSet rs = null;
	 Statement stm;
	 PreparedStatement prstm;
	 String sql,dname1,dname2;
	 

		
	Credit_Details_Entry()
	{
		super("Master Credit Details Entry form");
		setSize(750,550);
		setLocation(200,70);
		setLayout(null);
		// 2) memory allocation
		
			 // For JPanel 1
						
						panel_title = new JPanel();
					//	panel_title.setBackground(Color.pink);
					//	panel_title.setBorder(BorderFactory.createEtchedBorder(Color.GRAY,Color.red));
						
						lbl_title = new JLabel(" Master Entry for Credit Allocation");
                        lbl_title.setFont(new Font("Copperplate Gothic Bold",Font.BOLD,27));
						lbl_title.setForeground(new Color(212,125,25));
						panel_title.add(lbl_title);
						
						lbl_title.setBounds(0,20,600,50);
						add(panel_title);
						panel_title.setBounds(40,30,600,50);
						
			// 	for panel_info
			
			            panel_info = new JPanel();
						//panel_info.setBackground(Color.WHITE);
						panel_info.setBorder(BorderFactory.createEtchedBorder(Color.gray,Color.white));	
						panel_info.setLayout(null);
						panel_info.setBounds(140,100,450,250);
						add(panel_info);
			
			 lbl_select_course = new JLabel("Select Course Name");
			 lbl_select_semNo = new JLabel("Select Semester Number");
			 lbl_select_subcode = new JLabel("Select Subject Code");
			 lbl_select_subName = new JLabel("Select Subject Name");
			 lbl_select_required_credit_partition = new JLabel("Select Required Credit Partition");
			 
			 cb_select_course = new JComboBox();
			 cb_select_course.setFont(new Font("Times New Roman",Font.BOLD,16));
			 cb_select_course.addItem("      < = = Select = = >");
			 
			 cb_select_semNo = new JComboBox();
			 cb_select_semNo.setFont(new Font("Times New Roman",Font.BOLD,16));
			//  cb_select_semNo.addItem("      < = = Select = = >");
			  
			 cb_select_subNo = new JComboBox();
			 cb_select_subNo.setFont(new Font("Times New Roman",Font.BOLD,16));
			//  cb_select_subNo.addItem("      < = = Select = = >");
			  
			 cb_select_subName = new JComboBox();
			 cb_select_subName.setFont(new Font("Times New Roman",Font.BOLD,16));
			//  cb_select_subName.addItem("      < = = Select = = >");
			 			 String[] cb_val = {"1","2","3","4","5","6","7","8","9","10"};
			 cb_select_required_credit_partition = new JComboBox(cb_val);
			 

			// cb_select_required_credit_partition.addItem(cb_val);
		
			 
			 panel_info.add(lbl_select_course);							panel_info.add(cb_select_course);
			 panel_info.add(lbl_select_semNo);							panel_info.add(cb_select_semNo);
			 panel_info.add(lbl_select_subcode);						panel_info.add(cb_select_subNo);
			 panel_info.add(lbl_select_subName);		                panel_info.add(cb_select_subName);
			 panel_info.add(lbl_select_required_credit_partition);		panel_info.add(cb_select_required_credit_partition);
			 
			 lbl_select_course.setBounds(10,20,200,30);							cb_select_course.setBounds(210,20,200,30);
			 lbl_select_semNo.setBounds(10,60,200,30);							cb_select_semNo.setBounds(210,60,200,30);
			 lbl_select_subcode.setBounds(10,100,200,30);						cb_select_subNo.setBounds(210,100,200,30);
			 lbl_select_subName.setBounds(10,140,200,30);						cb_select_subName.setBounds(210,140,200,30);
			 lbl_select_required_credit_partition.setBounds(10,180,200,30);		cb_select_required_credit_partition.setBounds(210,180,200,30);
			 
			 
			 // for panel_button
			 
			            panel_button = new JPanel();
						//panel_button.setBackground(Color.WHITE);
						panel_button.setBorder(BorderFactory.createEtchedBorder(Color.gray,Color.white));	
						panel_button.setLayout(null);
						panel_button.setBounds(140,360,450,70);
						add(panel_button);
						
				
					btn_save = new JButton("Save");			 
					btn_reset = new JButton("Reset");			 
					btn_cancel = new JButton("Cancel");

					panel_button.add(btn_save);			panel_button.add(btn_reset);			panel_button.add(btn_cancel);
					
					btn_save.setBounds(40,20,100,30);
					btn_reset.setBounds(150,20,100,30);
					btn_cancel.setBounds(260,20,100,30);
					
		try
        {
            cn = DriverManager.getConnection("jdbc:mysql:///CBAS","root", "");

            stm = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
            rs = stm.executeQuery("select distinct course_name from courseInfo");
            while(rs.next())
                {
                    cb_select_course.addItem(rs.getString(1));
                }
            rs.close();
        }
		catch(SQLException ex1)
		{
			JOptionPane.showMessageDialog(null,"Connection Error . . .\n"+ex1);
		}
							
				btn_save.addActionListener(this);	
				btn_reset.addActionListener(this);	
				btn_cancel.addActionListener(this);	
				
				cb_select_course.addItemListener(this);
				cb_select_semNo.addItemListener(this);
				cb_select_subNo.addItemListener(this);
				cb_select_subName.addItemListener(this);
		
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
			
			if(cb_select_course.getSelectedIndex()==0 || cb_select_semNo.getSelectedIndex()==0 ||
			   cb_select_subNo.getSelectedIndex()==0  )
			   {
			      JOptionPane.showMessageDialog(null,"All Field Are Neccessary...","",JOptionPane.WARNING_MESSAGE);
			
			   }
				else
				{
						try
						{
							
				sql = "insert into mastercreditentry values ('"+cb_select_subNo.getSelectedItem().toString()
				               +"','"+cb_select_subName.getSelectedItem().toString()+"','"+
				              cb_select_course.getSelectedItem().toString()+"','"+
						      cb_select_semNo.getSelectedItem().toString()+"','"+
						     cb_select_required_credit_partition.getSelectedItem().toString()+"')";
						
				prstm = cn.prepareStatement(sql);
				prstm.execute();
				prstm.close();
				
				JOptionPane.showMessageDialog(null,"Pleaze Allocation Credit ....");
				
				
				String val = cb_select_required_credit_partition.getSelectedItem().toString();
				String subcode = cb_select_subNo.getSelectedItem().toString();
		        new Credit_Partition(val,subcode);
			    dispose();
				
     				}
					catch(SQLException ex2)
					{
						JOptionPane.showMessageDialog(null,"You Are Allocate Credit Already....!!!","",JOptionPane.WARNING_MESSAGE);
					}
				}
			
			
			//..........................................................................
			
			
		}
		if(e.getSource()==btn_reset)
		{
      
             cb_select_course.setSelectedIndex(0);	  
             cb_select_semNo.setSelectedIndex(0);	  
             cb_select_subNo.setSelectedIndex(0);	  
             cb_select_subName.setSelectedIndex(0);	  
		
		}
		
		if(e.getSource()==btn_cancel)
		{
			dispose();
		}
	}
	
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource()==cb_select_course)
		{
			cb_select_semNo.removeItemListener(this);
            cb_select_semNo.removeAllItems() ;
            cb_select_semNo.addItem("< = = Select = = >");
			
			try
				{ 
				    dname1=cb_select_course.getSelectedItem().toString(); 
				  //	System.out.println("Dept Name : "+dname1);
				  
					rs = stm.executeQuery("select distinct subjectInfo.no_of_sem from subjectInfo where subjectInfo.dept_course='"+dname1+"'");
			       //  cb_course_name.removeAllItems();
				while(rs.next())
					{
						cb_select_semNo.addItem(rs.getString(1));
						
					//	System.out.println(" course :"+rs.getString(1));
					}
					
					//rs.close();
					
					
				}
				catch(SQLException exx1)
				{
					JOptionPane.showMessageDialog(null,"Sql Error : "+exx1);
				}
				
				cb_select_semNo.addItemListener(this);
		}
		
		if(ie.getSource()==cb_select_semNo)
		{
			cb_select_subNo.removeItemListener(this);
            cb_select_subNo.removeAllItems() ;
            cb_select_subNo.addItem("< = = Select = = >");
			
			try
				{ 
				    dname1=cb_select_semNo.getSelectedItem().toString(); 
					dname2=cb_select_course.getSelectedItem().toString(); 
				  	System.out.println("Dept Name : "+dname1);
				  
					rs = stm.executeQuery("select distinct subjectInfo.sub_code from subjectInfo where subjectInfo.no_of_sem='"+dname1+"'and subjectInfo.dept_course='"+dname2+"'");
			       //  cb_course_name.removeAllItems();
				while(rs.next())
					{
						cb_select_subNo.addItem(rs.getString(1));
						
						System.out.println(" course :"+rs.getString(1));
					}
					
					//rs.close();
					
					
				}
				catch(SQLException exx1)
				{
					JOptionPane.showMessageDialog(null,"Sql Error : "+exx1);
				}
				
				cb_select_subNo.addItemListener(this);
				
		}
		
		if(ie.getSource()==cb_select_subNo)
		{
			
			cb_select_subName.removeItemListener(this);
            cb_select_subName.removeAllItems() ;
          //  cb_select_subName.addItem("< = = Select sub name = = >");
			
			try
				{ 
				    dname1=cb_select_subNo.getSelectedItem().toString(); 
 
				  	System.out.println("Sub COde: "+dname1);
				  
					rs = stm.executeQuery("select subjectInfo.sub_name from subjectInfo where subjectInfo.sub_code='"+dname1+"'");
			       //  cb_course_name.removeAllItems();
				while(rs.next())
					{
						cb_select_subName.addItem(rs.getString(1));
						
						System.out.println(" subject Name :"+rs.getString(1));
					}
					
					//rs.close();
					
					
				}
				catch(SQLException exx1)
				{
					JOptionPane.showMessageDialog(null,"Sql Error : "+exx1);
				}
				
				cb_select_subNo.addItemListener(this);
	
		}
		
		if(ie.getSource()==cb_select_subName)
		{
			
		}
	}
	
	
	public static void main(String args[])
	{
		new Credit_Details_Entry();
	}
}