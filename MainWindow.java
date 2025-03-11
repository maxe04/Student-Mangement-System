import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow {

	
	JFrame window;
	
	JPanel tablePanel;
	JPanel searchPanel;
	
	JPanel formPanel;
	JLabel idLabel;
	JLabel firstNameLabel;
	JLabel lastNameLabel;
	JLabel sexLabel;
	JLabel birthDateLabel;
	JLabel GPALabel;
	
	Font formFont = new Font("Arial", Font.PLAIN, 16);
	
	JPanel titlePanel;
	JLabel titleLabel;
	
	public MainWindow() {
		initialize();
	}
	
	private void initialize() {
		
		window = new JFrame();
		window.setSize(800,600);
		window.setTitle("Student Mangement System");
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(null);
		window.setVisible(true);
		
		titlePanel = new JPanel();
		titlePanel.setBackground(Color.red);
		titlePanel.setBounds(0, 0, 785, 80);

		titleLabel = new JLabel("Student Management System");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
		
		titlePanel.add(titleLabel);
		window.add(titlePanel);
		
		formPanel = new JPanel();
		//formPanel.setBackground(Color.black);
		formPanel.setBounds(0, 80, 300, 520);
		formPanel.setLayout(null); 
		
		
		
		
		idLabel = new JLabel("Student ID");
		idLabel.setFont(formFont);
		idLabel.setBounds(10,0,100,50);
		
		firstNameLabel = new JLabel("First Name");
		firstNameLabel.setFont(formFont);
		
		firstNameLabel.setBounds(10, 50, 100, 50);
		
		lastNameLabel = new JLabel("Name");
		lastNameLabel.setFont(formFont);
		lastNameLabel.setBounds(10, 100, 100, 50);
		
		sexLabel = new JLabel("Sex");
		sexLabel.setFont(formFont);
		sexLabel.setBounds(10, 150, 100, 50);
		
		birthDateLabel = new JLabel("Birth Date");
		birthDateLabel.setFont(formFont);
		birthDateLabel.setBounds(10,200,100,50);
		
		searchPanel = new JPanel();
		searchPanel.setBackground(Color.blue);
		searchPanel.setBounds(300, 80, 500, 80);
		window.add(searchPanel);
		
		tablePanel = new JPanel();
		tablePanel.setBackground(Color.pink);
		tablePanel.setBounds(300, 160, 500, 440);
		window.add(tablePanel);
	}
	
}
