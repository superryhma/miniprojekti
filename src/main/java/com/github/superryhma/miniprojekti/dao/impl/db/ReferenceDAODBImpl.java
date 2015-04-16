package com.github.superryhma.miniprojekti.dao.impl.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.superryhma.miniprojekti.dao.ReferenceDAO;
import com.github.superryhma.miniprojekti.dao.impl.db.models.AttributeDb;
import com.github.superryhma.miniprojekti.dao.impl.db.models.AttributeType;
import com.github.superryhma.miniprojekti.dao.impl.db.models.ProjectReference;
import com.github.superryhma.miniprojekti.dao.impl.db.models.ReferenceTypeDb;
import com.github.superryhma.miniprojekti.dao.impl.db.models.Tag;
import com.github.superryhma.miniprojekti.dbc.Dbc;
import com.github.superryhma.miniprojekti.models.Attribute;
import com.github.superryhma.miniprojekti.models.Reference;

public class ReferenceDAODBImpl implements ReferenceDAO {

	@Override
	public List<Reference> getReferences() {
		Dbc.open();
		List<ProjectReference> r = ProjectReference.findAll();
		List<Reference> references = new ArrayList<Reference>();
		for (ProjectReference reference : r) {
			references.add(getReferenceById(reference.getInteger("id")));
		}
		
		Dbc.close();
		return references;
	}

	@Override
	public Reference getReferenceById(int id) {
		Dbc.open();
		ProjectReference pr = ProjectReference.findFirst("id = ?", id);
		List<AttributeDb> attributesList = pr.getAll(AttributeDb.class);
		Set<Attribute> attributesSet = new HashSet<Attribute>();
		for (AttributeDb attribute : attributesList) {
			attributesSet.add(new Attribute(attribute.parent(AttributeType.class).getString("name"), attribute.getString("value")));
		}
		List<Tag> tagsList = pr.getAll(Tag.class);
		Set<String> tagsSet = new HashSet<String>();
		for (Tag tag : tagsList) {
			tagsSet.add(tag.getString("value"));
		}
		Reference reference = new Reference(pr.parent(ReferenceTypeDb.class).getString("name"),
				pr.getString("bibtexname"),
				new Date(pr.getTimestamp("created_at").getTime()),
				new Date(pr.getTimestamp("created_at").getTime()),
				attributesSet,
				tagsSet
				);
		Dbc.close();
		return reference;
	}

	@Override
	public Reference addReference(Reference reference) {
		// TODO Auto-generated method stub
		return null;
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
