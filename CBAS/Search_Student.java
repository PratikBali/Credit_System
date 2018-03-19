import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


class Search_Student extends JFrame implements ActionListener,ItemListener
{  
   // Declaration 
   
    JLabel lbl_frame_title;
    JPanel p1,p2,p3,p4;
	JLabel lbl_select_rollNo,lbl_select_sname;
	
	JLabel lbl_rno,lbl_sname,lbl_class,lbl_addr,lbl_conNo,lbl_gender,lbl_email,lbl_universityNo;
	
	JTextField txt_rno,txt_sname,txt_class,txt_addr,txt_conNo,txt_gender,txt_email,txt_universityNo;
	
	JTextField txt_select_rollNo,txt_select_sname;
	
	JRadioButton rb_roll_wise,rb_sname_wise;
    ButtonGroup bg;
	
	List lst_search;
	
	JComboBox cb_select_rollNo;
	
	JButton btn_reset,btn_cancel,btn_reset1,btn_cancel1;
	
	// sql  Declaration
	 
	 Connection cn = null;
	 ResultSet rs = null;
	 Statement stm;
	 PreparedStatement prstm;
	String sql;
	
   Search_Student()
   {
     super("Search Student Information");
		setSize(800,600);
		setLocation(100,50);
		setLayout(null);
		
		//  memory Allocation
		
		 
	  	
		p1 = new JPanel();
		//p1.setBackground(new Color(25,231,20));
	  //  p1.setBorder(BorderFactory.createEtchedBorder(Color.GRAY,Color.red));
		p1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		p1.setLayout(null);
		
		lbl_frame_title = new JLabel(" Search Student ",JLabel.CENTER);
		lbl_frame_title.setFont(new Font("Rockwell",Font.ITALIC,40));
		
		
		 // mem for radioButton
		 
		 rb_roll_wise = new JRadioButton("Search Student ID wise",true);
		 rb_sname_wise = new JRadioButton("Search Student Name wise");
		 
		  bg = new ButtonGroup();
		
		
		  // mem for panel p2 is cust id wise
		  
		p2 = new JPanel();
		p2.setBackground(Color.white);
	   // p2.setBorder(BorderFactory.createEtchedBorder(Color.GRAY,Color.red));
	     p2.setBorder(BorderFactory.createTitledBorder(" Search By Roll No."));
		p2.setLayout(null);
		p2.setVisible(true);
		lbl_select_rollNo = new JLabel("Select Student Roll No.");
		 cb_select_rollNo = new JComboBox();
		 cb_select_rollNo.addItem("Select Roll Number");
		
		
		btn_reset1 = new JButton("Reset");
		btn_cancel1 = new JButton("Cancel");
					
		// panel p3 is

		p3 = new JPanel();
		p3.setBackground(Color.white);
	  //  p2.setBorder(BorderFactory.createEtchedBorder(Color.GRAY,Color.red));
    	p3.setBorder(BorderFactory.createTitledBorder(" Search By Student Name"));
		p3.setLayout(null);
		p3.setVisible(false);
		lbl_select_sname = new JLabel("Select Student Name");
		txt_select_sname = new JTextField();
      	lst_search = new List();
		lst_search.setVisible(false);
		
		btn_reset = new JButton("Reset");
		btn_cancel = new JButton("Cancel");
		
		// panel for p4
		
		p4 = new JPanel();
		p4.setBackground(Color.white);
	  //  p4.setBorder(BorderFactory.createEtchedBorder(Color.GRAY,Color.red));
    	p4.setBorder(BorderFactory.createTitledBorder(" Student Details "));
		p4.setLayout(null);
		p4.setVisible(true);
		
		lbl_rno = new JLabel("Roll No.");
		lbl_sname = new JLabel(" Name");
		lbl_class = new JLabel("Class");
		lbl_addr = new JLabel(" Address");
		lbl_conNo = new JLabel("Contact No");
		lbl_gender = new JLabel("Gender");
		lbl_email = new JLabel("Email ID");
		lbl_universityNo = new JLabel("University No.");
		
		txt_rno = new JTextField();
		txt_sname = new JTextField();
		txt_class = new JTextField();
		txt_addr = new JTextField();
		txt_conNo = new JTextField();
		txt_gender = new JTextField();
		txt_email = new JTextField();
		txt_universityNo = new JTextField();
	
					
		// add to Frame
		// for panel p1 
		 
		p1.add(lbl_frame_title);
		add(p1);
		
		add(rb_roll_wise);  add(rb_sname_wise);
		bg.add(rb_roll_wise);  bg.add(rb_sname_wise);
		
	   
        // for panel p2

       p2.add(lbl_select_rollNo);		p2.add(cb_select_rollNo);
	  
	   p2.add(btn_reset1);  p2.add(btn_cancel1);
	   add(p2);
	   
	   // for panel p3
	   
	    p3.add(lbl_select_sname);		p3.add(txt_select_sname);
		p3.add(lst_search);
		p3.add(btn_reset);         p3.add(btn_cancel);
 	    add(p3);
		
	  // for panel p4
	    
	   p4.add(lbl_rno);     p4.add(lbl_sname);	   p4.add(lbl_class);		p4.add(lbl_addr);
	   p4.add(lbl_conNo);	    p4.add(lbl_gender);	   p4.add(lbl_email);	 p4.add(lbl_universityNo);
	   
       p4.add(txt_rno);        p4.add(txt_sname);	   p4.add(txt_class);	p4.add(txt_addr);
	   p4.add(txt_conNo);	    p4.add(txt_gender);	   p4.add(txt_email);	 p4.add(txt_universityNo);	
	   
	   add(p4);
		
		// setBounds
		
		lbl_frame_title.setBounds(10,10,700,40);
		p1.setBounds(50,30,700,60);
		
		rb_roll_wise.setBounds(120,100,200,25);
		rb_sname_wise.setBounds(330,100,200,25);
		
		 // setBounds for panel p2
		 
		 lbl_select_rollNo.setBounds(40,25,150,25);
		 cb_select_rollNo.setBounds(210,25,200,25);
		 
		 btn_reset1.setBounds(70,70,100,30);
		 btn_cancel1.setBounds(200,70,100,30);
		 
		 p2.setBounds(50,160,700,120);
		 
		 // setBounds for panel p3
		 
		 lbl_select_sname.setBounds(40,25,200,25);
		  txt_select_sname.setBounds(210,25,200,25);
		  
		  lst_search.setBounds(420,15,250,100);
		  
		  btn_reset.setBounds(70,70,100,30);
		  btn_cancel.setBounds(200,70,100,30);
		 p3.setBounds(50,160,700,120);
		 
		  // setBounds for panel p4;
		  
		lbl_rno.setBounds(40,40,100,30);   txt_rno.setBounds(150,40,200,30);
		lbl_sname.setBounds(40,80,100,30);   txt_sname.setBounds(150,80,200,30);
		lbl_class.setBounds(40,120,100,30);   txt_class.setBounds(150,120,200,30);
		lbl_addr.setBounds(40,160,100,30);   txt_addr.setBounds(150,160,200,30);
		
		lbl_conNo.setBounds(380,40,100,30);   txt_conNo.setBounds(450,40,200,30);		
		lbl_gender.setBounds(380,80,60,30);   txt_gender.setBounds(450,80,200,30);
		lbl_email.setBounds(380,120,60,30);   txt_email.setBounds(450,120,200,30);
		lbl_universityNo.setBounds(380,160,60,30);   txt_universityNo.setBounds(450,160,200,30);
		
		p4.setBounds(50,280,700,220);
		
		  // sql Connection
		  cb_select_rollNo.setFont(new Font("Times New Roman",Font.BOLD,16));
		  
		  try
		  {
			  cn = DriverManager.getConnection("jdbc:mysql:///CBAS","root","");
			stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			rs=stm.executeQuery("select * from studInfo");
								
								while(rs.next())
								{	
									cb_select_rollNo.addItem(rs.getString(1));
								}
		  }
		  catch(SQLException ex1)
		  {
			  //JOptionPane.showMessageDialog(null,"MYSQL Error :"+ex1);
			  
			  System.out.println(ex1);
		  }
		  
		  
		  
		  
		  txt_select_sname.addKeyListener(new KeyAdapter()
		  {  
		     
		     public void keyPressed(KeyEvent e)
			 {
			  lst_search.setVisible(true);
			   if(e.getKeyChar()==KeyEvent.VK_ENTER)
			   {
			     try
				 {
				   lst_search.clear();
				   rs=stm.executeQuery("select * from studInfo where sname like '%"+txt_select_sname.getText()+"%'");
				   while(rs.next())
				   lst_search.addItem(rs.getString(2));
				 }
				 catch(SQLException ex2)
				 {
				    System.out.println(ex2);
				 }
			   }
			 }
		  });
		
      
		// addListener
		
      
		rb_roll_wise.addActionListener(this);
		rb_sname_wise.addActionListener(this);
				
	   btn_reset.addActionListener(this);
	   btn_cancel.addActionListener(this);
	   
	    btn_reset1.addActionListener(this);
	    btn_cancel1.addActionListener(this);
	   
	   lst_search.addItemListener(this);
	   cb_select_rollNo.addItemListener(this);
	   
				
     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 setVisible(true);
   }
   
