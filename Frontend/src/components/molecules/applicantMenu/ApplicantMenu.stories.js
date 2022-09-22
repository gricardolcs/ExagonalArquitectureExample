import React from 'react'
import 'semantic-ui-css/semantic.min.css';
import ApplicantMenu from './ApplicantMenu';

export default {
    title: 'Platform/Molecules/ApplicantMenu',
    component: ApplicantMenu,
}

const Template = ({ children, ...args }) => <ApplicantMenu {...args}>
    {children}
</ApplicantMenu>

export const applicantMenu = Template.bind({});
