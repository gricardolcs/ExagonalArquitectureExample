import React, { useReducer, useState, useContext, useEffect } from 'react';
import State from '../authentication/state';
import Reducer from '../authentication/reducer';
import ApplicationContextActions from '../authentication/actions';

const ApplicationContext = React.createContext();
const ApplicationContextDispatch = React.createContext();

export default function ApplicationContextProvider(props) {
  const { children } = props;
  const [value, dispatch] = useReducer(Reducer, props, State);
  const [actions] = useState(ApplicationContextActions(dispatch));

  useEffect(() => {
    const token = sessionStorage.getItem('token');
    if (token) {
      actions.onLogin();
    }
  }, [actions]);

  return (
    <ApplicationContext.Provider value={value}>
      <ApplicationContextDispatch.Provider value={actions}>
        {children}
      </ApplicationContextDispatch.Provider>
    </ApplicationContext.Provider>
  );
}

export function useApplicationContext() {
  const state = useContext(ApplicationContext);
  const actions = useContext(ApplicationContextDispatch);
  return { state, actions };
}
