import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import { Grid } from 'semantic-ui-react';
import BasicCard from '../../atoms/customCard/BasicCard';
import CardContent from '../../atoms/customCard/CardContent';
import CardHeader from '../../atoms/customCard/CardHeader';
import CardDescription from '../../atoms/customCard/CardDescription';
import BasicImage from '../../atoms/basicImage/BasicImage';
import CardMeta from '../../atoms/customCard/CardMeta';
import CustomIcon from '../../atoms/customIcon/CustomIcon';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import iconDownloadStorage from '../../../images/icons/icon-download.png';
import iconMoreVertical from '../../../images/icons/icon-more-vertical.png';
import CustomDropDown from '../../atoms/customDropDown/CustomDropDown';
import './style.css';

export function CustomCard({
  cardImage,
  cardHeader,
  cardMeta,
  cardDescription,
  dropDownOptions,
  tabColorVariant,
  showStatus,
  showRemove,
  onClickCard,
  handleOnRemoveImage,
  children,
  typeCard,
  cardDownloadContent,
  onClickDownload,
}) {
  const [isEnabledToDownload, setIsEnabledToDownload] = useState(true);

  useEffect(() => {
    setIsEnabledToDownload(
      !Object.values(cardDownloadContent).includes('') &&
        Object.values(cardDownloadContent).length > 0
    );
  }, [cardDownloadContent]);

  return (
    <BasicCard className='card-container'>
      <div className={`card-little-column ${tabColorVariant}`} />
      <CardContent>
        <Grid>
          <Grid.Column
            width={6}
            textAlign='center'
            className='image-row'
            onClick={onClickCard}
          >
            <Grid.Row className='image-content'>{cardImage}</Grid.Row>
            {showStatus && (
              <Grid.Row className='status-row'>
                <CustomIcon color='blue' className='circle' />
                <p> Status </p>
              </Grid.Row>
            )}
            {showRemove && (
              <Grid.Row className='status-row'>
                <CustomLabel
                  size='large'
                  value='Remove'
                  handleOnClick={() => handleOnRemoveImage()}
                />
              </Grid.Row>
            )}
          </Grid.Column>
          <Grid.Column width={8} className='content-row' onClick={onClickCard}>
            <CardHeader className='card-header'>{cardHeader}</CardHeader>
            <CardMeta className='card-meta'>{cardMeta}</CardMeta>
            <Grid.Row>
              {cardDescription.map((item) => {
                return (
                  <CardDescription
                    textAlign='left'
                    key={`card-description-${item.key}`}
                    className='card-descriptions'
                  >
                    {item.render}
                  </CardDescription>
                );
              })}
            </Grid.Row>
          </Grid.Column>
          <Grid.Column width={2} className='card-options'>
            <Grid.Row>
              {typeCard !== 'upload-image' ? (
                <>
                  <CustomDropDown
                    key={`card-dropdown-${cardHeader}`}
                    trigger={
                      <BasicImage
                        src={iconMoreVertical}
                        className='icon-more'
                      />
                    }
                    direction='left'
                    items={dropDownOptions}
                  />
                  <div className='space' onClick={onClickCard} />
                  <BasicImage
                    src={iconDownloadStorage}
                    className={`icon-download ${
                      isEnabledToDownload ? 'enabled' : 'disabled'
                    }`}
                    onClick={
                      isEnabledToDownload
                        ? () => onClickDownload(cardDownloadContent)
                        : () => {}
                    }
                  />
                </>
              ) : (
                ''
              )}
            </Grid.Row>
          </Grid.Column>
        </Grid>
      </CardContent>
      {children}
    </BasicCard>
  );
}

CustomCard.prototype = {
  cardImage: PropTypes.string,
  cardHeader: PropTypes.string,
  cardMeta: PropTypes.string,
  cardDescription: PropTypes.string,
  dropDownOptions: PropTypes.arrayOf(
    PropTypes.shape({
      text: PropTypes.string,
      handleOnClick: PropTypes.func,
    })
  ),
  tabColorVariant: PropTypes.string,
  showStatus: PropTypes.bool,
  showRemove: PropTypes.bool,
  onClickCard: PropTypes.func,
  handleOnRemoveImage: PropTypes.func,
  children: PropTypes.node,
  typeCard: PropTypes.string,
  cardDownloadContent: PropTypes.object,
  onClickDownload: PropTypes.func,
};

CustomCard.defaultProps = {
  cardImage: '',
  cardHeader: '',
  cardMeta: '',
  cardDescription: '',
  dropDownOptions: [],
  tabColorVariant: '',
  onClickCard: () => {},
  handleOnRemoveImage: () => {},
  showStatus: false,
  showRemove: false,
  typeCard: '',
  cardDownloadContent: {},
  onClickDownload: () => {},
};
