
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*; 
import com.itextpdf.text.*;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.awt.List;
import java.awt.Font;
import java.io.*; 

class Repots_RankWise extends JFrame implements ItemListener
{  
    // 1) Declaration
	 JPanel p1,p2,p3;
	 JLabel lbl_report1_title;
	 JTextField t1;
	 
	 JTable table1,table2;
	 DefaultTableColumnModel colModel;
	 TableColumn col;
	 DefaultTableModel model ;
   DefaultTableModel mdl;
    JTable tab;
	String dname1,dname2;
	JLabel lbl_select_course,lbl_select_sem,lbl_select_subject;
	
	JComboBox cb_select_course,cb_select_sem,cb_select_subject;
	
	JTextField txt_subject;
	 
	 // sql declaration 
	    Connection cn=null;
		ResultSet rs=null,rs1=null;
		Statement stm;
		PreparedStatement prstm;
		String sql;
		

	
   Repots_RankWise()
   {
     	super("Reports for Students By Rank-Wise");
		setSize(1150,630);
		setLocation(100,50);
		setLayout(null);

		
		
	  // 2) Memory Allocation
	  
	   p1 = new JPanel();
	  // p1.setBorder(BorderFactory.createEtchedBorder(Color.white,Color.RED));		
   	  // p1.setBackground(Color.pink);
	   add(p1);
	  // p1.setLayout(null);
	   p1.setVisible(true);
	  
	   lbl_report1_title = new JLabel(" Reports for Students By Rank");
	   lbl_report1_title.setFont(new Font("Times Ne Roman",Font.BOLD,40));
	   lbl_report1_title.setForeground(Color.red);
	    p1.add(lbl_report1_title);
			  
	  lbl_report1_title.setBounds(10,10,400,30);
   
	   // for panel p2
	   
	   p2 = new JPanel();
	   p2.setBorder(BorderFactory.createEtchedBorder(Color.white,Color.RED));		
   	   p2.setBackground(Color.white);
	   p2.setVisible(true);
	   p2.setLayout(null);

	  add(p2);
	  
	  
	   p3 = new JPanel();
	   p3.setBorder(BorderFactory.createEtchedBorder(Color.white,Color.RED));		
   	   p3.setBackground(Color.white);
	   p3.setVisible(true);
	   p3.setLayout(null);

	  add(p3);
	  
	  lbl_select_course = new JLabel("Select Course");  
	  lbl_select_sem = new JLabel("Select Semester");
	  lbl_select_subject = new JLabel("Select Subject Name");
	  
	              cb_select_course = new JComboBox();
	  						  cb_select_course.setFont(new Font("Times New Roman",Font.BOLD,16));
                            cb_select_course.addItem("<== Select Class ==>");
							
							try
							{
								cn = DriverManager.getConnection("jdbc:mysql:///cbas","root", "");
                                stm = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
								rs1=stm.executeQuery("select * from courseInfo");
								
								while(rs1.next())
								{	
									cb_select_course.addItem(rs1.getString(3));
								}
							}// end of try
							catch(SQLException e2)
							{ 
								System.out.println(e2);
							}
	  
	  cb_select_sem = new JComboBox();
	  cb_select_sem.setFont(new Font("Times New Roman",Font.BOLD,16));
	  cb_select_subject = new JComboBox();
	  cb_select_subject.setFont(new Font("Times New Roman",Font.BOLD,16));
	  
	  txt_subject = new JTextField();
	  
	  
	  p3.add(lbl_select_course);	p3.add(lbl_select_sem);	p3.add(lbl_select_subject);
	  p3.add(cb_select_course);	p3.add(cb_select_sem);	p3.add(cb_select_subject);
	  
	  lbl_select_course.setBounds(20,20,100,30);
	  cb_select_course.setBounds(130,20,170,30);
	  
	  lbl_select_sem.setBounds(310,20,100,30);
	  cb_select_sem.setBounds(410,20,170,30);

	  lbl_select_subject.setBounds(590,20,130,30);
	  cb_select_subject.setBounds(720,20,170,30);	  
	  
	  // 4) SetBounds


	  p1.setBounds(150,50,700,80);
	  p2.setBounds(50,230,1000,300);
	  p3.setBounds(50,140,1000,70);
	  
	  
	   
	   cb_select_course.addItemListener(this);
	   cb_select_sem.addItemListener(this);
	   cb_select_subject.addItemListener(this);
	  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
    
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
					
					rs = stm.executeQuery("select distinct subjectInfo.no_of_sem from subjectInfo where subjectInfo.dept_course='"+dname1+"'");
			       //  cb_course_name.removeAllItems();
				while(rs.next())
					{
						cb_select_sem.addItem(rs.getString(1));
						
					}
					
					//rs.close();
					
					
				}
				catch(SQLException exx1)
				{
					JOptionPane.showMessageDialog(null,"Sql Error : "+exx1);
				}
				
