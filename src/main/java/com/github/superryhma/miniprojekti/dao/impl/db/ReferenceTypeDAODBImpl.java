package com.github.superryhma.miniprojekti.dao.impl.db;

import com.github.superryhma.miniprojekti.dao.ReferenceTypeDAO;
import com.github.superryhma.miniprojekti.jdbc.DBConnection;
import com.github.superryhma.miniprojekti.models.ReferenceType;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ReferenceTypeDAODBImpl implements ReferenceTypeDAO {

    @Override
    public Set<String> getRequiredFields(String type) {
        try {
            String query = "select Attribute_type.name \n"
                    + "from Attribute_type, Dependency, Reference_type \n"
                    + "where Attribute_type.id = Dependency.attribute_type \n"
                    + "and Dependency.required = 'true' \n"
                    + "and Dependency.reference_type = Reference_type.id \n"
                    + "and Reference_type.name = ?";

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
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<String> getOptionalFields(String type) {
        try {
            String query = "select Attribute_type.name \n"
                    + "from Attribute_type, Dependency, Reference_type \n"
                    + "where Attribute_type.id = Dependency.attribute_type \n"
                    + "and Dependency.required = 'false' \n"
                    + "and Dependency.reference_type = Reference_type.id \n"
                    + "and Reference_type.name = ?";

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
        } catch (SQLException e) {
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

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                ReferenceType referenceType = new ReferenceType();
                referenceType.setName(rs.getString("name"));
                Set<String> l = new HashSet<>();
                l.addAll(getRequiredFields(rs.getString("name")));
                referenceType.setRequiredAttributes(l);
                l.clear();
                l.addAll(getOptionalFields(rs.getString("name")));
                referenceType.setOptionalAttributes(l);
                referenceTypes.add(referenceType);
            }

            ps.close();
            connection.close();

            return referenceTypes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

