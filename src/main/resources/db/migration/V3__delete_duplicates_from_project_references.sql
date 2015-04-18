DELETE FROM reference_attributes
WHERE project_reference_id in
(SELECT y.id
FROM project_references y INNER JOIN
(SELECT
project, bibtextname, COUNT(*) AS CountOf
FROM project_references
GROUP BY project, bibtextname
HAVING COUNT(*)>1)
dt ON y.project=dt.project and y.bibtextname=dt.bibtextname);

DELETE FROM project_references_tags
WHERE project_reference_id in
(SELECT y.id
FROM project_references y INNER JOIN
(SELECT
project, bibtextname, COUNT(*) AS CountOf
FROM project_references
GROUP BY project, bibtextname
HAVING COUNT(*)>1)
dt ON y.project=dt.project and y.bibtextname=dt.bibtextname);

DELETE FROM tags
WHERE id not in
(SELECT tag_id
FROM project_references_tags);

DELETE FROM project_references
WHERE id in
(SELECT y.id
FROM project_references y INNER JOIN
(SELECT
project, bibtextname, COUNT(*) AS CountOf
FROM project_references
GROUP BY project, bibtextname
HAVING COUNT(*)>1)
dt ON y.project=dt.project and y.bibtextname=dt.bibtextname);