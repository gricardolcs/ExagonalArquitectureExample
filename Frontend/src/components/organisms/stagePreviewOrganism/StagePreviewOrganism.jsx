import React, { useCallback, useEffect, useState } from "react";
import PropTypes from 'prop-types';
import { Grid, Header } from "semantic-ui-react";
import BasicImage from "../../atoms/basicImage/BasicImage";
import CustomLabel from "../../atoms/customLabel/CustomLabel";
import iconPencil from "../../../images/icons/icon-pencil.png";
import StageFormOrganism from "../stageFormOrganism/StageFormOrganism";
import StageInformation from "../../molecules/stageInformation/StageInformation";
import EnumQualificationStatus from "./EnumQualificationStatus";
import GetDateUtils from "../../../utils/getValue/getValueOrCurrentDate";
import './style.css';

export function StagePreviewOrganism({ stage, isTheLastStage, bootcampId, nextStage, statuses, onSubmit, onSubmitFileReport, onDownloadFile }) {

    const [edit, setEdit] = useState(false);
    const [availableToEdit, setAvailableToEdit] = useState(false);
    const [file, setFile] = useState({});

    const handleOnSubmit = (data) => {
        data.nextStageId = nextStage.stageId;
        onSubmit(bootcampId, stage.stageId, data);
        if (file.name) {
            onSubmitFileReport(file, bootcampId);
        }
        setEdit(false);
    }

    const handleOnFile = (file) => {
        setFile(file);
    }

    const checkIfItIsAvailableToEdit = useCallback(() => {
        if (stage.qualificationStatus === EnumQualificationStatus.IN_PROGRESS) {
            setAvailableToEdit(true);
        } else if (!isTheLastStage(nextStage)) {
            setAvailableToEdit(nextStage.qualificationStatus === EnumQualificationStatus.IN_PROGRESS
                || nextStage.qualificationStatus === '' || isTheLastStage(nextStage));
        } else {
            setAvailableToEdit(nextStage.applicantQualification[0].selectedElement === "In progress");
        } 
    }, [stage.qualificationStatus, isTheLastStage, nextStage])

    useEffect(() => {
        checkIfItIsAvailableToEdit();
        return () => {
            setEdit(false);
            setAvailableToEdit(false);
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [stage, checkIfItIsAvailableToEdit])

    return (<Grid className="preview-stage" >
        <Grid.Row>
            <Grid.Column width={14}>
                <Header textAlign='left' as='h5' content={`${stage.stageName} stage`} />
            </Grid.Column>
            <Grid.Column textAlign='right' width={2}>
                {((availableToEdit && !edit) || isTheLastStage(stage)) &&
                    <CustomLabel variant='edit' handleOnClick={() => setEdit(true)} disabled={edit}>
                        <BasicImage className='icon-edit' src={iconPencil} />
                        Edit
                    </CustomLabel>}
            </Grid.Column>
        </Grid.Row>
        {(availableToEdit || isTheLastStage(stage)) && edit ?
            <StageFormOrganism
                stageName={stage.stageName}
                schema={stage.applicantQualification}
                comment={stage.comment}
                appliedDate={stage.appliedDate}
                submitDate={GetDateUtils.getValueOrCurrentDate(stage.submitDate)}
                qualificationStatus={stage.qualificationStatus}
                statuses={statuses}
                onCancel={() => setEdit(false)}
                onSubmitFileReport={handleOnFile}
                onSubmit={handleOnSubmit}
            /> : <Grid.Row>
                <Grid.Column>
                    <StageInformation stage={stage} handleOnFile={(setFile) => onDownloadFile(bootcampId, setFile)} />
                </Grid.Column>
            </Grid.Row>
        }
    </Grid>)
}

StagePreviewOrganism.prototype = {
    stage: PropTypes.objectOf(PropTypes.shape({
        stageName: PropTypes.string,
        comment: PropTypes.string,
        applicantQualification: PropTypes.arrayOf(PropTypes.shape({
            label: PropTypes.string,
            qualification: PropTypes.number,
            type: PropTypes.string
        })),
        qualificationStatus: PropTypes.string,
        isEnglishType: PropTypes.bool
    })),
    isTheLastStage: PropTypes.func,
    bootcampId: PropTypes.number.isRequired,
    nextStage: PropTypes.object,
    statuses: PropTypes.array,
    onSubmit: PropTypes.func,
    onSubmitFileReport: PropTypes.func,
    onDownloadFile: PropTypes.func
}

StagePreviewOrganism.defaultProps = {
    stage: {},
    isTheLastStage: () => { },
    nextStage: {},
    statuses: [],
    onSubmit: () => { },
    onSubmitFileReport: () => { },
    onDownloadFile: () => { }
}

export default StagePreviewOrganism;