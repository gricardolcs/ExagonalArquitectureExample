import React from 'react'
import PropTypes from 'prop-types';
import CustomSubMenu from '../../molecules/customSubMenu/CustomSubMenu';
import ProjectContent from '../../organisms/projectContent/ProjectContent';
import project from '../../../images/icons/icon-project.png'
import applicants from '../../../images/icons/icon-applicants.png'

function ProjectContentTemplate({
    projectInformationContent,
    handleOnClose,
    applicantContent
}) {
    const currentProjectId = sessionStorage.getItem('currentProjectId');

    const subMenuData = [
        {
            title: "Information",
            content:
                <div className='default-tab-content' >
                    {projectInformationContent}
                </div>
        }
    ]

    const menuData = currentProjectId
    ?
    [
        {
            title: "Project",
            image: project,
            content: <CustomSubMenu data={subMenuData} />
       },
       {
           title: "Applicants",
           image: applicants,
           content:
            <div>
                {applicantContent}
            </div>
       }
    ]
    :
    [
        {
            title: "Project",
            image: project,
            content: <CustomSubMenu data={subMenuData} />
        }
    ]

    return (
        <ProjectContent menuData={menuData} handleOnClose={handleOnClose}/>
    );

}

ProjectContentTemplate.prototype = {
    projectInformationContent: PropTypes.node.isRequired,
    handleOnClose: PropTypes.func
}

ProjectContentTemplate.defaultProps = {
    handleOnClose: () => {}
}

export default ProjectContentTemplate;