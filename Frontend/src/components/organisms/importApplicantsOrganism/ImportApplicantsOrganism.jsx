import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { Grid } from 'semantic-ui-react';
import CustomButton from '../../atoms/customButton/CustomButton';
import FormModal from '../../atoms/basicModal/BasicModal';
import CustomDragAndDrop from '../../atoms/customDragAndDrop/CustomDragAndDrop';
import { uploadApplicantsByCsvFile } from "../../../api/applicant/upload/ApplicantsFileUpload";
import bootcampFindById from "../../../api/bootcamp/fetch/BootcampFindById";
import ApplicantModalResult from '../../molecules/applicantModalResult/ApplicantModalResult';
import './style.css'
import CustomIcon from '../../atoms/customIcon/CustomIcon';

function ImportApplicantsOrganism({ onClose, onOpen, open, updateData }) {
    const [importCounter, setImportCounter] = useState(0);
    const [openResultModal, setOpenResultModal] = useState(false);
    const [file, setFile] = useState({});
    const [actualProject, setActualProject] = useState({});
    const [importResult, setImportResult] = useState({});

    function handleAcceptNewFile(newFile) {
        setFile(newFile[0])
    }

    function handleCloseModal() {
        setFile({});
        onClose();
    }

    function handleUploadAndShowResults() {
        onClose();
        handleUploadData(file);
        setOpenResultModal(true);
    }

    const handleUploadData = async (file) => {
        const projectId = sessionStorage.getItem("currentProjectId");
        bootcampFindById(projectId, setActualProject);
        const fileUploadResult = await uploadApplicantsByCsvFile(projectId, file);
        if (fileUploadResult) {
            setImportResult(fileUploadResult);
        }
    };

    function handleCloseModalAndExportedData() {
        setFile({});
        setOpenResultModal(false);
    }

    useEffect(() => {
        return () => {
            if (openResultModal) {
                updateData(importCounter);
                setImportCounter(importCounter + 1);
            }
        }
    }, [openResultModal, updateData, setImportCounter, importCounter]);

    return (
        <>
            <FormModal
                onClose={onClose}
                onOpen={onOpen}
                open={open}
                header={'Import from file'}
                classNameHeader={'headerText'}
            >
                <Grid.Row>
                    <div className='modal-content'>{'Import your file from your computer in an EXCEL format.'}</div>
                </Grid.Row>
                <Grid.Row>
                    <div className='modal-content'>{'File must have 6 columns with the titles in this order:'}</div>
                </Grid.Row>
                <Grid.Row className='titles-content'>
                    <div className='bold-content'>{'NAME, LASTNAME, EMAIL, PHONE, PROFESSION'}</div>
                    <div className='modal-content'>&nbsp;{'and'}&nbsp;</div>
                    <div className='bold-content'>{'CITY'}</div>
                </Grid.Row>
                <Grid.Row>
                    <CustomDragAndDrop
                        onDropAccepted={handleAcceptNewFile}
                        acceptedFiles={'.csv'}
                        file={file} />
                </Grid.Row>
                <Grid.Row>
                    <Grid columns={2} textAlign='right'>
                        <Grid.Column width={11}></Grid.Column>
                        <Grid.Column width={2} textAlign='center'>
                            <CustomButton
                                label={'Cancel'}
                                onClick={handleCloseModal}
                                circular={false} />
                        </Grid.Column>
                        <Grid.Column width={3} textAlign='center'>
                            <CustomButton
                                disabled={!file.name}
                                label={'Import'}
                                onClick={handleUploadAndShowResults}
                                circular={false}
                                color={'blue'} />
                        </Grid.Column>
                    </Grid>
                </Grid.Row>
            </FormModal>
            {importResult.failedRows && importResult.applicantsIds &&
                <ApplicantModalResult
                    onClose={() => setOpenResultModal(false)}
                    onOpen={() => setOpenResultModal(true)}
                    open={openResultModal}
                    handleCloseModal={handleCloseModalAndExportedData}
                    projectName={actualProject.name}
                    result={{
                        success: { value: importResult.applicantsIds.length, message: 'Applicants were imported successfully', render: '' },
                        fail: {
                            value: importResult.failedRows.length, message: 'Applicants could not be imported',
                            render: <Grid.Row className='import-results-data row-errors'>
                                {importResult.failedRows.length > 0 &&
                                    <>
                                        <CustomIcon name='warning sign' disabled={true} className='warning-error' />
                                        <div className='modal-content'>&nbsp;{'Errors found on rows:'}&nbsp;</div>
                                        <div className='modal-content'>
                                            {importResult.failedRows.length > 0 ? importResult.failedRows.join(', ') : '-'}
                                        </div>
                                    </>
                                }
                            </Grid.Row>
                        }
                    }}
                    header={'Import from file'}
                    className={'headerText'} />
            }
        </>
    );
}

ImportApplicantsOrganism.prototype = {
    onClose: PropTypes.func,
    onOpen: PropTypes.func,
    open: PropTypes.bool,
    updateData: PropTypes.func,
}

ImportApplicantsOrganism.defaultProps = {
    onClose: () => { },
    onOpen: () => { },
    open: false,
    updateData: () => { },
}

export default ImportApplicantsOrganism;