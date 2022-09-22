import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import BasicModal from '../../atoms/basicModal/BasicModal';
import TextAreaField from '../../molecules/textAreaField/TextAreaField';
import CustomButton from '../../atoms/customButton/CustomButton';
import BasicRadio from '../../atoms/basicRadio/BasicRadio';
import { Form, Grid } from 'semantic-ui-react';
import qualificationSchemaFactory from './qualificationSchema/QualificationSchemaFactory';
import qualificationSchemaGraphicProperties from './QualificationSchemaGraphicProperties';
import EnumQualificationStatus from './EnumQualificationStatus';
import BasicImage from '../../atoms/basicImage/BasicImage';
import readOnlyImage from '../../../images/atoms/basicImage/readOnly.png';
import './style.css';

function ApplicantsStagesModalTemplate({
  handleClose = () => {},
  handleSave = () => {},
  openForm = () => {},
  bootcampQualificationSchema = {},
  stageName = '',
  applicantQualificationResults = {},
}) {
  const [qualification, setQualification] = useState();
  const [isQualified, setIsQualified] = useState();
  const [comment, setComment] = useState();

  useEffect(() => {
    if (applicantQualificationResults.applicantQualification) {
      setQualification(
        applicantQualificationResults.applicantQualification.qualification
          ? applicantQualificationResults.applicantQualification.qualification
          : ''
      );
    }
    setIsQualified(
      applicantQualificationResults.qualificationStatus
        ? applicantQualificationResults.qualificationStatus
        : EnumQualificationStatus.FAILED
    );
    setComment(
      applicantQualificationResults.comment
        ? applicantQualificationResults.comment
        : ''
    );
  }, [applicantQualificationResults]);

  const qualificationSchema = qualificationSchemaFactory.getBySchemaType(
    bootcampQualificationSchema.type
  );
  qualificationSchema.deserializeSchema(
    bootcampQualificationSchema,
    applicantQualificationResults
  );

  function handleSubmit() {
    const serializedSchema = qualificationSchema.qualifyAndSerializeSchema(
      qualification
    );
    handleSave({
      schema: serializedSchema,
      comment: comment,
      qualificationStatus: isQualified,
      qualificationType: bootcampQualificationSchema.type,
    });
    handleClose();
  }

  function onQualify(inputValue) {
    setQualification(inputValue);
  }

  function handleOnRadioButtonChange(e, { value }) {
    setIsQualified(value);
  }

  const schemaGraphicProperties = qualificationSchemaGraphicProperties.getGraphicPropertiesBySchema(
    bootcampQualificationSchema
  );
  function renderQualificationSchema() {
    return qualificationSchema.render({
      graphicProperties: {
        ...schemaGraphicProperties,
        textBoxClassName: 'stage-input',
        disabled: isReadOnly,
      },
      onChange: onQualify,
    });
  }

  const isReadOnly = isQualified === 'QUALIFIED_AND_READONLY';

  return (
    <BasicModal
      modalVariant={schemaGraphicProperties.modalClass}
      classNameHeader="stage-modal-header"
      open={openForm}
      header={<h1>{stageName}</h1>}
      onClose={handleClose}
      displayCloseIcon
    >
      <Grid stackable relaxed>
        {renderQualificationSchema()}
        <Grid.Row columns={2}>
          <Grid.Column width={11}>
            <CustomField>
              <TextAreaField
                value={comment}
                variantTextArea="stage-text-area"
                variantLabel="stage-label stage-comments-label"
                inputWidth="16"
                labelWidth="10"
                labelValue="Comments"
                onChange={setComment}
                placeholder=""
                variantLabelTextArea="internal-padding"
                disabled={isReadOnly}
              />
            </CustomField>
          </Grid.Column>
          <Grid.Column verticalAlign="bottom" width={5}>
            <Grid.Row>
              <BasicRadio
                label="Qualified"
                name="radioGroup"
                value={EnumQualificationStatus.PASSED}
                checked={
                  EnumQualificationStatus.PASSED === isQualified ||
                  EnumQualificationStatus.QUALIFIED_AND_READONLY === isQualified
                }
                variant="stage-checkbox qualified"
                onChange={handleOnRadioButtonChange}
                disabled={isReadOnly}
              />
            </Grid.Row>
            <Grid.Row>
              <BasicRadio
                label="Disqualified"
                name="radioGroup"
                value={EnumQualificationStatus.FAILED}
                checked={EnumQualificationStatus.FAILED === isQualified}
                variant="stage-checkbox disqualified"
                onChange={handleOnRadioButtonChange}
                disabled={isReadOnly}
              />
            </Grid.Row>
          </Grid.Column>
        </Grid.Row>
        {!isReadOnly ? (
          <Grid.Row columns={2} textAlign="center" className="button-container">
            <Grid.Column textAlign="right" className="button-section">
              <CustomButton
                onClick={handleSubmit}
                label="Save"
                type="Submit"
                color="blue"
                variant="stage-button"
              />
            </Grid.Column>
            <Grid.Column textAlign="left" className="button-section">
              <CustomButton
                label="Cancel"
                type="Button"
                variant="stage-button"
                onClick={handleClose}
              />
            </Grid.Column>
          </Grid.Row>
        ) : (
          <Grid.Row className="internal-padding message-section">
            <BasicImage
              src={readOnlyImage}
              size="small"
              className="message-image"
            />
            <p className="message-text">Read only </p>
          </Grid.Row>
        )}
      </Grid>
    </BasicModal>
  );
}

ApplicantsStagesModalTemplate.prototype = {
  handleClose: PropTypes.func,
  handleSave: PropTypes.func,
  openForm: PropTypes.func,
  qualificationSchema: PropTypes.object,
  applicantQualificationResults: PropTypes.object,
  stageName: PropTypes.string,
};

export default ApplicantsStagesModalTemplate;