   public void itemStateChanged(ItemEvent e)
   {          
	if(e.getSource()==lst_search)
      {	 
     try
	 {	 
      rs = stm.executeQuery("select * from studInfo where sname='"+lst_search.getSelectedItem().toString()+"'");
	  
	 rs.next();
	 txt_rno.setText(rs.getString(1));
	 txt_sname.setText(rs.getString(2));
	 txt_class.setText(rs.getString(3));
	 txt_addr.setText(rs.getString(4));
	 txt_conNo.setText(rs.getString(5));
	 txt_email.setText(rs.getString(6));
	 txt_gender.setText(rs.getString(8));
	 txt_universityNo.setText(rs.getString(9));
	 
	  }
	  catch(SQLException ex3)
	  {
		  JOptionPane.showMessageDialog(null,"MySql Error :"+ex3);
	  }
	  }
	 if(e.getSource()==cb_select_rollNo)
	 {
		 try
		 {
			 
	      String rno = cb_select_rollNo.getSelectedItem().toString();
		  rs = stm.executeQuery("select * from studInfo where roll='"+rno+"'");
		  
		  rs.next();
		  
		  txt_rno.setText(rs.getString(1));
		  txt_sname.setText(rs.getString(2));
		  txt_class.setText(rs.getString(3));
		  txt_addr.setText(rs.getString(4));
		  txt_conNo.setText(rs.getString(5));
		  txt_gender.setText(rs.getString(8));
		  txt_email.setText(rs.getString(6));
		  txt_universityNo.setText(rs.getString(9));

		 }
		 catch(SQLException ex2)
		 {
			 JOptionPane.showMessageDialog(null,"Error :"+ex2);
		 }
		  
	 
	 }
	 
	
	
   }
   
