import React from 'react';
import 'semantic-ui-css/semantic.min.css';
import { action } from '@storybook/addon-actions';
import CustomAccordionTitle from './CustomAccordionTitle';

export default {
    title: 'Platform/Atoms/CustomAccordionTitle',
    component: CustomAccordionTitle,
}

const Template = ({ children, ...args }) =>
    <CustomAccordionTitle {...args} >
        {children}
    </CustomAccordionTitle>

export const customAccordionTitle = Template.bind({});
customAccordionTitle.args = {
    active: false,
    index: 'My Accordion',
    title: 'My Accordion',
    iconExpand: 'caret square down outline',
    iconNonExpand: 'caret square left outline',
    handleClick: action('Handle Click')
}