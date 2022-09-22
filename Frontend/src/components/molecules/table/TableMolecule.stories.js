import React from 'react'
import 'semantic-ui-css/semantic.min.css';
import { action } from '@storybook/addon-actions';
import { TableMolecule } from './TableMolecule';

export default {
  title: 'Platform/Molecules/TableMolecule',
  component: TableMolecule
};

const Template = ({ children, ...args }) =>
  <TableMolecule {...args}>{children}</TableMolecule>;

const tableHeaders = [
  'id',
  'username',
  'name',
  'email',
  'age',
  'edit',
  'delete',
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

export const tableMolecule = Template.bind({});
tableMolecule.args = {
  tableHeaders: [],
  tableData: [],
  tableRowAttributes: [],
  textAlign: 'center',
  handleEdit: action('On Edit'),
  handleDelete: action('On Delete'),
  textAlignHeaders: '',
  headerVariant: '',
  cellVariant: '',
  tableVariant: '',
  rowVariant: '',
  bodyVariant: ''
}
