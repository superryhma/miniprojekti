package com.github.superryhma.miniprojekti.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import com.github.superryhma.miniprojekti.jdbc.DBConnection;

public class Reference implements Serializable {

	protected int id;
	protected String bibtexname;
	protected Date createdAt;
	protected Date updatedAt;
	protected List<Attribute> fields;
	protected List<Tag> tags;
	protected ReferenceType type;

	public Reference() {
	}

	public static Reference getById(int id) throws NamingException,
			SQLException {
		String query = "select * from Reference where id = ?";

		DBConnection dbc = new DBConnection();
		Connection connection = dbc.getConnection();

		Reference reference = null;

		PreparedStatement ps = connection.prepareStatement(query);

		ps.setInt(1, id);

		ResultSet result = ps.executeQuery();

		if (result.next()) {
			reference = new Reference();
			reference.id = id;
			reference.loadAttributes();
			reference.loadTags();
			reference.type = ReferenceType.getById(result
					.getInt("reference_type"));
		} else {
			reference = null;
		}
		ps.close();
		connection.close();

		return reference;
	}

	public List<Reference> getAll() throws NamingException, SQLException {
		String query = "select * from Reference";

		DBConnection dbc = new DBConnection();
		Connection connection = dbc.getConnection();

		ArrayList<Reference> references = new ArrayList<>();

		PreparedStatement ps = connection.prepareStatement(query);

		Reference reference = null;

		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			reference = new Reference();
			reference.id = id;
			reference.loadAttributes();
			reference.loadTags();
			reference.type = ReferenceType.getById(rs.getInt("reference_type"));
			references.add(reference);
		}

		ps.close();
		connection.close();

		return references;
	}

	private void loadTags() throws NamingException {
		String query = "select * from Tag where reference = ?";

		DBConnection dbc = new DBConnection();

		try {
			Connection connection = dbc.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();

			Reference reference;
			tags = new LinkedList<>();

			while (result.next()) {
				Tag tag = new Tag();
				tag.setId(result.getInt("id"));
				tag.setReference(this);
				tag.setValue(result.getString("value"));

				tags.add(tag);
			}

			ps.close();
			connection.close();
		} catch (SQLException ex) {

		}

	}

	private void loadAttributes() throws NamingException {
		String query = "select * from Attribute where reference = ?";

		DBConnection dbc = new DBConnection();

		try {
			Connection connection = dbc.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();

			Reference reference;
			fields = new LinkedList<>();

			while (result.next()) {
				Attribute attribute = new Attribute();
				attribute.setId(result.getInt("id"));
				attribute.setReference(this);
				attribute.setValue(result.getString("value"));
				attribute.setAttribute_type(AttributeType.getById(result
						.getInt("attribute_type")));

				fields.add(attribute);
			}

			ps.close();
			connection.close();
		} catch (SQLException ex) {

		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return bibtexname;
	}

	public void setName(String name) {
		this.bibtexname = name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Attribute> getFields() {
		return fields;
	}

	public void setFields(ArrayList<Attribute> fields) {
		this.fields = fields;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}

}
