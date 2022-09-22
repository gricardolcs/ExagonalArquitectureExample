import React from 'react';
import BasicCard from '../../atoms/customCard/BasicCard';
import CardContent from '../../atoms/customCard/CardContent';
import CardHeader from '../../atoms/customCard/CardHeader';
import CardDescription from '../../atoms/customCard/CardDescription';
import { doNothing } from '../../../utils/constants/constants';
import PropTypes from 'prop-types';
import './style.css';
import SettingItem from './SettingItem';

const defaultSettings = { title: '', description: '', color: '' };

const SettingCard = ({ value, setComponent }) => {
  const { title, items } = value;

  const renderItems = items
    ? items.map((item, key) => (
        <SettingItem key={key} value={item} setComponent={setComponent} />
      ))
    : [];

  return (
    <BasicCard className="settings-card">
      <div className="settings-card-decoration" />
      <CardContent>
        <CardHeader textAlign="left">{title}</CardHeader>
        <CardDescription textAlign="left">{renderItems}</CardDescription>
      </CardContent>
    </BasicCard>
  );
};

SettingCard.propTypes = {
  value: PropTypes.object,
  onClick: PropTypes.func,
};

SettingCard.defaultProps = {
  value: defaultSettings,
  onclick: doNothing,
};

export default SettingCard;
