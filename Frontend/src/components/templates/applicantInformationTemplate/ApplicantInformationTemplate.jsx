import { useEffect, useState, useCallback } from 'react';
import PropTypes from 'prop-types';
import CreateApplicantQualification from '../../../api/applicant/createApplicantQualification/CreateApplicantQualification';
import { fetchApplicantById } from '../../../api/applicant/fetch/ApplicantFetchById';
import { fetchAllApplicantWorkflowsByApplicantId } from '../../../api/applicant/fetchApplicantWorkflow/FetchAllApplicantWorkflowsByApplicantId';
import { fetchAllApplicantBootcampsByApplicantId } from '../../../api/applicant/fetchApplicantBootcamps/FetchAllApplicantBootcampsByApplicantId';
import { uploadFile } from '../../../api/applicant/upload/ReportFileUpload';
import { fetchApplicantReportById } from '../../../api/applicant/download/ApplicantReportByApplicant';
import { statusFetch } from '../../../api/status/fetch/StatusFetch';
import ApplicantInformationOrganism from '../../organisms/applicantInformationOrganism/ApplicantInformationOrganism';
import moment from 'moment';
import { SESSION_COMPONENT } from '../../../utils/constants/constants';
import { ApplicantsPaths, BootcampsPaths } from '../../../enum/availableRoutes';
import './style.css';

function ApplicantInformationTemplate({ setComponent, previousLocationName }) {
  const [applicant, setApplicant] = useState({});
  const [applicantWorkflows, setApplicantWorkflows] = useState([]);
  const [englishQualification, setEnglishQualification] = useState({});
  const [bootcamps, setBootcamps] = useState([]);
  const [statuses, setStatuses] = useState([]);

  const handlePreviuosBootcampProfilePage = useCallback(() => {
    const component = sessionStorage.getItem(SESSION_COMPONENT);
    if (component.includes(BootcampsPaths.DEFAULT)) {
      const projectId = sessionStorage.getItem('currentProjectId');
      if (projectId !== null) {
        sessionStorage.setItem(SESSION_COMPONENT, BootcampsPaths.PROFILE);
        setComponent(BootcampsPaths.PROFILE);
      } else {
        sessionStorage.setItem(SESSION_COMPONENT, BootcampsPaths.DEFAULT);
        setComponent(BootcampsPaths.DEFAULT);
      }
    } else {
      sessionStorage.setItem(SESSION_COMPONENT, ApplicantsPaths.DEFAULT);
      setComponent(ApplicantsPaths.DEFAULT);
    }
  }, [setComponent]);

  const handleOnSubmitAnApplicantQualification = async (
    bootcampId,
    stagedId,
    data
  ) => {
    const applicantId = sessionStorage.getItem('applicantId');
    if (applicantId) {
      return await CreateApplicantQualification.create(
        bootcampId,
        stagedId,
        applicantId,
        data
      ).then((response) => {
        fetchAllApplicantWorkflowsByApplicantId(
          applicantId,
          setApplicantWorkflows
        );
        return response;
      });
    } else {
      handlePreviuosBootcampProfilePage();
    }
  };

  const updateApplicant = (applicant) => {
    setApplicant(applicant);
    fetchAllApplicantWorkflowsByApplicantId(
      applicant.id,
      setApplicantWorkflows
    );
  };

  const handleOnDownloadFile = (bootcampId, callback) => {
    const applicantId = sessionStorage.getItem('applicantId');
    fetchApplicantReportById(applicantId, bootcampId, callback);
  };

  const handleOnUploadFile = (file, bootcampId) => {
    const applicantId = sessionStorage.getItem('applicantId');
    uploadFile(applicantId, bootcampId, file);
  };

  useEffect(() => {
    // TODO(cristal.flores): This is a temporary logic, so the idea is that when a project is selected
    // we can filter the applicant workflows and obtain the results of the applicant's English stage by Bootcamp id.
    const applicantWorkflowsWithEnglishStage = applicantWorkflows.filter(
      (workflow) =>
        workflow.applicantQualifications.filter(
          (stage) => stage.isEnglishType && stage.submitDate !== null
        ).length > 0
    );
    if (applicantWorkflowsWithEnglishStage.length > 0) {
      applicantWorkflowsWithEnglishStage.sort(
        (firstApplicantWorkflow, secondApplicantWorkflow) => {
          const firstEnglishResultList = firstApplicantWorkflow.applicantQualifications.filter(
            (applicantQualification) => applicantQualification.isEnglishType
          );
          const secondEnglishResultList = secondApplicantWorkflow.applicantQualifications.filter(
            (applicantQualification) => applicantQualification.isEnglishType
          );
          return (
            moment(firstEnglishResultList[0].submitDate).valueOf() -
            moment(secondEnglishResultList[0].submitDate).valueOf()
          );
        }
      );
      const lastSubmitedEnglishResult = applicantWorkflowsWithEnglishStage[
        applicantWorkflowsWithEnglishStage.length - 1
      ].applicantQualifications.filter(
        (applicantQualification) => applicantQualification.isEnglishType
      );
      setEnglishQualification(lastSubmitedEnglishResult[0]);
    }
  }, [applicantWorkflows]);

  useEffect(() => {
    statusFetch(setStatuses);
    const applicantId = sessionStorage.getItem('applicantId');
    if (applicantId) {
      fetchApplicantById(applicantId, updateApplicant);
      fetchAllApplicantBootcampsByApplicantId(applicantId, setBootcamps);
      sessionStorage.setItem('applicantsTab', 'applicantInformation');
    } else {
      handlePreviuosBootcampProfilePage();
    }
    return () => {
      setApplicantWorkflows([]);
      sessionStorage.setItem('applicantsTab', 'applicantProjectsMenu');
    };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <>
      {Object.keys(applicant).length !== 0 && (
        <ApplicantInformationOrganism
          applicant={applicant}
          englishStageQualification={englishQualification}
          handleOnClickPrevious={handlePreviuosBootcampProfilePage}
          applicantBootcamps={bootcamps}
          applicantWorkflows={applicantWorkflows}
          statuses={statuses}
          onSubmitAnApplicantQualification={
            handleOnSubmitAnApplicantQualification
          }
          onSubmitFileReport={handleOnUploadFile}
          onDownloadFile={handleOnDownloadFile}
          previousLocationName={previousLocationName}
        />
      )}
    </>
  );
}

ApplicantInformationTemplate.prototype = {
  setComponent: PropTypes.func,
  previousLocationName: PropTypes.string,
};

ApplicantInformationTemplate.defaultProps = {
  setComponent: () => {},
  previousLocationName: 'Applicants',
};

export default ApplicantInformationTemplate;
