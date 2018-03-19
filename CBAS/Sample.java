import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Sample extends JFrame
{	
	// 1) Declaration
	
	Sample()
	{
		super("Frame Title");
		setSize(1350,720);
		setLocation(10,10);
		setLayout(null);
		// 2) memory allocation
		
		// 3) add Component on frame

		// 4) setBounds
		
		// 5) addListenere
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		new Sample();
	}
}