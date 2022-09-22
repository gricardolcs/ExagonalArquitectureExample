import React from 'react';
import CreateAndUpdateForm from './CreateAndUpdateApplicantForm';
import { action } from '@storybook/addon-actions';

import 'semantic-ui-css/semantic.min.css';

export default {
  title: 'Platform/Organisms/CreateAndUpdateApplicantForm',
  component: CreateAndUpdateForm,
};

const Template = ({ ...args }) => <CreateAndUpdateForm {...args} />;

export const createAndUpdateApplicantForm = Template.bind({});
createAndUpdateApplicantForm.args = {
  onClose: action('handle close'),
  onOpen: action('handle open'),
  open: true,
  onSubmit: action('Submited'),
  currentApplicant: {},
  dropDownValues: {
    Afghanistan: [
      'Herat',
      'Kabul',
      'Kandahar',
      'Molah',
      'Rana',
      'Shar',
      'Sharif',
      'Wazir Akbar Khan',
    ],
    Albania: [
      'Elbasan',
      'Petran',
      'Pogradec',
      'Shkoder',
      'Tirana',
      'Ura Vajgurore',
    ],
  },
  fields: [
    {
      type: 'input',
      label: 'Name',
      propertyName: 'name',
      labelColor: 'black',
      labelWidth: '5',
      placeholder: 'Name...',
      sizeInput: 'large',
      className: 'inputCreateForm',
      labelVariant: 'labelCreateForm',
      inputWidth: '11',
      required: true,
      fieldErrors: [
        {
          required: {
            value: true,
          },
          minLength: {
            value: 6,
          },
        },
      ],
    },
    {
      type: 'input',
      label: 'Last Name',
      propertyName: 'lastName',
      labelColor: 'black',
      labelWidth: '5',
      placeholder: 'Last Name...',
      sizeInput: 'large',
      className: 'inputCreateForm',
      labelVariant: 'labelCreateForm',
      inputWidth: '11',
      required: true,
      fieldErrors: [
        {
          required: {
            value: true,
          },
          minLength: {
            value: 6,
          },
        },
      ],
    },
  ],
  useForm: action('On handle form'),
};
