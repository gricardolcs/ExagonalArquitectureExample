import React from 'react';
import PropTypes from 'prop-types';
import { Grid, Icon, Message } from 'semantic-ui-react';
import './style.css'

function CustomMessage({
    iconName,
    message,
    color,
    size
}) {
    return (
        <Message id='message' className={color} size={size}>
            {iconName ? (
                <Grid columns={2}>
                    <Grid.Column width={1} verticalAlign='middle'>
                        <Icon name={iconName} size='large' />
                    </Grid.Column>
                    <Grid.Column width={13} verticalAlign='middle'>
                        {message}
                    </Grid.Column>
                </Grid>
            ) : (
                <Grid columns={1}>
                    <Grid.Column width={14} verticalAlign='middle'>
                        {message}
                    </Grid.Column>
                </Grid>
            )}
        </Message>

    )
}

CustomMessage.prototype = {
    iconName: PropTypes.string,
    message: PropTypes.string,
    color: PropTypes.string,
    size: PropTypes.string
}

CustomMessage.defaultProps = {
    iconName: '',
    message: '',
    color: 'black',
    size: 'tiny'
}

export default CustomMessage;