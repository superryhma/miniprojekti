INSERT INTO reference_types (name) VALUES ('book');

INSERT INTO attribute_types (name) VALUES ('edition');

INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES 
((select id from reference_types where name = 'book'), 
(select id from attribute_types where name = 'author'), 'true');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'book'),
(select id from attribute_types where name = 'title'), 'true');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'book'),
(select id from attribute_types where name = 'publisher'), 'true');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'book'),
(select id from attribute_types where name = 'year'), 'true');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'book'),
(select id from attribute_types where name = 'volume'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'book'),
(select id from attribute_types where name = 'number'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'book'),
(select id from attribute_types where name = 'series'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'book'),
(select id from attribute_types where name = 'address'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'book'),
(select id from attribute_types where name = 'edition'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'book'),
(select id from attribute_types where name = 'month'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'book'),
(select id from attribute_types where name = 'note'), 'false');
INSERT INTO reference_types_attribute_types (reference_type_id, attribute_type_id, required) VALUES
((select id from reference_types where name = 'book'),
(select id from attribute_types where name = 'key'), 'false');
