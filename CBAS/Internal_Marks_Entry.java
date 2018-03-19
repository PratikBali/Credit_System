import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

class Internal_Marks_Entry extends JFrame implements ActionListener,ItemListener
{	
	// 1) Declaration
	
	JPanel pnl_title,pnl_sub_info,pnl_stud_info,pnl_btn,pnl_btn2,pnl_show;
	
	JLabel lbl_title1,lbl_title2,lbl_title3;
	JLabel lbl_select_course,lbl_select_sem,lbl_select_subjectCode,lbl_select_subjectName,lbl_select_cdreditNO,
		   lbl_select_srno,lbl_sname,lbl_marks;
		   
	JComboBox cb_select_course,cb_select_sem,cb_select_subjectCode,cb_select_subjectName,cb_select_creditNO,
              cb_select_rno;

    JTextField txt_marks,txt_sname;

    JButton btn_add,btn_reset,btn_cancel,btn_saveAll,btn_back,
			btn_first,btn_next;
			
	
    DefaultTableModel model ;
    DefaultTableModel mdl;
    JTable tab;
    int cnt_sr;
	
	// sql  Declaration
	 
	 Connection cn = null,cn2=null;
	 ResultSet rs = null,rs2=null;
	 Statement stm,stm2;
	 PreparedStatement prstm,prstm2;
	 String sql,sql2,dname1,dname2;

	
	Internal_Marks_Entry()
	{
		super("Internal Marks Entry for credit ");
		setSize(1100,650);
		setLocation(40,40);
		setLayout(null);
		
	   
	     lbl_title1 = new JLabel(" Entry for ");
		 lbl_title1.setFont(new Font("Rockwell",Font.ITALIC,35));
		 add(lbl_title1);
		 lbl_title1.setForeground(new Color(43,105,105));
		 
		 lbl_title2 = new JLabel("Internal Credit");
		 lbl_title2.setFont(new Font("Rockwell",Font.ITALIC,35));
		 add(lbl_title2);
		 lbl_title2.setForeground(new Color(255,0,51));
		 
		 lbl_title3 = new JLabel("Marks of students");
		 lbl_title3.setFont(new Font("Rockwell",Font.ITALIC,35));
		 add(lbl_title3);
		 lbl_title3.setForeground(new Color(43,105,105));
		 
		//jLabel1.setForeground(new java.awt.Color(51, 51, 255));
		 
		 lbl_title1.setBounds(200,20,170,50);
		 lbl_title2.setBounds(345,20,230,50);
		 lbl_title3.setBounds(580,20,300,50);
		 
		 
		      // panel for pnl_sub_info
			  
						pnl_sub_info = new JPanel();
						pnl_sub_info.setBackground(Color.WHITE);
						pnl_sub_info.setBorder(BorderFactory.createTitledBorder("Subject Information"));	
						pnl_sub_info.setLayout(null);
						pnl_sub_info.setFont(new Font("Harlow Solid Italic",Font.BOLD,20));
						add(pnl_sub_info);
						pnl_sub_info.setBounds(80,90,500,240);
						
						lbl_select_course = new JLabel("Select Course Name");
						lbl_select_sem = new JLabel("Select semester Number");
						lbl_select_subjectCode = new JLabel("Select Subject Code");
						lbl_select_subjectName = new JLabel("Select Subject Name");
						lbl_select_cdreditNO = new JLabel("Select Credit NO");
						
						cb_select_course = new JComboBox();
						cb_select_course.setFont(new Font("Times New Roman",Font.BOLD,16));
						cb_select_course.addItem("< = = Select = = >");
						
						cb_select_sem = new JComboBox();
						cb_select_sem.setFont(new Font("Times New Roman",Font.BOLD,16));
						
						cb_select_subjectCode = new JComboBox();
						cb_select_subjectCode.setFont(new Font("Times New Roman",Font.BOLD,16));
						
		                cb_select_subjectName = new JComboBox();
						cb_select_subjectName.setFont(new Font("Times New Roman",Font.BOLD,16));
						
						cb_select_creditNO = new JComboBox();
						cb_select_creditNO.setFont(new Font("Times New Roman",Font.BOLD,16));
						
						pnl_sub_info.add(lbl_select_course);			pnl_sub_info.add(cb_select_course);
						pnl_sub_info.add(lbl_select_sem);				pnl_sub_info.add(cb_select_sem);
						pnl_sub_info.add(lbl_select_subjectCode);		pnl_sub_info.add(cb_select_subjectCode);
						pnl_sub_info.add(lbl_select_subjectName);		pnl_sub_info.add(cb_select_subjectName);
						pnl_sub_info.add(lbl_select_cdreditNO);			pnl_sub_info.add(cb_select_creditNO);
						
						lbl_select_course.setBounds(20,30,200,30);			cb_select_course.setBounds(230,30,200,30);
						lbl_select_sem.setBounds(20,70,200,30);				cb_select_sem.setBounds(230,70,200,30);
						lbl_select_subjectCode.setBounds(20,110,200,30);	cb_select_subjectCode.setBounds(230,110,200,30);
						lbl_select_subjectName.setBounds(20,150,200,30);	cb_select_subjectName.setBounds(230,150,200,30);
						lbl_select_cdreditNO.setBounds(20,190,200,30);		cb_select_creditNO.setBounds(230,190,200,30);
						
						
						
						
			  // panel for pnl_stud_info
	
						pnl_stud_info = new JPanel();
						pnl_stud_info.setBackground(Color.WHITE);
						pnl_stud_info.setBorder(BorderFactory.createTitledBorder("Student Marks Details"));	
						pnl_stud_info.setLayout(null);
						add(pnl_stud_info);
						pnl_stud_info.setBounds(80,350,500,220);
						
						lbl_select_srno = new JLabel("Select Student Roll No");		cb_select_rno = new JComboBox();
																					cb_select_rno.setFont(new Font("Times New Roman",Font.BOLD,16));
																					cb_select_rno.addItem("< = = Select = = >");
																					
						lbl_sname = new JLabel("Select Student Name");				txt_sname = new JTextField();
																					txt_sname.setEditable(false);
						lbl_marks = new JLabel("Enter the Marks");	
						txt_marks = new JTextField();
						
						
						btn_add = new JButton("Add");
						btn_add.setForeground(Color.blue);
						btn_add.setFont(new Font("Times New Roman",Font.BOLD,18));
						btn_next = new JButton("Next");
						btn_next.setForeground(Color.blue);
						btn_next.setFont(new Font("Times New Roman",Font.BOLD,18));
						btn_next.setEnabled(false);
						
						pnl_stud_info.add(lbl_select_srno);		pnl_stud_info.add(cb_select_rno);
						pnl_stud_info.add(lbl_sname);		pnl_stud_info.add(txt_sname);
						pnl_stud_info.add(lbl_marks);		pnl_stud_info.add(txt_marks);
						
						pnl_stud_info.add(btn_add);
						pnl_stud_info.add(btn_next);

						
						lbl_select_srno.setBounds(20,30,200,30);
						lbl_sname.setBounds(20,70,200,30);
						lbl_marks.setBounds(20,110,200,30);
						
						cb_select_rno.setBounds(230,30,200,30);
						txt_sname.setBounds(230,70,200,30);
						txt_marks.setBounds(230,110,200,30);
						
						btn_add.setBounds(100,160,120,40);
						btn_next.setBounds(230,160,120,40);
						btn_next.setIcon(new javax.swing.ImageIcon("E:\\Java House\\Project Required Img\\next.png"));
						
			 // panel for pnl_show			
                        
						pnl_show = new JPanel();
						pnl_show.setBackground(Color.WHITE);
						pnl_show.setBorder(BorderFactory.createTitledBorder("Student Information"));	
						pnl_show.setLayout(null);
						add(pnl_show);
						pnl_show.setBounds(600,90,400,350);
				
         // ******************************************************************
		   
				final String[] colHead = {"ROll Number","Student Name","Activity Name","Marks"};
				Object data[][]={{"","","",""}};
				model= new DefaultTableModel(data,colHead);
				
				tab = new JTable(model);;
				tab.setBackground(Color.PINK);
				int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
				int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
				JScrollPane jsp = new JScrollPane(tab, v, h);
				pnl_show.add(jsp);
				jsp.setBounds(0,0,400,350);
		 
		 
		 //********************************************************************		

				// panel for pnl_btn2
				
					    pnl_btn2 = new JPanel();
						pnl_btn2.setBackground(Color.WHITE);
						pnl_btn2.setBorder(BorderFactory.createTitledBorder("Other Operation"));	
						pnl_btn2.setLayout(null);
						add(pnl_btn2);
						pnl_btn2.setBounds(600,450,400,120);
						
						btn_saveAll = new JButton("Save All");
						btn_saveAll.setForeground(Color.red);
						btn_reset = new JButton("Reset");
						btn_reset.setForeground(Color.red);
						btn_back = new JButton("Cancel");
						btn_back.setForeground(Color.red);
						
						pnl_btn2.add(btn_saveAll);     	pnl_btn2.add(btn_reset);
						pnl_btn2.add(btn_back);
						
						btn_saveAll.setBounds(50,40,90,30);
						btn_reset.setBounds(150,40,90,30);
						btn_back.setBounds(250,40,90,30);
						
						
						cb_select_course.addItemListener(this);
						cb_select_sem.addItemListener(this);
						cb_select_subjectCode.addItemListener(this);
						cb_select_subjectName.addItemListener(this);
						
						cb_select_rno.addItemListener(this);
												

		// 2) memory allocation
		
		// sql Connection
		try
        {
            cn = DriverManager.getConnection("jdbc:mysql:///CBAS","root", "");

            stm = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
            rs = stm.executeQuery("select distinct course_name from courseInfo");
          //  rs2 = stm.executeQuery("select distinct roll from studInfo");
            while(rs.next())
                {
                    cb_select_course.addItem(rs.getString(1));
                }
           
				// rs.close();
        }
		catch(SQLException ex1)
		{
			JOptionPane.showMessageDialog(null,"Connection Error . . .\n"+ex1);
		}
		
		
		
		// 3) add Component on frame

		// 4) setBounds
		
	  valid_marks(txt_marks);
		
		// 5) addListenere
		
		btn_add.addActionListener(this);
		btn_next.addActionListener(this);
		btn_saveAll.addActionListener(this);
		btn_back.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
	}
	
	
				// text_field validator function
		 
