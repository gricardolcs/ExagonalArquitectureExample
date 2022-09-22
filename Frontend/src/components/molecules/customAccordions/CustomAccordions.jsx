import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import { Grid, Accordion } from 'semantic-ui-react';
import CustomAccordionTitle from '../../atoms/customAccordionTitle/CustomAccordionTitle';
import './style.css';

function CustomAccordions({
    data,
    iconExpand,
    iconNonExpand,
    initialIndexes,
    handleOnNonExpand,
    variantInternalAccordion
}) {

    const [activeIndexes, setActiveIndexes] = useState(initialIndexes);

    const handleClick = (e, titleProps) => {
        const { index } = titleProps;
        if (activeIndexes.includes(index)) {
            const value = activeIndexes.indexOf(index);
            activeIndexes.splice(value, 1);
            initialIndexes.splice(value, 1);
            handleOnNonExpand();
        } else {
            activeIndexes.push(index);
        }
        setActiveIndexes([...activeIndexes]);
    };

    useEffect(() => {
        if (initialIndexes.length >= 0) {
            setActiveIndexes(initialIndexes);
        }
    }, [initialIndexes])

    return (
        <Grid className={variantInternalAccordion}>
            <Grid.Column>
                {data.map((item) =>
                    <Accordion key={item.title} >
                        <CustomAccordionTitle
                            active={activeIndexes.includes(item.title)}
                            index={item.title}
                            handleClick={handleClick}
                            title={item.title}
                            iconExpand={iconExpand}
                            iconNonExpand={iconNonExpand}
                        />
                        {activeIndexes.includes(item.title) &&
                            <Grid>
                                <Grid.Column>

                                {item.content}
                                </Grid.Column>
                            </Grid>}
                    </Accordion>
                )}
            </Grid.Column>
        </Grid>
    );
}

CustomAccordions.prototype = {
    data: PropTypes.array,
    iconExpand: PropTypes.string,
    iconNonExpand: PropTypes.string,
    initialIndexes: PropTypes.array,
    handleOnNonExpand: PropTypes.func,
    variantInternalAccordion: PropTypes.string
}

CustomAccordions.defaultProps = {
    data: [],
    iconExpand: 'caret square down outline',
    iconNonExpand: 'caret square left outline',
    initialIndexes: [],
    handleOnNonExpand: () => { },
    variantInternalAccordion: ''
}

export default CustomAccordions;