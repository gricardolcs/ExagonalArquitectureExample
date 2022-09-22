import React from 'react';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import CustomIcon from '../../atoms/customIcon/CustomIcon';
import PropTypes from 'prop-types';
import { doNothing } from '../../../utils/constants/constants';

const SettingTemplate = ({ onReturn, previousLocationName, children }) => {
  return (
    <div className="setup-page-template">
      <div className="setup-page-template-header">
        <CustomLabel
          elementType="p"
          size="big"
          handleOnClick={onReturn}
          color="blue"
        >
          <CustomIcon name="chevron left" color="blue" size="small" />
          {previousLocationName}
        </CustomLabel>
      </div>
      <div className="setup-page-template-body">{children}</div>
    </div>
  );
};

SettingTemplate.propTypes = {
  onReturn: PropTypes.func,
  previousLocationName: PropTypes.string,
  children: PropTypes.any,
};

SettingTemplate.defaultProps = {
  onReturn: doNothing,
  previousLocationName: '',
};

export default SettingTemplate;
