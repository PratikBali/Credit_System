import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class CBAS_Home extends JFrame implements ActionListener
{	
	// 1) Declaration
	
	JMenuBar jm;
	JMenu Insert,Edit,Marks_Entry,Report,Show,Search,About,Extra,Help;
	JMenuItem Insert_student,Insert_teacher,Insert_subject,Insert_credit,Insert_university_no,Insert_department,Insert_course,
			  Edit_student,Edit_teacher,Edit_marks,Edit_department,Edit_course,
			  Marks_Entry_internal,Marks_Entry_external,
			  Report_rollwise,Report_namewise,Report_rankwise,Report_gradewise,Report_percentagewise,
			  Search_student,Search_subject,Search_teacher,Search_dept,
			  Extra_Notepad,Extra_Calculator;
			  
	ImageIcon ic,ic2; 
	JLabel limage;		  
			   
	
	CBAS_Home()
	{
		super("CBAS Home");
		setSize(1374,768);
	//	setLocation(0,0);
		setLayout(null);
		
		
		//setBackground Image
		 
		   ic=new ImageIcon("C:\\Users\\Uday\\Desktop\\abc.jpg");
            limage=new JLabel(ic);
			add(limage);
            limage.setBounds(200,0,1150,680);
		
		
		// 2) memory allocation
		
		jm = new JMenuBar();
		jm.setBackground(Color.pink);
		
		Insert = new JMenu("Insert");
		Insert.setIcon(new javax.swing.ImageIcon("E:\\mcs_project\\CBAS\\Images\\insert_img.png"));
		Edit = new JMenu("Edit");
		Edit.setIcon(new javax.swing.ImageIcon("E:\\mcs_project\\CBAS\\Images\\Edit24.gif"));
		Marks_Entry = new JMenu("Marks Entry");
		Marks_Entry.setIcon(new javax.swing.ImageIcon("E:\\mcs_project\\CBAS\\Images\\Add24.gif"));
		Report = new JMenu("Report");
		Report.setIcon(new javax.swing.ImageIcon("E:\\mcs_project\\CBAS\\Images\\report.gif"));
		Show = new JMenu("Show");
		Search = new JMenu("Search");
		Search.setIcon(new javax.swing.ImageIcon("E:\\Java House\\Project Required Img\\search.png"));
		About = new JMenu("About");
		About.setIcon(new javax.swing.ImageIcon("E:\\mcs_project\\CBAS\\Images\\About24.gif"));
		Extra = new JMenu("Extra");
		Help = new JMenu("Help");
		Help.setIcon(new javax.swing.ImageIcon("E:\\Java House\\Project Required Img\\help.png"));
		
		Insert_student = new JMenuItem("Add Student");
		Insert_teacher = new JMenuItem("Add Teacher");
		//Insert_teacher.setIcon(new javax.swing.ImageIcon("E:\\mcs_project\\CBAS\\Images\\t_add.jpg"));
		Insert_subject = new JMenuItem("Add Subject");
		Insert_department = new JMenuItem("Add Department");
		Insert_course = new JMenuItem("Add Course");
		Insert_credit = new JMenuItem("  Credit ");
		Insert_university_no = new JMenuItem("University Seat NO.");
		
		Edit_student = new JMenuItem("Student");
		Edit_teacher = new JMenuItem("Teacher");
		Edit_marks = new JMenuItem("Marks");
		
		Marks_Entry_internal = new JMenuItem("Internal");
		Marks_Entry_external = new JMenuItem("External");
		
		Report_rollwise = new JMenuItem("Roll NO.");
		Report_namewise = new JMenuItem("Name ");
		Report_rankwise = new JMenuItem("Rank");
		Report_gradewise = new JMenuItem("Gradewise");
		Report_percentagewise = new JMenuItem("Percentage");
		
		Search_student  = new JMenuItem("Student");
		Search_subject = new JMenuItem("Subject");
		Search_teacher = new JMenuItem("Teacher");
		Search_dept = new JMenuItem("Department");
		
		Extra_Notepad = new JMenuItem("Note Pad");
		Extra_Calculator = new JMenuItem("Calculator");
		
		// 3) add Component on frame
		
		Insert.add(Insert_student);	Insert.add(Insert_teacher);	Insert.add(Insert_subject);		Insert.add(Insert_department);		Insert.add(Insert_course);
		Insert.add(Insert_credit);	Insert.add(Insert_university_no);
				
		Edit.add(Edit_student);		Edit.add(Edit_teacher);		Edit.add(Edit_marks);
		
		Marks_Entry.add(Marks_Entry_internal);		Marks_Entry.add(Marks_Entry_external);
		
		Report.add(Report_rollwise);	Report.add(Report_namewise);	Report.add(Report_rankwise);	Report.add(Report_gradewise);		Report.add(Report_percentagewise);
		
		Search.add(Search_student);	Search.add(Search_subject);
		Search.add(Search_teacher);	Search.add(Search_dept);
		
		Extra.add(Extra_Notepad);		Extra.add(Extra_Calculator);
		
		jm.add(Insert);	jm.add(Edit);	jm.add(Marks_Entry);	jm.add(Report);	jm.add(Search);	
		jm.add(Extra);		jm.add(Help);		jm.add(About);
		
		 setJMenuBar(jm);

		// 4) setBounds
		
		// 5) addListenere
		Insert_student.addActionListener(this);
		Insert_department.addActionListener(this);
		Insert_course.addActionListener(this);
		Insert_teacher.addActionListener(this);
		Insert_subject.addActionListener(this);
		Insert_credit.addActionListener(this);
		
		Marks_Entry_internal.addActionListener(this);
		Marks_Entry_external.addActionListener(this);
		
		Extra_Notepad.addActionListener(this);
		Extra_Calculator.addActionListener(this);
		
		Search_student.addActionListener(this);
		Search_subject.addActionListener(this);
		Search_teacher.addActionListener(this);
		Search_dept.addActionListener(this);
		
		About.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource()==Insert_student)
		{   
			new New_Student_Registration();
			/*
			JDialog jd = new JDialog(this,"Add New Student",true);
			jd.setContentPane(new New_Student_Registration().getContentPane());
			jd.setSize(1000,600);
			jd.setLocation(230,100);
			jd.show();
			*/
			
		}
		
		if(e.getSource()==Insert_department)
		{
			new addDept();
			/*
			JDialog jd = new JDialog(this,"Add New Department",true);
			jd.setContentPane(new addDept().getContentPane());
			jd.setSize(710,450);
			jd.setLocation(250,100);
			jd.show();			
			*/

		}
		
		if(e.getSource()==Insert_course)
		{
		    //new addCourse();
		    /*
			JDialog jd = new JDialog(this,"Departmental Course Entry form",true);
			jd.setContentPane(new addCourse().getContentPane());
			jd.setSize(600,400);
			jd.setLocation(250,100);
			jd.show();
			*/
		}
		
		if(e.getSource()==Insert_teacher)
		{
			new addTeacher();
			/*
			JDialog jd = new JDialog(this,"New Teacher Registration Form",true);
			jd.setContentPane(new addTeacher().getContentPane());
			jd.setSize(700,500);
			jd.setLocation(250,100);
			jd.show();
			*/
		}
		
		if(e.getSource()==Insert_subject)
		{
			new addSubject();
			/*
			JDialog jd = new JDialog(this,"Add Subject on Course",true);
			jd.setContentPane(new addSubject().getContentPane());
			jd.setSize(700,500);
			jd.setLocation(250,100);
			jd.show();
			*/
		}
		
		if(e.getSource()==Insert_credit)
		{
			new Credit_Details_Entry();
			/*
			JDialog jd = new JDialog(this,"Master Credit Details Entry form",true);
			jd.setContentPane(new Credit_Details_Entry().getContentPane());
			jd.setSize(800,600);
			jd.setLocation(250,100);
			jd.show();
			*/
		}
		
		if(e.getSource()==Marks_Entry_internal)
		{
			new Internal_Marks_Entry();
			/*
			JDialog jd = new JDialog(this,"Internal Marks Entry for credit ",true);
			jd.setContentPane(new Internal_Marks_Entry().getContentPane());
			jd.setSize(1100,650);
			jd.setLocation(250,100);
			jd.show();
			*/
		}
		
		if(e.getSource()==Marks_Entry_external)
		{
			new External_Marks_Entry();
			/*
			JDialog jd = new JDialog(this,"External Marks Entry for credit",true);
			jd.setContentPane(new External_Marks_Entry().getContentPane());
			jd.setSize(710,450);
			jd.setLocation(250,100);
			jd.show();
			*/
		}
		
		if(e.getSource()==Extra_Notepad)
		{
			Runtime rs = Runtime.getRuntime();
 
			try {
					rs.exec("notepad");
				}
			catch (IOException ex) 
			{
				System.out.println(ex);
			}   
		}
		
		if(e.getSource()==Extra_Calculator)
		{
			
		}
		
		if(e.getSource()==Search_student)
		{
			new Search_Student();
		}
		
		if(e.getSource()==Search_subject)
		{
			
		}
		
		if(e.getSource()==Search_teacher)
		{
			
		}
		
		if(e.getSource()==Search_dept)
		{
			
		}
		if(e.getSource()==About)
		{
		      new About();
			
		}
	}
	
	public static void main(String args[])
	{
		new CBAS_Home();
	}
}