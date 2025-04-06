import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

;

public class MainWindow {

	// Create UI Elements
	JFrame window;

	JPanel tablePanel;
	JPanel formPanel;

	JLabel firstNameLabel;
	JTextField firstNameField;

	JLabel lastNameLabel;
	JTextField lastNameField;

	JLabel sexLabel;
	JRadioButton femaleButton;
	JRadioButton maleButton;

	JLabel birthDateLabel;
	JTextField birthDateField;
	String placeholder = "YYYY-MM-DD";

	JLabel GPALabel;
	JTextField GPAField;

	JLabel searchLabel;
	JTextField searchField;

	JButton removeButton;
	JButton addButton;
	JButton editButton;

	JPanel titlePanel;
	JLabel titleLabel;

	JTable table;
	DefaultTableModel model;

	Font formFont = new Font("Arial", Font.BOLD, 16);
	Font fieldFont = new Font("Times New Roman", Font.PLAIN, 15);

	public MainWindow() {
		initialize();
	}

	// Initialize UI Elements
	private void initialize() {

		// Initialize Window
		window = new JFrame();
		window.setSize(800, 500);
		window.setTitle("Student Mangement System");
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(null);
		window.setVisible(true);
		window.setResizable(false);

		// Initialize Title Panel and Title Label
		titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 785, 80);

