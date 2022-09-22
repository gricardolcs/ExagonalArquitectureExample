import React from 'react'
import 'semantic-ui-css/semantic.min.css';
import { TableHeader } from './TableHeader';

export default {
  title: 'Platform/Atoms/TableHeader',
  component: TableHeader
};

const Template = ({ children, ...args }) => <TableHeader {...args}>{children}</TableHeader>;

const headers = [
  'id',
  'name',
  'last name',
  'email',
  'type'
]
export const tableHeader = Template.bind({});
tableHeader.args = {
  tableHeaders: headers,
  textAlign: 'center',
  variant: '',
  cellVariant: ''
}
