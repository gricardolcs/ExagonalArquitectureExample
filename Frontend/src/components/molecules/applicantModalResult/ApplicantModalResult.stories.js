import { action } from '@storybook/addon-actions';
import React from 'react'
import 'semantic-ui-css/semantic.min.css';
import ApplicantModalResult from './ApplicantModalResult';

export default {
    title: 'Platform/Molecules/ApplicantModalResult',
    component: ApplicantModalResult,
}

const Template = ({ children, ...args }) => <ApplicantModalResult {...args}>
    {children}
</ApplicantModalResult>

export const applicantModalResult = Template.bind({});
applicantModalResult.args = {
    onClose: action('On Close'),
    onOpen: action('On Open'),
    open: true,
    handleCloseModal: action('Handle Close Modal'),
    projectName: 'My Project',
    result: {
        success: {
            value: 3,
            message: 'Message of Success',
            render: ''
        },
        fail: {
            value: 1,
            message: 'Message of Fail',
            render: ''
        }
    },
    header: 'My Result',
    className: ''
}
