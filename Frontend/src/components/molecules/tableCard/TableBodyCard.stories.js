import React from 'react'
import 'semantic-ui-css/semantic.min.css';
import { action } from '@storybook/addon-actions';
import { TableBodyCard } from './TableBodyCard';
import cardImage from '../../../images/atoms/customCard/default-bootcamp-image.jpg';

export default {
  title: 'Platform/Molecules/TableBodyCard',
  component: TableBodyCard
};

const Template = ({ children, ...args }) =>
  <TableBodyCard {...args}>{children}</TableBodyCard>;

const tableData = [
  {
    username: 'JuanValdez',
    name: 'Juan',
    email: 'JValdez@jalasoft.com',
    age: 30
  },
  {
    username: 'DanielMila',
    name: 'Daniel',
    email: 'DMila@jalasoft.com',
    age: 25
  },
  {
    username: 'MilenaRocha',
    name: 'Milena',
    email: 'MRocha@jalasoft.com',
    age: 20
  }
]

const tableRowAttributes = [
  'username',
  'name',
  'email',
  'age',
]

export const tableBodyCard = Template.bind({});
tableBodyCard.args = {
  tableData,
  tableRowAttributes,
  textAlign: 'center',
  cardImage,
  cardImageSize: 'tiny',
  handleEdit: action('On Edit'),
  handleDelete: action('On Delete'),
  handleNavigate: action('On Navigate')
}
