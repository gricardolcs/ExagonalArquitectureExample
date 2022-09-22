import React from 'react';
import CustomIcon from './CustomIcon'
import { action } from '@storybook/addon-actions'
import 'semantic-ui-css/semantic.min.css';


export default {
    title: 'Platform/Atoms/CustomIcon',
    component: CustomIcon
};

const Template = ({ children, ...args }) => <CustomIcon {...args}>{children}</CustomIcon>;

export const pencilIcon = Template.bind({});
pencilIcon.args = {
    icon: '',
    className: '',
    size: 'large',
    name: 'pencil alternate',
    onClick: action('On Click'),
    color: 'grey'
}

export const trashIcon = Template.bind({});
trashIcon.args = {
    icon: 'red',
    className: '',
    name: 'trash alternate outline',
    size: 'large',
    onClick: action('On Click'),
    color: 'grey'
}