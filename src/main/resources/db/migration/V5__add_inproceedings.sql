INSERT INTO reference_types (name) VALUES ('inproceedings');

INSERT INTO attribute_types (name) VALUES ('booktitle');
INSERT INTO attribute_types (name) VALUES ('editor');
INSERT INTO attribute_types (name) VALUES ('series');
INSERT INTO attribute_types (name) VALUES ('address');
INSERT INTO attribute_types (name) VALUES ('organization');
INSERT INTO attribute_types (name) VALUES ('publisher');

INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES 
((select id from reference_types where name = 'inproceedings'), 
(select id from attribute_types where name = 'author'), 'true');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'inproceedings'),
(select id from attribute_types where name = 'title'), 'true');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'inproceedings'),
(select id from attribute_types where name = 'booktitle'), 'true');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'inproceedings'),
(select id from attribute_types where name = 'year'), 'true');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'inproceedings'),
(select id from attribute_types where name = 'editor'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'inproceedings'),
(select id from attribute_types where name = 'volume'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'inproceedings'),
(select id from attribute_types where name = 'number'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'inproceedings'),
(select id from attribute_types where name = 'series'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'inproceedings'),
(select id from attribute_types where name = 'pages'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'inproceedings'),
(select id from attribute_types where name = 'address'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'inproceedings'),
(select id from attribute_types where name = 'month'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'inproceedings'),
(select id from attribute_types where name = 'organization'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'inproceedings'),
(select id from attribute_types where name = 'publisher'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'inproceedings'),
(select id from attribute_types where name = 'note'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'inproceedings'),
(select id from attribute_types where name = 'key'), 'false');
