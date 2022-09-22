import React from 'react'
import 'semantic-ui-css/semantic.min.css';
import { action } from '@storybook/addon-actions';
import CustomAccordions from './CustomAccordions';

export default {
  title: 'Platform/Molecules/CustomAccordions',
  component: CustomAccordions,
}

const Template = ({ children, ...args }) => <CustomAccordions {...args}>
  {children}
</CustomAccordions>

export const customAccordions = Template.bind({});
customAccordions.args = {
    data: [{title: 'My Accodion 1', content: 'My Content 2'}, {title: 'My Accodion 2', content: 'My Content 2'}],
    iconExpand: 'caret square down outline',
    iconNonExpand: 'caret square left outline',
    initialIndexes: [],
    handleOnNonExpand: action('Handle On Non Expand'),
    variantInternalAccordion: ''
}
