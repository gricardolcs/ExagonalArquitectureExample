import React from 'react';
import PropTypes from 'prop-types';
import { Grid } from 'semantic-ui-react';
import CustomButton from '../../atoms/customButton/CustomButton';
import FormModal from '../../atoms/basicModal/BasicModal';
import './style.css';

function ApplicantModalResult({
    onClose,
    onOpen,
    open,
    handleCloseModal,
    projectName,
    result,
    header,
    className
}) {

    return (
        <FormModal
            onClose={onClose}
            onOpen={onOpen}
            open={open}
            header={header}
            classNameHeader={className}
        >
            {result.success && <>
                <Grid.Row className='import-results-data'>
                    <div className='modal-content'>{'Project:'}&nbsp;</div>
                    <div className='bold-content'>{projectName}</div>
                </Grid.Row>
                <Grid.Row className='import-results-data'>
                    <div className='modal-content summary-title'>{'Summary:'}</div>
                </Grid.Row>
                <Grid.Row className='import-results-data'>
                    <div className='bold-content big-dot-result'>{'·'}&nbsp;</div>
                    <div className='bold-content big-number-result'>{result.success.value}&nbsp;</div>
                    <div className='modal-content'>{result.success.message}</div>
                </Grid.Row>
                {result.success.render}
            </>}
            {result.fail && <>
                <Grid.Row className='import-results-data'>
                    <div className='bold-content big-dot-result'>{'·'}&nbsp;</div>
                    <div className='bold-content big-number-result'>
                        {result.fail.value}
                        &nbsp;
                    </div>
                    <div className='modal-content'>{result.fail.message}</div>
                </Grid.Row>
                {result.fail.render}
            </>}
            <Grid.Row>
                <Grid columns={2} textAlign='right'>
                    <Grid.Column width={13}></Grid.Column>
                    <Grid.Column width={3} textAlign='center'>
                        <CustomButton
                            label={'Done'}
                            onClick={handleCloseModal}
                            circular={false}
                            color={'blue'} />
                    </Grid.Column>
                </Grid>
            </Grid.Row>
        </FormModal>
    );
}

ApplicantModalResult.prototype = {
    onClose: PropTypes.func,
    onOpen: PropTypes.func,
    open: PropTypes.bool,
    handleCloseModal: PropTypes.func,
    projectName: PropTypes.string,
    result: PropTypes.object,
    header: PropTypes.string,
    className: PropTypes.string
}

ApplicantModalResult.defaultProps = {
    onClose: () => { },
    onOpen: () => { },
    open: false,
    handleCloseModal: () => { },
    projectName: '',
    result: {},
    header: '',
    className: ''
}

export default ApplicantModalResult;