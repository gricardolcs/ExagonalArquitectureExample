import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { TableOrganismDeprecated } from '../../organisms/table/TableOrganismDeprecated';
import { Loading } from '../../atoms/loading/Loading';
import ApplicantsProfileTemplate from '../../../components/templates/applicantsProfileTemplate/ApplicantsProfileTemplate';
import ApplicantProfileFetch from '../../../api/applicant/fetch/ApplicantFetchById';
import applicantStages from './ApplicantStages';
import GetPaginationIntervalFormat from '../../../utils/paginationUtils/PaginationUtils';

function ApplicantsListTemplate({
  tableData,
  tableRowAttributes,
  tableHeaders,
  totalPages,
  activePage,
  numberApplicantsOption,
  numberOfApplicantsToShow,
  setNumberOfApplicantsToShow,
  setActivePage,
  totalElements,
  bootcampStages,
  handleOpenStageModal,
}) {
  const [profile, setProfile] = useState({});
  const [openProfile, setOpenProfile] = useState(false);
  const handleCloseModalProfile = () => setOpenProfile(false);

  const basic = 'very';
  const paginationInterval = GetPaginationIntervalFormat.getPaginationIntervalFormat(
    numberOfApplicantsToShow,
    totalElements,
    activePage
  );

  const handleProfile = (id) => {
    handleFetchApplicant(id);
    setOpenProfile(true);
  };

  async function handleFetchApplicant(profileId) {
    const response = await ApplicantProfileFetch.fetchApplicantById(profileId);
    if (response) {
      setProfile(response);
    }
  }

  const textAlign = 'center';
  const labelValue = 'Show applicants:';

  if (tableData === undefined) {
    return <Loading></Loading>;
  }

  const buildApplicants = applicantStages.buildApplicantStages(
    tableData.content,
    bootcampStages,
    handleProfile,
    handleOpenStageModal
  );

  return (
    <>
      <TableOrganismDeprecated
        tableHeaders={tableHeaders}
        textAlign={textAlign}
        textAlignHeaders={textAlign}
        labelValue={labelValue}
        basic={basic}
        tableRowAttributes={tableRowAttributes}
        tableData={buildApplicants}
        totalPages={totalPages}
        activePage={activePage}
        setNumberOfApplicantsToShow={setNumberOfApplicantsToShow}
        setActivePage={setActivePage}
        numberApplicantsOption={numberApplicantsOption}
        paginationInterval={paginationInterval}
      />
      <ApplicantsProfileTemplate
        handleClose={handleCloseModalProfile}
        openModal={openProfile}
        applicant={profile}
      />
    </>
  );
}

ApplicantsListTemplate.prototype = {
  tableData: PropTypes.array,
  tableRowAttributes: PropTypes.array,
  totalPages: PropTypes.number,
  activePage: PropTypes.number,
  numberApplicantsOption: PropTypes.array,
  setNumberOfApplicantsToShow: PropTypes.func,
  setActivePage: PropTypes.func,
  paginationInterval: PropTypes.string,
};

ApplicantsListTemplate.defaultProps = {
  tableRowAttributes: [],
  totalPages: 1,
  activePage: 1,
  numberApplicantsOption: [],
  setNumberOfApplicantsToShow: () => {},
  setActivePage: () => {},
  paginationInterval: '',
};

export default ApplicantsListTemplate;
