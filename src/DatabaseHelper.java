import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

	// Store PostgreSQL url, username and password.
	// Enter your url, username and password here.
	private static final String url = "jdbc:postgresql://localhost:5432/Testing";
	private static final String username = "postgres";
	private static final String password = "405011";

	// Connect to database containing students table
	private static Connection getConnection() throws SQLException {
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Database driver not found");
		}

	}

	// Return ResultSet containing students table
	public static ResultSet getDataFromDatabase() {
		String query = "SELECT * FROM students ";
		Statement st = null;
		Connection con = null;
		try {
			con = getConnection();
			st = con.createStatement();
			{
				return st.executeQuery(query);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	// Update students table for specific JTable cell that got edited
	public static void editData(String columnName, String updatedValue, String id) {
		String query = "UPDATE students SET " + columnName + " = " + "'" + updatedValue + "'" + " WHERE id = " + id;
		PreparedStatement prst = null;
		Connection con = null;

		try {
			con = getConnection();
			prst = con.prepareStatement(query);
			int affectedRows = prst.executeUpdate();

			if (affectedRows > 0) {
				System.out.print("Successfully edited students table");
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (prst != null)
					prst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// Add user details to JTable and students table
	// Return array containing elements of the new row added to students table
	public static Object[] addStudent(String firstName, String lastName, String gender, String birthDate, double GPA) {

		PreparedStatement prst = null;
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		Object[] row = new Object[6];

		try {
			con = getConnection();
			String sql = "INSERT INTO students (firstname, lastname, gender, birthdate, gpa) VALUES (?, ?, ?, ?, ?)";

			prst = con.prepareStatement(sql);

			prst.setString(1, firstName);
			prst.setString(2, lastName);
			prst.setString(3, gender);
			prst.setString(4, birthDate);
			prst.setDouble(5, GPA);

			int rowsInserted = prst.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Student data added successfully!");
			}

			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM students ORDER BY id DESC LIMIT 1;");
			rs.next();

			for (int i = 1; i <= 6; i++) {
				row[i - 1] = rs.getObject(i);
			}

		} catch (SQLException e) {
			e.printStackTrace();

			// Close resources
		} finally {
			try {
				if (st != null)
					st.close();
				if (con != null)
					con.close();
				if (prst != null)
					prst.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;

	}

	// Remove student from students table
	public static void removeStudent(int id) {
		String query = "DELETE FROM students WHERE id = ?";

		try (Connection con = getConnection(); PreparedStatement prst = con.prepareStatement(query)) {
			prst.setInt(1, id);
			int affectedRows = prst.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Student removed successfully");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

}
