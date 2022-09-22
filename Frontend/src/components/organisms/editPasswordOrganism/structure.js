import { Grid } from 'semantic-ui-react';
import CustomField from '../../atoms/customField/CustomField';

const setStructure = (fieldNames) => {
  return [
    <Grid>
      <Grid.Column>
        <CustomField>{fieldNames[0]}</CustomField>
      </Grid.Column>
    </Grid>,
    <Grid>
      <Grid.Column>
        <CustomField>{fieldNames[1]}</CustomField>
      </Grid.Column>
    </Grid>,
    <Grid>
      <Grid.Column>
        <CustomField>{fieldNames[2]}</CustomField>
      </Grid.Column>
    </Grid>,
  ];
};

const StructureEditPasswordUserProfile = {
  setStructure,
};

export default StructureEditPasswordUserProfile;
