import React from 'react';
import { action } from '@storybook/addon-actions';

import 'semantic-ui-css/semantic.min.css';
import CustomLink from './CustomLink';

export default {
    title: 'Platform/Atoms/CustomLink',
    component: CustomLink,
};

const Template = ({ children, ...args }) => <CustomLink {...args}>
    {children}
</CustomLink>

export const link = Template.bind({});

link.args = {
    value: 'My Link',
    handleOnClick: action('Handle On Click')
}
