import React, { useState, useEffect, useCallback } from 'react';
import PropTypes from 'prop-types';
import { Grid, Header } from 'semantic-ui-react';
import applicantDefaultPhoto from '../../../images/icons/applicant-default-photo.png';
import CustomSideBar from '../../atoms/customSideBar/CustomSideBar';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import CustomIcon from '../../atoms/customIcon/CustomIcon';
import BasicImage from '../../atoms/basicImage/BasicImage';
import ApplicantEnglishInformation from '../applicantEnglishInformation/ApplicantEnglishInformation';
import CustomButton from '../../atoms/customButton/CustomButton';
import BasicLabel from '../../atoms/basicLabel/BasicLabel';
import ApplicantStageCard from '../../molecules/applicantStageCard/ApplicantStageCard';
import CustomMessage from '../../atoms/customMessage/CustomMessage';
import './style.css';

function ApplicantInformationOrganism({
  applicant,
  englishStageQualification,
  applicantBootcamps,
  applicantWorkflows,
  statuses,
  handleOnClickPrevious,
  onSubmitAnApplicantQualification,
  onSubmitFileReport,
  onDownloadFile,
  previousLocationName,
}) {
  const [englishResults, setEnglishResults] = useState([]);
  const message =
    'Here you will find the Projects the Applicant is actually applying.';
  const noBootcampMessage =
    'The Applicant is a candidate of no recruitment process.';

  const getApplicantWorkflowByBootcampId = (bootcampId) => {
    const foundApplicantWorkflow = applicantWorkflows.find(
      (applicantWorkflow) => applicantWorkflow.bootcampId === bootcampId
    );
    return foundApplicantWorkflow;
  };

  const loadEnglishResult = useCallback(() => {
    // TODO(cristal.flores): This is a temporary logic, so the idea is that when a project is selected
    // we can filter the applicant workflows and obtain the results of the applicant's English stage by Bootcamp id.
    if (englishStageQualification.applicantQualification) {
      const results = englishStageQualification.applicantQualification.map(
        (item) => {
          return { label: item.label, value: item.qualification };
        }
      );
      setEnglishResults(
        results.filter((item) => !item.label.includes('Score'))
      );
    }
  }, [englishStageQualification.applicantQualification]);

  useEffect(() => {
    loadEnglishResult();
  }, [englishStageQualification, loadEnglishResult]);

  return (
    <CustomSideBar
      sideBarContent={
        <Grid>
          <Grid.Row className='previous-label-row'>
            <Grid.Column width={16}>
              <CustomLabel
                elementType='p'
                size='big'
                handleOnClick={handleOnClickPrevious}
                variant='label-sidebar'
                color='blue'
              >
                <CustomIcon name='chevron left' color='blue' size='small' />
                {previousLocationName}
              </CustomLabel>
            </Grid.Column>
          </Grid.Row>
          <Grid.Row className='image-center'>
            {applicant.photo === '' ? (
              <BasicImage
                className='applicant-image'
                verticalAlign='middle'
                size='small'
                bordered
                centered
                circular
                src={applicantDefaultPhoto}
              />
            ) : (
              <BasicImage
                className='applicant-image'
                verticalAlign='middle'
                size='small'
                centered
                circular
                bordered
                src={applicant.photo}
              />
            )}
          </Grid.Row>

          <div className='applicant-information'>
            {applicant.fullName && (
              <Grid.Row className='applicant-name-row'>
                <Grid.Column width={16} className='applicant-name-column'>
                  <div>
                    <Header
                      className='applicant-name'
                      as='h1'
                      content={applicant.fullName}
                    />
                    <p className='applicant-email'>{applicant.email}</p>
                  </div>
                </Grid.Column>
              </Grid.Row>
            )}
            <Grid.Row className='applicant-information-row'>
              <Grid.Column width={16} className='custom-column'>
                <div>
                  <div className='applicant-info'>
                    <span className='bold'>Cel:</span> {applicant.telephone}
                  </div>
                  <div className='applicant-info'>
                    <span className='bold'>Country:</span> {applicant.country} -{' '}
                    {applicant.city}
                  </div>
                  <div className='applicant-info'>
                    <span className='bold'>Career:</span> {applicant.career}
                  </div>
                </div>
              </Grid.Column>
            </Grid.Row>
            <Grid.Row className='custom-birthday-age-row'>
              <Grid.Column width={16} className='custom-column'>
                <div className='applicant-info'>
                  <span className='bold'>Degree:</span> {applicant.degree}
                </div>
                <div className='applicant-info'>
                  <span className='bold'>Birthday:</span> {applicant.birthday}
                </div>
                <div className='applicant-info'>
                  <span className='bold'>Age:</span> {applicant.age}
                </div>
              </Grid.Column>
            </Grid.Row>
            <Grid.Row>
              <Grid.Column width={16}>
                <ApplicantEnglishInformation englishResults={englishResults} />
              </Grid.Column>
            </Grid.Row>
          </div>
        </Grid>
      }
      bodyContent={
        <div className='container'>
          <Grid.Row className='row-section'>
            <h1 className='title-stage'>Projects</h1>
            <Grid.Column floated='left' width={5} className='optionSection'>
              <CustomButton
                label={applicantBootcamps.length}
                color='orange'
                size='mini'
                variant='buttonStyle'
              />
            </Grid.Column>
          </Grid.Row>
          <BasicLabel children={message} />
          <div>
            <div className='bootcamp-cards-container'>
              {applicantBootcamps &&
              applicantBootcamps.length > 0 &&
              applicantWorkflows.length > 0 ? (
                applicantBootcamps.map((item) => {
                  return (
                    <ApplicantStageCard
                      key={`applicant-stage-card-${item.name}`}
                      project={item}
                      applicantWorkflow={getApplicantWorkflowByBootcampId(
                        item.id
                      )}
                      statuses={statuses}
                      onSubmitAnApplicantQualification={
                        onSubmitAnApplicantQualification
                      }
                      onSubmitFileReport={onSubmitFileReport}
                      onDownloadFile={onDownloadFile}
                    />
                  );
                })
              ) : (
                <CustomMessage
                  iconName='eye slash outline'
                  message={noBootcampMessage}
                  color='red'
                  size='large'
                />
              )}
            </div>
          </div>
        </div>
      }
    />
  );
}

ApplicantInformationOrganism.prototype = {
  applicant: PropTypes.object,
  englishStageQualification: PropTypes.object,
  applicantBootcamps: PropTypes.array,
  applicantWorkflows: PropTypes.array,
  statuses: PropTypes.array,
  handleOnClickPrevious: PropTypes.func,
  onSubmitAnApplicantQualification: PropTypes.func,
  onSubmitFileReport: PropTypes.func,
  onDownloadFile: PropTypes.func,
  previousLocationName: PropTypes.string,
};

ApplicantInformationOrganism.defaultProps = {
  applicant: {},
  englishStageQualification: {},
  applicantBootcamps: [],
  applicantWorkflows: [],
  statuses: [],
  handleOnClickPrevious: () => {},
  onSubmitAnApplicantQualification: () => {},
  onSubmitFileReport: () => {},
  onDownloadFile: () => {},
  previousLocationName: 'Go back',
};

export default ApplicantInformationOrganism;
