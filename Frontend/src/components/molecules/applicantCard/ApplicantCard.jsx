import React, { useCallback } from 'react';
import PropTypes from 'prop-types';
import { CustomCard } from '../customCard/CustomCard';
import BasicImage from '../../atoms/basicImage/BasicImage';
import iconUserAlternate from '../../../images/icons/icon-user-alternate.png';
import { downloadFileUtils } from '../../../utils/downloadFileUtils/DownloadFileUtils';
import { SESSION_COMPONENT } from '../../../utils/constants/constants';
import { ApplicantsPaths, BootcampsPaths } from '../../../enum/availableRoutes';
import './style.css';

export function ApplicantCard({
  applicant = {},
  setComponent = () => {},
  setOpenBootcampsEnrollment = () => {},
}) {
  const handleOnClickCard = useCallback(() => {
    sessionStorage.setItem('applicantId', applicant.id);
    const component = sessionStorage.getItem(SESSION_COMPONENT);
    if (component.includes(ApplicantsPaths.DEFAULT)) {
      sessionStorage.setItem(SESSION_COMPONENT, ApplicantsPaths.PROFILE);
      setComponent(ApplicantsPaths.PROFILE);
    } else {
      sessionStorage.setItem(
        SESSION_COMPONENT,
        BootcampsPaths.APPLICANT_PROFILE
      );
      setComponent(BootcampsPaths.APPLICANT_PROFILE);
    }
  }, [applicant, setComponent]);

  return (
    <CustomCard
      cardImage={
        <BasicImage
          className="basicImageCard"
          src={applicant.image ? applicant.image : iconUserAlternate}
          circular
        />
      }
      onClickCard={handleOnClickCard}
      handleOnClickImage={handleOnClickCard}
      cardHeader={applicant.fullName}
      cardMeta={applicant.email}
      cardDescription={[
        { key: 'applicant-telephone', render: `${applicant.telephone}` },
        {
          key: 'applicant-city',
          render: `${applicant.city} - ${applicant.country}`,
        },
        { key: 'applicant-career', render: `${applicant.career}` },
      ]}
      dropDownOptions={[
        {
          text: 'Add Applicant',
          handleOnClick: () => {
            sessionStorage.setItem('applicantId', applicant.id);
            setOpenBootcampsEnrollment(true);
          },
        },
        { text: 'Remove from' },
      ]}
      cardDownloadContent={{
        fileName: applicant.curriculum.fileName,
        report: applicant.curriculum.curriculumVitae,
      }}
      onClickDownload={downloadFileUtils}
    />
  );
}

ApplicantCard.prototype = {
  applicant: PropTypes.object,
  setComponent: PropTypes.func,
};

export default ApplicantCard;
