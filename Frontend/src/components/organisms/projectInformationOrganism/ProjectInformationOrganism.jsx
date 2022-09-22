import React, { useState, useEffect, useCallback } from 'react';
import PropTypes from 'prop-types';
import { Grid, Header } from 'semantic-ui-react';
import ProjectDefaultPhoto from '../../../images/logos/logo-dev.png';
import IconMoreVerticalBlue from '../../../images/icons/icon-more-vertical-blue.png';
import CustomSideBar from '../../atoms/customSideBar/CustomSideBar';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import CustomIcon from '../../atoms/customIcon/CustomIcon';
import BasicImage from '../../atoms/basicImage/BasicImage';
import CustomPopup from '../../atoms/customPopup/CustomPopup';
import bootcampFindById from '../../../api/bootcamp/fetch/BootcampFindById';
import CustomDropDown from '../../atoms/customDropDown/CustomDropDown';
import convertDateFormat from '../../../utils/dateConvertFormatUtils/DateConvertUtils';
import IconSimpleUserDark from '../../../images/icons/icon-simple-user-dark.png';
import { SESSION_COMPONENT } from '../../../utils/constants/constants';
import { BootcampsPaths } from '../../../enum/availableRoutes';
import './style.css';

function ProjectInformationOrganism({
  setComponent,
  headerContent,
  pageContent,
}) {
  const [project, setProject] = useState({});
  const [projectNameDivided, setProjectNameDivided] = useState([]);

  const handleReturnBootcampList = useCallback(() => {
    sessionStorage.setItem(SESSION_COMPONENT, BootcampsPaths.DEFAULT);
    setComponent(BootcampsPaths.DEFAULT);
  }, [setComponent]);

  function renderStage(element) {
    return (
      <div className="project-stage" key={`row-${element.id}`}>
        <span>
          <CustomIcon name={'circle'} color={'blue'} />
        </span>
        {element.name}
      </div>
    );
  }

  const buildingProject = useCallback((project) => {
    const words = project.name.split(' ');
    if (words.length > 1) {
      let index = 0;
      let size = 0;
      while (size < 14 && index < words.length) {
        size += words[index].length;
        index++;
      }
      if (index !== 0 || index !== words.length) {
        index--;
      }
      let firstValue = '';
      for (let firstIndex = 0; firstIndex < index; firstIndex++) {
        firstValue += words[firstIndex] + ' ';
      }
      let secondValue = '';
      if (index < words.length) {
        for (
          let secondIndex = index;
          secondIndex < words.length;
          secondIndex++
        ) {
          secondValue += words[secondIndex] + ' ';
        }
      }
      setProjectNameDivided([firstValue, secondValue]);
    } else {
      setProjectNameDivided(words);
    }
    setProject(project);
  }, []);

  function renderStages(stages) {
    return stages.map((element) => {
      return renderStage(element);
    });
  }

  useEffect(() => {
    const currentProjectId = sessionStorage.getItem('currentProjectId');
    if (currentProjectId) {
      bootcampFindById(currentProjectId, buildingProject);
    }
  }, [buildingProject]);

  return (
    <CustomSideBar
      sideBarContent={
        <Grid>
          <Grid.Row className="previous-label-row">
            <Grid.Column width={16}>
              <CustomLabel
                elementType="p"
                size="big"
                handleOnClick={handleReturnBootcampList}
                color="blue"
              >
                <CustomIcon name="chevron left" color="blue" size="small" />
                Projects
              </CustomLabel>
            </Grid.Column>
          </Grid.Row>
          <Grid.Row className="image-center">
            <Grid.Column width={13} textAlign="center" verticalAlign="middle">
              <div className="project-image-border">
                <BasicImage
                  className="project-image"
                  verticalAlign="middle"
                  size="small"
                  centered
                  src={ProjectDefaultPhoto}
                />
              </div>
            </Grid.Column>
            <Grid.Column width={3}>
              <CustomDropDown
                trigger={
                  <BasicImage
                    src={IconMoreVerticalBlue}
                    className="icon-more"
                  />
                }
                direction="right"
                items={[]}
              />
            </Grid.Column>
            <div className="dropdown-status">
              <CustomIcon
                color={'yellow'}
                name={'circle'}
                size={'small'}
                className="icon-state"
              />
              <CustomDropDown
                variant="dropdown-name"
                trigger={'Not Started'}
                icon={'chevron down'}
                items={[]}
              />
            </div>
          </Grid.Row>
          <div className="project-information">
            {project && (
              <Grid.Row className="project-name-row">
                <Grid.Column width={16} className="project-name-column">
                  {project.name && project.name.length <= 14 ? (
                    <Header
                      className="project-name"
                      as="h1"
                      content={project.name}
                    />
                  ) : (
                    <CustomPopup
                      color="black"
                      message={project.name}
                      trigger={
                        projectNameDivided.length === 1 ? (
                          <div style={{ textAlign: '-webkit-center' }}>
                            <Header
                              className="project-name project-name-ellipsis"
                              as="h1"
                              content={projectNameDivided[0]}
                            />
                          </div>
                        ) : (
                          <div style={{ textAlign: '-webkit-center' }}>
                            <Header
                              className="project-name"
                              as="h1"
                              content={projectNameDivided[0]}
                            />
                            <Header
                              className="project-name project-name-ellipsis"
                              as="h1"
                              content={projectNameDivided[1] + ' '}
                            />
                          </div>
                        )
                      }
                    />
                  )}
                  <p className="project-description">{project.description}</p>
                </Grid.Column>
              </Grid.Row>
            )}
            <Grid.Row>
              <Grid.Column width={16} className="custom-column">
                <div>
                  <div className="project-specific-info">
                    {project.startDate && project.endDate
                      ? convertDateFormat.convert(project.startDate) +
                        ' thru ' +
                        convertDateFormat.convert(project.endDate)
                      : ''}
                  </div>
                  <div className="project-specific-info">
                    <span className="bold">
                      <BasicImage
                        src={IconSimpleUserDark}
                        className="participants-icon"
                        name={'user outline'}
                        size="mini"
                        verticalAlign="top"
                      />
                      Accepted Participants:
                    </span>{' '}
                    {`${project.acceptedParticipants}/${project.participantsExpectedQuantity}`}
                  </div>
                  <div className="project-specific-info">
                    <span className="bold">Department:</span>{' '}
                    {project.department ? project.department.name : ''}
                  </div>
                  <div className="project-type-info">
                    <span className="bold">Project type:</span>{' '}
                    {project.project ? project.project.name : ''}
                  </div>
                </div>
              </Grid.Column>
            </Grid.Row>
            <Grid.Row>
              <Grid.Column width={16}>
                <Header
                  className="project-stage-name"
                  as="h3"
                  content={project.workflow ? project.workflow.name : ''}
                />
                {project.workflow !== undefined &&
                project.workflow.stages.length !== 0 ? (
                  renderStages(project.workflow.stages)
                ) : (
                  <div className={'message-error'}>
                    There are no stages to display
                  </div>
                )}
              </Grid.Column>
            </Grid.Row>
          </div>
        </Grid>
      }
      headerContent={headerContent}
      bodyContent={pageContent}
    />
  );
}

ProjectInformationOrganism.prototype = {
  setComponent: PropTypes.object,
  headerContent: PropTypes.node,
  pageContent: PropTypes.node,
};

ProjectInformationOrganism.defaultProps = {
  setComponent: () => {},
};

export default ProjectInformationOrganism;
