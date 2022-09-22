import React from 'react';
import TextField from './TextField';
import { action } from '@storybook/addon-actions';

import 'semantic-ui-css/semantic.min.css';

export default {
  title: 'Platform/Molecules/TextField',
  component: TextField,
  argTypes: {
    labelWidth: {
      control: {
        type: 'select',
        options: [
          '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16'
        ],
      },
    },
    inputWidth: {
      control: {
        type: 'select',
        options: [
          '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16'
        ],
      },
    }
  },
}

const Template = ({ ...args }) => <TextField {...args} />

export const requiredField = Template.bind({});
requiredField.args = {
  value: '',
  className: '',
  labelVariant: '',
  labelValue: 'Name',
  labelIcon: '',
  labelWidth: '2',
  labelColor: 'black',
  inputWidth: '10',
  onChange: action('On change'),
  placeholder: 'Bootcamp',
  required: true,
  readOnly: false,
  disabled: false,
  readOnly: false,
  imagePosition: 'right',
  inputSize: 'mini'
}

export const notRequiredField = Template.bind({});
notRequiredField.args = {
  value: '',
  className: '',
  labelVariant: '',
  labelValue: 'Name',
  labelIcon: '',
  labelWidth: '2',
  labelColor: 'black',
  inputWidth: '10',
  onChange: action('On change'),
  placeholder: 'Bootcamp',
  required: false,
  readOnly: false,
  disabled: false,
  readOnly: false,
  imagePosition: 'right',
  inputSize: 'mini'
}
