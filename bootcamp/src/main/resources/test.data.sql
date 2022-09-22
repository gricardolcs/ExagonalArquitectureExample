-- BOOTCAMP
INSERT INTO bootcamp (id, name, description, start_date, end_date, workflow_type, workflow_id,
                      department, department_id, participants_expected_quantity, project_type, project_type_id)
VALUES (-8501493239495665810, 'Dev Bootcamp Bolivia', 'First Bolivian Dev Bootcamp Bolivia', '2020-12-07',
        '2021-06-12', 1, 1, 1, 1, 30, 2, 2),
       (-6304490379095215818, 'Dev Bootcamp Columbia', 'First Bolivian Dev Bootcamp Columbia', '2020-12-07',
        '2021-06-12', 1, 1, 2, 2, 30, 1, 1) ON CONFLICT
ON CONSTRAINT bootcamp_pkey DO NOTHING;

UPDATE bootcamp
SET location = 'Latam'
WHERE location IS NULL;

-- BOOTCAMP APPLICANT
INSERT INTO bootcamp_applicant (bootcamp_id, applicant_id)
VALUES (-8501493239495665810, 1),
       (-6304490379095215818, 1);

-- APPLICANT WORKFLOW
INSERT INTO applicant_workflow (id, bootcamp_id, applicant_id)
VALUES (1, -8501493239495665810, 1),
       (2, -6304490379095215818, 1) ON CONFLICT
ON CONSTRAINT applicant_workflow_pkey DO NOTHING;


-- APPLICANT STAGE QUALIFICATION
INSERT INTO applicant_stage_qualification (id, comment, stage_id, applicant_workflow_id,
                                           qualification_status, applied_date, submit_date)
VALUES (1, 'Passed successfully', 1, 1, 'PASSED', '2020-12-07', '2021-01-07T06:30:00'),
       (2, 'Passed successfully', 2, 1, 'PASSED', '2021-01-08', '2021-02-08T08:35:00'),
       (3, 'Passed successfully', 3, 1, 'PASSED', '2021-02-09', '2021-03-09T07:07:00'),
       (4, 'Passed successfully', 4, 1, 'PASSED', '2021-03-10', '2021-04-10T08:35:00'),
       (5, 'Passed successfully', 5, 1, 'PASSED', '2021-04-11', '2021-05-11T10:22:00'),
       (6, 'Passed successfully', 6, 1, 'PASSED', '2021-05-12', '2021-06-12T11:32:00'),
       (7, 'Passed successfully', 1, 2, 'PASSED', '2020-12-07', '2021-01-07T09:44:00'),
       (8, 'Passed successfully', 2, 2, 'PASSED', '2021-01-08', '2021-02-08T06:30:00'),
       (9, 'Passed successfully', 3, 2, 'PASSED', '2021-02-09', '2021-03-09T08:38:00'),
       (10, 'Passed successfully', 4, 2, 'PASSED', '2021-03-10', '2021-04-10T07:30:00'),
       (11, 'Passed successfully', 5, 2, 'PASSED', '2021-04-11', '2021-05-11T08:50:00'),
       (12, 'Passed successfully', 6, 2, 'PASSED', '2021-05-12', '2021-06-12T10:30:00');

-- QUALIFICATION SCHEMA
INSERT INTO qualification_field (id, qualification_field_schema, applicant_stage_qualification_id)
VALUES (1, '{ "type": "NUMERIC_INPUT", "label": "Score:", "qualification": 5}', 1),
       (2, '{ "type": "NUMERIC_INPUT", "label": "CA:", "qualification": 6}', 2),
       (3, '{ "type": "NUMERIC_INPUT", "label": "Mixed Control:", "qualification": 5}', 2),
       (4, '{ "type": "NUMERIC_INPUT", "label": "Score:", "qualification": 56}', 3),
       (5, '{ "type": "NUMERIC_INPUT", "label": "Reading", "qualification": 56}', 4),
       (6, '{ "type": "NUMERIC_INPUT", "label": "Listening", "qualification": 56}', 4),
       (7, '{ "type": "NUMERIC_INPUT", "label": "Speaking", "qualification": 56}', 4),
       (8, '{ "type": "NUMERIC_INPUT", "label": "Writing", "qualification": 56}', 4),
       (9, '{ "type": "NUMERIC_INPUT", "label": "Score(AVG):", "qualification": 56}', 4),
       (10, '{ "type": "DROPDOWN_INPUT", "label": "Recommended:", "elements": [ "YES", "NO" ], "selectedElement": "YES"}', 5),
       (11, '{ "type": "UPLOAD_FILE_INPUT", "label": "Upload Report:"}', 5),
       (12, '{ "type": "DROPDOWN_INPUT", "label": "Status:", "elements": [ "Signed", "On Hold", "Declined" ], "selectedElement": "Signed"}', 6),
       (13, '{ "type": "NUMERIC_INPUT", "label": "Score:", "qualification": 6}', 7),
       (14, '{ "type": "NUMERIC_INPUT", "label": "CA:", "qualification": 7}', 8),
       (15, '{ "type": "NUMERIC_INPUT", "label": "Mixed Control:", "qualification": 6}', 8),
       (16, '{ "type": "NUMERIC_INPUT", "label": "Score:", "qualification": 60}', 9),
       (17, '{ "type": "NUMERIC_INPUT", "label": "Reading", "qualification": 60}', 10),
       (18, '{ "type": "NUMERIC_INPUT", "label": "Listening", "qualification": 60}', 10),
       (19, '{ "type": "NUMERIC_INPUT", "label": "Speaking", "qualification": 60}', 10),
       (20, '{ "type": "NUMERIC_INPUT", "label": "Writing", "qualification": 60}', 10),
       (21, '{ "type": "NUMERIC_INPUT", "label": "Score(AVG):", "qualification": 60}', 10),
       (22, '{ "type": "DROPDOWN_INPUT", "label": "Recommended:", "elements": [ "YES", "NO" ], "selectedElement": "YES"}', 11),
       (23, '{ "type": "UPLOAD_FILE_INPUT", "label": "Upload Report:"}', 11),
       (24, '{ "type": "DROPDOWN_INPUT", "label": "Status:", "elements": [ "Signed", "On Hold", "Declined" ], "selectedElement": "On Hold"}', 12);