		titleLabel = new JLabel("Student Management System");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 50));

		titlePanel.add(titleLabel);
		window.add(titlePanel);

		// Initialize Table Panel and its Elements
		tablePanel = new JPanel();
		tablePanel.setBounds(300, 80, 500, 500);

		// Initialize Table and TableModel
		model = new DefaultTableModel();
		table = new JTable() {

			// Make ID column non-editable
			public boolean isCellEditable(int row, int column) {
				return column != 0;

			}
		};

		// Load data from database
		loadData();

		// Editing functionality
		model.addTableModelListener(new TableModelListener() {

			// If JTable cell gets updated, then update table in database
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int row = e.getFirstRow();
					int column = e.getColumn();

					String columnName = table.getColumnName(column);
					Object updatedValue = table.getValueAt(row, column);
					Object id = table.getValueAt(row, 0);

					DatabaseHelper.editData(columnName, updatedValue.toString(), id.toString());

				}

			}

		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(440, 330));

		searchLabel = new JLabel("Search ");
		searchLabel.setFont(new Font("Arial", Font.BOLD, 20));
		searchLabel.setForeground(Color.red);

		searchField = new JTextField(25);
		searchField.setFont(new Font("Serif", Font.PLAIN, 20));
		searchField.setBorder(new LineBorder(Color.red));

		// Filter table using search field
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);

		searchField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				filterTable();

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				filterTable();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				filterTable();

			}

			private void filterTable() {
				String text = searchField.getText().trim().toLowerCase();
				if (text.length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

		});

		tablePanel.add(searchLabel);
		tablePanel.add(searchField);
		tablePanel.add(scrollPane);

		window.add(tablePanel);

		// Initialize Form Panel and its elements
		formPanel = new JPanel();
		formPanel.setBounds(0, 80, 300, 520);
		formPanel.setLayout(null);

		firstNameLabel = new JLabel("First Name");
		firstNameLabel.setFont(formFont);
		firstNameLabel.setBounds(15, 35, 100, 30);

		firstNameField = new JTextField(10);
		firstNameField.setFont(fieldFont);
		firstNameField.setBounds(115, 35, 180, 30);

		lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setFont(formFont);
		lastNameLabel.setBounds(15, 90, 100, 30);

		lastNameField = new JTextField(10);
		lastNameField.setFont(fieldFont);
		lastNameField.setBounds(115, 90, 180, 30);

		sexLabel = new JLabel("Sex");
		sexLabel.setFont(formFont);
		sexLabel.setBounds(15, 145, 100, 30);

		maleButton = new JRadioButton("Male");
		maleButton.setBounds(115, 135, 80, 50);
		maleButton.setFocusable(false);

		femaleButton = new JRadioButton("Female");
		femaleButton.setBounds(195, 135, 80, 50);
		femaleButton.setFocusable(false);

		ButtonGroup group = new ButtonGroup();
		group.add(maleButton);
		group.add(femaleButton);

		birthDateLabel = new JLabel("Birth Date");
		birthDateLabel.setFont(formFont);
		birthDateLabel.setBounds(15, 200, 100, 30);

		birthDateField = new JTextField(10);
		birthDateField.setFont(fieldFont);
		birthDateField.setBounds(115, 200, 180, 30);

		// Display Placeholder Text and make it gray by default
		birthDateField.setText(placeholder);
		birthDateField.setForeground(Color.gray);

		// Show placeholder text when focused and remove it when focus is lost
		birthDateField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (birthDateField.getText().equals(placeholder)) {
					birthDateField.setText("");
					birthDateField.setForeground(Color.black);
				}
			}

			public void focusLost(FocusEvent e) {
				if (birthDateField.getText().isEmpty()) {
					birthDateField.setForeground(Color.GRAY);
					birthDateField.setText(placeholder);
				}
			}

		});

		GPALabel = new JLabel("GPA");
		GPALabel.setFont(formFont);
		GPALabel.setBounds(15, 255, 100, 30);

		GPAField = new JTextField(10);
		GPAField.setFont(fieldFont);
		GPAField.setBounds(115, 255, 180, 30);

		removeButton = new JButton("❌ Remove");
		removeButton.setBounds(10, 315, 140, 45);
		removeButton.setFocusable(false);

		// Remove Button functionality
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Remove Student from JTable and database table
				if (table.getSelectedRow() != -1) {
					int modelRow = table.convertRowIndexToModel(table.getSelectedRow());
					Object studentIdObj = table.getModel().getValueAt(modelRow, 0);

					model.removeRow(table.getSelectedRow());
					int studentId = Integer.parseInt(studentIdObj.toString());
					DatabaseHelper.removeStudent(studentId);

				}

			}

		});

		addButton = new JButton("✔ Add");
		addButton.setBounds(160, 315, 140, 45);
		addButton.setFocusable(false);

		// Add button functionality
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Add Student to JTable and database table
				// Make sure no field is left empty
				// Make sure the textfields only accept letters
				// Make sure birthday and gpa field only accepts digits and corresponding
				// symbols
				if (!firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty()
						&& !birthDateField.getText().isEmpty() && !GPAField.getText().isEmpty()) {
					String gender = maleButton.isSelected() ? "Male" : "Female";

					if (firstNameField.getText().matches("[a-zA-ZäöüßÄÖÜ\\s\\-,\\.]+")
							&& !firstNameField.getText().matches("\\d+")
							&& lastNameField.getText().matches("[a-zA-ZäöüßÄÖÜ\\s\\-,\\.]+")
							&& !lastNameField.getText().matches("\\d+") && birthDateField.getText().matches("[0-9\\-]+")
							&& GPAField.getText().matches("[0-9\\.]+")
							&& (maleButton.isSelected() || femaleButton.isSelected())) {

						model.addRow(DatabaseHelper.addStudent(firstNameField.getText(), lastNameField.getText(),
								gender, birthDateField.getText(), Double.parseDouble(GPAField.getText())));

						firstNameField.setText("");
						lastNameField.setText("");
						birthDateField.setText("");
						femaleButton.setSelected(false);
						GPAField.setText("");

					} else {
						JOptionPane.showMessageDialog(window, "Invalid Input!");
					}

				} else {
					JOptionPane.showMessageDialog(window, "Please fill out every text field!");
				}

			}

		});

		formPanel.add(firstNameField);
		formPanel.add(lastNameField);
		formPanel.add(birthDateField);
		formPanel.add(GPAField);

		formPanel.add(firstNameLabel);
		formPanel.add(lastNameLabel);
		formPanel.add(sexLabel);
		formPanel.add(birthDateLabel);
		formPanel.add(GPALabel);

		formPanel.add(maleButton);
		formPanel.add(femaleButton);

		formPanel.add(removeButton);
		formPanel.add(addButton);

		window.add(formPanel);

	}

	// Load Data from database table to JTable
	public void loadData() {

		ResultSet rs = DatabaseHelper.getDataFromDatabase();

		try {
			if (rs != null) {
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();

				// Add column names to the table model
				for (int i = 1; i <= columnCount; i++) {
					model.addColumn(metaData.getColumnName(i));
				}

				// Add rows to the table model
				while (rs.next()) {
					Object[] row = new Object[columnCount];
					for (int i = 1; i <= columnCount; i++) {
						row[i - 1] = rs.getObject(i);
					}
					model.addRow(row);
				}

				// Set table model
				table.setModel(model);
				table.setRowHeight(30);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("An Error occured while trying to load data from database");
		} finally {
			// Close Result Set
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
