import React from 'react';
import 'semantic-ui-css/semantic.min.css';
import NumberField from './NumberField';
import { action } from '@storybook/addon-actions';

export default {
  title: 'Platform/Molecules/NumberField',
  component: NumberField,
};

const Template = ({ children, ...args }) => (
  <NumberField {...args}> {children} </NumberField>
);

export const numberField = Template.bind({});
numberField.args = {
  name: 'participants',
  setValue: action('Set a new value'),
  trigger: action('Number'),
  value: '',
  min: '',
  className: '',
  labelVariant: '',
  labelValue: '',
  labelIcon: '',
  labelWidth: '5',
  labelColor: 'black',
  image: '',
  imagePosition: 'right',
  inputWidth: '11',
  inputSize: 'mini',
  errorNumber: '',
  onChange: () => {},
  placeholder: '',
  required: false,
  disabled: false,
  readOnly: false,
};

export const numberFieldWithValue = Template.bind({});
numberFieldWithValue.args = {
  name: 'participants',
  setValue: action('Set a new value'),
  trigger: action('Number'),
  value: 50,
  min: 0,
  className: '',
  labelVariant: '',
  labelValue: 'Score',
  labelIcon: '',
  labelWidth: '5',
  labelColor: 'black',
  image: '',
  imagePosition: 'right',
  inputWidth: '11',
  inputSize: 'mini',
  errorNumber: '',
  onChange: () => {},
  placeholder: '',
  required: false,
  disabled: false,
  readOnly: false,
};
