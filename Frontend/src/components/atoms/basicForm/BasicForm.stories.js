import React from 'react';
import BasicForm from './BasicForm';
import CustomButton from '../customButton/CustomButton';
import TextInput from '../textInput/TextInput';
import { action } from '@storybook/addon-actions';

import 'semantic-ui-css/semantic.min.css';

export default {
  title: 'Platform/Atoms/BasicForm',
  component: BasicForm,
}

const requiredChildren = <React.Fragment>
  <TextInput placeholder='Required bootcamp' required />
  <CustomButton label='Save' />
</React.Fragment>

const notRequiredChildren = <React.Fragment>
  <TextInput placeholder='No required bootcamp' />
  <CustomButton label='Save' />
</React.Fragment>

const Template = ({ children, args }) => <BasicForm {...args} >
  {children}
</BasicForm>

export const requiredForm = Template.bind({});
requiredForm.args = {
  checkForm: action('Check form'),
  handleOnSubmit: action('Handle On Submit'),
  children: requiredChildren,
  size: 'mini',
}

export const notRequiredForm = Template.bind({});
notRequiredForm.args = {
  checkForm: action('Check form'),
  handleOnSubmit: action('Handle On Submit'),
  children: notRequiredChildren,
  size: 'mini',
}
