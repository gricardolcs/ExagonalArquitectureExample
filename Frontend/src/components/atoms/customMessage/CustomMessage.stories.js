import React from 'react';
import 'semantic-ui-css/semantic.min.css';
import CustomMessage from './CustomMessage';

export default {
    title: 'Platform/Atoms/CustomMessage',
    component: CustomMessage
};

const Template = ({ children, ...args }) => <CustomMessage {...args}>{children}</CustomMessage>;

export const messageWarning = Template.bind({});
messageWarning.args = {
    iconName: 'warning sign',
    message: 'My message warning',
    color: 'yellow',
    size: 'tiny'
}

export const messageError = Template.bind({});
messageError.args = {
    iconName: 'times circle',
    message: 'My message error',
    color: 'red',
    size: 'tiny'
}