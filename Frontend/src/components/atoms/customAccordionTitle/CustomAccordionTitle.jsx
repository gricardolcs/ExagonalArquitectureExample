import React from 'react'
import PropTypes from 'prop-types';
import { Accordion, Divider, Grid, Icon } from 'semantic-ui-react';
import './style.css'

function CustomAccordionTitle({
    active,
    index,
    title,
    iconExpand,
    iconNonExpand,
    handleClick
}) {
    return (
        <Accordion.Title
            style={{padding: '0.5em 1em'}}
            active={active}
            index={index}
            onClick={handleClick}
        >
            <Grid>
                <Grid.Row columns={2} className='projectInformationTitle' >
                    <Grid.Column width={15} >
                        <h5>{title}</h5>
                    </Grid.Column>
                    <Grid.Column textAlign='right' width={1} >
                        {active ?
                            <Icon name={iconExpand} />
                            : <Icon name={iconNonExpand} />}
                    </Grid.Column>
                </Grid.Row>
            </Grid>
            <Divider fitted />
        </Accordion.Title>
    );
}

CustomAccordionTitle.prototype= {
    active: PropTypes.bool,
    index: PropTypes.string,
    title: PropTypes.string,
    iconExpand: PropTypes.string,
    iconNonExpand: PropTypes.string,
    handleClick: PropTypes.func
}

CustomAccordionTitle.defaultProps = {
    active: false,
    index: '',
    title: '',
    iconExpand: 'caret square down outline',
    iconNonExpand: 'caret square left outline',
    handleClick: () => {}
}

export default CustomAccordionTitle;