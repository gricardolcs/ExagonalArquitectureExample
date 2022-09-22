import React, { useState } from 'react'
import PropTypes from 'prop-types';
import { Pagination } from 'semantic-ui-react';

function CustomPagination({
  activePage,
  totalPages,
  onChange
}) {
  const [selectedPage, SetSelectedPage] = useState(activePage);

  const handleChange = (event, data) => {
    SetSelectedPage(data.activePage);
    onChange(data.activePage)
  }

  return (
    <Pagination
      activePage={selectedPage}
      totalPages={totalPages}
      onPageChange={handleChange}
    />
  )
}

CustomPagination.ProtoType = {
  activePage: PropTypes.number,
  totalPages: PropTypes.number,
  onChange: PropTypes.func
}

CustomPagination.defaultProps = {
  activePage: '',
  totalPages: '',
  onChange: () => {}
}

export default CustomPagination
