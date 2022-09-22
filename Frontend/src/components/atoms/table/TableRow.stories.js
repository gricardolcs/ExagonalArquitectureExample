import React from 'react'
import 'semantic-ui-css/semantic.min.css';
import { TableRow } from './TableRow';

export default {
  title: 'Platform/Atoms/TableRow',
  component: TableRow
};

const Template = ({ children, ...args }) =>
  <TableRow {...args}>{children}</TableRow>;

export const tableBody = Template.bind({});
tableBody.args = {
  children: 'Row Content',
  rowVariant: '',
  item: { id: 1 },
}
