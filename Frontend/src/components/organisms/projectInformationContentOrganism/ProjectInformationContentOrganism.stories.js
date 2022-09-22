import React from 'react';
import 'semantic-ui-css/semantic.min.css';
import ProjectInformationContentOrganism from './ProjectInformationContentOrganism';
import { action } from "@storybook/addon-actions";

export default {
    title: 'Platform/Organisms/ProjectInformationContentOrganism',
    component: ProjectInformationContentOrganism,
}

const Template = ({ children, ...args }) => <ProjectInformationContentOrganism {...args}>
    {children}
</ProjectInformationContentOrganism>

export const projectInformationContentOrganism = Template.bind({});

projectInformationContentOrganism.args = {
    setComponent: action('Set Component')
}
