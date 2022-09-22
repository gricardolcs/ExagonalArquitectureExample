import React from 'react';
import CardDescription from './CardDescription';
import 'semantic-ui-css/semantic.min.css';

export default {
  title: 'Platform/Atoms/CardDescription',
  component: CardDescription,
}

const Template = ({ children, ...args }) => <CardDescription {...args}>
  {children}
</CardDescription>

export const cardDescription = Template.bind({});
cardDescription.args = {
  className: '',
  textAlign: 'center',
}
