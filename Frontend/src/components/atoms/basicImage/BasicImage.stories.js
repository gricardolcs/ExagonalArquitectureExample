import React from 'react';
import BasicImage from './BasicImage';
import 'semantic-ui-css/semantic.min.css';
import photoDefault from '../../../images/atoms/basicImage/photoDefault.png';

export default {
  title: 'Platform/Atoms/BasicImage',
  component: BasicImage,
}

const Template = ({ children, ...args}) => <BasicImage {...args}>
  {children}
</BasicImage>

export const basicImage = Template.bind({});
basicImage.args={
  rounded: false,
  circular: true,
  className: '',
  src: photoDefault,
  size: 'tiny',
  verticalAlign: 'middle',
}