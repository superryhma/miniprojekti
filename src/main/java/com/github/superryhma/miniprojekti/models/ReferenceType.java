package com.github.superryhma.miniprojekti.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import com.github.superryhma.miniprojekti.jdbc.DBConnection;


public class ReferenceType implements Serializable {


	protected int id;

	protected String name;


	public ReferenceType() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

        public List<AttributeTypeAssociation> getAttributeTypes(){
            String query = "select * from Dependency where reference_type = ?";

            List<AttributeTypeAssociation> types = new LinkedList<>();
            DBConnection dbc = new DBConnection();

            try {
                Connection connection = dbc.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);

                ps.setInt(1, this.id);

                ResultSet result = ps.executeQuery();

                 while(result.next()){
                    AttributeTypeAssociation type = new AttributeTypeAssociation();
                    type.attribute_type = result.getInt("attribute_type");
                    type.reference_type = this.id;
                    type.required = result.getBoolean("required");

                    types.add(type);
                }

                ps.close();
                connection.close();
            } catch (SQLException ex) {

            }


            return types;
        }

        public static List<ReferenceType> getReferenceTypes(){
            String query = "select * from Reference_type";

            List<ReferenceType> types = new LinkedList<>();
            DBConnection dbc = new DBConnection();

            try {
                Connection connection = dbc.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);

                ResultSet result = ps.executeQuery();

                while(result.next()){
                    ReferenceType type = new ReferenceType();
                    type.id = result.getInt("id");
                    type.name = result.getString("name");
                    types.add(type);
                }
                ps.close();
                connection.close();
            } catch (SQLException ex) {

            }


            return types;
        }

        public static ReferenceType getById(int id){
            String query = "select * from Reference_type where id = ?";

             DBConnection dbc = new DBConnection();
             Connection connection = dbc.getConnection();

             ReferenceType type = null;

             try {
                 PreparedStatement ps = connection.prepareStatement(query);

                 ps.setInt(1, id);

                 ResultSet result = ps.executeQuery();

                 if(result.next()){
                     type = new ReferenceType();
                     type.id = id;
                     type.name = result.getString("name");
                 }else{
                     type = null;
                 }
                 ps.close();
                 connection.close();
             } catch (SQLException ex) {

             }

             return type;
        }


}
