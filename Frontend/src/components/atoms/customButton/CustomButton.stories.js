import React from 'react'
import { action } from '@storybook/addon-actions'
import 'semantic-ui-css/semantic.min.css';
import CustomButton from './CustomButton';

export default {
    title: 'Platform/Atoms/CustomButton',
    component: CustomButton
};

const Template = ({ children, ...args }) => <CustomButton {...args}>{children}</CustomButton>;

export const primary = Template.bind({});
primary.args = {
    id: 'custom-button',
    color: 'blue',
    disabled: false,
    label: 'Button',
    onClick: action('On Click'),
    type: 'button',
    variant: '',
    circular: false,
    size: 'small'
}