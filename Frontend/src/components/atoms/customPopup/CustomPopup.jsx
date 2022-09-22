import React from 'react';
import PropTypes from 'prop-types';
import { Popup } from 'semantic-ui-react';
import './style.css';

function CustomPopup({
    open,
    message,
    messageVariant,
    pointerVariant,
    position,
    color,
    size,
    trigger
}) {
    return (
        <>
            {open === null ?
                <Popup
                    className={`message pointer ${color} ${messageVariant} ${pointerVariant}`}
                    content={message}
                    position={`${position} center`}
                    size={size}
                    trigger={trigger}
                /> :
                <Popup
                    className='message pointer'
                    content={message}
                    open={open}
                    position={`${position} center`}
                    size={size}
                    trigger={trigger}
                />
            }
        </>
    );
}

CustomPopup.prototype = {
    open: PropTypes.bool,
    message: PropTypes.string,
    messageVariant: PropTypes.string,
    pointerVariant: PropTypes.string,
    position: PropTypes.string,
    color: PropTypes.string,
    size: PropTypes.string,
    trigger: PropTypes.node,
}

CustomPopup.defaultProps = {
    open: null,
    message: '',
    messageVariant: '',
    pointerVariant: '',
    position: 'right',
    color: 'red',
    size: null,
}

export default CustomPopup;