import React from 'react';
import FileInput from './FileInput';
import { action } from '@storybook/addon-actions';

import 'semantic-ui-css/semantic.min.css';

export default {
    title: 'Platform/Atoms/FileInput',
    component: FileInput,
}

const Template = ({ children, ...args }) => <FileInput {...args}>
    {children}
</FileInput>
export const fileInput = Template.bind({});
fileInput.args = {
    name: 'cv',
    setValue: action('On set a new Value'),
    trigger: action('Cv'),
    accept: '.csv',
    handleFile: action('Handle File'),
    placeholder: 'Upload file'
}
