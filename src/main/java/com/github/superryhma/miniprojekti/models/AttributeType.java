package com.github.superryhma.miniprojekti.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import javax.naming.NamingException;

import com.github.superryhma.miniprojekti.jdbc.DBConnection;

public class AttributeType {

	protected int id;
	protected String type;
	private Set<String> requiredAttributes;
    private Set<String> optionalAttributes;

	public AttributeType() {
	}

	public static AttributeType getById(int id) {
		try {
			String query = "select * from Attribute_type where id = ?";

			DBConnection dbc = new DBConnection();
			Connection connection = dbc.getConnection();

			AttributeType type;

			PreparedStatement ps = connection.prepareStatement(query);

			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();

			if (result.next()) {
				type = new AttributeType();
				type.id = id;
				type.type = result.getString("name");
			} else {
				type = null;
			}
			ps.close();
			connection.close();

			return type;
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String name) {
		this.type = name;
	}

	public Set<String> getRequiredAttributes() {
		return requiredAttributes;
	}

	public void setRequiredAttributes(Set<String> requiredAttributes) {
		this.requiredAttributes = requiredAttributes;
	}

	public Set<String> getOptionalAttributes() {
		return optionalAttributes;
	}

	public void setOptionalAttributes(Set<String> optionalAttributes) {
		this.optionalAttributes = optionalAttributes;
	}
	
}
