import React from 'react';
import CreateAndUpdateBootcampForm from './CreateAndUpdateBootcampForm';
import { action } from '@storybook/addon-actions';

import 'semantic-ui-css/semantic.min.css';

export default {
  title: 'Platform/Organisms/CreateAndUpdateBootcampForm',
  component: CreateAndUpdateBootcampForm,
}

const Template = ({ ...args }) => <CreateAndUpdateBootcampForm {...args} />

export const createBootcampForm = Template.bind({});
createBootcampForm.args = {
  onClose: action('handle close'),
  onSubmit: action('Submited'),
  onChangeHeader: action("handleClose"),
  workflowTypes: [
    { text: 'Development workflow', value: 1 },
    { text: 'Manual testing workflow', value: 2 },
    { text: 'Automation testing workflow', value: 3 }
  ],
  departmentTypes: [
    { text: 'QA', value: 1 },
    { text: 'Development', value: 2 },
    { text: 'Automation', value: 3 },
    { text: 'DevOps', value: 4 }
  ],
  projectTypes: [
    { text: 'Bootcamp', value: 1 },
    { text: 'Job Offer', value: 2 }
  ],
  currentBootcamp: {},
}

export const updateBootcampForm = Template.bind({});
updateBootcampForm.args = {
  onClose: action('handle close'),
  onSubmit: action('Submited'),
  onChangeHeader: action("handleClose"),
  workflowTypes: [
    { text: 'Development workflow', value: 1 },
    { text: 'Manual testing workflow', value: 2 },
    { text: 'Automation testing workflow', value: 3 }
  ],
  departmentTypes: [
    { text: 'QA', value: 1 },
    { text: 'Development', value: 2 },
    { text: 'Automation', value: 3 },
    { text: 'DevOps', value: 4 }
  ],
  projectTypes: [
    { text: 'Bootcamp', value: 1 },
    { text: 'Job Offer', value: 2 }
  ],
  currentBootcamp: {
    id: '-1029384756',
    name: 'Bolvian Bootcamp',
    description: 'First Bolivian Bootcamp',
    startDate: '2021-06-20',
    endDate: '2021-12-20',
    workflowType: 1,
    departmentType: 1,
    projectType:1,
  },
}
