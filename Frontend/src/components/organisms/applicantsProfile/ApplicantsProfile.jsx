import React from 'react';
import PropTypes from 'prop-types';
import BasicCard from '../../atoms/customCard/BasicCard';
import CardContent from '../../atoms/customCard/CardContent';
import BasicImage from '../../atoms/basicImage/BasicImage';
import applicantsSmartphone from '../../../images/templates/applicantsProfile/applicantsSmartphone.png';
import applicantsEmail from '../../../images/templates/applicantsProfile/applicantsEmail.png';
import applicantsBirthday from '../../../images/templates/applicantsProfile/applicantsBirthday.png';
import applicantsAge from '../../../images/templates/applicantsProfile/applicantsAge.png';
import { Grid } from 'semantic-ui-react';
import NameInitial from '../../../utils/nameInitialUtils/NameInitial';
import './style.css';

function ApplicantsProfile({ applicant }) {
  return (
    <Grid textAlign="center">
      <BasicCard fluid className="styleModal">
        <CardContent textAlign="center">
          <Grid>
            <Grid.Column className="styleGridColumn" width={6}>
              {applicant.photo === '' ? (
                <div className="styleApplicantInitial">
                  <p>{NameInitial.getNameInitial(applicant.fullName)}</p>
                </div>
              ) : (
                <BasicImage
                  className="styleApplicantPhoto"
                  circular
                  size="tiny"
                  src={applicant.photo}
                />
              )}
            </Grid.Column>
            <Grid.Column
              className="styleGridColumn"
              width={10}
              textAlign="left"
            >
              <Grid.Row className="styleApplicantData">
                {applicant.fullName}
              </Grid.Row>
              <Grid>
                <Grid.Column width={1}></Grid.Column>
                <Grid.Column className="stylePersonalData" width={12}>
                  <Grid.Row>
                    <p className="styleTruncated">
                      <BasicImage
                        size="mini"
                        className="stylePersonalPicture"
                        src={applicantsEmail}
                      />
                      {applicant.email}
                    </p>
                  </Grid.Row>
                  <Grid.Row>
                    <BasicImage
                      size="mini"
                      className="stylePersonalPicture"
                      src={applicantsSmartphone}
                    />
                    {applicant.telephone}
                  </Grid.Row>
                  <Grid.Row>
                    <BasicImage
                      size="mini"
                      className="stylePersonalPicture"
                      src={applicantsBirthday}
                    />
                    {applicant.birthday}
                  </Grid.Row>
                  <Grid.Row>
                    <BasicImage
                      size="mini"
                      className="stylePersonalPicture"
                      src={applicantsAge}
                    />
                    {applicant.age} years
                  </Grid.Row>
                </Grid.Column>
              </Grid>
            </Grid.Column>
          </Grid>
          <Grid textAlign="center">
            <Grid.Column width={5} textAlign="left" className="styleColumn">
              <Grid.Row className="styleApplicantData">Current City:</Grid.Row>
            </Grid.Column>
            <Grid.Column width={7} textAlign="right" className="styleColumn">
              <Grid.Row>{applicant.city}</Grid.Row>
            </Grid.Column>
          </Grid>
          <Grid textAlign="center">
            <Grid.Column width={5} textAlign="left" className="styleColumn">
              <Grid.Row className="styleApplicantData">Career:</Grid.Row>
            </Grid.Column>
            <Grid.Column width={7} textAlign="right" className="styleColumn">
              <Grid.Row>{applicant.career}</Grid.Row>
            </Grid.Column>
          </Grid>
          <Grid textAlign="center">
            <Grid.Column width={5} textAlign="left" className="styleColumn">
              <Grid.Row className="styleApplicantData">Degree:</Grid.Row>
            </Grid.Column>
            <Grid.Column width={7} textAlign="right" className="styleColumn">
              <Grid.Row className="styleTruncated">{applicant.degree}</Grid.Row>
            </Grid.Column>
          </Grid>
        </CardContent>
      </BasicCard>
    </Grid>
  );
}

ApplicantsProfile.prototype = {
  applicant: PropTypes.shape({
    id: PropTypes.string,
    name: PropTypes.string,
    email: PropTypes.string,
    photo: PropTypes.string,
    telephone: PropTypes.string,
    birthday: PropTypes.string,
    age: PropTypes.string,
    city: PropTypes.string,
    career: PropTypes.string,
    degree: PropTypes.string,
  }),
};

ApplicantsProfile.defaultProps = {
  applicant: {
    id: '',
    name: '',
    email: '',
    photo: '',
    telephone: '',
    birthday: '',
    age: '',
    city: '',
    career: '',
    degree: '',
  },
};

export default ApplicantsProfile;
