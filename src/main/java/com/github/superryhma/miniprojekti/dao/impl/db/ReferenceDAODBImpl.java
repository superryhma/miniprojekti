package com.github.superryhma.miniprojekti.dao.impl.db;

import com.github.superryhma.miniprojekti.dao.ReferenceDAO;
import com.github.superryhma.miniprojekti.dao.impl.db.models.*;
import com.github.superryhma.miniprojekti.dbc.Dbc;
import com.github.superryhma.miniprojekti.models.Attribute;
import com.github.superryhma.miniprojekti.models.Reference;

import java.util.*;

public class ReferenceDAODBImpl implements ReferenceDAO {

    @Override
    public List<Reference> getReferences() {
        Dbc.open();
        List<ProjectReference> projectReferences = ProjectReference.findAll();
        List<Reference> references = new ArrayList<>();
        for (ProjectReference reference : projectReferences) {
            references.add(getReferenceById(reference.getInteger("id")));
        }
        Dbc.close();
        return references;
    }

    @Override
    public List<Reference> getReferencesByName(String name) {
        Dbc.open();
        List<ProjectReference> projectReferences = ProjectReference
                .where("bibtextname like '" + name + "%'");
        List<Reference> references = new ArrayList<Reference>();
        for (ProjectReference reference : projectReferences) {
            references.add(getReferenceById((Integer) reference.getId()));
        }
        Dbc.close();
        return references;
    }

    @Override
    public Reference getReferenceById(int id) {
        Dbc.open();
        ProjectReference pr = ProjectReference.findFirst("id = ?", id);

        Set<Attribute> attributesSet = loadAttributes(pr);
        Set<String> tagsSet = loadTags(pr);
        String name = pr.parent(ReferenceTypeDb.class).getString("name");
        String bibtexName = pr.getString("bibtextname");
        Date dateCreated = new Date(pr.getTimestamp("created_at").getTime());
        Date dateUpdated = new Date(pr.getTimestamp("updated_at").getTime());

        Reference reference = new Reference(name, bibtexName, dateCreated,
                dateUpdated, attributesSet, tagsSet);
        reference.setId((pr.getLongId()).intValue());
        Dbc.close();
        return reference;
    }

    @Override
    public Reference addReference(Reference reference) {
        try {
            Dbc.open();
            Dbc.openTransaction();
            ProjectReference r = new ProjectReference();
            insertReference(reference, r);
            insertAttributes(reference, r);
            insertTags(reference, r);
            reference.setId(r.getLongId().intValue());
            Dbc.commitTransaction();
        } catch (Exception e) {
            Dbc.rollbackTransaction();
            Dbc.close();
            throw e;
        }
        Dbc.close();
        return reference;
    }

    @Override
    public Reference updateReference(int id, Reference reference) {
        try {
            Dbc.open();
            Dbc.openTransaction();
            ProjectReference pr = ProjectReference.findFirst("id = ?", id);
            deleteTags(pr);
            deleteAttributes(pr);
            insertReference(reference, pr);
            insertTags(reference, pr);
            insertAttributes(reference, pr);
            Dbc.commitTransaction();
        } catch (Exception e) {
            Dbc.rollbackTransaction();
            Dbc.close();
            throw e;
        }
        Dbc.close();
        return reference;
    }

    @Override
    public boolean deleteReference(int id) {
        Dbc.open();
        ProjectReference pr = ProjectReference.findFirst("id = ?", id);
        deleteTags(pr);
        deleteAttributes(pr);
        pr.delete();
        Dbc.close();
        return true;
    }

    private void insertReference(Reference reference, ProjectReference pr) {
        pr.set("bibtextname", reference.getBibtexName());
        pr.set("project", Project.findFirst("name = ?", "miniproject").getId());
        pr.set("reference_type_id",
                ReferenceTypeDb.findFirst("name = ?", reference.getType())
                        .getId());
        pr.save();
    }

    private void insertTags(Reference r, ProjectReference pr) {
        Tag tag;
        for (String tagString : r.getTags()) {
            tag = Tag.create("value", tagString);
            pr.add(tag);
        }
    }

    private void insertAttributes(Reference r, ProjectReference pr) {
        AttributeDb attributeDb;
        AttributeType attributeType;
        for (Attribute attribute : r.getAttributes()) {
            attributeDb = new AttributeDb();
            attributeDb.set("project_reference_id", pr.getId());
            attributeDb.set("value", attribute.getValue());
            attributeType = AttributeType.findFirst("name = ?",
                    attribute.getAttributeType());
            attributeType.add(attributeDb);
        }
    }

    private Set<Attribute> loadAttributes(ProjectReference pr) {
        List<AttributeDb> attributes = pr.getAll(AttributeDb.class);
        Set<Attribute> attributeSet = new HashSet<>();
        for (AttributeDb attribute : attributes) {
            String name = attribute.parent(AttributeType.class).getString(
                    "name");
            String value = attribute.getString("value");
            attributeSet.add(new Attribute(name, value));
        }
        return attributeSet;
    }

    private Set<String> loadTags(ProjectReference pr) {
        List<Tag> tags = pr.getAll(Tag.class);
        Set<String> tagSet = new HashSet<>();
        for (Tag tag : tags) {
            tagSet.add(tag.getString("value"));
        }

        return tagSet;
    }

    private void deleteAttributes(ProjectReference pr) {
        List<AttributeDb> attributes = pr.getAll(AttributeDb.class);
        for (AttributeDb attribute : attributes) {
            attribute.delete();
        }
    }

    private void deleteTags(ProjectReference pr) {
        List<Tag> tags = pr.getAll(Tag.class);
        for (Tag tag : tags) {
            pr.remove(tag);
        }
    }
}
