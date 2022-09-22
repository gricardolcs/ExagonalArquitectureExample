import React from 'react';
import BasicRadio from './BasicRadio';
import 'semantic-ui-css/semantic.min.css';
import { action } from '@storybook/addon-actions';

export default {
  title: 'Platform/Atoms/BasicRadio',
  component: BasicRadio,
}

const Template = ({ children, ...args }) =>
  <BasicRadio
    {...args}
  >
    {children}
  </BasicRadio>

export const basicRadioUnChecked = Template.bind({});
basicRadioUnChecked.args = {
  label: 'Select this one',
  name: 'radioGroup',
  value: 'SomeValue',
  variant: '',
  checked: false,
  onChange: action('on Change'),
  disabled: false,
  readOnly: false
}

export const basicRadioChecked = Template.bind({});
basicRadioChecked.args = {
  style: {},
  label: 'Select this one',
  name: 'radioGroup',
  value: 'SomeValue',
  variant: '',
  checked: true,
  onChange: action('on Change'),
  disabled: false,
  readOnly: false
}
