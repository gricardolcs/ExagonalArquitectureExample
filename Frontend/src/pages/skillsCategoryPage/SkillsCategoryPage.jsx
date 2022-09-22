import React, { useEffect, useState } from 'react';
import { SkillsCategoryTemplate } from '../../components/templates/skillsCategoryTemplate/SkillsCategoryTemplate';
import { categoryFetch } from '../../api/settings/fetch/CategoryFetch';

const tableHeaders = ['Name', 'Description', 'Skills', 'Actions'];

const tableRowAttributes = ['name', 'description', 'skills'];

const tableRowActions = [
  { name: 'trash alternate outline', color: 'red', onClick: () => {} },
  { name: 'pencil alternate', onClick: () => {} },
];

export const SkillsCategoryPage = () => {
  const [
    categoriesWithSkillsUpdated,
    setListCategoriesWithSkillsUpdated,
  ] = useState([]);

  const [openModal, setOpenModal] = useState(false);

  const getCategories = (categories) => {
    setListCategoriesWithSkillsUpdated(
      categories.map((category) => ({
        ...category,
        skills: `${category.skills.join(', ')}`,
      }))
    );
  };

  useEffect(() => {
    categoryFetch(getCategories);
  }, []);

  return (
    <SkillsCategoryTemplate
      tableHeaders={tableHeaders}
      tableData={categoriesWithSkillsUpdated}
      tableRowAttributes={tableRowAttributes}
      onCloseModal={() => setOpenModal(false)}
      onOpenModal={() => setOpenModal(true)}
      openModal={openModal}
      handleCloseModal={() => setOpenModal(false)}
      headerModal={'Create Skill Category'}
      classNameModal={'headerText'}
      getCategories={getCategories}
      buttonLabel={'Save'}
      tableRowActions={tableRowActions}
    />
  );
};
