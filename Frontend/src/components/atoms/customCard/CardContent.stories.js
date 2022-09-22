import React from 'react';
import CardContent from './CardContent';
import 'semantic-ui-css/semantic.min.css';

export default {
  title: 'Platform/Atoms/CardContent',
  component: CardContent,
}

const Template = ({ children, ...args}) => <CardContent {...args}>
  {children}
</CardContent>

export const cardContent = Template.bind({});
cardContent.args={
  className: '',
  textAlign: 'center',
}