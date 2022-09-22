import React from 'react';
import PropTypes from 'prop-types';
import { Grid } from 'semantic-ui-react';
import FormModal from '../../atoms/basicModal/BasicModal';
import CustomForm from '../../atoms/customForm/CustomForm';
import { Ref } from 'semantic-ui-react';
import { useForm } from 'react-hook-form';
import categoryFields from '../../../data/skillsCategoryForm/skillsCategoryFields.json';
import StructureCategoryForm from '../../../data/skillsCategoryForm/structure';
import createCategory from '../../../api/settings/createCategory/CreateCategory';
import { categoryFetch } from '../../../api/settings/fetch/CategoryFetch';
import { doNothing } from '../../../utils/constants/constants';
import './style.css';

export const CreateSkillsCategoryModalOrganism = ({
  onClose = doNothing,
  onOpen = doNothing,
  open = false,
  handleCloseModal = doNothing,
  header = '',
  className = '',
  getCategories = doNothing,
  buttonLabel = '',
  modalSize = '',
}) => {
  const formRef = React.useRef(null);
  const { register, setValue, handleSubmit } = useForm();

  const handleOnSubmit = (data) => {
    createCategory(
      {
        name: data.categoryName,
        description: data.description,
        skills: data.skills.split('\n'),
      },
      null
    ).then(() => {
      categoryFetch(getCategories);
    });
    handleCloseModal();
  };
  return (
    <Grid>
      <FormModal
        onClose={onClose}
        onOpen={onOpen}
        open={open}
        header={header}
        classNameHeader={className}
        size={modalSize}
      >
        <Ref innerRef={formRef}>
          <CustomForm
            structure={StructureCategoryForm}
            fields={categoryFields}
            handleOnCancel={handleCloseModal}
            handleOnSubmit={handleSubmit(handleOnSubmit)}
            register={register}
            setValue={setValue}
            buttonLabel={buttonLabel}
          />
        </Ref>
      </FormModal>
    </Grid>
  );
};

CreateSkillsCategoryModalOrganism.prototype = {
  onClose: PropTypes.func,
  onOpen: PropTypes.func,
  tableRowAttributes: PropTypes.array,
  open: PropTypes.bool,
  handleCloseModal: PropTypes.func,
  header: PropTypes.string,
  className: PropTypes.string,
  getCategories: PropTypes.func,
  buttonLabel: PropTypes.string,
  modalSize: PropTypes.string,
};
