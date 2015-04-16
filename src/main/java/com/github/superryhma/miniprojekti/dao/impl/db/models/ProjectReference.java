package com.github.superryhma.miniprojekti.dao.impl.db.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Many2Many;

@Many2Many(other = Tag.class, join = "project_references_tags", sourceFKName = "project_reference_id", targetFKName = "tag_id")
public class ProjectReference extends Model {}
