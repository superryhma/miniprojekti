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

import com.github.superryhma.miniprojekti.dao.ReferenceDAO;
import com.github.superryhma.miniprojekti.jdbc.DBConnection;
import com.github.superryhma.miniprojekti.models.Attribute;
import com.github.superryhma.miniprojekti.models.Reference;
import com.github.superryhma.miniprojekti.models.ReferenceType;
import com.github.superryhma.miniprojekti.models.Tag;

public class ReferenceDAOdbImpl implements ReferenceDAO {

	@Override
	public List<Reference> getReferences() {
		try {
			String query = "select * from Reference";

			DBConnection dbc = new DBConnection();
			Connection connection = dbc.getConnection();

			List<Reference> references = new ArrayList<>();

			PreparedStatement ps = connection.prepareStatement(query);

			Reference reference = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				reference = new Reference();
				reference.setId(rs.getInt("id"));
				reference.setAttributes(loadAttributes(rs.getInt("id")));
				reference.setTags(loadTags(rs.getInt("id")));
				reference.setType(getReferenceType(rs.getInt("reference_type")));
				reference.setCreated_at(rs.getDate("created_at"));
				reference.setUpdated_at(rs.getDate("updated_at"));
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

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				reference = new Reference();
				reference.setId(rs.getInt("id"));
				reference.setAttributes(loadAttributes(rs.getInt("id")));
				reference.setTags(loadTags(rs.getInt("id")));
				reference.setType(getReferenceType(rs.getInt("reference_type")));
				reference.setCreated_at(rs.getDate("created_at"));
				reference.setUpdated_at(rs.getDate("updated_at"));
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
			saveAttributes(reference);
			saveTags(reference);

			String sql = "INSERT INTO Reference (reference_type, project, bibtexname, created_at, updated_at) \n"
					+ "VALUES (?,?,?,?,?)";

			DBConnection dbc = new DBConnection();
			Connection c = dbc.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, reference.getType().getId());
			ps.setInt(2, 1);
			ps.setString(3, reference.getBibtexname());
			ps.setDate(4, new java.sql.Date(reference.getCreated_at().getTime()));
			ps.setDate(5, new java.sql.Date(reference.getUpdated_at().getTime()));

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

	public static ReferenceType getReferenceType(int reference_type)
			throws NamingException, SQLException {
		String query = "select * from Reference_type where id = ?";

		DBConnection dbc = new DBConnection();
		Connection connection = dbc.getConnection();

		ReferenceType type = null;

		try {
			PreparedStatement ps = connection.prepareStatement(query);

			ps.setInt(1, reference_type);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				type = new ReferenceType();
				type.setId(rs.getInt("id"));
				type.setName(rs.getString("name"));
			} else {
				type = null;
			}
			ps.close();
			connection.close();
		} catch (SQLException ex) {

		}

		return type;
	}

	private Set<Tag> loadTags(int reference) throws NamingException,
			SQLException {
		String query = "select * from Tag where reference = ?";

		DBConnection dbc = new DBConnection();

		Connection connection = dbc.getConnection();
		PreparedStatement ps = connection.prepareStatement(query);

		ps.setInt(1, reference);

		ResultSet result = ps.executeQuery();

		Set<Tag> tags = new HashSet<>();

		while (result.next()) {
			Tag tag = new Tag();
			tag.setId(result.getInt("id"));
			tag.setReference(reference);
			tag.setValue(result.getString("value"));

			tags.add(tag);
		}

		ps.close();
		connection.close();

		return tags;
	}

	private void saveTags(Reference reference) throws NamingException {
		try {
			String sql = "INSERT INTO Tag (reference, value) \n"
					+ "VALUES (?,?)";
			DBConnection dbc = new DBConnection();
			Connection c;
			PreparedStatement ps;

			for (Tag tag : reference.getTags()) {
				c = dbc.getConnection();
				ps = c.prepareStatement(sql);

				ps.setInt(1, reference.getId());
				ps.setString(2, tag.getValue());

				ps.execute();
				ps.close();
				c.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Set<Attribute> loadAttributes(int reference)
			throws NamingException, SQLException {
		Set<Attribute> attributes = new HashSet<>();

		String query = "select * from Attribute where reference = ?";

		DBConnection dbc = new DBConnection();

		Connection connection = dbc.getConnection();
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, reference);

		ResultSet result = ps.executeQuery();

		Attribute attribute = null;

		while (result.next()) {
			attribute = new Attribute();
			attribute.setId(result.getInt("id"));
			attribute.setReference(reference);
			attribute.setValue(result.getString("value"));
			attribute.setAttribute_type(result.getInt("attribute_type"));

			attributes.add(attribute);
		}

		ps.close();
		connection.close();

		return attributes;
	}

	private void saveAttributes(Reference reference) throws NamingException {
		try {
			String sql = "INSERT INTO Attribute (reference, attribute_type, value) \n"
					+ "VALUES (?,?,?)";
			DBConnection dbc = new DBConnection();
			Connection c;
			PreparedStatement ps;

			for (Attribute attribute : reference.getAttributes()) {
				c = dbc.getConnection();
				ps = c.prepareStatement(sql);

				ps.setInt(1, reference.getId());
				ps.setInt(2, attribute.getAttribute_type());
				ps.setString(3, attribute.getValue());

				ps.execute();
				ps.close();
				c.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Reference updateReference(int id, Reference reference) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteReference(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
