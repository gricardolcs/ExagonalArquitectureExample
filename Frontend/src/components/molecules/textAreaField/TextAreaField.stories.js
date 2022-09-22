import React from 'react';
import TextAreaField from './TextAreaField';
import BasicForm from '../../atoms/basicForm/BasicForm';
import { action } from '@storybook/addon-actions';

import 'semantic-ui-css/semantic.min.css';

export default {
  title: 'Platform/Molecules/TextAreaField',
  component: TextAreaField,
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

const Template = ({ ...args }) => <BasicForm>
  <TextAreaField {...args} />
</BasicForm>

export const textAreaField = Template.bind({});
textAreaField.args = {
  name: 'description',
  setValue: action('Set a new value'),
  trigger: action('TextArea'),
  value: '',
  className: '',
  labelVariant: '',
  labelIcon: '',
  labelWidth: '2',
  labelValue: 'Description',
  labelColor: 'black',
  inputWidth: '8',
  onChange: action('On Change'),
  placeholder: 'Some description',
  disabled: false,
  readOnly: false,
  maxLength: 500
}
