import React from 'react';
import 'semantic-ui-css/semantic.min.css';
import CustomField from './CustomField';

export default {
    title: 'Platform/Atoms/CustomField',
    component: CustomField
}

const Template = ({ children, ...args }) => <CustomField {...args}>{children}</CustomField>;

export const customField = Template.bind({});
customField.args = {
    key: 'my-field',
    variant: '',
    width: 8,
    disabled: false,
    children: <div>Content Field</div>,
}