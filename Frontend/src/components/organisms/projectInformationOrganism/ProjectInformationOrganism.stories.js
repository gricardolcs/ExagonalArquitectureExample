import React from 'react';
import 'semantic-ui-css/semantic.min.css';
import ProjectInformationOrganism from './ProjectInformationOrganism';
import { action } from '@storybook/addon-actions';

export default {
    title: 'Platform/Organisms/ProjectInformationOrganism',
    component: ProjectInformationOrganism,
}

const Template = ({ children, ...args }) => <ProjectInformationOrganism {...args}>
    {children}
</ProjectInformationOrganism>

export const projectInformationOrganism = Template.bind({});
projectInformationOrganism.args = {
    headerContent: <div></div>,
    pageContent: <div></div>,
    setComponent: action('Set Component')
}