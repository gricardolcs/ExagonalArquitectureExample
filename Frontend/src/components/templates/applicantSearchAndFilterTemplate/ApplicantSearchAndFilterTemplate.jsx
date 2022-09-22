import React from "react";
import PropTypes from 'prop-types';
import ApplicantSearchAndFilterOrganism from "../../organisms/applicantSearchAndFilteringOrganism/ApplicantSearchAndFilteringOrganism";
import './style.css';

function ApplicantSearchAndFilterTemplate({
  handleOnSearch,
  handleOnClickOnStage,
  stages
}) {

  return (
    <ApplicantSearchAndFilterOrganism
      stages={stages}
      handleOnClickOnStage={handleOnClickOnStage}
      handleOnSearch={handleOnSearch}
    />
  );
};

ApplicantSearchAndFilterTemplate.prototype = {
  handleOnSearch: PropTypes.func,
  handleOnClickOnStage: PropTypes.func,
  stages: PropTypes.arrayOf(PropTypes.number)
}

ApplicantSearchAndFilterTemplate.defaultProps = {
  handleOnSearch: () => { },
  handleOnClickOnStage: () => { },
  stages: []
}

export default ApplicantSearchAndFilterTemplate;
