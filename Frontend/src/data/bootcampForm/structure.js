import { Grid } from 'semantic-ui-react';
import CustomField from '../../components/atoms/customField/CustomField';
import { FORM_ROW } from '../../utils/constants/constants';

function setStructure(buildFields) {

    return [
            <Grid.Row className={FORM_ROW}>
              <CustomField>
                {buildFields[0]}
              </CustomField>
            </Grid.Row>,

            <Grid.Row className={FORM_ROW}>
              <CustomField>
              {buildFields[1]}
              </CustomField>
            </Grid.Row>,

            <Grid.Row className={FORM_ROW}>
              <CustomField>
              {buildFields[2]}
              </CustomField>
            </Grid.Row>,

            <Grid.Row className={FORM_ROW}>
              <Grid>
                <Grid.Column width={8}>
                  <CustomField>
                  {buildFields[3]}
                  </CustomField>
                </Grid.Column>
                <Grid.Column width={8}>
                  <CustomField>
                  {buildFields[4]}
                  </CustomField>
                </Grid.Column>
              </Grid>
            </Grid.Row>,

            <Grid.Row className={FORM_ROW}>
              <Grid>
                <Grid.Column width={8}>
                  <CustomField>
                  {buildFields[5]}
                  </CustomField>
                </Grid.Column>
                <Grid.Column width={8}>
                  <CustomField>
                  {buildFields[6]}
                  </CustomField>
                </Grid.Column>
              </Grid>
            </Grid.Row>,

            <Grid.Row className={FORM_ROW}>
              <CustomField>
                {buildFields[7]}
              </CustomField>
            </Grid.Row>,

            <Grid.Row className={FORM_ROW}>
              <CustomField>
              {buildFields[8]}
              </CustomField>
            </Grid.Row>
    ];
} 

const StructureApplicantForm = {
    setStructure
}

export default StructureApplicantForm;
