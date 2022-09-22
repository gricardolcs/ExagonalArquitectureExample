/**
 * Initializes page routes
 *
 * @param {string} param0 default path
 * @param {string} param1 session storage string value
 * @param {function} setComponent change component function
 */
export default function initPage({ defaultPath, variable }, setComponent) {
  const currentComponent = sessionStorage.getItem(variable);
  if (currentComponent) {
    setComponent(currentComponent);
  } else {
    sessionStorage.setItem(variable, defaultPath);
    setComponent(defaultPath);
  }
}
