import React from "react";
import { action } from "@storybook/addon-actions";
import "semantic-ui-css/semantic.min.css";
import { ProjectList } from "./ProjectList";

export default {
  title: "Platform/organisms/ProjectList",
  component: ProjectList,
};

const Template = ({ children, ...args }) => (
  <ProjectList {...args}>{children}</ProjectList>
);

export const projectList = Template.bind({});
projectList.args = {
  data: [
    {
      department: null,
      departmentType: 2,
      description: "First Bolivian Dev Bootcamp Columbia",
      endDate: "2021-06-12",
      id: "-6304490379095215818",
      location: "Latam",
      name: "Dev Bootcamp Columbia",
      project: null,
      projectType: 1,
      startDate: "2020-12-07",
      workflow: null,
      workflowType: 1,
    },
    {
      department: null,
      departmentType: 1,
      description: "First Bolivian Dev Bootcamp Bolivia",
      endDate: "2021-06-12",
      id: "-8501493239495665810",
      location: "Latam",
      name: "Dev Bootcamp Bolivia",
      project: null,
      projectType: 2,
      startDate: "2020-12-07",
      workflow: null,
      workflowType: 1,
    },
  ],
  onNewElementClick: action("Add new project"),
  handleEdit: action("Handle Edit"),
  handleDelete: action("Handle Delete"),
  setComponent: action("Set Component"),
};
