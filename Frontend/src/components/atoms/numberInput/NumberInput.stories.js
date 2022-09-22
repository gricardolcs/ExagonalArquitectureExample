import React from 'react';
import { action } from '@storybook/addon-actions'
import 'semantic-ui-css/semantic.min.css';
import NumberInput from './NumberInput';

export default {
  title: 'Platform/Atoms/NumberInput',
  component: NumberInput
};

const Template = ({ children, ...args }) => <NumberInput { ...args }> {children} </NumberInput>

export const numberInput = Template.bind({});
numberInput.args = {
  name: 'count',
  setValue: action('Set a new value'),
  trigger: action('NumberInput'),
  value: '',
  min: '',
  size: '',
  className: '',
  onChange: () => { },
  placeholder: '',
  required: false,
  disabled: false,
  readOnly: false
}

export const numberInputWithValues = Template.bind({});
numberInputWithValues.args = {
  name: 'count',
  setValue: action('Set a new value'),
  trigger: action('NumberInput'),
  value: 50,
  min: 0,
  size: '',
  className: '',
  onChange: () => { },
  placeholder: '',
  required: false,
  disabled: false,
  readOnly: false
}