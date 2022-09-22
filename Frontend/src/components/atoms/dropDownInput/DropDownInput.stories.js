import React from 'react';
import { action } from '@storybook/addon-actions'
import 'semantic-ui-css/semantic.min.css';
import DropDownInput from './DropDownInput';


export default {
    title: 'Platform/Atoms/DropDownInput',
    component: DropDownInput
};

const Template = ({ children, ...args }) => <DropDownInput {...args}>{children}</DropDownInput>;

export const dropDownInput = Template.bind({});
dropDownInput.args = {
    name: 'country',
    setValue: action('On set a new Value'),
    trigger: action('Country'),
    selectedValue: '',
    size: 'mini',
    className: '',
    onChange: action('On Change'),
    compact: false,
    options: [
        { text: 'Option 1', value: 1 },
        { text: 'Option 2', value: 2 },
        { text: 'Option 3', value: 3 },
    ],
    placeholder: 'Select one',
    allowAdditions: true,
    search: true,
    clearable: true,
    disabled: false,
    readOnly: false,
    openIconVariant: '',
}