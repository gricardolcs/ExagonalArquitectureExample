import React from 'react'
import PropTypes from 'prop-types';
import { Grid } from 'semantic-ui-react';
import CustomMenu from '../../molecules/customMenu/CustomMenu';
import cancel from '../../../images/icons/icon-cancel.png';
import more from '../../../images/icons/icon-more.png';
import BasicImage from '../../atoms/basicImage/BasicImage';
import './style.css'

function ProjectContent({
    menuData,
    handleOnClose
}) {
    return (
        <Grid>
            <Grid.Column width={16} textAlign='right' className='optionsColumn' >
                <BasicImage src={more} className='projectContentImage' />
                <BasicImage src={cancel} className='projectContentImage' onClick={handleOnClose} />
            </Grid.Column>
            <Grid.Column width={16} className='menuColumn'>
                <CustomMenu data={menuData} />
            </Grid.Column>
        </Grid>
    );
}

ProjectContent.prototype = {
    menuData: PropTypes.array,
    handleOnClose: PropTypes.func
}

ProjectContent.defaultProps = {
    menuData: [],
    handleOnClose: () => {}
}

export default ProjectContent;