INSERT INTO reference_types (name) VALUES ('article');

INSERT INTO attribute_types (name) VALUES ('author');
INSERT INTO attribute_types (name) VALUES ('title');
INSERT INTO attribute_types (name) VALUES ('journal');
INSERT INTO attribute_types (name) VALUES ('year');
INSERT INTO attribute_types (name) VALUES ('volume');
INSERT INTO attribute_types (name) VALUES ('number');
INSERT INTO attribute_types (name) VALUES ('pages');
INSERT INTO attribute_types (name) VALUES ('month');
INSERT INTO attribute_types (name) VALUES ('note');
INSERT INTO attribute_types (name) VALUES ('key');

INSERT INTO users (username, digestive) VALUES ('miniprojectuser','digestive');
INSERT INTO projects (name, owner) VALUES ('miniproject',1);

INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES 
((select id from reference_types where name = 'article'), 
(select id from attribute_types where name = 'author'), 'true');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'article'),
(select id from attribute_types where name = 'title'), 'true');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'article'),
(select id from attribute_types where name = 'journal'), 'true');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'article'),
(select id from attribute_types where name = 'year'), 'true');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'article'),
(select id from attribute_types where name = 'volume'), 'true');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'article'),
(select id from attribute_types where name = 'number'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'article'),
(select id from attribute_types where name = 'pages'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'article'),
(select id from attribute_types where name = 'month'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'article'),
(select id from attribute_types where name = 'note'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'article'),
(select id from attribute_types where name = 'key'), 'false');