				cb_select_sem.addItemListener(this);
	   }
	   
	     if(ie.getSource()==cb_select_sem)
	   {
		   cb_select_subject.removeItemListener(this);
            cb_select_subject.removeAllItems() ;
            cb_select_subject.addItem("< = = Select = = >");
			
			try
				{ 
				    dname1=cb_select_sem.getSelectedItem().toString(); 
					dname2=cb_select_course.getSelectedItem().toString(); 
				  	//System.out.println("Dept Name : "+dname1);
				  
					rs = stm.executeQuery("select distinct subjectInfo.sub_name from subjectInfo where subjectInfo.no_of_sem='"+dname1+"'and subjectInfo.dept_course='"+dname2+"'");

				while(rs.next())
					{
						cb_select_subject.addItem(rs.getString(1));
						
					}
					
					//rs.close();
					
					
				}
				catch(SQLException exx1)
				{
					JOptionPane.showMessageDialog(null,"Sql Error : "+exx1);
				}
				
				cb_select_subject.addItemListener(this);
	   }
	   
	     if(ie.getSource()==cb_select_subject)
	   {
		   //**********************************   JTable  **********************************
		   
		   
		   
		   
		   
	
	  String colHeads[] = { "Roll No", "Name","credit 1", "Credit 2"," Credit 3","Total"};
		 String data[][]={};
         try
         {
          cn = DriverManager.getConnection("jdbc:mysql:///cbas","root", "");
          stm = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
	      int rows=0,i;	
           rs= stm.executeQuery("select * from internalMarks where course_name='"+cb_select_course.getSelectedItem().toString()
		                          +"' and sem_no='"+cb_select_sem.getSelectedItem().toString()+"' and  sub_name='"+
								  cb_select_subject.getSelectedItem().toString() +"'");
		   
          while(rs.next())
          {
              rows++;
          }
          data = new String[rows][6];
          rs.first();

          for(i=0;i<rows;i++)
          {
              data[i][0]=rs.getString(1);
              data[i][1]=rs.getString(2);
              data[i][2]=rs.getString(3);
              data[i][3]=rs.getString(4);
              data[i][4]=rs.getString(6);
              data[i][5]=rs.getString(5);

              rs.next();
          
		 } 
      }
      catch(Exception exx)
      {
          JOptionPane.showMessageDialog(null,""+exx);
      }
		table1 = new JTable(data, colHeads);
		p2.add(table1);
        DefaultTableColumnModel colModel=(DefaultTableColumnModel)
		table1.getColumnModel();
    	TableColumn col=colModel.getColumn(1);
     	col=colModel.getColumn(0);        col.setPreferredWidth(70);
        col=colModel.getColumn(1);        col.setPreferredWidth(130);
    	col=colModel.getColumn(2);        col.setPreferredWidth(100);
    	col=colModel.getColumn(3);        col.setPreferredWidth(100);
    	col=colModel.getColumn(4);        col.setPreferredWidth(150);
    	col=colModel.getColumn(5);        col.setPreferredWidth(100);
    	col=colModel.getColumn(6);        col.setPreferredWidth(50);
    	col=colModel.getColumn(7);        col.setPreferredWidth(100);
    	col=colModel.getColumn(8);        col.setPreferredWidth(100);
    	col=colModel.getColumn(9);        col.setPreferredWidth(100);
		table1.setEnabled(false);
		//table1.setFont(new Font("Time New Roman",Font.BOLD,"8"));
        JScrollPane jsp = new JScrollPane(table1);
        p2.add(jsp);
        jsp.setBounds(5,5,990,390);
	//	rs.close();

		
    // *****************************************************************************
	   }
   }
   

   public static void main(String args[])
   {
      new Repots_RankWise();
   }
}