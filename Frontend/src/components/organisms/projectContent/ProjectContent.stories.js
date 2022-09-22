import React from 'react'
import 'semantic-ui-css/semantic.min.css';
import { action } from '@storybook/addon-actions';
import ProjectContent from './ProjectContent';
import '../../../styles/common.css'
import '../../../styles/themes/theme-default.css'

export default {
    title: 'Platform/Organisms/ProjectContent',
    component: ProjectContent,
}

const Template = ({ children, ...args }) => <ProjectContent {...args}>
    {children}
</ProjectContent>

export const projectContent = Template.bind({});
projectContent.args = {
    menuData: [
        {
            title: "Project",
            content: "Project"
        },
        {
            title: "Applicants",
            content: 'Applicants'
        }
    ],
    handleOnClose: action('Handle On Close')
}