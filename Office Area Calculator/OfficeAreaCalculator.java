/***********************************************************************
 Program Name: OfficeAreaCalculator.java
 Programmer's Name: Steven Bennett
 Program Description: Program displays dialog box that allows user to
 enter length and width to calculate the area of an office.
 ***********************************************************************/ 

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class OfficeAreaCalculator extends JFrame{
	
	private JFrame mainFrame;
	private JButton calculateButton;
	private JButton exitButton;
	private JTextField lengthField;
	private JTextField widthField;
	private JTextField areaField;
	private JLabel lengthLabel;
	private JLabel widthLabel;
	private JLabel areaLabel;
	
	//Class constructor method
	public OfficeAreaCalculator(){
		
		//instantiate JFrame object
		mainFrame = new JFrame("Office Area Calculator");
		
		//instantiate calculator buttons
		calculateButton = new JButton("Calculate");
		exitButton = new JButton("Exit");
		
		
		//Create labels
		lengthLabel = new JLabel("Enter the length of the office: ");
		widthLabel = new JLabel("Enter the width of the office: ");
		areaLabel = new JLabel("Office area: ");
		
		//Set Label foreground color to white
		lengthLabel.setForeground(Color.white);
		widthLabel.setForeground(Color.white);
		areaLabel.setForeground(Color.white);
		
		//Create text fields
		lengthField = new JTextField(5);
		widthField = new JTextField(5);
		areaField = new JTextField(13);
		
		//disable area field for editability
		areaField.setEditable(false);
		
		//Get container for the frame and set layout
		Container c = mainFrame.getContentPane();
		c.setLayout(new FlowLayout());
		
		//set background color of container
		c.setBackground(Color.black);
		
		//Add components to container
		c.add(lengthLabel);
		c.add(lengthField);
		c.add(widthLabel);
		c.add(widthField);
		c.add(areaLabel);
		c.add(areaField);
		c.add(calculateButton);
		c.add(exitButton);
		
		//set color of each button
		calculateButton.setBackground(Color.green);
		exitButton.setBackground(Color.red);
		
		//set font color of exitButton
		exitButton.setForeground(Color.white);
		
		//set mnemonic for each button
		calculateButton.setMnemonic('C');
		exitButton.setMnemonic('X');
		
		//set location and size of GUI frame (x,y,width,height)
		mainFrame.setBounds(800, 300, 300, 150);
		
		//Terminate program when application closed
		mainFrame.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
		});
		
		//add action listener to each button
		CalculateButtonHandler chandler = new CalculateButtonHandler();
		calculateButton.addActionListener(chandler);
		
		ExitButtonHandler ehandler = new ExitButtonHandler();
		exitButton.addActionListener(ehandler);
		
		FocusHandler fhandler = new FocusHandler();
		lengthField.addFocusListener(fhandler);
		widthField.addFocusListener(fhandler);
		areaField.addFocusListener(fhandler);
		
		//Set the GUI frame visible
		mainFrame.setVisible(true);	
	}//end constructor method
	
	class CalculateButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e){
			DecimalFormat num = new DecimalFormat(",###.##");
			double width, length, area;
			String instring;
			
			instring = lengthField.getText();
			if(instring.equals("")){
				instring = ("0");
				lengthField.setText("0");
			}
			length = Double.parseDouble(instring);
			
			instring = widthField.getText();
			if(instring.equals("")){
				instring = "0";
				widthField.setText("0");
			}
			width = Double.parseDouble(instring);
			
			area = length * width;
			areaField.setText(num.format(area));
		}
	}//end CalculateButtonHandler
		
	class ExitButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	
	class FocusHandler implements FocusListener {
		
		//if focus returns to lenghtField or widthField clear text in areaField
		public void focusGained(FocusEvent e) {
			if(e.getSource() == lengthField || e.getSource() == widthField){
				areaField.setText("");
			}else if(e.getSource() == areaField){
				calculateButton.requestFocus();
			}
		}
		
		public void focusLost(FocusEvent e){
			if(e.getSource() == widthField){
				calculateButton.requestFocus();
			}
		}
	}
	
}
