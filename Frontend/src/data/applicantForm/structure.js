import { Grid } from 'semantic-ui-react';
import CustomField from '../../components/atoms/customField/CustomField';

function setStructure(fieldNames) {
    return [
        <Grid>
            <Grid.Column width={8}>
                {fieldNames[0]}
            </Grid.Column>
            <Grid.Column width={8}>
                <CustomField>
                    {fieldNames[1]}
                </CustomField>
                <CustomField>
                    {fieldNames[2]}
                </CustomField>
            </Grid.Column>
        </Grid>,
        <Grid>
            <Grid.Column width={8}>
                <CustomField>
                    {fieldNames[3]}
                </CustomField>
            </Grid.Column>
            <Grid.Column width={8}>
                <CustomField>
                    {fieldNames[4]}
                </CustomField>
            </Grid.Column>
        </Grid>,
        <Grid>
            <Grid.Column width={8}>
                <CustomField>
                    {fieldNames[5]}
                </CustomField>
            </Grid.Column>
            <Grid.Column width={8}>
                <CustomField>
                    {fieldNames[6]}
                </CustomField>
        </Grid.Column>
        </Grid>,
        <Grid>
            <Grid.Column width={8}>
                <CustomField>
                    {fieldNames[7]}
                </CustomField>
            </Grid.Column>
            <Grid.Column width={8}>
                <CustomField>
                    {fieldNames[8]}
                </CustomField>
            </Grid.Column>
        </Grid>,
        <Grid>
            <Grid.Column width={8}>
                <CustomField>
                    {fieldNames[9]}
                </CustomField>
            </Grid.Column>
            <Grid.Column width={8}></Grid.Column>
        </Grid>,
        <Grid>
            <Grid.Column width={16}> 
                <CustomField>
                    {fieldNames[10]}
                </CustomField>
            </Grid.Column>
        </Grid>
    ];
} 

const StructureApplicantForm = {
    setStructure
}

export default StructureApplicantForm;
