import React, { useState, useEffect, useCallback } from 'react';
import { Grid } from 'semantic-ui-react';
import PropTypes from 'prop-types';
import BasicImage from '../../atoms/basicImage/BasicImage';
import iconDownloadStorage from '../../../images/icons/icon-download.png';
import EnumQualificationStatus from './helpers/EnumQualificationStatus';
import DateUtils from '../../../utils/getValue/getValueOrCurrentDate';
import StringCasesUtils from '../../../utils/stringCases/StringCasesUtils';
import EnumSchemaInputType from '../../organisms/stageFormOrganism/EnumSchemaInputType';
import { downloadFileUtils } from '../../../utils/downloadFileUtils/DownloadFileUtils';
import './style.css';

/**
 * Component to show evaluation stage information.
 * @author Cristhian Ortiz
 * @property {object} stage - stage information to render
 */
function StageInformation({ stage, handleOnFile }) {
  const [file, setFile] = useState({});

  const setFileIfAFieldOfTypeUploadFileInputAppears = useCallback(() => {
    if (stage.applicantQualification) {
      const fields = stage.applicantQualification.filter(
        (item) => item.type === EnumSchemaInputType.UPLOAD_FILE_INPUT
      );
      if (fields.length > 0) {
        handleOnFile((file) => {
          setFile(file);
        });
      }
    }
  }, [handleOnFile, stage.applicantQualification]);

  const getStatus = (status) => {
    let result = '';
    if (
      status === EnumQualificationStatus.QUALIFIED_AND_READONLY ||
      status === EnumQualificationStatus.PASSED
    ) {
      result = EnumQualificationStatus.PASSED;
    } else {
      result = status;
    }
    return result.replace(/_/g, ' ');
  };

  const renderCustomFields = () => {
    return (
      stage.applicantQualification && (
        <Grid.Row>
          {stage.applicantQualification.map((applicantQualification) => {
            switch (applicantQualification.type) {
              case 'DROPDOWN_INPUT':
                return (
                  <Grid.Column key={`field-${applicantQualification.label}`}>
                    {applicantQualification.label}&nbsp;
                    <span className='subtitle'>
                      {applicantQualification.selectedElement}&nbsp;
                    </span>
                  </Grid.Column>
                );
              case 'UPLOAD_FILE_INPUT':
                return (
                  <Grid.Column key={`field-${applicantQualification.label}`}>
                    {applicantQualification.label}&nbsp;
                    <span className='subtitle'>
                      {applicantQualification.fileName}&nbsp;
                    </span>
                    {applicantQualification.fileName ? (
                      <BasicImage
                        src={iconDownloadStorage}
                        className='icon-download'
                        onClick={() => downloadFileUtils(file)}
                      />
                    ) : (
                      <span className='no-file'>No file</span>
                    )}
                  </Grid.Column>
                );
              default:
                return (
                  <Grid.Column key={`field-${applicantQualification.label}`}>
                    {applicantQualification.label}&nbsp;
                    <span className='subtitle'>
                      {applicantQualification.qualification}&nbsp;
                    </span>
                  </Grid.Column>
                );
            }
          })}
        </Grid.Row>
      )
    );
  };

  useEffect(() => {
    setFileIfAFieldOfTypeUploadFileInputAppears();
  }, [stage, setFileIfAFieldOfTypeUploadFileInputAppears]);

  return (
    <Grid columns={3} width={12}>
      {stage !== {} && renderCustomFields()}
      <Grid.Row>
        {stage.stageName !== 'Contract' && (
          <Grid.Column>
            Status:&nbsp;
            <span className='subtitle'>
              {stage.qualificationStatus
                ? StringCasesUtils.firstLetterUpperCaseWithSpaceAsSeparation(
                    getStatus(stage.qualificationStatus)
                  )
                : ''}
            </span>
          </Grid.Column>
        )}
        <Grid.Column>
          Applied date:&nbsp;
          <span className='subtitle'>{stage.appliedDate}</span>
        </Grid.Column>
        <Grid.Column>
          Submit date:&nbsp;
          <span className='subtitle'>
            {DateUtils.getValueOrCurrentDate(stage.submitDate)}
          </span>
        </Grid.Column>
      </Grid.Row>
      <Grid.Row>
        <Grid.Column>
          Comments:&nbsp;
          <br />
          <span className='subtitle'>{stage.comment}</span>
        </Grid.Column>
      </Grid.Row>
    </Grid>
  );
}

StageInformation.prototype = {
  stage: PropTypes.object,
  handleOnFile: PropTypes.func,
};

StageInformation.defaultProps = {
  stage: {},
  handleOnFile: () => {},
};

export default StageInformation;
