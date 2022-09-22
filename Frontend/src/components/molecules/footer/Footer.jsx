import React from 'react';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import './style.css';
const { REACT_APP_VERSION } = process.env;

function Footer() {
  const actualVersion = REACT_APP_VERSION ? REACT_APP_VERSION : '';

  return (
    <div className="footer-container">
      <CustomLabel
        variant="applicant-version"
        size="large"
        value={'version: ' + actualVersion}
      />
    </div>
  );
}
export default Footer;
