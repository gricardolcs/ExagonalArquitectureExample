import { Grid } from 'semantic-ui-react';
import CustomField from '../../components/atoms/customField/CustomField';

function setStructure(fieldNames) {
  return (
    <Grid>
      <Grid.Column>
        <CustomField>{fieldNames[0]}</CustomField>
      </Grid.Column>
    </Grid>
  );
}

const NaturalNumberBlockAttempts = {
  setStructure,
};

export default NaturalNumberBlockAttempts;
