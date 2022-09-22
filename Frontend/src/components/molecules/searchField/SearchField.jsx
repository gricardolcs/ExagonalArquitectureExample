import React, { useState } from "react";
import PropTypes from 'prop-types';
import BasicForm from "../../atoms/basicForm/BasicForm";
import TextInput from '../../atoms/textInput/TextInput';
import './style.css';

function SearchField({ handleOnPressingEnter, placeholder, icon }) {

    const [text, setText] = useState('');

    return (
        <BasicForm handleOnSubmit={() => handleOnPressingEnter(text)}>
            <TextInput
              className='inputSearch'
              placeholder={placeholder}
              value={text}
              onChange={setText}
              size='small'
              icon={icon}
            />
        </BasicForm>
    )
}

SearchField.prototype = {
    handleOnPressingEnter: PropTypes.func,
    placeholder: PropTypes.string,
    icon: PropTypes.string
}

SearchField.defaultProps = {
    handleOnPressingEnter: () => { },
    placeholder: 'Search',
    icon: ''
}

export default SearchField;
