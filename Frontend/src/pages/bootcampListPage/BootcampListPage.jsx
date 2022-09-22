import React, { useState, useEffect, useCallback, Fragment } from 'react';
import CreateAndUpdateBootcampTemplate from '../../components/templates/createAndUpdateBootcampTemplate/CreateAndUpdateBootcampTemplate';
import DeleteBootcampTemplate from '../../components/templates/deleteBootcampTemplate/DeleteBootcampTemplate';
import BootcampCreation from '../../api/bootcamp/create/BootcampCreate';
import BootcampUpdate from '../../api/bootcamp/update/BootcampUpdate';
import bootcampPagination from '../../api/bootcamp/fetch/BootcampPagination';
import BootcampDelete from '../../api/bootcamp/delete/BootcampDelete';
import { workflowFetch } from '../../api/workflowtype/fetch/WorkflowTypeFetch';
import { departmentFetch } from '../../api/department/fetch/DepartmentFetch';
import { projectTypeFetch } from '../../api/projecttype/fetch/ProjectTypeFetch';
import ProjectInformationOrganism from '../../components/organisms/projectInformationOrganism/ProjectInformationOrganism';
import { ProjectList } from '../../components/organisms/projectList/ProjectList';
import ProjectInformationContentOrganism from '../../components/organisms/projectInformationContentOrganism/ProjectInformationContentOrganism';
import ApplicantInformationTemplate from '../../components/templates/applicantInformationTemplate/ApplicantInformationTemplate';
import { SESSION_COMPONENT } from '../../utils/constants/constants';
import { ApplicantsPaths, BootcampsPaths } from '../../enum/availableRoutes';
import initPage from '../../helpers/page/initPage';
import './style.css';

function BootcampListPage() {
  const [bootcamp, setBootcamp] = useState({});
  const [bootcampId, setBootcampId] = useState({});
  const [openModalBootcamp, setOpenModalBootcamp] = useState(false);
  const [tableData, setTableData] = useState([]);
  const [openModalDelete, setOpenModalDelete] = useState(false);
  const pageSize = 20;
  const [workflowTypes, setWorkflowTypes] = useState([]);
  const [projectTypes, setProjectTypes] = useState([]);
  const [departments, setDepartments] = useState([]);
  const [component, setComponent] = useState();

  async function handleCreate(bootcamp) {
    await BootcampCreation.createBootcamp(bootcamp);
    bootcampPagination(setTableData, 0, pageSize);
  }

  async function handleUpdate(bootcamp) {
    const response = await BootcampUpdate.updateBootcamp(bootcamp.id, bootcamp);
    if (response) {
      const tableDataNew = tableData.map((item) => {
        if (item.id === bootcamp.id) {
          return response;
        }
        return item;
      });
      setTableData([...tableDataNew]);
    }
  }

  async function handleModalDelete(id) {
    const response = await BootcampDelete.deleteBootcamp(id);
    if (response.status === 200) {
      const tableDataNew = tableData.filter((item) => {
        return item.id !== id;
      });
      setTableData(tableDataNew);
    }
  }

  const handleCloseModalBootcamp = () => setOpenModalBootcamp(false);

  const handleEdit = (item) => {
    setBootcamp(item);
    setOpenModalBootcamp(true);
  };

  const handleCloseModalDelete = () => setOpenModalDelete(false);

  const handleDelete = (item) => {
    setBootcampId(item.id);
    setOpenModalDelete(true);
  };

  const handleOpenCreateModal = (item) => {
    setBootcamp(item);
    setOpenModalBootcamp(true);
  };

  const loadWorkflowTypes = useCallback((data) => {
    const workflowTypes = data.map((item) => {
      return {
        text: item.name,
        value: item.id,
      };
    });
    setWorkflowTypes(workflowTypes);
  }, []);

  const loadDepartments = useCallback((data) => {
    const departments = data.map((item) => {
      return {
        text: item.name,
        value: item.id,
      };
    });
    setDepartments(departments);
  }, []);

  const loadProjectTypes = useCallback((data) => {
    const projectTypes = data.map((item) => {
      return {
        text: item.name,
        value: item.id,
      };
    });
    setProjectTypes(projectTypes);
  }, []);

  useEffect(() => {
    bootcampPagination(setTableData, 0, pageSize);
    initPage(
      {
        defaultPath: ApplicantsPaths.DEFAULT,
        variable: SESSION_COMPONENT,
      },
      setComponent
    );
  }, [setComponent]);

  useEffect(() => {
    workflowFetch(loadWorkflowTypes);
    departmentFetch(loadDepartments);
    projectTypeFetch(loadProjectTypes);
  }, [loadWorkflowTypes, loadDepartments, loadProjectTypes]);

  return (
    <Fragment>
      {component === BootcampsPaths.DEFAULT && (
        <div className="container-bootcamp-list">
          <CreateAndUpdateBootcampTemplate
            handleClose={handleCloseModalBootcamp}
            handleSave={bootcamp.id ? handleUpdate : handleCreate}
            openForm={openModalBootcamp}
            workflowTypes={workflowTypes}
            departments={departments}
            projectTypes={projectTypes}
            currentBootcamp={bootcamp}
          />
          <ProjectList
            data={tableData}
            onNewElementClick={handleOpenCreateModal}
            handleEdit={handleEdit}
            handleDelete={handleDelete}
            setComponent={setComponent}
          />
          <DeleteBootcampTemplate
            handleDelete={handleModalDelete}
            handleClose={handleCloseModalDelete}
            openModal={openModalDelete}
            id={bootcampId}
          />
        </div>
      )}
      {component === BootcampsPaths.PROFILE && (
        <ProjectInformationOrganism
          headerContent={
            <ProjectInformationContentOrganism setComponent={setComponent} />
          }
          setComponent={setComponent}
        />
      )}
      {component === BootcampsPaths.APPLICANT_PROFILE && (
        <ApplicantInformationTemplate
          setComponent={setComponent}
          previousLocationName="Return to project"
        />
      )}
    </Fragment>
  );
}

export default BootcampListPage;
