import React from 'react'
import 'semantic-ui-css/semantic.min.css';
import { action } from '@storybook/addon-actions';
import { TableCardOrganism } from './TableCardOrganism';
import { TableBodyCard } from '../../molecules/tableCard/TableBodyCard';
import cardImage from '../../../images/atoms/customCard/default-bootcamp-image.jpg';

export default {
  title: 'Platform/Organisms/TableCardOrganism',
  component: TableCardOrganism
};

const Template = ({ children, ...args }) =>
  <TableCardOrganism {...args}>{children}</TableCardOrganism>;

const tableHeaders = [
  'username',
  'name',
  'email',
  'age',
  'edit',
  'delete',
]

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
const textAlign = 'center';
const cardImageSize = 'tiny';

const getChildren = () => {
  return <TableBodyCard
    tableData={tableData}
    tableRowAttributes={tableRowAttributes}
    textAlign={textAlign}
    cardImage={cardImage}
    cardImageSize={cardImageSize}
    handleEdit={action('On Edit')}
    handleDelete={action('On Delete')}
    handleNavigate={action('On Navigate')}
  >
  </TableBodyCard>
}

export const tableCardOrganism = Template.bind({});
tableCardOrganism.args = {
  tableHeaders,
  textAlign: 'center',
  children: getChildren()
}
