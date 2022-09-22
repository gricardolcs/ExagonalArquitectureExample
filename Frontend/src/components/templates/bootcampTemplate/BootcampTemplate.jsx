import React from "react";
import PropTypes from 'prop-types';
import BasicImage from '../../../components/atoms/basicImage/BasicImage'
import CustomIcon from '../../atoms/customIcon/CustomIcon';
import { Loading } from '../../atoms/loading/Loading'
import bootcampImage from '../../../images/atoms/customCard/default-bootcamp-image.jpg';
import convertDateFormat from '../../../utils/dateConvertFormatUtils/DateConvertFormatUtils'
import verifyDateInRange from '../../../utils/verifyDateInRange/VerifyDateInRangeUtils'
import { Grid } from 'semantic-ui-react';
import './style.css'

function BootcampTemplate({
  item,
  handleNavigateBootcampListPage,
  handleEdit,
  handleDelete
}) {

  if (item === undefined) {
    return <Loading></Loading>
  }

  const iconSizeArrow = 'huge';
  const iconSize = 'large';
  const imageSize = 'small';
  const statusActive = 'Active';
  const statusInactive = 'Inactive';
  const isActive = verifyDateInRange.verify(item['startDate'], item['endDate']);
  const status = isActive ? statusActive : statusInactive;

  return (
    <Grid stackable container>
      <Grid.Column width={4} textAlign="left" className="arrow-section">
        <Grid container textAlign="justified">
          <Grid.Row className="bootcamp-arrow paddingZero">
            <CustomIcon
              size={iconSizeArrow}
              name="arrow alternate circle left outline"
              onClick={() => handleNavigateBootcampListPage()}
            />
          </Grid.Row>
          <Grid.Row className="bootcamp-circle paddingZero">
            <BasicImage
              src={bootcampImage}
              size={imageSize}
              className='bootcamp-image-container'
            />
          </Grid.Row>
        </Grid>
      </Grid.Column>
      <Grid.Column width={12} textAlign="right" className="bootcamp-details">
        <Grid textAlign="center" className="container-details">
          <Grid.Row className="text-format text-tittle">
            <Grid.Column textAlign="left" width={16} className="paddingZero text-tittle">
              {item.name}
            </Grid.Column>
          </Grid.Row>
            <Grid.Row className="paddingZero">
              <Grid.Column textAlign="right" width={16} className="paddingZero date-section">
                <p className='text-format paddingZero date-margin'>
                  Start date: <span>{convertDateFormat.convert(item.startDate)}</span></p>
                <p className='text-format paddingZero date-margin'>
                  End date: <span>{convertDateFormat.convert(item.endDate)}</span></p>
              </Grid.Column>
            </Grid.Row>
          <Grid.Row className="text-format paddingZero status">
            <Grid.Column textAlign="left" width={16} className="paddingZero">
              {status}
            </Grid.Column>
          </Grid.Row>
          <Grid.Row className="description paddingZero">{item.description}</Grid.Row>
          <Grid.Row>
            <Grid.Column textAlign="right" width={16} className="paddingZero">
              <CustomIcon
                name="pencil alternate"
                size={iconSize}
                onClick={() => handleEdit(item)} />
              <CustomIcon
                name='trash alternate outline'
                color='red'
                size={iconSize}
                onClick={() => handleDelete(item)} />
              </Grid.Column>
            </Grid.Row>
          </Grid>
        </Grid.Column>
    </Grid>
  );
};

BootcampTemplate.prototype = {
  item: PropTypes.object,
  handleNavigateBootcampListPage: PropTypes.func,
  handleEdit: PropTypes.func,
  handleDelete: PropTypes.func
}

BootcampTemplate.defaultProps = {
  handleNavigateBootcampListPage: () => { },
  handleEdit: () => { },
  handleDelete: () => { }
}

export default BootcampTemplate;
