import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import { Form } from 'semantic-ui-react';
import './style.css';

function RangeInput({ variant, value, onChange, min, max, readOnly }) {

    const [number, setNumber] = useState(0);

    const handleChange = ({ target }) => {
        if (!readOnly) {
            setNumber(target.value);
            onChange(target.value);
        }
    }

    useEffect(() => {
        setNumber(value); 
     }, [value]);

    return (
        <Form.Input
            style={{ width: '100%' }}
            type="range"
            input={{ className: `custom-range-input ${variant}` }}
            min={min}
            width={16}
            max={max}
            value={number}
            onChange={handleChange}
        />
    );
}

RangeInput.prototype = {
    value: PropTypes.number,
    onChange: PropTypes.func,
    min: PropTypes.number,
    max: PropTypes.number,
    readOnly: PropTypes.bool
}

RangeInput.defaultProps = {
    value: 0,
    onChange: () => { },
    min: 0,
    max: 100,
    readOnly: false
}

export default RangeInput;