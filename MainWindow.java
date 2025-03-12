import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class MainWindow {

	JFrame window;

	JPanel tablePanel;
	JPanel searchPanel;

	JPanel formPanel;

	JLabel idLabel;
	JTextField idField;

	JLabel firstNameLabel;
	JTextField firstNameField;

	JLabel lastNameLabel;
	JTextField lastNameField;

	JLabel sexLabel;
	JRadioButton femaleButton;
	JRadioButton maleButton;

	JLabel birthDateLabel;
	JTextField birthDateField;

	JLabel GPALabel;
	JTextField GPAField;

	JLabel searchLabel;
	JTextField searchField;
	
	
	Font formFont = new Font("Arial", Font.BOLD, 16);
	Font fieldFont = new Font("Times New Roman", Font.PLAIN, 15);   

	JPanel titlePanel;
	JLabel titleLabel;

	public MainWindow() {
		initialize();
	}

	private void initialize() {

		// Init Window
		window = new JFrame();
		window.setSize(800, 550);
		window.setTitle("Student Mangement System");
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(null);
		window.setVisible(true);
		window.setResizable(false);
		
	

		// Title Panel
		titlePanel = new JPanel();
		//titlePanel.setBackground(Color.red);
		titlePanel.setBounds(0, 0, 785, 80);

		titleLabel = new JLabel("Student Management System");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 50));

		titlePanel.add(titleLabel);
		window.add(titlePanel);

		// Form Panel
		formPanel = new JPanel();
		formPanel.setBounds(0, 80, 300, 520);
		formPanel.setLayout(null);

		idLabel = new JLabel("Student ID");
		idLabel.setFont(formFont);
		idLabel.setBounds(15, 35, 100, 30);

		idField = new JTextField(10);
		idField.setFont(fieldFont);
		idField.setBounds(115, 35, 180, 30);

		firstNameLabel = new JLabel("First Name");
		firstNameLabel.setFont(formFont);
		firstNameLabel.setBounds(15, 90, 100, 30);

		firstNameField = new JTextField(10);
		firstNameField.setFont(fieldFont);
		firstNameField.setBounds(115, 90, 180, 30);

		lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setFont(formFont);
		lastNameLabel.setBounds(15, 145, 100, 30);

		lastNameField = new JTextField(10);
		lastNameField.setFont(fieldFont);
		lastNameField.setBounds(115, 145, 180, 30);

		sexLabel = new JLabel("Sex");
		sexLabel.setFont(formFont);
		sexLabel.setBounds(15, 200, 100, 30);

		maleButton = new JRadioButton("Male");
		maleButton.setBounds(115, 190, 80, 50);
		maleButton.setFocusable(false);
		
		femaleButton = new JRadioButton("Female");
		femaleButton.setBounds(195, 190, 80, 50);
		femaleButton.setFocusable(false);

		ButtonGroup group = new ButtonGroup();
		group.add(maleButton);
		group.add(femaleButton);


		birthDateLabel = new JLabel("Birth Date");
		birthDateLabel.setFont(formFont);
		birthDateLabel.setBounds(15, 255, 100, 30);

		birthDateField = new JTextField(10);
		birthDateField.setFont(fieldFont);
		birthDateField.setBounds(115, 255, 180, 30);

		GPALabel = new JLabel("GPA");
		GPALabel.setFont(formFont);
		GPALabel.setBounds(15, 310, 100, 30);

		GPAField = new JTextField(10);
		GPAField.setFont(fieldFont);
		GPAField.setBounds(115, 310, 180, 30);

		formPanel.add(idField);
		formPanel.add(firstNameField);
		formPanel.add(lastNameField);
		formPanel.add(birthDateField);
		formPanel.add(GPAField);

		formPanel.add(idLabel);
		formPanel.add(firstNameLabel);
		formPanel.add(lastNameLabel);
		formPanel.add(sexLabel);
		formPanel.add(birthDateLabel);
		formPanel.add(GPALabel);

		formPanel.add(maleButton);
		formPanel.add(femaleButton);
		
		

		window.add(formPanel);

		
		// Search Panel
		searchPanel = new JPanel();
		searchPanel.setBounds(300, 80, 484, 80);
		searchPanel.setLayout(null);
		
		searchLabel = new JLabel("Search For Students");
		searchLabel.setBounds(20, 0, 300, 80);
		searchLabel.setFont(formFont);
		
		searchField = new JTextField(20);
		searchField.setBounds(200, 25, 260, 30);
		searchField.setFont(fieldFont);
		
		searchPanel.add(searchLabel);
		searchPanel.add(searchField);
		
		
		window.add(searchPanel);

		// Table Panel
		tablePanel = new JPanel();
		
		tablePanel.setBounds(300, 160, 500, 440);
		window.add(tablePanel);
	}

}
