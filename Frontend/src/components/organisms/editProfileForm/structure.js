import { Grid } from 'semantic-ui-react';
import CustomField from '../../atoms/customField/CustomField';

const setStructure = (fieldNames) => {
  return [
    <Grid>
      <Grid.Column width={8}>{fieldNames[0]}</Grid.Column>
    </Grid>,
    <Grid columns={2}>
      <Grid.Column width={8}>
        <CustomField>{fieldNames[1]}</CustomField>
      </Grid.Column>
      <Grid.Column width={8}>
        <CustomField>{fieldNames[2]}</CustomField>
      </Grid.Column>
    </Grid>,
    <Grid columns={2}>
      <Grid.Column width={8}>
        <CustomField>{fieldNames[3]}</CustomField>
      </Grid.Column>
      <Grid.Column width={8}>
        <CustomField>{fieldNames[4]}</CustomField>
      </Grid.Column>
    </Grid>,
    <Grid columns={2}>
      <Grid.Column width={8}>
        <CustomField>{fieldNames[5]}</CustomField>
      </Grid.Column>
    </Grid>,
  ];
};

const StructureEditUserProfile = {
  setStructure,
};

export default StructureEditUserProfile;
