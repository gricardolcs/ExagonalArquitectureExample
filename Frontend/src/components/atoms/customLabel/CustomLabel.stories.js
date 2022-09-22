import React from 'react';
import { action } from '@storybook/addon-actions';
import 'semantic-ui-css/semantic.min.css';
import CustomLabel from './CustomLabel';

export default {
    title: 'Platform/Atoms/CustomLabel',
    component: CustomLabel
};

const Template = ({ children, ...args }) => <CustomLabel {...args}>{children}</CustomLabel>;

export const labelBasic = Template.bind({});
labelBasic.args = {
    icon: '',
    value: 'Calendar',
    required: false,
    elementType: 'label',
    basic: true,
    variant: '',
    handleOnClick: action('Handle On Click'),
    size: 'medium',
    imageSize: 'mini',
    iconColor: 'gray',
    iconPosition: 'right',
    color:'gray'
}

export const labelWithIcon = Template.bind({});
labelWithIcon.args = {
    icon: 'calendar',
    value: 'Calendar',
    required: false,
    elementType: 'label',
    basic: true,
    variant: '',
    handleOnClick: action('Handle On Click'),
    size: 'medium',
    imageSize: 'mini',
    iconColor: 'gray',
    iconPosition: 'right',
    color:'gray'
}

export const labelRequired = Template.bind({});
labelRequired.args = {
    icon: 'calendar',
    value: 'Calendar',
    required: true,
    elementType: 'label',
    basic: true,
    variant: '',
    handleOnClick: action('Handle On Click'),
    size: 'medium',
    imageSize: 'mini',
    iconColor: 'gray',
    iconPosition: 'right',
    color: 'gray'
}