import React from 'react';
import { action } from '@storybook/addon-actions'
import 'semantic-ui-css/semantic.min.css';
import CustomDropDown from './CustomDropDown';

export default {
    title: 'Platform/Atoms/CustomDropDown',
    component: CustomDropDown
}

const Template = ({ children, ...args }) => <CustomDropDown {...args}>{children}</CustomDropDown>;

export const customDropDown = Template.bind({});
customDropDown.args = {
    trigger: <div>my dropdown</div>,
    icon: null,
    direction: 'right',
    items: [],
    variant: '',
    header: <div>dropdown header</div>,
    menuVariant: '',
    headerVariant: '',
}