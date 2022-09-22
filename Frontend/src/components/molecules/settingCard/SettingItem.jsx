import React from 'react';
import { SESSION_COMPONENT } from '../../../utils/constants/constants';

const SettingItem = ({ value, setComponent }) => {
  const { label, component } = value;

  const handleOnClick = () => {
    sessionStorage.setItem(SESSION_COMPONENT, component);
    setComponent(component);
  };

  return (
    <div className="settings-card-links" onClick={handleOnClick}>
      {label}
    </div>
  );
};

export default SettingItem;
