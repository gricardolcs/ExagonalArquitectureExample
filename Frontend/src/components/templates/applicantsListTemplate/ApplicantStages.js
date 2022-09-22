import ActionIcons from "../../molecules/actionIcons/ActionIcons";
import ApplicantsTableActionButton from "../../molecules/applicantsTableActionButton/ApplicantTableActionButton";
import GetStages from "../../../pages/bootcampPage/GetStages";
import EnumQualificationStatus from "../../organisms/stagePreviewOrganism/EnumQualificationStatus";
import "./style.css";

function buildApplicantStages(
  applicants,
  bootcampStages,
  handleProfile,
  handleOpenStageModal
) {
  const stagesArray = GetStages.getStages(bootcampStages).slice().splice(1);
  let buildApplicants = [];
  applicants.forEach((item) => {
    const applicantsStages = item.stages;
    const tableApplicant = {
      id: item.applicantId,
      fullName: [
        buildIconProfile(item.applicantId, handleProfile),
        item.fullName,
      ],
    };
    buildStages(
      stagesArray,
      applicantsStages,
      tableApplicant,
      bootcampStages,
      handleOpenStageModal
    );
    buildApplicants = [...buildApplicants, tableApplicant];
  });
  return buildApplicants;
}

function buildIconProfile(id, handleProfile) {
  return (
    <ActionIcons
      key="actionIcons"
      gridVariant="styleGrid"
      arrayIcons={[
        {
          name: "user outline",
          size: "small",
          onClick: handleProfile,
          variant: "styleIcons",
        },
      ]}
      id={id}
    />
  );
}

function buildStages(
  stagesArray,
  applicantsStages,
  tableApplicant,
  bootcampStages,
  handleOpenStageModal
) {
  stagesArray.forEach((stage) => {
    const currentBootcampStage = getCurrentStage(bootcampStages, stage);
    const nextStage = getNextStage(bootcampStages, currentBootcampStage);
    //Stage buttons must be created with general data (name and style)
    tableApplicant[`${stage}`] = (
      <ApplicantsTableActionButton
        arrayButtons={buildEmptyStage(stage)}
        id={tableApplicant.id}
      />
    );
    //Stage buttons are created with additional data to give it a behavior
    applicantsStages.forEach((applicantsStage) => {
      if (applicantsStage.stageName === stage) {
        tableApplicant[`${stage}`] = (
          <ApplicantsTableActionButton
            arrayButtons={buildNonEmptyStage(applicantsStage, handleOpenStageModal)}
            id={tableApplicant.id}
            bootcampStage={currentBootcampStage}
            idNextStage={nextStage ? nextStage.id : ""}
          />
        );
      }
    });
  });
}

// TODO we have the list of the stages and the name of the current stage, for now the "currentBootcampStage" 
// can be found from the name, but the problem is that it would happen if we have two names of the same stages, 
// for greater security you must add an order in the applicant's stages and find the nextStage with that data.
function getCurrentStage(bootcampStages, currentStageName) {
  const currentBootcampStage =
    bootcampStages[bootcampStages.findIndex(bootcampStage => bootcampStage.name === currentStageName)]
  return currentBootcampStage;
}
//TODO the next stage can be found from the list of stages and the name of the stages, for greater security 
// an order in the stages of the applicant must be added and so it looks for the nextStage with that data.
function getNextStage(bootcampStages, currentBootcampStage) {
  const currentStageOrder = currentBootcampStage.order
  const nextBootcampStage =
    bootcampStages[bootcampStages.findIndex(bootcampStage => bootcampStage.order === currentStageOrder + 1)];
  return nextBootcampStage;
}

function buildEmptyStage(stage) {
  return [
    {
      label: stage,
      variant: "styleButtons",
    },
  ];
}

function buildNonEmptyStage(stage, handleOpenStageModal) {
  return [
    stage.status !== ""
      ? {
          color: getStateColor(stage.status),
          label: stage.stageName,
          variant: "styleButtons",
          onClick: handleOpenStageModal,
        }
      : {
          label: stage.stageName,
          variant: "styleButtons",
          onClick: handleOpenStageModal,
        },
  ];
}

function getStateColor(status) {
  let color = "";
  switch (status) {
    case EnumQualificationStatus.PASSED:
      color = "green";
      break;
    case EnumQualificationStatus.IN_PROGRESS:
      color = "orange";
      break;
    case EnumQualificationStatus.FAILED:
      color = "red";
      break;
    case EnumQualificationStatus.QUALIFIED_AND_READONLY:
      color = "green";
      break;
    default:
      color = "";
  }
  return color;
}

const applicantStages = {
  buildApplicantStages,
};

export default applicantStages;
