CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    username TEXT NOT NULL,
    digestive TEXT NOT NULL
);
 
CREATE TABLE projects(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    owner INTEGER NOT NULL REFERENCES users(id)
);
 
CREATE TABLE reference_types(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);
 
CREATE TABLE attribute_types(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);
 
CREATE TABLE reference_types_attribute_types(
    reference_type_id INTEGER REFERENCES reference_types(id),
    attribute_type_id INTEGER REFERENCES attribute_types(id),
    required BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (reference_type_id, attribute_type_id)
);
 
CREATE TABLE project_references(
    id SERIAL PRIMARY KEY,
    reference_type_id INTEGER NOT NULL REFERENCES reference_types(id),
    project INTEGER NOT NULL REFERENCES projects(id),
    bibtextname TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);
 
CREATE TABLE reference_attributes(
	id SERIAL PRIMARY KEY,
    project_reference_id INTEGER NOT NULL REFERENCES project_references(id),
    attribute_type_id INTEGER NOT NULL REFERENCES attribute_types(id),
    value TEXT NOT NULL
);
 
CREATE TABLE tags(
    id SERIAL PRIMARY KEY,
    value TEXT NOT NULL
);

CREATE TABLE project_references_tags(
	id SERIAL PRIMARY KEY,
    project_reference_id INTEGER REFERENCES project_references(id),
    tag_id INTEGER REFERENCES tags(id)
);
