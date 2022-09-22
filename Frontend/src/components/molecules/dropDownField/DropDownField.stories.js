import React from 'react';
import DropDownField from './DropDownField';
import { action } from '@storybook/addon-actions';

import 'semantic-ui-css/semantic.min.css';

export default {
  title: 'Platform/Molecules/DropDownField',
  component: DropDownField,
  argTypes: {
    labelWidth: {
      control: {
        type: 'select',
        options: [
          '1',
          '2',
          '3',
          '4',
          '5',
          '6',
          '7',
          '8',
          '9',
          '10',
          '11',
          '12',
          '13',
          '14',
          '15',
          '16',
        ],
      },
    },
    inputWidth: {
      control: {
        type: 'select',
        options: [
          '1',
          '2',
          '3',
          '4',
          '5',
          '6',
          '7',
          '8',
          '9',
          '10',
          '11',
          '12',
          '13',
          '14',
          '15',
          '16',
        ],
      },
    },
  },
};
const Template = ({ ...args }) => <DropDownField {...args} />;

export const dropDownRequired = Template.bind({});
dropDownRequired.args = {
  name: 'country',
  setValue: action('Set a new value'),
  trigger: action('Country'),
  value: '',
  className: '',
  labelIcon: '',
  labelWidth: '3',
  labelValue: 'Workflow type',
  labelColor: 'black',
  inputWidth: '12',
  openIconVariant: '',
  inputSize: 'mini',
  placeholder: 'Select one',
  onChange: action('On Change'),
  options: [
    { text: '1', value: '1' },
    { text: '2', value: '2' },
    { text: '3', value: '3' },
  ],
  required: true,
  labelSize: 'medium',
  compact: false,
  classNameFields: '',
  disabled: false,
  allowAdditions: true,
  search: true,
  clearable: true,
  readOnly: false,
};

export const dropDownNotRequired = Template.bind({});
dropDownNotRequired.args = {
  name: 'country',
  setValue: action('Set a new value'),
  trigger: action('Country'),
  value: '',
  className: '',
  labelIcon: '',
  labelWidth: '3',
  labelValue: 'Workflow type',
  labelColor: 'black',
  inputWidth: '10',
  inputSize: 'mini',
  openIconVariant: '',
  placeholder: 'Select one',
  onChange: action('On Change'),
  options: [
    { text: '1', value: '1' },
    { text: '2', value: '2' },
    { text: '3', value: '3' },
  ],
  labelSize: 'medium',
  compact: false,
  classNameFields: '',
  disabled: false,
  allowAdditions: true,
  search: true,
  clearable: true,
  readOnly: false,
};
