package com.github.superryhma.miniprojekti.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;

import com.github.superryhma.miniprojekti.dao.TypeDAO;
import com.github.superryhma.miniprojekti.jdbc.DBConnection;
import com.github.superryhma.miniprojekti.models.ReferenceType;

public class TypeDAOdbImpl implements TypeDAO {

	@Override
	public Set<String> getRequiredFields(String type) {
		try {
			String query = "select AT.name /n"
					+ "from Attribute_type AT, Dependency DE, Refenrence_type RT /n"
					+ "where AT.id = DE.attribute_type /n"
					+ "and DE.required = 1 /n"
					+ "and DE.reference_type = RT.id /n"
					+ "and RT.name = ?";

			DBConnection dbc = new DBConnection();
			Connection connection = dbc.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setString(1, type);

			ResultSet rs = ps.executeQuery();
			
			Set<String> optFieldNames = new HashSet<>();
		
			while (rs.next()) {
				optFieldNames.add(rs.getString(1));
			}

			rs.close();
			ps.close();
			connection.close();
			
			return optFieldNames;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Set<String> getOptionalFields(String type) {
		try {
			String query = "select AT.name /n"
					+ "from Attribute_type AT, Dependency DE, Refenrence_type RT /n"
					+ "where AT.id = DE.attribute_type /n"
					+ "and DE.required = 0 /n"
					+ "and DE.reference_type = RT.id /n"
					+ "and RT.name = ?";

			DBConnection dbc = new DBConnection();
			Connection connection = dbc.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setString(1, type);

			ResultSet rs = ps.executeQuery();
			
			Set<String> optFieldNames = new HashSet<>();
		
			while (rs.next()) {
				optFieldNames.add(rs.getString(1));
			}

			rs.close();
			ps.close();
			connection.close();
			
			return optFieldNames;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Set<ReferenceType> getTypes() {
		try {
			String query = "select * from Reference_type";

			DBConnection dbc = new DBConnection();
			Connection connection = dbc.getConnection();

			Set<ReferenceType> referenceTypes = new HashSet<>();

			PreparedStatement ps = connection.prepareStatement(query);

			ReferenceType referenceType = null;

			ResultSet rs = ps.executeQuery();
			
			List<String> l = new ArrayList<String>();

			while (rs.next()) {
				referenceType = new ReferenceType();
				referenceType.setId(rs.getInt("id"));
				referenceType.setName(rs.getString("name"));
				l.addAll(getRequiredFields(rs.getString("name")));
				referenceType.setRequired(l);
				l.clear();
				l.addAll(getOptionalFields(rs.getString("name")));
				referenceType.setOptional(l);
				referenceTypes.add(referenceType);
			}

			ps.close();
			connection.close();

			return referenceTypes;
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
