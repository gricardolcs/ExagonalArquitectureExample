import React from 'react';
import 'semantic-ui-css/semantic.min.css';
import { action } from '@storybook/addon-actions';
import SettingCard from './SettingCard';

const settings = {
  title: 'Platform/Molecules/SettingCard',
  component: SettingCard,
};
export default settings;

const Template = ({ children, ...args }) => <SettingCard {...args} />;

export const settingCard = Template.bind({});
settingCard.args = {
  value: {
    title: 'Setting tittle',
    description: 'This is the description',
  },
  onClick: action('Settings Card Clicked'),
};
