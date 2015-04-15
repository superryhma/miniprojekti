INSERT INTO Reference_type (name) VALUES ('article');

INSERT INTO Attribute_type (name) VALUES ('author');
INSERT INTO Attribute_type (name) VALUES ('title');
INSERT INTO Attribute_type (name) VALUES ('journal');
INSERT INTO Attribute_type (name) VALUES ('year');
INSERT INTO Attribute_type (name) VALUES ('volume');
INSERT INTO Attribute_type (name) VALUES ('number');
INSERT INTO Attribute_type (name) VALUES ('pages');
INSERT INTO Attribute_type (name) VALUES ('month');
INSERT INTO Attribute_type (name) VALUES ('note');
INSERT INTO Attribute_type (name) VALUES ('key');

INSERT INTO Users (username, digestive) VALUES ('miniprojectuser','digestive');
INSERT INTO Project (name, owner) VALUES ('miniproject',1);

INSERT INTO Dependency (reference_type, attribute_type, required) VALUES 
((select id from Reference_type where name = 'article'), 
(select id from Attribute_type where name = 'author'), 'true');
INSERT INTO Dependency (reference_type, attribute_type, required) VALUES
((select id from Reference_type where name = 'article'),
(select id from Attribute_type where name = 'title'), 'true');
INSERT INTO Dependency (reference_type, attribute_type, required) VALUES
((select id from Reference_type where name = 'article'),
(select id from Attribute_type where name = 'journal'), 'true');
INSERT INTO Dependency (reference_type, attribute_type, required) VALUES
((select id from Reference_type where name = 'article'),
(select id from Attribute_type where name = 'year'), 'true');
INSERT INTO Dependency (reference_type, attribute_type, required) VALUES
((select id from Reference_type where name = 'article'),
(select id from Attribute_type where name = 'volume'), 'true');
INSERT INTO Dependency (reference_type, attribute_type, required) VALUES
((select id from Reference_type where name = 'article'),
(select id from Attribute_type where name = 'number'), 'false');
INSERT INTO Dependency (reference_type, attribute_type, required) VALUES
((select id from Reference_type where name = 'article'),
(select id from Attribute_type where name = 'pages'), 'false');
INSERT INTO Dependency (reference_type, attribute_type, required) VALUES
((select id from Reference_type where name = 'article'),
(select id from Attribute_type where name = 'month'), 'false');
INSERT INTO Dependency (reference_type, attribute_type, required) VALUES
((select id from Reference_type where name = 'article'),
(select id from Attribute_type where name = 'note'), 'false');
INSERT INTO Dependency (reference_type, attribute_type, required) VALUES
((select id from Reference_type where name = 'article'),
(select id from Attribute_type where name = 'key'), 'false');
