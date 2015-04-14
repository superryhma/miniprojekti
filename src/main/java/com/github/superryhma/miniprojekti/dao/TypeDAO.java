package com.github.superryhma.miniprojekti.dao;

import com.github.superryhma.miniprojekti.models.ReferenceType;

import java.util.Set;

public interface TypeDAO {
	Set<String> getRequiredFields(String type);

	Set<String> getOptionalFields(String type);

	Set<ReferenceType> getTypes();
}