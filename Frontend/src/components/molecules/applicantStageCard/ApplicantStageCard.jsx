import React, { useCallback, useEffect, useState } from 'react';
import CustomStepNavigation from '../../atoms/customStepNavigation/StepNavigation';
import BasicImage from '../../atoms/basicImage/BasicImage';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import iconArrowDown from '../../../images/icons/icon-arrow-down.png';
import iconArrowUp from '../../../images/icons/icon-arrow-up.png';
import StagePreviewOrganism from '../../organisms/stagePreviewOrganism/StagePreviewOrganism';
import EnumQualificationStatus from '../../organisms/stagePreviewOrganism/EnumQualificationStatus';
import { verifyStatus } from './helper/HandleStatus';
import DateUtils from '../../../utils/getValue/getDateTime';
import './style.css';

const ApplicantStageCard = ({
  project,
  applicantWorkflow,
  statuses,
  onSubmitAnApplicantQualification,
  onSubmitFileReport,
  onDownloadFile,
}) => {
  const { id, name, workflow } = project;
  const [stageSelected, setStageSelected] = useState({});
  const [nextStage, setNextStage] = useState(null);
  const [stages, setStages] = useState([]);
  const [open, setOpen] = useState(false);

  function handleOnClickOnStep(step) {
    const { stages } = workflow;
    const foundStage = stages.find((stage) => stage.order === step);
    const { applicantQualifications } = applicantWorkflow;
    const foundApplicantQualification = applicantQualifications.find(
      (applicantQualification) =>
        applicantQualification.stageName === foundStage.name
    );
    setStageSelected(foundApplicantQualification);
    const foundNextStage = stages.find((stage) => stage.order === step + 1);
    if (foundNextStage) {
      const foundNextApplicantQualification = applicantQualifications.find(
        (item) => item.stageName === foundNextStage.name
      );
      if (foundNextApplicantQualification) {
        setNextStage(foundNextApplicantQualification);
      } else {
        setNextStage({
          stageId: foundNextStage.id,
          qualificationStatus: EnumQualificationStatus.IN_PROGRESS,
        });
      }
    } else {
      setNextStage({});
    }
    setOpen(true);
  }

  const isTheLastStage = (currentStage) => {
    const { stages } = workflow;
    return stages[stages.length - 1].name === currentStage.stageName;
  };

  const buildingLabelsFromStageForStepNavigation = useCallback(() => {
    const stagesLabels = {};
    workflow.stages.forEach((item) => {
      const foundQualification = applicantWorkflow.applicantQualifications.find(
        (qualification) => qualification.stageName === item.name
      );
      stagesLabels[item.name] = foundQualification
        ? foundQualification.qualificationStatus
        : '';
    });
    setStages(stagesLabels);
  }, [applicantWorkflow.applicantQualifications, workflow.stages]);

  const setByDefaultAStageSelected = useCallback(() => {
    const { applicantQualifications } = applicantWorkflow;
    let foundApplicantQualification = applicantQualifications.find(
      (applicantQualification) =>
        verifyStatus(applicantQualification.qualificationStatus)
    );
    if (!foundApplicantQualification) {
      foundApplicantQualification = applicantQualifications[5];
    }
    if (foundApplicantQualification) {
      setStageSelected(foundApplicantQualification);
      const foundCurrentStage = workflow.stages.find(
        (stage) => stage.name === foundApplicantQualification.stageName
      );
      const foundNextStage = workflow.stages.find(
        (stage) => stage.order === foundCurrentStage.order + 1
      );
      if (foundNextStage) {
        const foundNextApplicantQualification = applicantQualifications.find(
          (item) => item.stageName === foundNextStage.name
        );
        if (foundNextApplicantQualification) {
          setNextStage(foundNextApplicantQualification);
        } else {
          setNextStage({ stageId: foundNextStage.id, qualificationStatus: '' });
        }
      } else {
        setNextStage({});
      }
    }
  }, [applicantWorkflow, workflow]);

  async function handleOnSubmitAnApplicantQualification(
    bootcampId,
    stageId,
    data
  ) {
    const response = await onSubmitAnApplicantQualification(
      bootcampId,
      stageId,
      { ...data, submitDate: DateUtils.getDateTime(data.submitDate) }
    );
    if (response.status === 200) {
      const { stages } = workflow;
      const foundCurrentStage = stages.find(
        (stage) => stage.name === stageSelected.stageName
      );
      const foundNextStage = stages.find(
        (stage) => stage.order === foundCurrentStage.order + 1
      );
      if (foundNextStage) {
        setStageSelected({ stageName: foundNextStage.name });
      } else {
        setStageSelected({ stageName: stages[stages.length - 1].name });
      }
    }
  }

  useEffect(() => {
    if (stageSelected && stageSelected.stageName) {
      buildingLabelsFromStageForStepNavigation();
      const { applicantQualifications } = applicantWorkflow;
      const updateApplicantQualification = applicantQualifications.find(
        (applicantQualification) =>
          applicantQualification.stageName === stageSelected.stageName
      );
      setStageSelected(updateApplicantQualification);
    }
  }, [
    stageSelected,
    applicantWorkflow,
    buildingLabelsFromStageForStepNavigation,
  ]);

  useEffect(() => {
    buildingLabelsFromStageForStepNavigation();
    setByDefaultAStageSelected();
  }, [buildingLabelsFromStageForStepNavigation, setByDefaultAStageSelected]);

  return (
    <div className="applicant-stage-card">
      <div className="title-project">
        <span>{name}</span>
      </div>
      <div className="sub-title">
        <label>Current stage:</label>
        <CustomStepNavigation
          workflowStageStatus={stages}
          updateStep={handleOnClickOnStep}
          scoreStatus={applicantWorkflow.applicantQualifications}
        />
        <div className="see-details" onClick={() => setOpen(!open)}>
          <CustomLabel value="See details" />
          <BasicImage
            className="view-more"
            src={open ? iconArrowUp : iconArrowDown}
          />
        </div>
        {open && (
          <StagePreviewOrganism
            stage={stageSelected}
            bootcampId={id}
            nextStage={nextStage}
            statuses={statuses}
            onSubmit={handleOnSubmitAnApplicantQualification}
            onSubmitFileReport={onSubmitFileReport}
            onDownloadFile={onDownloadFile}
            isTheLastStage={isTheLastStage}
          />
        )}
      </div>
    </div>
  );
};

export default ApplicantStageCard;