   public void actionPerformed(ActionEvent e)
   {
       if(rb_roll_wise.isSelected())
	  {
	     p2.setVisible(true);
	     p3.setVisible(false);
	  }
	  
	   if(rb_sname_wise.isSelected())
	  {
	     p3.setVisible(true);
	     p2.setVisible(false);
	  }
	  
	  if(e.getSource()==btn_reset)
	  {
	      lst_search.clear();
		  txt_select_sname.setText("");
		  lst_search.setVisible(false);
		  
		  txt_rno.setText("");
		  txt_sname.setText("");
		  txt_class.setText("");
		  txt_addr.setText("");
		  txt_conNo.setText("");
		  txt_gender.setText("");
		  txt_email.setText("");
		  txt_universityNo.setText("");

	  }
	  
	  if(e.getSource()==btn_cancel)
	  {
	     dispose();
	  }
	  
	  if(e.getSource()==btn_reset1)
	  {
	     // cb_select_id.setSelectedIndex(0);
	  
	      txt_rno.setText("");
		  txt_sname.setText("");
		  txt_class.setText("");
		  txt_addr.setText("");
		  txt_conNo.setText("");
		  txt_gender.setText("");
		  txt_email.setText("");
		  txt_universityNo.setText("");
	  }
	  
	  if(e.getSource()==btn_cancel1)
	  {
	     dispose();
	  }
   }
   
   public static void main(String args[])
   {
       new Search_Student();
   }
}