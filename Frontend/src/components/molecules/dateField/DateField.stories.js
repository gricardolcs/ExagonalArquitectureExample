import React from 'react';
import { action } from '@storybook/addon-actions';
import 'semantic-ui-css/semantic.min.css';
import DateField from './DateField';

export default {
    title: 'Platform/Molecules/DateField',
    component: DateField
};

const Template = ({ children, ...args }) => <DateField {...args}>{children}</DateField>;

export const dateField = Template.bind({});
dateField.args = {
    name: 'date',
    setValue: action('Set a new Value'),
    trigger: action('Date'),
    value: '',
    className: '',
    disabled: false,
    errorDate: '',
    labelIcon: 'calendar',
    iconColor:'grey',
    labelWidth: '5',
    labelValue: 'Date',
    labelColor: 'black',
    inputWidth: '11',
    minDate: '',
    onChange: action('On Change'),
    placeholder: '',
    errors: {}
}

export const dateFieldWithValue = Template.bind({});
dateFieldWithValue.args = {
    name: 'date',
    setValue: action('Set a new Value'),
    trigger: action('Date'),
    value: new Date().toLocaleString("en-CA", {dateStyle:"short"}),
    className: '',
    disabled: false,
    errorDate: '',
    labelIcon: 'calendar',
    iconColor:'grey',
    labelWidth: '5',
    labelValue: 'Date',
    labelColor: 'black',
    inputWidth: '11',
    minDate: '',
    onChange: action('On Change'),
    placeholder: '',
    errors: {}
}