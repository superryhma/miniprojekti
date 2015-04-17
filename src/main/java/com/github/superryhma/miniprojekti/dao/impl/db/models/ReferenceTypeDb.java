package com.github.superryhma.miniprojekti.dao.impl.db.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Many2Many;
import org.javalite.activejdbc.annotations.Table;

@Table("reference_types")
@Many2Many(other = AttributeType.class, join = "reference_types_attribute_types", sourceFKName = "reference_type_id", targetFKName = "attribute_type_id")
public class ReferenceTypeDb extends Model {
}
