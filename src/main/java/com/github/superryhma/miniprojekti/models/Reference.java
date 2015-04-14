package com.github.superryhma.miniprojekti.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;

import com.github.superryhma.miniprojekti.dao.ReferenceDAO;
import com.github.superryhma.miniprojekti.jdbc.DBConnection;

public class Reference implements ReferenceDAO {

	protected int id;
	protected String bibtexname;
	protected Date createdAt;
	protected Date updatedAt;
	protected Set<Attribute> attributes;
	protected Set<Tag> tags;
	protected ReferenceType type;

	public Reference() {
	}

	@Override
	public List<Reference> getReferences() {
		try {
			String query = "select * from Reference";

			DBConnection dbc = new DBConnection();
			Connection connection = dbc.getConnection();

			List<Reference> references = new ArrayList<>();

			PreparedStatement ps = connection.prepareStatement(query);

			Reference reference = null;

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				reference = new Reference();
				reference.id = id;
				reference.loadAttributes();
				reference.loadTags();
				reference.type = ReferenceType.getById(rs
						.getInt("reference_type"));
				references.add(reference);
			}

			ps.close();
			connection.close();

			return references;
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Reference getReferenceById(int id) {
		try {
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
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Reference addReference(Reference reference) {
		try {
			reference.saveAttributes();
			reference.saveTags();

			String sql = "INSERT INTO Reference (reference_type, project, bibtexname) \n"
					+ "VALUES (?,?,?)";

			DBConnection dbc = new DBConnection();
			Connection c = dbc.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, reference.getType().id);
			ps.setInt(2, 1);
			ps.setString(3, this.getBibtexname());

			ps.execute();

			ps.close();
			c.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reference;
	}

	private void loadTags() throws NamingException {
		String query = "select * from Tag where reference = ?";

		DBConnection dbc = new DBConnection();

		try {
			Connection connection = dbc.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();

			tags = new HashSet<>();

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

	private void saveTags() throws NamingException {
		try {
			String sql = "INSERT INTO Tag (reference, value) \n"
					+ "VALUES (?,?)";
			DBConnection dbc = new DBConnection();
			Connection c;
			PreparedStatement ps;

			for (Tag tag : tags) {
				c = dbc.getConnection();
				ps = c.prepareStatement(sql);

				ps.setInt(1, tag.getReference().getId());
				ps.setString(2, tag.getValue());

				ps.execute();
				ps.close();
				c.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

			attributes = new HashSet<>();

			while (result.next()) {
				Attribute attribute = new Attribute();
				attribute.setId(result.getInt("id"));
				attribute.setReference(this);
				attribute.setValue(result.getString("value"));
				attribute.setAttribute_type(AttributeType.getById(result
						.getInt("attribute_type")));

				attributes.add(attribute);
			}

			ps.close();
			connection.close();
		} catch (SQLException ex) {

		}
	}

	private void saveAttributes() throws NamingException {
		try {
			String sql = "INSERT INTO Attribute (reference, attribute_type, value) \n"
					+ "VALUES (?,?,?)";
			DBConnection dbc = new DBConnection();
			Connection c;
			PreparedStatement ps;

			for (Attribute attribute : attributes) {
				c = dbc.getConnection();
				ps = c.prepareStatement(sql);

				ps.setInt(1, attribute.getReference().getId());
				ps.setInt(2, attribute.getAttribute_type().getId());
				ps.setString(3, attribute.getValue());

				ps.execute();
				ps.close();
				c.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBibtexname() {
		return bibtexname;
	}

	public void setBibtexname(String bibtexname) {
		this.bibtexname = bibtexname;
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

	public Set<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public ReferenceType getType() {
		return type;
	}

	public void setType(ReferenceType type) {
		this.type = type;
	}

}
