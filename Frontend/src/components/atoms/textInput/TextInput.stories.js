import React from 'react';
import { action } from '@storybook/addon-actions'
import 'semantic-ui-css/semantic.min.css';
import TextInput from './TextInput';

export default {
    title: 'Platform/Atoms/TextInput',
    component: TextInput
};

const Template = ({ children, ...args }) => <TextInput {...args}>{children}</TextInput>;

export const textInput = Template.bind({});
textInput.args = {
    value: '',
    size: 'mini',
    className: '',
    onChange: action('On Change'),
    placeholder: 'Text',
    required: false,
    disabled: false,
    readOnly: false,
    label: ''
}