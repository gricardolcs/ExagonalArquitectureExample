import React from 'react';
import { action } from '@storybook/addon-actions';
import 'semantic-ui-css/semantic.min.css';
import TextAreaInput from './TextAreaInput';


export default {
    title: 'Platform/Atoms/TextAreaInput',
    component: TextAreaInput
};

const Template = ({ children, ...args }) => <TextAreaInput {...args}>{children}</TextAreaInput>;

export const textAreaInput = Template.bind({});
textAreaInput.args = {
    name: 'description',
    setValue: action('Set a new value'),
    trigger: action('TextAreaInput'),
    value: '',
    onChange: action('On Change'),
    placeholder: 'Text',
    variant: '',
    rows: 3,
    disabled: false,
    readOnly: false,
    maxLength: 500
}