    void valid_marks(final JTextField tt)
	{
		tt.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				if(tt.getText().length()<2 && e.getKeyChar()>='0' && e.getKeyChar()<='9')

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
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btn_add)
		{
					
			if( cb_select_course.getSelectedIndex()== 0 ||
				cb_select_sem.getSelectedIndex()== 0 ||
				cb_select_subjectCode.getSelectedIndex()== 0 ||
			txt_sname.getText().length()==0 ||
            txt_marks.getText().length()==0 )
			{
			   JOptionPane.showMessageDialog(null,"All Field are Neccesary");
			}
			
			else
			{
				//    Insert into database
				
				try
				{
					  
				sql = "insert into internalMarks values ('"+cb_select_course.getSelectedItem().toString()+"','"+
				                                            Integer.parseInt(cb_select_sem.getSelectedItem().toString())+"','"+
										                    cb_select_subjectCode.getSelectedItem().toString()+"','"+
															cb_select_subjectName.getSelectedItem().toString()+"','"+
						                                    Integer.parseInt(cb_select_creditNO.getSelectedItem().toString())+"','"+
															Integer.parseInt(cb_select_rno.getSelectedItem().toString())+"','"+
															txt_sname.getText()+"','"+
															Integer.parseInt(txt_marks.getText())+"')";
															
						
				prstm = cn.prepareStatement(sql);
				prstm.execute();
				prstm.close();
				
				JOptionPane.showMessageDialog(null,"Marks Added SuccessFully....");
				
				}
				catch(SQLException ex2)
				{
					//JOptionPane.showMessageDialog(null,"Sql Error : "+ex2);
					
					System.out.println(ex2);
				}
				
				
						
				
	 
	     // to get total value
		   int rno = model.getRowCount();
		 //  int tot_val = Integer.parseInt((mdl.getValueAt(rno-1,7)).toString());
		   final String[] colHead = {"","","","",""};
		   
		    String activity="Seminar";
		   
		   // set/ over write new values to last row/ total cost row
		   model.setValueAt(cb_select_rno.getSelectedItem().toString(),rno-1,0);
		   model.setValueAt(txt_sname.getText(),rno-1,1);
		   model.setValueAt(activity,rno-1,2);
		   model.setValueAt(txt_marks.getText(),rno-1,3);


		  // t1.requestFocus();
		   //ass new row to for total cost
		   
		   cnt_sr++;
		   model.addRow(colHead);
		   rno = model.getRowCount();
		   model.setValueAt("",rno-1,0);
		   model.setValueAt("",rno-1,1);
		   model.setValueAt("",rno-1,2); 
		   model.setValueAt("",rno-1,3); 
		   
		   txt_marks.setText("");
		   txt_marks.requestFocus();
 
		   btn_add.setEnabled(false);
		   btn_next.setEnabled(true);
		   
		   
		   }
		}

