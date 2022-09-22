import React from 'react';
import FileField from './FileField';
import { action } from '@storybook/addon-actions';

import 'semantic-ui-css/semantic.min.css';

export default {
    title: 'Platform/Molecules/FileField',
    component: FileField,
    argTypes: {
        labelWidth: {
            control: {
                type: 'select',
                options: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16],
            },
        },
        inputWidth: {
            control: {
                type: 'select',
                options: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16],
            },
        }
    },
}

const Template = ({ children, ...args }) => <FileField {...args}>
    {children}
</FileField>

export const fileInput = Template.bind({});
fileInput.args = {
    name: 'file',
    setValue: action('Set a new value'),
    trigger: action('File'),
    labelValue: 'My file',
    handleFile: action('Handle File'),
    labelWidth: 2,
    labelColor: 'black',
    inputWidth: 5,
    placeholder: 'Upload file'
}
