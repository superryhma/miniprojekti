package com.github.superryhma.miniprojekti.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.postgresql.ds.PGPoolingDataSource;

public class DBConnection {
    private InitialContext cxt;
    private DataSource dataSource;

    public DBConnection() {
    }

    public Connection getConnection() throws SQLException {
        try {
            this.cxt = new InitialContext();
            this.dataSource = (DataSource) cxt.lookup("DataSource");
            return dataSource.getConnection();
        } catch (NamingException e) {
            PGPoolingDataSource source = new PGPoolingDataSource();
            source.setDataSourceName("A Data Source");
            source.setServerName("localhost");
            source.setPortNumber(5432);
            source.setDatabaseName("miniprojekti");
            source.setUser("miniprojekti");
            source.setPassword("secret");
            source.setMaxConnections(10);
            try {
                new InitialContext().rebind("DataSource", source);
                this.dataSource = (DataSource) cxt.lookup("DataSource");
                return dataSource.getConnection();
            } catch (NamingException e1) {
                e1.printStackTrace();
            }
        }
        return null;
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
