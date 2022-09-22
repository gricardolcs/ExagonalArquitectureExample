import React from "react";
import { Loader } from 'semantic-ui-react'

export const Loading = () => {
    return (
      <Loader 
        size='massive'
        active inline='centered' 
      />
    );
};
