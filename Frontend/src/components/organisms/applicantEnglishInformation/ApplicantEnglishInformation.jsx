import React from 'react'
import PropTypes from 'prop-types';
import { Grid, Header } from 'semantic-ui-react';
import RangeField from '../../molecules/rangeField/RangeField'
import './style.css'

function ApplicantEnglishInformation({ englishResults }) {
    const areas = ['Listening', 'Speaking', 'Writing', 'Reading'];

    function checkArea(actualArea) {
        const areaFiltered = englishResults.filter(area => area.label === actualArea);
        return areaFiltered.length > 0 ? areaFiltered[0].value : 0;
    }

    function checkNumberOfAreas() {
        return englishResults.length === 4 ? true : false;
    }

    return (
        <Grid>
            <Grid.Row columns={2} className='english-row'>
                <Grid.Column width={16}>
                    <Header className='english-header' as='h3' content='English Level' />
                </Grid.Column>
                <RangeField
                    key={areas[0]}
                    value={checkArea(areas[0])}
                    labelVariant=''
                    labelValue={
                        <Grid columns={2}>
                            <Grid.Row>
                                <Grid.Column width={8}>
                                    {areas[0]}
                                </Grid.Column>
                                <Grid.Column width={8} className='english-result-value'>
                                    {checkArea(areas[0]) ? checkArea(areas[0]) : '-'}%
                                </Grid.Column>
                            </Grid.Row>
                        </Grid>
                    }
                    labelWidth={16}
                    inputWidth={16}
                    readOnly
                />
                <RangeField
                    key={areas[1]}
                    value={checkArea(areas[1])}
                    labelVariant=''
                    labelValue={
                        <Grid columns={2}>
                            <Grid.Row>
                                <Grid.Column width={8}>
                                    {areas[1]}
                                </Grid.Column>
                                <Grid.Column width={8} className='english-result-value'>
                                    {checkArea(areas[1]) ? checkArea(areas[1]) : '-'}%
                                </Grid.Column>
                            </Grid.Row>
                        </Grid>
                    }
                    labelWidth={16}
                    inputWidth={16}
                    readOnly
                />
                <RangeField
                    key={areas[2]}
                    value={checkArea(areas[2])}
                    labelVariant=''
                    labelValue={
                        <Grid columns={2}>
                            <Grid.Row>
                                <Grid.Column width={8}>
                                    {areas[2]}
                                </Grid.Column>
                                <Grid.Column width={8} className='english-result-value'>
                                    {checkArea(areas[2]) ? checkArea(areas[2]) : '-'}%
                                </Grid.Column>
                            </Grid.Row>
                        </Grid>
                    }
                    labelWidth={16}
                    inputWidth={16}
                    readOnly
                />
                <RangeField
                    key={areas[3]}
                    value={checkArea(areas[3])}
                    labelVariant=''
                    labelValue={
                        <Grid columns={2}>
                            <Grid.Row>
                                <Grid.Column width={8}>
                                    {areas[3]}
                                </Grid.Column>
                                <Grid.Column width={8} className='english-result-value'>
                                    {checkArea(areas[3]) ? checkArea(areas[3]) : '-'}%
                                </Grid.Column>
                            </Grid.Row>
                        </Grid>
                    }
                    labelWidth={16}
                    inputWidth={16}
                    readOnly
                />
                <div className={'message-error'}>{checkNumberOfAreas() ? '' : '(-%) There is no English grades to display'}</div>
            </Grid.Row>
        </Grid>
    );
}

ApplicantEnglishInformation.prototype = {
    englishResults: PropTypes.array

}

ApplicantEnglishInformation.defaultProps = {
    englishResults: []
}

export default ApplicantEnglishInformation;