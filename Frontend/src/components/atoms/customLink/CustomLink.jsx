import React from 'react';
import PropTypes from 'prop-types';
import CustomLabel from "../customLabel/CustomLabel";
import './style.css'

function CustomLink({ value, handleOnClick }) {
    return (
        <CustomLabel
            variant='link'
            elementType='a'
            value={value}
            handleOnClick={handleOnClick}
        />
    )
}

CustomLink.prototype = {
    value: PropTypes.string,
    handleOnClick: PropTypes.func
}

CustomLink.defaultProps = {
    value: '',
    handleOnClick: () => { }
}

export default CustomLink;