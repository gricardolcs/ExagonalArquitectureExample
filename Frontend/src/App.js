import ApplicationContextProvider from './context/ApplicationContext';
import './App.css';
import './styles/themes/theme-default.css';
import AppRoutes from './routes/AppRoutes';
// Apply semantic ui CSS to the whole app.
import 'semantic-ui-css/semantic.min.css';

function App() {
  return (
    <ApplicationContextProvider>
      <AppRoutes />;
    </ApplicationContextProvider>
  );
}

export default App;
