import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class Credit_Partition extends JFrame implements ActionListener
{	
	// 1) Declaration
	int i,a,n;
	JLabel frame_title;
	
	JLabel lbl_marks,lbl_activity;
	JLabel[] lbl= new JLabel[50];
	JTextField[] txtM = new JTextField[50];
	JTextField[] txtA = new JTextField[50];
	
	JButton btn_save,btn_reset,btn_back;
	
	 Connection cn = null;
	 ResultSet rs = null;
	 Statement stm;
	 PreparedStatement prstm;
	 String sql;
	 
	 String subCode;
	
	Credit_Partition(String str,String scode)
	{
		
		 n = Integer.parseInt(str);
		 
		 subCode=scode;

		// super("Credit_Partition allocation");
		setSize(800,600);
		setLocation(150,100);
		setLayout(null);
		
		frame_title = new JLabel("Allocate Marks For Credit Activity");
		frame_title.setFont(new Font("Castellar",Font.ITALIC,24));
		setForeground(Color.blue);
		
		add(frame_title);
		
		frame_title.setBounds(120,30,600,50);
		
		lbl_activity = new JLabel("Enter Activity Name");
		lbl_activity.setFont(new Font("Times New Roman",Font.BOLD,15));
		lbl_activity.setForeground(Color.blue);
		
		lbl_marks = new JLabel("Enter Max.Marks for Activity");
		lbl_marks.setFont(new Font("Times New Roman",Font.BOLD,15));
		lbl_marks.setForeground(Color.blue);
		
		
		add(lbl_activity);
		add(lbl_marks);
		
		lbl_activity.setBounds(300,90,200,30);
		lbl_marks.setBounds(510,90,200,30);
		
		// 2) memory allocation
	 //	lbl= new JLabel[50];
		 a=40;
		 for(i=1;i<=n;i++)
		 {
			 a=a+40;
		  lbl[i] = new JLabel("Enter Details for Allocation Number : "+i);
		  lbl[i].setFont(new Font("Lucida Fax",Font.BOLD,12));
		  lbl[i].setForeground(Color.red);
 
			 add(lbl[i]);
			 
		 txtM[i] = new JTextField();	 
		 txtA[i] = new JTextField();	
		 
		  add(txtM[i]);
		  add(txtA[i]);
			 
			 lbl[i].setBounds(40,(a+40),250,30);
			 
			 txtA[i].setBounds(300,(a+40),200,30);
			 txtM[i].setBounds(510,(a+40),200,30);
			 
			 
			
		 }
		 
		btn_save = new JButton("save");
		btn_reset= new JButton("Reset");
		btn_back = new JButton("Back");
		
		add(btn_save);
		add(btn_reset);
		add(btn_back);
		
		btn_save.setBounds(100,(a+100),100,30);
		btn_reset.setBounds(210,(a+100),100,30);
		btn_back.setBounds(320,(a+100),100,30);
		
		
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
		

		// 4) setBounds
		
		// 5) addListenere
		btn_save.addActionListener(this);
		btn_reset.addActionListener(this);
		btn_back.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource()==btn_save)
		{
		    
          try
		  {
			   for(i=1;i<=n;i++)
			   {
			   sql = "insert into creditAllocation values ('"+subCode+"','"+i+"','"+txtM[i].getText()+"','"+txtA[i].getText()+"')";
						
				prstm = cn.prepareStatement(sql);
				prstm.execute();
				prstm.close();
		    }
			
			JOptionPane.showMessageDialog(null,"Save All SuccessFully...");
			 
			dispose();
			
			new Credit_Details_Entry();
		  }
		  catch(SQLException ex2)
		  {
			  JOptionPane.showMessageDialog(null,"MYSQL Error"+ex2);
		  }
			
		}
		
		if(e.getSource()==btn_reset)
		{
            for(i=1;i<=n;i++)
			{
				txtA[i].setText("");
				txtM[i].setText("");
			}
			txtA[1].requestFocus();
			
		}
		
		if(e.getSource()==btn_back)
		{
			dispose();
			
		}
	}
	
/*
	
	public static void main(String args[])
	{
		
	 new Credit_Partition("3");
	
	}
	
	*/
	
}