import React from 'react';
import { action } from '@storybook/addon-actions'
import 'semantic-ui-css/semantic.min.css';
import CustomPagination from './CustomPagination';

export default {
  title: 'Platform/Atoms/CustomPagination',
  component: CustomPagination
}

const Template = ({ children, ...args }) => <CustomPagination {...args}>{children}</CustomPagination>;

export const customPagination = Template.bind({});
customPagination.args = {
  activePage: 1,
  totalPages: 3,
  onChange: action('On Change')
}