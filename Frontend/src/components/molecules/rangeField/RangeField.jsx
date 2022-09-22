import React from 'react';
import PropTypes from 'prop-types';
import RangeInput from '../../atoms/rangeInput/RangeInput';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import { Fragment } from 'react';
import { Grid } from 'semantic-ui-react';
import './style.css';

function RangeField({
    value,
    rangeMin,
    rangeMax,
    rangeVariant,
    readOnly,
    inputWidth,
    onChange,
    labelVariant,
    labelValue,
    labelWidth,
}) {
    return (
        <Fragment>
            <Grid.Column className='custom-label-column' width={labelWidth} verticalAlign='bottom' textAlign='left' >
                <CustomLabel
                    variant={`custom-label-field ${labelVariant}`}
                >
                    {labelValue}
                </CustomLabel>
            </Grid.Column>
            <Grid.Column className='custom-input-column' width={inputWidth} verticalAlign='bottom'>
                <RangeInput
                    value={value}
                    variant={rangeVariant}
                    min={rangeMin}
                    max={rangeMax}
                    readOnly={readOnly}
                    onChange={onChange}
                />
            </Grid.Column>
        </Fragment>
    );   
}

RangeField.prototype = {
    value: PropTypes.number,
    rangeMin: PropTypes.number,
    rangeMax: PropTypes.number,
    rangeVariant: PropTypes.string,
    readOnly: PropTypes.bool,
    inputWidth: PropTypes.number,
    onChange: PropTypes.func,
    labelVariant: PropTypes.string,
    labelValue: PropTypes.node,
    labelWidth: PropTypes.string,
}

RangeField.defaultProps = {
    value: null,
    rangeMin: 0,
    rangeMax: 100,
    rangeVariant: '',
    readOnly: false,
    inputWidth: '11',
    onChange: () => {},
    labelVariant: '',
    labelWidth: '5',
}

export default RangeField;