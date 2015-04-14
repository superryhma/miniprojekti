CREATE TABLE Users(
    id SERIAL PRIMARY KEY,
    username TEXT NOT NULL,
    digestive TEXT NOT NULL
);
 
CREATE TABLE Project(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    owner INTEGER NOT NULL REFERENCES Users(id)
);
 
CREATE TABLE Reference_type(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);
 
CREATE TABLE Attribute_type(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);
 
CREATE TABLE Dependency(
    reference_type INTEGER REFERENCES Reference_type(id),
    attribute_type INTEGER REFERENCES Attribute_type(id),
    required BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (reference_type, attribute_type)
);
 
CREATE TABLE Reference(
    id SERIAL PRIMARY KEY,
    reference_type INTEGER NOT NULL REFERENCES Reference_type(id),
    project INTEGER NOT NULL REFERENCES Project(id),
    bibtextname TEXT NOT NULL
);
 
CREATE TABLE Attribute(
    id SERIAL PRIMARY KEY,
    reference INTEGER NOT NULL REFERENCES Reference(id),
    attribute_type INTEGER NOT NULL REFERENCES Attribute_type(id),
    value TEXT NOT NULL
);
 
CREATE TABLE Tag(
    id SERIAL PRIMARY KEY,
    reference INTEGER REFERENCES Reference(id),
    value TEXT NOT NULL
);
