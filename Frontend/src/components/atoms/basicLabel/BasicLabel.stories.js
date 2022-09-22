import React from 'react';
import BasicLabel from './BasicLabel';
import 'semantic-ui-css/semantic.min.css';
import { action } from '@storybook/addon-actions';

export default {
  title: 'Platform/Atoms/BasicLabel',
  component: BasicLabel,
}

const Template = ({ children, ...args }) => <BasicLabel {...args}>
  {children}
</BasicLabel>

export const basicLabel = Template.bind({});
basicLabel.args = {
  variant: '',
  size: 'big',
  color: 'red',
  children: 'content',
  handleOnClick: action('Handle On Click')
}