			if(e.getSource()==btn_next)
			{
				btn_add.setEnabled(true);
				btn_next.setEnabled(false);
								
			//	txt_sname.setText("");
				txt_marks.setText("");
				
				
				try 
				{
					rs2.next();    
					if(rs2.isAfterLast())
					{
					rs2.last();
					JOptionPane.showMessageDialog(null,"This is last Record");
					}
					
					cb_select_rno.setSelectedItem(rs2.getString(1));
					
					//txt_sname.setText(rs2.getString(1));
				} 
				catch(SQLException ex4)
				{ ex4.printStackTrace(); } 
				
			//	rs.next();
				}
				
				
			if(e.getSource()==btn_saveAll)
			{
				JOptionPane.showMessageDialog(null,"All Record Saved SuccessFully...");
			}
			
			if(e.getSource()==btn_back)
			{
				dispose();
			}
					
	}
	
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource()==cb_select_course)
		{
		  cb_select_sem.removeItemListener(this);
            cb_select_sem.removeAllItems() ;
            cb_select_sem.addItem("< = = Select = = >");
			
			try
				{ 
				    dname1=cb_select_course.getSelectedItem().toString(); 
				  //	System.out.println("Dept Name : "+dname1);
				  
					rs = stm.executeQuery("select distinct subjectInfo.no_of_sem from subjectInfo where subjectInfo.dept_course='"+dname1+"'");
			       //  cb_course_name.removeAllItems();
				while(rs.next())
					{
						cb_select_sem.addItem(rs.getString(1));
						
					//	System.out.println(" course :"+rs.getString(1));
					}
					
					//rs.close();
					
					
				}
				catch(SQLException exx1)
				{
					JOptionPane.showMessageDialog(null,"Sql Error : "+exx1);
				}
				
				cb_select_sem.addItemListener(this);
				
				
	//.........................................................................................................

	      cb_select_rno.removeItemListener(this);
		  cb_select_rno.removeAllItems() ;
		  cb_select_rno.addItem("< =  = Select = = >");
	
		 try
         {
			String course = cb_select_course.getSelectedItem().toString();
            cn2 = DriverManager.getConnection("jdbc:mysql:///CBAS","root", "");

            stm2 = cn2.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
           // rs = stm.executeQuery("select distinct course_name from courseInfo");
            rs2 = stm2.executeQuery("select roll from studInfo where sclass ='"+course+"'order by roll");
            while(rs2.next())
                {
                    cb_select_rno.addItem(rs2.getString(1));
                }
				
				rs2.first();
           
				// rs2.close();
				
			cb_select_rno.addItemListener(this);	
        }
		catch(SQLException ex1)
		{
			JOptionPane.showMessageDialog(null,"Connection Error . . .2\n"+ex1);
			System.out.println(ex1);
		}			
				
	//.......................................................................................................			
				
		}
		
		if(ie.getSource()==cb_select_sem)
		{
			
			cb_select_subjectCode.removeItemListener(this);
            cb_select_subjectCode.removeAllItems() ;
            cb_select_subjectCode.addItem("< = = Select = = >");
			
			try
				{ 
				    dname1=cb_select_sem.getSelectedItem().toString(); 
					dname2=cb_select_course.getSelectedItem().toString(); 
				  	System.out.println("Dept Name : "+dname1);
				  
					rs = stm.executeQuery("select distinct subjectInfo.sub_code from subjectInfo where subjectInfo.no_of_sem='"+dname1+"'and subjectInfo.dept_course='"+dname2+"'");
			       //  cb_course_name.removeAllItems();
				while(rs.next())
					{
						cb_select_subjectCode.addItem(rs.getString(1));
						
						System.out.println(" course :"+rs.getString(1));
					}
					
					//rs.close();
					
					
				}
				catch(SQLException exx1)
				{
					JOptionPane.showMessageDialog(null,"Sql Error : "+exx1);
				}
				
				cb_select_subjectCode.addItemListener(this);
			
		}
		
		if(ie.getSource()==cb_select_subjectCode)
		{
			
				cb_select_subjectName.removeItemListener(this);
                cb_select_subjectName.removeAllItems() ;
          //  cb_select_subjectName.addItem("< = = Select sub name = = >");
			
			try
				{ 
				    dname1=cb_select_subjectCode.getSelectedItem().toString(); 
 
				  	System.out.println("Sub COde: "+dname1);
				  
					rs = stm.executeQuery("select subjectInfo.sub_name from subjectInfo where subjectInfo.sub_code='"+dname1+"'");
			       //  cb_course_name.removeAllItems();
				while(rs.next())
					{
						cb_select_subjectName.addItem(rs.getString(1));
						
						System.out.println(" subject Name :"+rs.getString(1));
					}
					
					//rs.close();
					
					
				}
				catch(SQLException exx1)
				{
					JOptionPane.showMessageDialog(null,"Sql Error : "+exx1);
				}
				
				cb_select_subjectName.addItemListener(this);
				
				//**************************************************************************************************
				
				cb_select_creditNO.removeItemListener(this);
                cb_select_creditNO.removeAllItems() ;
                cb_select_creditNO.addItem("< = = Select = = >");
				
					try
				{ 
				  
				   dname1=cb_select_subjectCode.getSelectedItem().toString(); 
					System.out.println("Subject Code : "+dname1);
					rs = stm.executeQuery("select distinct creditNo from creditAllocation where subcode='"+dname1+"'");
			       //  cb_course_name.removeAllItems();
				while(rs.next())
					{
						cb_select_creditNO.addItem(rs.getString(1));
						
						System.out.println(" Credit No :"+rs.getString(1));
					}
					
					//rs.close();
					
					
				}
				catch(SQLException exx1)
				{
					JOptionPane.showMessageDialog(null,"Sql Error : "+exx1);
				}

				
				
				//****************************************************************************************************
			
		}
		
		if(ie.getSource()==cb_select_subjectName)
		{

		}
		
		if(ie.getSource()==cb_select_creditNO)
		{
 
		}
		
		if(ie.getSource()==cb_select_rno)
		{
           
			try
			{
				 dname2 = cb_select_rno.getSelectedItem().toString();
				rs = stm.executeQuery("select sname from studInfo where roll='"+dname2+"'");
				rs.next();
			     txt_sname.setText(rs.getString(1));
				 
				 
			}
			catch(SQLException ex2)
			{
				JOptionPane.showMessageDialog(null,"MySql Error : "+ex2);
				
				System.out.println(" Error..........: "+ex2);
			}
			
		}
	}
	
	public static void main(String args[])
	{
		new Internal_Marks_Entry();
	}
}