import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import SettingCard from './SettingCard';

function defaultRender(props) {
  const { container } = render(<SettingCard {...props} />);
  return container;
}

const onClick = jest.fn();

const mockSetComponent = jest.fn();

const testData = {
  onClick,
  value: {
    id: 1,
    title: 'Test title card',
    items: [{ label: 'Test description card', component: 'component' }],
  },
  setComponent: mockSetComponent,
};

describe('test', () => {
  it('Should render card title', async () => {
    defaultRender(testData);
    expect(screen.getByText('Test title card')).toBeInTheDocument();
  });

  it('Should render card description', () => {
    defaultRender(testData);
    expect(screen.getByText('Test description card')).toBeInTheDocument();
  });

  it('Should execute onClick function', () => {
    const component = defaultRender(testData);
    const options = component.querySelectorAll('.settings-card-links');
    expect(options).toHaveLength(1);
    fireEvent.click(options[0]);
    expect(mockSetComponent).toHaveBeenCalled();
  });
});
