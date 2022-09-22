import React, { useState, useEffect } from 'react';
import ApplicantInformationTemplate from '../../components/templates/applicantInformationTemplate/ApplicantInformationTemplate';
import fetchAllApplicantShortProfile from '../../api/applicant/fetch/ApplicantFetchShortProfile';
import { ApplicantList } from '../../components/organisms/applicantList/ApplicantList';
import { SESSION_COMPONENT } from '../../utils/constants/constants';
import initPage from '../../helpers/page/initPage';
import { ApplicantsPaths } from '../../enum/availableRoutes';
import './style.css';

function ApplicantsListPage() {
  const [component, setComponent] = useState('');
  const [applicants, setApplicants] = useState([]);

  useEffect(() => {
    fetchAllApplicantShortProfile(setApplicants);
    initPage(
      {
        defaultPath: ApplicantsPaths.DEFAULT,
        variable: SESSION_COMPONENT,
      },
      setComponent
    );
  }, []);

  return (
    <>
      {component === ApplicantsPaths.DEFAULT && (
        <div className='container-applicant-list'>
          <ApplicantList data={applicants} setComponent={setComponent} />
        </div>
      )}
      {component === ApplicantsPaths.PROFILE && (
        <ApplicantInformationTemplate
          setComponent={setComponent}
          previousLocationName='Applicants'
        />
      )}
    </>
  );
}

export default ApplicantsListPage;
