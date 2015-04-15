package com.github.superryhma.miniprojekti.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
	private final InitialContext cxt;
	private final DataSource dataSource;

	public DBConnection() throws NamingException {
		this.cxt = new InitialContext();
        this.dataSource = (DataSource) cxt.lookup("java:comp/env/jdbc/DataSource");
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public Statement getStatement() throws SQLException {
		Statement st = this.getConnection().createStatement();
		return st;
	}

	public PreparedStatement getPreparedStatement(String sql)
			throws SQLException {
		return this.getConnection().prepareStatement(sql);
	}
}
