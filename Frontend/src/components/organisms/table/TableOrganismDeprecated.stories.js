import React from 'react'
import 'semantic-ui-css/semantic.min.css';
import { action } from '@storybook/addon-actions';
import { TableOrganism } from './TableOrganism';

export default {
  title: 'Platform/Organisms/TableOrganism',
  component: TableOrganism
};

const Template = ({ children, ...args }) =>
  <TableOrganism {...args}>{children}</TableOrganism>;

const tableHeaders = [
  'id',
  'username',
  'name',
  'email',
  'age',
  'edit',
  'delete'
]

const tableData = [
  {
    id: -8778104601,
    username: 'JuanValdez',
    name: 'Juan',
    email: 'JValdez@jalasoft.com',
    age: 30
  },
  {
    id: -8778104602,
    username: 'DanielMila',
    name: 'Daniel',
    email: 'DMila@jalasoft.com',
    age: 25
  },
  {
    id: -8778104603,
    username: 'MilenaRocha',
    name: 'Milena',
    email: 'MRocha@jalasoft.com',
    age: 20
  }
]

const tableRowAttributes = [
  'id',
  'username',
  'name',
  'email',
  'age',
]

export const tableOrganism = Template.bind({});
tableOrganism.args = {
  tableHeaders,
  tableData,
  tableRowAttributes,
  textAlign: 'center',
  handleEdit: action('On Edit'),
  handleDelete: action('On Delete'),
  textAlignHeaders: '',
  totalPages: 3,
  setNumberOfApplicantsToShow: action('On Change'),
  activePage: 1,
  setActivePage: action('On change'),
  numberApplicantsOption: [],
  paginationInterval: ''
}
