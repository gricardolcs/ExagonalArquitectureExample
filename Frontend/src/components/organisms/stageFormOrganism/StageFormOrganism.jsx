import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { Grid } from 'semantic-ui-react';
import TextAreaField from '../../molecules/textAreaField/TextAreaField';
import { getStageFields } from './StageFields';
import GetValueUtils from '../../../utils/getValue/getValueOrEmpty';
import DateUtils from '../../../utils/getValue/getValueOrCurrentDate';
import CustomButton from '../../atoms/customButton/CustomButton';
import DateField from '../../molecules/dateField/DateField';
import EnumQualificationStatus from '../stagePreviewOrganism/EnumQualificationStatus';
import DropDownField from '../../molecules/dropDownField/DropDownField';
import { dropdownProps } from './CommonProps';
import StringCasesUtils from '../../../utils/stringCases/StringCasesUtils';
import EnumSchemaInputType from './EnumSchemaInputType';
import getAllowedReportFileTypes from '../../../utils/fileTypes/reportFileTypes';
import CustomField from '../../atoms/customField/CustomField';
import './style.css';

function StageFormOrganism({
  schema = [],
  appliedDate = '',
  submitDate = '',
  qualificationStatus = '',
  comment = '',
  statuses = [],
  onCancel = () => {},
  onSubmit = () => {},
  stageName = '',
  onSubmitFileReport = () => {},
}) {
  const [editedSchema, setEditedSchema] = useState(schema);
  const [editedAppliedDate, setEditedAppliedDate] = useState(appliedDate);
  const [editedSubmitDate, setEditedSubmitDate] = useState(submitDate);
  const [editedQualificationStatus, setEditedQualificationStatus] = useState(
    qualificationStatus
  );
  const [editedComment, setEditedComment] = useState(comment);
  const [isValidAllQualifications, setIsValidAllQualifications] = useState(
    true
  );
  const [isValidReportType, setIsValidReportType] = useState(true);
  const COMMMENT_INPUT_MAX_LENGTH = 1000;
  const MIN_SCORE_QUALIFICATION = 0;

  const onChangeSchema = (label, value, elements) => {
    const index = editedSchema.findIndex((item) => item.label === label);
    editedSchema[index].qualification = value;
    editedSchema[index].errorMessageQualification =
      editedSchema[index].qualification < 0
        ? 'The value must be a positive number'
        : '';
    if (editedSchema[index].type === EnumSchemaInputType.UPLOAD_FILE_INPUT) {
      editedSchema[index].fileName = value;
      editedSchema[index].errorMessageFile = !isValidReportFileType(
        editedSchema[index].fileName
      )
        ? 'File type not supported yet'
        : '';
    }
    editedSchema[index].elements = elements;
    editedSchema[index].selectedElement = value;
    handleContractStageQualification(
      label,
      editedSchema[index].selectedElement
    );
    setEditedSchema([...editedSchema]);
  };

  const handleContractStageQualification = (label, selectedElement) => {
    if (label === 'Status:') {
      switch (selectedElement) {
        case 'Declined':
          setEditedQualificationStatus(EnumQualificationStatus.FAILED);
          break;
        // TODO(cristal.flores): Will need to implement a case process to Draft feature
        case 'In progress':
        case 'On Hold':
          setEditedQualificationStatus(EnumQualificationStatus.IN_PROGRESS);
          break;
        case 'Signed':
          setEditedQualificationStatus(EnumQualificationStatus.PASSED);
          break;
        default:
          break;
      }
    }
  };

  const getMinDate = () => {
    const applicantsAppliedDate = new Date(appliedDate);
    const currentDate = new Date();
    if (applicantsAppliedDate >= currentDate) {
      return appliedDate;
    }
    return currentDate.toLocaleString('en-CA').split(',')[0];
  };

  const getOptions = () => {
    if (statuses) {
      const options = statuses.map((element) => {
        const statusNameFormatCorrect = StringCasesUtils.firstLetterUpperCaseWithSpaceAsSeparation(
          element.name
        );
        return {
          key: element.id,
          text: statusNameFormatCorrect,
          value: statusNameFormatCorrect,
        };
      });
      return options;
    } else {
      return [];
    }
  };

  const handleOnSubmit = () => {
    if (
      appliedDate !== editedAppliedDate ||
      submitDate !== editedSubmitDate ||
      qualificationStatus !== editedQualificationStatus ||
      comment !== editedComment ||
      schema !== editedSchema
    ) {
      onSubmit({
        schema: editedSchema,
        comment: editedComment,
        qualificationStatus: editedQualificationStatus
          ? StringCasesUtils.allUpperCaseWithUnderscoreAsSeparation(
              editedQualificationStatus
            )
          : EnumQualificationStatus.IN_PROGRESS,
        appliedDate: editedAppliedDate,
        submitDate: editedSubmitDate,
      });
    }
  };

  const isValidReportFileType = (file) => {
    const allowedReportFileTypes = getAllowedReportFileTypes();
    const currentFileType = file.substr(file.lastIndexOf('.') + 1);
    const result = allowedReportFileTypes.includes(
      currentFileType.toLowerCase()
    );
    setIsValidReportType(result);
    return result;
  };

  useEffect(() => {
    setIsValidAllQualifications(
      !editedSchema.find(
        (schema) => schema.qualification < MIN_SCORE_QUALIFICATION
      )
    );
  }, [editedSchema]);

  return (
    <Grid>
      <Grid.Row columns={3} className="inputsTopRow">
        {schema.map((item) => (
          <Grid.Column
            key={`form-field-${item.label}`}
            width={5}
            style={{ display: 'flex' }}
          >
            {getStageFields(
              item,
              item.type,
              onChangeSchema,
              onSubmitFileReport
            )}
          </Grid.Column>
        ))}
      </Grid.Row>
      <Grid.Row
        className={` ${stageName === 'Contract' ? 'inputsMidRow' : ''}`}
      >
        {stageName !== 'Contract' && (
          <Grid.Column width={4} style={{ paddingRight: 0 }}>
            <CustomField variant="field-section">
              <DropDownField
                value={StringCasesUtils.firstLetterUpperCaseWithSpaceAsSeparation(
                  editedQualificationStatus
                )}
                {...dropdownProps}
                labelValue="Status:"
                placeholder="Select one"
                selection
                options={getOptions()}
                onChange={setEditedQualificationStatus}
                allowAdditions={false}
              />
            </CustomField>
          </Grid.Column>
        )}
        <Grid.Column width={5} style={{ padding: 0 }}>
          <CustomField variant="field-section">
            <DateField
              labelValue="Applied Date:"
              className="textBoxClassName"
              labelWidth="8"
              inputWidth="8"
              value={GetValueUtils.getValueOrEmpty(editedAppliedDate)}
              onChange={setEditedAppliedDate}
              readOnly
            />
          </CustomField>
        </Grid.Column>
        <Grid.Column width={5} style={{ padding: 0 }}>
          <CustomField variant="field-section">
            <DateField
              labelValue="Submit Date:"
              className="textBoxClassName"
              labelWidth="8"
              inputWidth="8"
              minDate={getMinDate()}
              value={DateUtils.getValueOrCurrentDate(editedSubmitDate)}
              onChange={setEditedSubmitDate}
              readOnly
            />
          </CustomField>
        </Grid.Column>
      </Grid.Row>
      <Grid.Row width={16}>
        <CustomField variant="commentsSection">
          <TextAreaField
            value={GetValueUtils.getValueOrEmpty(editedComment)}
            onChange={setEditedComment}
            variantTextArea="stage-text-area"
            inputWidth="16"
            labelWidth="16"
            labelValue="Comments:"
            placeholder=""
            rows={10}
            maxLength={COMMMENT_INPUT_MAX_LENGTH}
          />
        </CustomField>
      </Grid.Row>
      <Grid.Row>
        <Grid.Column textAlign="right">
          <CustomButton circular={false} onClick={onCancel} label="Cancel" />
          <CustomButton
            variant="button-submit"
            label="Submit"
            circular={false}
            onClick={handleOnSubmit}
            disabled={!isValidAllQualifications || !isValidReportType}
          />
        </Grid.Column>
      </Grid.Row>
    </Grid>
  );
}

StageFormOrganism.prototype = {
  schema: PropTypes.array,
  appliedDate: PropTypes.string,
  submitDate: PropTypes.string,
  qualificationStatus: PropTypes.string,
  comment: PropTypes.string,
  statuses: PropTypes.array,
  onCancel: PropTypes.func,
  onSubmit: PropTypes.func,
  stageName: PropTypes.string,
  onSubmitFileReport: PropTypes.func,
};

export default StageFormOrganism;
