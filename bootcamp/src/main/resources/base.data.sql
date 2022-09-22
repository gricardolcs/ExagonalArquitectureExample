-- -- WORKFLOW SCHEMA
INSERT INTO workflow (id, name)
VALUES (1, 'Development workflow'),
       (2, 'Manual testing workflow'),
       (3, 'Automation testing workflow') ON CONFLICT
ON CONSTRAINT workflow_pkey DO NOTHING;

-- -- DEPARTMENT SCHEMA
INSERT INTO department (id, name, description)
VALUES (1, 'QA', 'this is the QA description'),
       (2, 'Development', 'this is the Development description'),
       (3, 'Automation', 'this is the Automation description'),
       (4, 'DevOps', 'this is the DevOps description') ON CONFLICT
ON CONSTRAINT department_pkey DO NOTHING;

-- -- PROJECT TYPE SCHEMA
INSERT INTO project_type (id, name)
VALUES (1, 'Bootcamp'),
       (2, 'Job Offer') ON CONFLICT
ON CONSTRAINT project_type_pkey DO NOTHING;

-- STATUSES
INSERT INTO status (id, name)
values (1, 'IN_PROGRESS'),
       (2, 'PASSED'),
       (3, 'FAILED'),
       (4, 'WITHDRAW'),
       (5, 'ON HOLD') ON CONFLICT
ON CONSTRAINT status_pkey DO NOTHING;

-- STAGES
INSERT INTO stage (id, name, stage_order, show_comments, workflow_id, is_english_type, is_contract_type)
VALUES (1, 'Initial', 1, true, 1, false, false),
       (2, 'Psychometric', 2, true, 1, false, false),
       (3, 'Technical', 3, true, 1, false, false),
       (4, 'English', 4, true, 1, true, false),
       (5, 'Human Development', 5, true, 1, false, false),
       (6, 'Contract', 6, true, 1, false, true) ON CONFLICT
ON CONSTRAINT stage_pkey DO NOTHING;

UPDATE stage SET name='Dev Test' WHERE id = 3;
UPDATE stage SET name='HD' WHERE id = 5;

--SCHEMA
INSERT INTO field (id, input_field_schema, stage_id)
VALUES (1, '{ "type": "NUMERIC_INPUT", "label": "Score:" }', 1),
       (2, '{ "type": "NUMERIC_INPUT", "label": "CA:" }', 2),
       (3, '{ "type": "NUMERIC_INPUT", "label": "Mixed Control:" }', 2),
       (4, '{ "type": "NUMERIC_INPUT", "label": "Score:" }', 3),
       (5, '{ "type": "NUMERIC_INPUT", "label": "Reading" }', 4),
       (6, '{ "type": "NUMERIC_INPUT", "label": "Listening" }', 4),
       (7, '{ "type": "NUMERIC_INPUT", "label": "Speaking" }', 4),
       (8, '{ "type": "NUMERIC_INPUT", "label": "Writing" }', 4),
       (9, '{ "type": "NUMERIC_INPUT", "label": "Score(AVG):" }', 4),
       (10, '{ "type": "DROPDOWN_INPUT", "label": "Recommended:", "elements": [ "YES", "NO" ]}', 5),
       (11, '{ "type": "UPLOAD_FILE_INPUT", "label": "Upload Report:" }', 5),
       (12, '{ "type": "DROPDOWN_INPUT", "label": "Status:", "elements": ["In progress","Signed", "On Hold","Declined"], "selectedElement": "In progress" }', 6);

