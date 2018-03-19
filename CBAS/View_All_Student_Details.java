
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

class View_All_Student_Details extends JFrame 
{  
    // 1) Declaration
	 JPanel p1,p2;
	 JLabel lbl_report1_title;

	 
	 JTable table1,table2;
	 DefaultTableColumnModel colModel;
	 TableColumn col;
	 DefaultTableModel model ;
   DefaultTableModel mdl;
    JTable tab;
	 
	 // sql declaration 
	    Connection cn=null;
		ResultSet rs=null,rs1=null;
		Statement stm;
		PreparedStatement prstm;
		String sql;
		

	
   View_All_Student_Details()
   {
     	super("Details of All Students");
		setSize(1150,630);
		setLocation(150,50);
		setLayout(null);

		
		
	  // 2) Memory Allocation
	  
	   p1 = new JPanel();
	  // p1.setBorder(BorderFactory.createEtchedBorder(Color.white,Color.RED));		
   	  // p1.setBackground(Color.pink);
	   add(p1);
	  // p1.setLayout(null);
	   p1.setVisible(true);
	  
	   lbl_report1_title = new JLabel(" Details of the All Students");
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
	  
	  // 4) SetBounds


	  p1.setBounds(150,50,700,80);
	  p2.setBounds(50,150,1000,400);
	  
	//**********************************   JTable  **********************************
	
	  String colHeads[] = { "Roll No", "Name","Course", "Address","email","contact","Gender","Date Of Birth","Academic Year","Uni Seat" };
		 String data[][]={};
         try
         {
          cn = DriverManager.getConnection("jdbc:mysql:///cbas","root", "");
          stm = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
	      int rows=0,i;	
           rs= stm.executeQuery("select * from studInfo");
		   
          while(rs.next())
          {
              rows++;
          }
          data = new String[rows][10];
          rs.first();

          for(i=0;i<rows;i++)
          {
              data[i][0]=rs.getString(1);
              data[i][1]=rs.getString(2);
              data[i][2]=rs.getString(3);
              data[i][3]=rs.getString(4);
              data[i][4]=rs.getString(6);
              data[i][5]=rs.getString(5);
              data[i][6]=rs.getString(8);
              data[i][7]=rs.getString(7);
              data[i][8]=rs.getString(10);
              data[i][9]=rs.getString(9);
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
	//	rs1.close();
		
    // *****************************************************************************	

	  
	   
	   
	  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
    
   }
   

   public static void main(String args[])
   {
      new View_All_Student_Details();
   }
}