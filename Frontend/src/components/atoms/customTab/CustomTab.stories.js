import React from 'react';
import { action } from '@storybook/addon-actions'
import 'semantic-ui-css/semantic.min.css';
import CustomTab from './CustomTab';

export default {
    title: 'Platform/Atoms/CustomTab',
    component: CustomTab
}

const Template = ({ children, ...args }) => <CustomTab {...args}>{children}</CustomTab>;

export const customTab = Template.bind({});
customTab.args = {
    defaultActiveIndex: 0,
    variant: '',
    menu: {
        secondary: true,
        pointing: true
    },
    panes: [],
    onTabChange: action('On Tab Change'),
    renderActiveOnly: true
}