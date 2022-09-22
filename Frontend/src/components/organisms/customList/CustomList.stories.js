import React from 'react';
import 'semantic-ui-css/semantic.min.css';
import { CustomList } from './CustomList';
import { Button } from 'semantic-ui-react';

export default {
  title: 'Platform/organisms/CustomList',
  component: CustomList
};

const Template = ({ children, ...args }) => <CustomList {...args}>{children}</CustomList>;

export const customList = Template.bind({});
customList.args = {
  title: 'Testing Custom List',
  data: [
    {
      career: "Digital Marketing",
      city: "Tarija",
      country: "Bolivia",
      email: "cristal.dev@gmail.com",
      fullName: "Cristal Dev",
      id: -9202665956764764000,
      telephone: "78233656"
    },
    {
      career: "System Engineering",
      city: "Santa Cruz",
      country: "Bolivia",
      email: "cristhian.ortiz@gmail.com",
      fullName: "Cristhian Ortiz",
      id: -5860254223043773000,
      telephone: "78434827"
    },
    {
      career: "Industrial Engineering",
      city: "La Paz",
      country: "Bolivia",
      email: "dora.solares@hotmail.com",
      fullName: "Dora Solares",
      id: -8946462828418711000,
      telephone: "76259390"
    }
  ],
  searchLogic: () => { },
  addButton: <Button>Add</Button>,
  toolBarContent: () => { },
  renderCard: () => { },
  cardVariant: 'new-style',
  tableVariant: 'new-style',
  emptyData: <div></div>,
}
