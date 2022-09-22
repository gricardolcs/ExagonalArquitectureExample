import React from 'react';
import 'semantic-ui-css/semantic.min.css';
import { RowIconsCell } from './RowIconsCell';

export default {
  title: 'Platform/Atoms/RowIconsCell',
  component: RowIconsCell,
};

const Template = ({ ...args }) => <RowIconsCell {...args} />;

export const rowIconsCell = Template.bind({});
rowIconsCell.args = {
  actions: [
    { name: 'trash alternate outline', color: 'red', onClick: () => {} },
    { name: 'pencil alternate', onClick: () => {} },
  ],
};
