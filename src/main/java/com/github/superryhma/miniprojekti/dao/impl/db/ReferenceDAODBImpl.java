package com.github.superryhma.miniprojekti.dao.impl.db;

import com.github.superryhma.miniprojekti.dao.ReferenceDAO;
import com.github.superryhma.miniprojekti.jdbc.DBConnection;
import com.github.superryhma.miniprojekti.models.Attribute;
import com.github.superryhma.miniprojekti.models.Reference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReferenceDAODBImpl implements ReferenceDAO {

	@Override
	public List<Reference> getReferences() {
		try {
			String query = "select * from Reference";

			DBConnection dbc = new DBConnection();
			Connection connection = dbc.getConnection();

			List<Reference> references = new ArrayList<>();

			PreparedStatement ps = connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Reference reference = new Reference();
				reference.setId(rs.getInt("id"));
				reference.setAttributes(loadAttributes(rs.getInt("id")));
				reference.setTags(loadTags(rs.getInt("id")));
				reference.setType(getReferenceType(rs.getInt("reference_type")));
				reference.setCreatedAt(rs.getDate("created_at"));
				reference.setUpdatedAt(rs.getDate("updated_at"));
				references.add(reference);
			}

			ps.close();
			connection.close();

			return references;
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

			PreparedStatement ps = connection.prepareStatement(query);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			Reference reference;
			if (rs.next()) {
				reference = new Reference();
				reference.setId(rs.getInt("id"));
				reference.setAttributes(loadAttributes(rs.getInt("id")));
				reference.setTags(loadTags(rs.getInt("id")));
				reference.setType(getReferenceType(rs.getInt("reference_type")));
				reference.setCreatedAt(rs.getDate("created_at"));
				reference.setUpdatedAt(rs.getDate("updated_at"));
			} else {
				reference = null;
			}
			ps.close();
			connection.close();

			return reference;
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

			ps.setInt(1, getReferenceTypeId(reference.getType()));
			ps.setInt(2, 1);
			ps.setString(3, reference.getBibtexname());
			ps.setDate(4, new java.sql.Date(reference.getCreatedAt().getTime()));
			ps.setDate(5, new java.sql.Date(reference.getUpdatedAt().getTime()));

			ResultSet resultSet = ps.executeQuery();
			reference.setId(resultSet.getInt("id"));

			ps.close();
			c.close();
		}  catch (SQLException e) {
			e.printStackTrace();
		}

		return reference;
	}

	private static String getReferenceType(int reference_type) {
		String query = "select * from Reference_type where id = ?";

		DBConnection dbc = new DBConnection();
		String type;
		try {
			Connection connection = dbc.getConnection();

			PreparedStatement ps = connection.prepareStatement(query);

			ps.setInt(1, reference_type);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				type = rs.getString("name");
			} else {
				type = null;
			}
			ps.close();
			connection.close();
		} catch (SQLException ex) {
			return null;
		}

		return type;
	}

	private String getAttributeType(int attributeType) throws SQLException {
		String query = "select attribute_type from Attribute_type where id = ?";

		DBConnection dbc = new DBConnection();
		Connection connection = dbc.getConnection();

		PreparedStatement ps = connection.prepareStatement(query);

		ps.setInt(1, attributeType);

		ResultSet rs = ps.executeQuery();

		String type = rs.getString("id");

		ps.close();
		connection.close();

		return type;
	}

	private int getAttributeTypeId(String attributeType) throws SQLException {
		String query = "select id from Attribute_type where attribute_type = ?";

		DBConnection dbc = new DBConnection();
		Connection connection = dbc.getConnection();

		PreparedStatement ps = connection.prepareStatement(query);

		ps.setString(1, attributeType);

		ResultSet rs = ps.executeQuery();

		int id = rs.getInt("id");

		ps.close();
		connection.close();

		return id;
	}

	private int getReferenceTypeId(String referenceType) throws SQLException {
		String query = "select id from Reference_type where reference_type = ?";

		DBConnection dbc = new DBConnection();
		Connection connection = dbc.getConnection();

		PreparedStatement ps = connection.prepareStatement(query);

		ps.setString(1, referenceType);

		ResultSet rs = ps.executeQuery();

		int id = rs.getInt("id");

		ps.close();
		connection.close();

		return id;
	}

	private Set<String> loadTags(int reference) throws SQLException {
		String query = "select * from Tag where reference = ?";

		DBConnection dbc = new DBConnection();

		Connection connection = dbc.getConnection();
		PreparedStatement ps = connection.prepareStatement(query);

		ps.setInt(1, reference);

		ResultSet result = ps.executeQuery();

		Set<String> tags = new HashSet<>();

		while (result.next()) {
			tags.add(result.getString("value"));
		}

		ps.close();
		connection.close();

		return tags;
	}

	private void saveTags(Reference reference) {
		try {
			String sql = "INSERT INTO Tag (reference, value) \n"
					+ "VALUES (?,?)";
			DBConnection dbc = new DBConnection();
			Connection c;
			PreparedStatement ps;

			for (String tag : reference.getTags()) {
				c = dbc.getConnection();
				ps = c.prepareStatement(sql);

				ps.setInt(1, reference.getId());
				ps.setString(2, tag);

				ps.execute();
				ps.close();
				c.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Set<Attribute> loadAttributes(int reference) throws SQLException {
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
			attribute.setAttributeType(getAttributeType(result.getInt("attribute_type")));
			attribute.setValue(result.getString("value"));
			attributes.add(attribute);
		}

		ps.close();
		connection.close();

		return attributes;
	}

	private void saveAttributes(Reference reference) {
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
				ps.setInt(2, getAttributeTypeId(attribute.getAttributeType()));
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
