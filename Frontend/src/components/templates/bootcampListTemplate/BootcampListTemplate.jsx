import React from "react";
import PropTypes from 'prop-types';
import CustomIcon from '../../atoms/customIcon/CustomIcon'
import BasicImage from '../../atoms/basicImage/BasicImage'
import { Loading } from '../../atoms/loading/Loading'
import bootcampImage from '../../../images/atoms/customCard/default-bootcamp-image.jpg';
import convertDateFormat from '../../../utils/dateConvertFormatUtils/DateConvertFormatUtils'
import verifyDateInRange from '../../../utils/verifyDateInRange/VerifyDateInRangeUtils'
import './style.css';

const BootcampListTemplate = ({
  tableData,
  handleEdit,
  handleDelete,
  handleNavigate
}) => {

  const imageSize = 'small';
  const iconSize = 'large';
  const statusActive = 'Active';
  const statusInactive = 'Inactive';
  const iconNameEdit = 'pencil alternate';
  const iconNameDelete = 'trash alternate outline';
  const iconColorDelete = 'red';
  const statusClassNameActive = 'bootcamp-table-status-label-active';
  const statusClassNameInactive = 'bootcamp-table-status-label-inactive';

  if (tableData === undefined) {
    return <Loading></Loading>
  }

  return (
    <div>
      {
        tableData.map(item => {
          const isActive = verifyDateInRange.verify(item['startDate'], item['endDate']);
          const status = isActive ? statusActive : statusInactive;
          const statusClassName = isActive ? statusClassNameActive : statusClassNameInactive;
          return (
            <div key={`row-${item.id}`} className='bootcamp-table-container-row'>
              <div className='bootcamp-table-leftbox'>
                <BasicImage
                  src={bootcampImage}
                  size={imageSize}
                  className='bootcamp-table-image-container'
                />
                <p className='bootcamp-table-name icon' onClick={() => handleNavigate(item)}>
                  {item['name']}
                </p>
              </div>
              <div className='bootcamp-table-middlebox'>
                <div className='bootcamp-table-status'>
                  <p className={`bootcamp-table-status-label ${statusClassName}`}>
                    {status}
                  </p>
                </div>
                <p className='bootcamp-table-description'>
                  {item['description']}
                </p>
                <div className='bootcamp-table-action'>
                  <div className='bootcamp-table-action-icons-edit'>
                    <CustomIcon
                      name={iconNameEdit}
                      size={iconSize}
                      onClick={() => handleEdit(item)} />
                  </div>
                  <div className='bootcamp-table-action-icons-delete'>
                    <CustomIcon
                      name={iconNameDelete}
                      color={iconColorDelete}
                      size={iconSize}
                      onClick={() => handleDelete(item)} />
                  </div>
                </div>
              </div>
              <div className='bootcamp-table-rightbox'>
                <div className='bootcamp-table-dates bootcamp-table-startdate'>
                  {convertDateFormat.convert(item['startDate'])}
                </div>
                <div className='bootcamp-table-dates'>
                  {convertDateFormat.convert(item['endDate'])}
                </div>
              </div>
            </div>
          )
        })
      }
    </div>
  );
};

BootcampListTemplate.prototype = {
  tableData: PropTypes.array,
  handleEdit: PropTypes.func,
  handleDelete: PropTypes.func
}

BootcampListTemplate.defaultProps = {
  handleEdit: () => { },
  handleDelete: () => { }
}

export default BootcampListTemplate;
