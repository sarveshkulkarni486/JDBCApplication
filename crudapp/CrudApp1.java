package crudapp;
import java.sql.*;
import java.util.*;
import java.util.jar.Attributes.Name;
public class CrudApp1 {
	public static void Create(String tablename) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3309/sumago", "root", "Sarvesh@2001");
			System.out.println("Connection established");
			Statement stmt = con.createStatement();
			String createTableQuery = "CREATE TABLE " + tablename + " (id INT(2) primary key, name VARCHAR(200) not null, marks INT(20) not null)";
			stmt.executeUpdate(createTableQuery);
			System.out.println("Table created: ");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void insert(int id, String name, int marks, String tablename) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3309/sumago", "root", "Sarvesh@2001");
			System.out.println("connection established");
			PreparedStatement ps = con.prepareStatement("INSERT INTO " + tablename + " (id, name, marks) VALUES(?, ?, ?);");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, marks);
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void update(int oldid, int newid, String tablename) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3309/sumago", "root", "Sarvesh@2001");
			System.out.println("connection established");
			PreparedStatement ps = con.prepareStatement("UPDATE " + tablename + " SET id=? where id=?");
			ps.setInt(1, newid);
			ps.setInt(2, oldid);
			ps.executeUpdate();
			System.out.println("data updated successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void update1(String newname, int id, String tablename) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3309/sumago", "root", "Sarvesh@2001");
				System.out.println("connection established");
				PreparedStatement ps = con.prepareStatement("UPDATE " + tablename + " SET name=? where id=?");
				ps.setString(1, newname);
				ps.setInt(2, id);
				ps.executeUpdate();
				System.out.println("data updated successfully");
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	public static void update(String name, int marks, String tablename) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3309/sumago", "root", "Sarvesh@2001");
			System.out.println("connection established");
			PreparedStatement ps = con.prepareStatement("UPDATE " + tablename + " SET marks=? where name=?");
			ps.setInt(1, marks);
			ps.setString(2, name);
			ps.executeUpdate();
			System.out.println("data updated successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
			
	public static void delete(int id, String tablename) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3309/sumago", "root", "Sarvesh@2001");
			Statement st = con.createStatement();
			String str = "DELETE FROM `" + tablename + "` WHERE id='" + id + "'";
			int rs = st.executeUpdate(str);
			System.out.println("Deleted successfully");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void select(String tablename) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3309/sumago", "root", "Sarvesh@2001");
			CallableStatement stmt = con.prepareCall("{call GetAllRecords(?)}");
			stmt.setString(1, tablename);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter any one choice from below: ");
		System.out.println("1. Create table \t 2. Insert data \t 3. Update Data \t 4. Delete Data \t 5. Display data");
		int ch = sc.nextInt();
		System.out.println("enter tablename");
		String tablename = sc.next();
		switch(ch) {
		case 1: 
			Create(tablename);
			break;
		case 2: 
			System.out.println("how many data you want to insert: ");
			int noofdata = sc.nextInt();
			int i=0;
			while(i!=noofdata) {
				System.out.println("Enter id: ");
				int id = sc.nextInt();
				System.out.println("Enter name: ");
				String name = sc.next();
				System.out.println("Enter marks: ");
				int marks = sc.nextInt();
				insert(id, name, marks, tablename);
				i++;
			}
			break;
		case 3:
			System.out.println("Enter value to be updated");
			System.out.println("1. id \t 2. name \t 3. marks");
			int choices = sc.nextInt();
			if(choices == 1) {
				System.out.println("Enter old id");
				int oldid = sc.nextInt();
				System.out.println("Enter new id");
				int newid = sc.nextInt();
				update(oldid, newid, tablename);
			}
			else if(choices == 2) {
				System.out.println("Enter new name");
				String newname = sc.next();
				System.out.println("Enter id which specifies the name");
				int id = sc.nextInt();
				update1(newname, id, tablename);
			}
		    else if(choices == 3) {
				System.out.println("Enter marks you want to update: ");
				int newmarks = sc.nextInt();
				System.out.println("Enter name by which we can identify");
				String name = sc.next();
				update(name, newmarks, tablename);
			}
			break;
		case 4:
			System.out.println("Enter id by which you can delete a row");
			int id = sc.nextInt();
			delete(id, tablename);
			break;
		case 5: 
			select(tablename);
		}


	}

}
