import React, { useCallback, useEffect, useState } from "react";
import PropTypes from 'prop-types';
import ActionLabels from "../../molecules/actionLabels/ActionLabels";
import { Grid } from "semantic-ui-react";
import SearchField from "../../molecules/searchField/SearchField";

function ApplicantSearchAndFilterOrganism({
    stages,
    handleOnClickOnStage,
    handleOnSearch
}) {
    const [filters, setFilters] = useState([]);
    const [filtersSelected, setFiltersSelected] = useState([]);

    /*
     Since all filters have been selected, we will change  
     filtersSelected to be an empty array which is 
     a convention for 'all'.
    */
    const handleOnClickOnFilterAll = useCallback(() => {
        setFiltersSelected([]);
        handleOnClickOnStage([]);
    }, [handleOnClickOnStage])

    const handleOnClick = useCallback((selectedFilter) => {
        const stageId = selectedFilter.id;
        if (!filtersSelected.includes(stageId)) {
            filtersSelected.push(stageId);
        } else {
            const index = filtersSelected.indexOf(stageId);
            filtersSelected.splice(index, 1);
        }
        /*
         Since all filters have been selected, we will change 
         filtersSelected to be an empty array which is 
         a convention for 'all'.
        */
        if (filtersSelected.length === stages.length) {
            filtersSelected.splice(0, filtersSelected.length);
        }
        setFiltersSelected([...filtersSelected]);
        handleOnClickOnStage([...filtersSelected]);
    }, [filtersSelected, stages.length, handleOnClickOnStage]);

    const loadFilters = useCallback((stages) => {
        const newFilters = stages.map((item) => {
            return {
                id: item.id,
                content: item.name,
                size: 'large',
                onClick: handleOnClick,
                color: filtersSelected.includes(item.id) ? 'blue' : null,
            }
        })

        /*
         The All filter is not part of the stages obtained from 
         the backend, it is used to filter everything.
        */
        const firstFilter = {
            id: 'All',
            content: 'All',
            size: 'large',
            onClick: handleOnClickOnFilterAll,
            color: !filtersSelected.length ? 'blue' : null,
        }
        setFilters([firstFilter, ...newFilters])
    }, [filtersSelected, handleOnClick, handleOnClickOnFilterAll])

    useEffect(() => {
        loadFilters(stages)
    }, [loadFilters, stages])

    return (
        <Grid container stackable columns='equal'>
            <Grid.Row columns='equal' verticalAlign='middle' className='rowFilter' color='grey'>
                <ActionLabels arrayLabels={filters} />
                <Grid.Column width={4} textAlign='right' verticalAlign='middle'>
                    <SearchField handleOnPressingEnter={handleOnSearch} />
                </Grid.Column>
            </Grid.Row>
        </Grid>
    )
}

ApplicantSearchAndFilterOrganism.prototype = {
    stages: PropTypes.array,
    handleOnClickOnStage: PropTypes.func,
    handleOnSearch: PropTypes.func,
    stagesSelected: PropTypes.array
}

ApplicantSearchAndFilterOrganism.defaultProps = {
    stages: [],
    handleOnClickOnStage: () => { },
    handleOnSearch: () => { },
    stagesSelected: []
}

export default ApplicantSearchAndFilterOrganism;