SELECT
  e.id AS id,
  e.name AS name,
  e.joined_date AS joined_date,
  e.department_id AS department_id,
  d.name AS department_name
FROM employee AS e
JOIN department AS d
  ON e.department_id = d.id
WHERE e.name LIKE /*name*/'%田%'