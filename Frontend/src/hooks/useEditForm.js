import { useState } from 'react';

const useEditForm = () => {
  const [isEnableEditForm, setIsEnableEditForm] = useState(false);

  const hideEditForm = () => {
    setIsEnableEditForm(false);
  };

  const showEditForm = () => {
    setIsEnableEditForm(true);
  };

  return { isEnableEditForm, hideEditForm, showEditForm };
};

export default useEditForm;
