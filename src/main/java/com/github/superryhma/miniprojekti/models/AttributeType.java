package com.github.superryhma.miniprojekti.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.github.superryhma.miniprojekti.jdbc.DBConnection;


public class AttributeType {

	protected int id;

	protected String name;


	public AttributeType() {
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



    public static AttributeType getById(int id){
        String query = "select * from Attribute_type where id = ?";

        DBConnection dbc = new DBConnection();
        Connection connection = dbc.getConnection();

        AttributeType type;

        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, id);

            ResultSet result = ps.executeQuery();

            if(result.next()){
                type = new AttributeType();
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
