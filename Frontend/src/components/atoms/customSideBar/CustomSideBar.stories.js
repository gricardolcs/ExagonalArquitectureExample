import React from 'react';
import { action } from '@storybook/addon-actions'
import 'semantic-ui-css/semantic.min.css';
import CustomSideBar from './CustomSideBar';

export default {
  title: 'Platform/Atoms/CustomSideBar',
  component: CustomSideBar
}

const Template = ({ children, ...args }) => <CustomSideBar {...args}>{children}</CustomSideBar>;

export const customSideBar = Template.bind({});
customSideBar.args = {
    sideBarContent: 'My Side Bar',
    headerContent: 'My Header',
    bodyContent: 'My Body'
}