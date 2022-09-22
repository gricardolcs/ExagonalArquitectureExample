import React from 'react'
import { action } from '@storybook/addon-actions';
import 'semantic-ui-css/semantic.min.css';
import ActionIcons from './ActionIcons';

export default {
  title: 'Platform/Molecules/ActionIcons',
  component: ActionIcons
};

const Template = ({ children, ...args }) => <ActionIcons {...args}>{children}</ActionIcons>;

const arrayIcons = [
  {
    name: 'upload',
    size: 'large',
    color: 'red',
    className: '',
    onClick: action('on Click')
  },
  {
    name: 'plus circle',
    size: 'large',
    color: 'green',
    className: '',
    onClick: action('on Click')
  },
  {
    name: 'plus square outline',
    size: 'big',
    color: 'pink',
    className: '',
    onClick: action('on Click')
  },
  {
    name: 'download',
    size: 'big',
    color: 'grey',
    className: '',
    onClick: action('on Click')
  }
]

export const actionIcons = Template.bind({});
actionIcons.args = {
  arrayIcons
}
