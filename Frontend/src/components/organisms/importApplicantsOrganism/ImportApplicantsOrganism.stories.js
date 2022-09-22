import React from "react";
import ImportApplicantsOrganism from './ImportApplicantsOrganism';
import 'semantic-ui-css/semantic.min.css';

export default {
    title: 'Platform/Organisms/ImportApplicantsOrganism',
    component: ImportApplicantsOrganism,
};

const Template = ({...args}) => <ImportApplicantsOrganism {...args}/>;

export const importApplicantsOrganism = Template.bind({});
importApplicantsOrganism.args = {
    onClose: () => { },
    onOpen: () => { },
    open: true,
    updateData: () => { },
};
