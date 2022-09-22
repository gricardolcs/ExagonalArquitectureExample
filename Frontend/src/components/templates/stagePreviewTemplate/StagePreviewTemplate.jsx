import React from "react";
import PropTypes from 'prop-types';
import { useCallback } from "react";
import { useState } from "react";
import { useEffect } from "react";
import CustomAccordions from "../../molecules/customAccordions/CustomAccordions";
import './style.css'
import StageFormOrganism from "../../organisms/stageFormOrganism/StageFormOrganism";

function StagePreviewTemplate({ stagesSchemas, stageSelected, handleOnNonExpand }) {
  const [schemas, setSchemas] = useState([]);

  const loadStagesSchemas = useCallback(() => {
    const data = stagesSchemas.map((item) => {
      return {
        title: `${item.name}`,
        content: <StageFormOrganism schema={item.schema} stageName={item.name}/>
      }
    });
    setSchemas(data);
  }, [stagesSchemas])

  useEffect(() => {
    loadStagesSchemas();
  }, [stagesSchemas, loadStagesSchemas])

  return (
    <CustomAccordions
      data={[
        {
          title: "Stages Preview",
          content: (
            <CustomAccordions
              data={schemas}
              initialIndexes={[stageSelected]}
              handleOnNonExpand={handleOnNonExpand}
              variantInternalAccordion='accordionGrid'
            />
          )
        }
      ]}
      initialIndexes={["Stages Preview"]}
    />
  );
}

StagePreviewTemplate.prototype = {
  stagesSchemas: PropTypes.array,
  stageSelected: PropTypes.string,
  handleOnNonExpand: PropTypes.func
}

StagePreviewTemplate.defaultProps = {
  stagesSchemas: [],
  stageSelected: '',
  handleOnNonExpand: () => { }
}

export default StagePreviewTemplate;
