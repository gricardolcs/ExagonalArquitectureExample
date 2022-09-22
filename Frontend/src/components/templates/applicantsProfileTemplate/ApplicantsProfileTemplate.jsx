import React from "react";
import PropTypes from 'prop-types';
import BasicModal from "../../atoms/basicModal/BasicModal";
import ApplicantsProfile from "../../organisms/applicantsProfile/ApplicantsProfile";
import './style.css';

function ApplicantsProfileTemplate({ handleClose, openModal, applicant }) {
  return (
    <BasicModal
      displayCloseIcon
      classNameHeader="styleHeaderModal"
      open={openModal}
      header="Applicant"
      size="tiny"
      onClose={handleClose}
    >
      <ApplicantsProfile 
        applicant={applicant}  
      />
    </BasicModal>
  );
}

ApplicantsProfileTemplate.prototype = {
  handleClose: PropTypes.func,
  openModal: PropTypes.func,
  applicant: PropTypes.shape({
    id: PropTypes.string,
    name: PropTypes.string,
    email: PropTypes.string,
    photo: PropTypes.string,
    telephone: PropTypes.string,
    birthday: PropTypes.string,
    age: PropTypes.string,
    city: PropTypes.string,
    career: PropTypes.string,
    degree: PropTypes.string,
  })
};

ApplicantsProfileTemplate.defaultProps = {
  handleClose: () => {},
  openModal: () => {},
  applicant: {
    id: '',
    name: '',
    email: '',
    photo: '',
    telephone: '',
    birthday: '',
    age: '',
    city: '',
    career: '',
    degree: '',
  }
};

export default ApplicantsProfileTemplate;
