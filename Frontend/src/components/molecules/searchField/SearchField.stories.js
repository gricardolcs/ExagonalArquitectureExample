import React from 'react'
import 'semantic-ui-css/semantic.min.css';
import { action } from '@storybook/addon-actions';
import SearchField from './SearchField';

export default {
  title: 'Platform/Molecules/SearchField',
  component: SearchField,
}

const Template = ({ children, ...args }) => <SearchField {...args}>
  {children}
</SearchField>

export const searchField = Template.bind({});
searchField.args = {
    handleOnPressingEnter: action('Handle On Pressing Enter'),
    placeholder: 'My Search'
}
