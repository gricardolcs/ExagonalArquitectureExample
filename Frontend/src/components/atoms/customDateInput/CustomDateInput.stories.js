import React from 'react'
import { action } from '@storybook/addon-actions'
import 'semantic-ui-css/semantic.min.css';
import CustomDateInput from './CustomDateInput';

export default {
    title: 'Platform/Atoms/CustomDateInput',
    component: CustomDateInput
};

const Template = ({ children, ...args }) => <CustomDateInput {...args}>{children}</CustomDateInput>;

export const dateInputBasic = Template.bind({});
dateInputBasic.args = {
    name: 'birthday',
    setValue: action('On set a new Value'),
    trigger: action('Birthday'),
    value: '',
    onChange: action('On Change'),
    readOnly: false,
    placeholder: 'Date',
    popupPosition: 'bottom left',
    size: 'mini',
}

export const dateInputWithError = Template.bind({});
dateInputWithError.args = {
    name: 'birthday',
    setValue: action('On set a new Value'),
    trigger: action('Birthday'),
    value: '2021-04-01',
    onChange: action('On Change'),
    readOnly: false,
    error: 'Invalid date',
    placeholder: 'Date',
    popupPosition: 'bottom left',
    size: 'mini',
}