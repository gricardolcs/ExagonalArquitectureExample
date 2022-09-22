import { Grid } from 'semantic-ui-react';
import CustomField from '../../components/atoms/customField/CustomField';

function setStructure(fields) {
  return [
    <Grid>
      <Grid.Column width={16}>
        <Grid.Row>
          <CustomField>{fields[0]}</CustomField>
        </Grid.Row>
        <Grid.Row>
          <CustomField>{fields[1]}</CustomField>
        </Grid.Row>
        <Grid.Row>
          <CustomField>{fields[2]}</CustomField>
        </Grid.Row>
      </Grid.Column>
    </Grid>,
  ];
}

const StructureApplicantForm = {
  setStructure,
};

export default StructureApplicantForm;
