import React, { useRef } from 'react';

import { useForm } from 'react-hook-form';
import { Grid, Ref } from 'semantic-ui-react';
import CustomForm from '../../atoms/customForm/CustomForm';
import CustomButton from '../../atoms/customButton/CustomButton';
import useEditForm from '../../../hooks/useEditForm';

const InputNumberBlockOrganism = ({
  fields,
  structure,
  defaultValues,
  onSubmit,
  blockclassName,
  title,
  className,
  numberValue,
  valueClassName,
}) => {
  const formRef = useRef(null);
  const { isEnableEditForm, hideEditForm, showEditForm } = useEditForm();

  const {
    register,
    setValue,
    trigger,
    handleSubmit,
    formState: { errors, isValid },
  } = useForm();

  const handleOnSubmit = (data) => {
    onSubmit(data);
    hideEditForm();
  };

  return (
    <div className={blockclassName}>
      {isEnableEditForm ? (
        <Grid columns={3}>
          <Grid.Row>
            <Grid.Column>
              <Ref innerRef={formRef}>
                <CustomForm
                  register={register}
                  setValue={setValue}
                  trigger={trigger}
                  errors={errors}
                  handleOnSubmit={handleSubmit(handleOnSubmit)}
                  handleOnCancel={hideEditForm}
                  buttonLabel='Save'
                  structure={structure}
                  fields={fields}
                  defaultValues={defaultValues}
                  isValid={isValid}
                  textAlignButtons={'left'}
                />
              </Ref>
            </Grid.Column>
          </Grid.Row>
        </Grid>
      ) : (
        <div>
          <p>{title}</p>
          <div className={className}>
            <p className={valueClassName}>{numberValue}</p>
            <CustomButton
              label='Edit'
              color='blue'
              onClick={showEditForm}
              circular={false}
            />
          </div>
        </div>
      )}
    </div>
  );
};

export default InputNumberBlockOrganism;
