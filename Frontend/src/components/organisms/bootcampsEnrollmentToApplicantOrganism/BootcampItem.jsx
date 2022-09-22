import { Grid } from 'semantic-ui-react';
import PropTypes from 'prop-types';
import iconPlus from '../../../images/icons/icon-plus.png';
import iconMinus from '../../../images/icons/icon-minus.png';
import BasicImage from '../../atoms/basicImage/BasicImage';

const BootcampItem = ({
  bootcamp = {},
  bootcampIdsSelected = [],
  setBootcampIdsSelected = () => {},
}) => {
  const addBootcamp = (bootcamp, bootcampIdsSelected) => {
    if (!bootcampIdsSelected.includes(bootcamp.id)) {
      setBootcampIdsSelected([...bootcampIdsSelected, bootcamp.id]);
    }
  };

  const removeBootcamp = (bootcamp, bootcampIdsSelected) => {
    const bootcampIdsSelectedUpdated = bootcampIdsSelected.filter(
      (bootcampId) => bootcampId !== bootcamp.id
    );
    setBootcampIdsSelected(bootcampIdsSelectedUpdated);
  };

  return (
    <Grid className='skills-grid'>
      <Grid.Column width={12}>
        {bootcamp.project.name}
      </Grid.Column>
      <Grid.Column width={4} className='skills-column'>
        {bootcampIdsSelected.includes(bootcamp.id) ? (
          <BasicImage
            className='icon-minus'
            src={iconMinus}
            onClick={() => removeBootcamp(bootcamp, bootcampIdsSelected)}
          />
        ) : (
          <BasicImage
            className='icon-plus'
            src={iconPlus}
            onClick={() => addBootcamp(bootcamp, bootcampIdsSelected)}
          />
        )}
      </Grid.Column>
    </Grid>
  );
};

BootcampItem.prototype = {
  bootcamp: PropTypes.object,
  bootcampIdsSelectes: PropTypes.arrayOf(PropTypes.number),
  setBootcampIdsSelected: PropTypes.func,
};

export default BootcampItem;
