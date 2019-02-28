package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import com.lti.model.Customer;

// Data Access Object
public class CustomerDao {

	// public void add(int id, String name, String email)
	public void add(Customer customer) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Properties dbProps = new Properties();
			dbProps.load(this.getClass().getClassLoader().getResourceAsStream("prod-db.properties"));

			Class.forName(dbProps.getProperty("driverClassName"));
			conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps.getProperty("user"),
					dbProps.getProperty("pass"));

			String sql = "insert into customer_ap values(?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, customer.getId());
			stmt.setString(2, customer.getName());
			stmt.setString(3, customer.getEmail());

			stmt.executeUpdate();
			// System.out.println("Record inserted");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
