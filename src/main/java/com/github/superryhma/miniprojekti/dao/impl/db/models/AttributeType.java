package com.github.superryhma.miniprojekti.dao.impl.db.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Many2Many;

@Many2Many(other = ProjectReference.class, join = "reference_attributes", sourceFKName = "attribute_type_id", targetFKName = "project_reference_id")
public class AttributeType extends Model {
}
