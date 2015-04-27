package com.github.superryhma.miniprojekti.dao.impl.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.superryhma.miniprojekti.dao.ReferenceDAO;
import com.github.superryhma.miniprojekti.dao.impl.db.models.AttributeDb;
import com.github.superryhma.miniprojekti.dao.impl.db.models.AttributeType;
import com.github.superryhma.miniprojekti.dao.impl.db.models.Project;
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
	public List<Reference> getReferencesByName(String name) {
    	Dbc.open();
    	List<ProjectReference> r = ProjectReference.where("bibtextname like '" + name + "%'");
    	List<Reference> references = new ArrayList<Reference>();
        for (ProjectReference reference : r) {
            references.add(getReferenceById((Integer)reference.getId()));
        }
        Dbc.close();
    	return references;
	}

    @Override
    public Reference getReferenceById(int id) {
        Dbc.open();
        System.out.println(id);
        ProjectReference pr = ProjectReference.findFirst("id = ?", id);

        Set<Attribute> attributesSet = loadAttributes(pr);
        Set<String> tagsSet = loadTags(pr);
        Date dateCreated = new Date(pr.getTimestamp("created_at").getTime());
        Date dateUpdated = new Date(pr.getTimestamp("updated_at").getTime());
        
        Reference reference = new Reference(pr.parent(ReferenceTypeDb.class)
                .getString("name"), pr.getString("bibtextname"), dateCreated, dateUpdated, attributesSet, tagsSet);
        reference.setId((pr.getLongId()).intValue());
        Dbc.close();
        return reference;
    }
    
    private Set<Attribute> loadAttributes(ProjectReference pr){
        List<AttributeDb> attributesList = pr.getAll(AttributeDb.class);
        Set<Attribute> attributesSet = new HashSet<Attribute>();
        for (AttributeDb attribute : attributesList) {
            attributesSet.add(new Attribute(attribute.parent(
                    AttributeType.class).getString("name"), attribute
                    .getString("value")));
        }
        
        return attributesSet;
    }
        
    private Set<String> loadTags(ProjectReference pr){
        List<Tag> tagsList = pr.getAll(Tag.class);
        Set<String> tagsSet = new HashSet<String>();
        for (Tag tag : tagsList) {
            tagsSet.add(tag.getString("value"));
        }
        
        return tagsSet;
    }

    @Override
    public Reference addReference(Reference reference) {
        Dbc.open();
        ProjectReference r = new ProjectReference();
        insertReference(reference, r);
        insertAttributes(reference, r);
        insertTags(reference, r);
        reference.setId(((Long)r.getId()).intValue());
        Dbc.close();
        return reference;
    }

    @Override
    public Reference updateReference(int id, Reference reference) {
        Dbc.open();
        ProjectReference pr = ProjectReference.findFirst("id = ?", id);
        deleteTags(pr);
        deleteAttributes(pr);
        insertReference(reference, pr);
        insertTags(reference, pr);
        insertAttributes(reference, pr);
        Dbc.close();
        return reference;
    }

    @Override
    public boolean deleteReference(int id) {
        Dbc.open();
        ProjectReference pr = ProjectReference.findFirst("id = ?", id);
        Dbc.close();
        deleteTags(pr);
        deleteAttributes(pr);
        Dbc.open();
        pr.delete();
        Dbc.close();
        return true;
    }

    private void deleteAttributes(ProjectReference pr) {
        Dbc.open();
        List<AttributeDb> a = pr.getAll(AttributeDb.class);
        for (AttributeDb attribute : a) {
            attribute.delete();
        }
        Dbc.close();
    }

    private void deleteTags(ProjectReference pr) {
        Dbc.open();
        List<Tag> t = pr.getAll(Tag.class);
        for (Tag tag : t) {
            pr.remove(tag);
        }
        Dbc.close();
    }

    private void insertReference(Reference reference, ProjectReference pr) {
        Dbc.open();
        pr.set("bibtextname", reference.getBibtexName());
        pr.set("project", Project.findFirst("name = ?", "miniproject").getId());
        pr.set("reference_type_id", ReferenceTypeDb.findFirst("name = ?", reference.getType()).getId());
        pr.save();
        Dbc.close();
    }

    private void insertTags(Reference r, ProjectReference pr) {
        Dbc.open();
        Tag tag;
        for (String tagString : r.getTags()) {
            tag = Tag.create("value", tagString);
            pr.add(tag);
        }
        Dbc.close();
    }

    private void insertAttributes(Reference r, ProjectReference pr) {
        Dbc.open();
        AttributeDb a;
        AttributeType at;
        for (Attribute attribute : r.getAttributes()) {
            a = new AttributeDb();
            a.set("project_reference_id", pr.getId());
            a.set("value", attribute.getValue());
            at = AttributeType.findFirst("name = ?",
                    attribute.getAttributeType());
            at.add(a);
        }
        Dbc.close();
    }

}
