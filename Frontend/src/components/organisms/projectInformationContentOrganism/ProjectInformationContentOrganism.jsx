import React, { Fragment, useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { Menu, Tab } from 'semantic-ui-react';
import CustomTab from '../../atoms/customTab/CustomTab';
import { getApplicantsByBootcampId } from '../../../api/applicant/fetch/ApplicantsFetchByBootcamp';
import { ApplicantsProjectList } from '../applicantsProjectList/ApplicantsProjectList';
import bootcampFindById from '../../../api/bootcamp/fetch/BootcampFindById';
import './style.css';

function ProjectInformationContentOrganism({ setComponent }) {
  const [isthereNewData, setIsthereNewData] = useState({});
  // const [applicantsBasicInfo, setApplicantsBasicInfo] = useState({});
  const [applicants, setApplicants] = useState([]);
  const [project, setProject] = useState({ workflow: { stages: [] } });

  const menuStyleConfiguration = {
    display: 'flex',
    justifyContent: 'center',
    margin: 0,
    paddingTop: '1rem',
    background: '#F3F3F4',
  };

  const panes = [
    {
      menuItem: (
        <Menu.Item className="custom-item" key="Applicants">
          <p>Applicants</p>
        </Menu.Item>
      ),
      render: () => (
        <Tab.Pane className="custom-segment">
          <div className="container-applicants-project-list">
            <ApplicantsProjectList
              dataApplicants={applicants}
              updateDataApplicants={setIsthereNewData}
              projectStages={project.workflow.stages}
              setComponent={setComponent}
            />
          </div>
        </Tab.Pane>
      ),
    },
    {
      menuItem: (
        <Menu.Item className="custom-item" key="Description">
          <p>Description</p>
        </Menu.Item>
      ),
      render: () => (
        <Tab.Pane className="custom-segment">
          <p>Description</p>
        </Tab.Pane>
      ),
    },
    {
      menuItem: (
        <Menu.Item className="custom-item" key="Form">
          <p>Form</p>
        </Menu.Item>
      ),
      render: () => (
        <Tab.Pane className="custom-segment">
          <p>Form</p>
        </Tab.Pane>
      ),
    },
  ];

  const setApplicantsOfBootcamp = (applicantsReceived) => {
    if (
      applicantsReceived.content !== undefined &&
      applicantsReceived.content.length > 0
    ) {
      setApplicants(applicantsReceived.content);
    }
  };

  useEffect(() => {
    setApplicants([]);
    const projectId = sessionStorage.getItem('currentProjectId');
    getApplicantsByBootcampId(projectId, true, 0, 20, setApplicantsOfBootcamp);
    bootcampFindById(projectId, setProject);
  }, [isthereNewData]);

  return (
    <Fragment>
      <div>
        <CustomTab
          variant={'tabs'}
          menu={{
            secondary: true,
            pointing: true,
            text: true,
            className: 'navbar-tab custom-tabs',
            style: menuStyleConfiguration,
          }}
          panes={panes}
        />
      </div>
    </Fragment>
  );
}

ProjectInformationContentOrganism.prototype = {
  setComponent: PropTypes.func,
};

ProjectInformationContentOrganism.defaultProps = {
  setComponent: () => {},
};

export default ProjectInformationContentOrganism;
