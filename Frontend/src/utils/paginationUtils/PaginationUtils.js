function getPaginationIntervalFormat(optionsToShow, totalElements, activePage) {
  const interval = computePaginationInterval(activePage, optionsToShow, totalElements)
  return `${interval} of ${totalElements}`;
}

function computePaginationInterval(activePage, optionsToShow, totalElements) {
  let initial = 1;
  let final = optionsToShow;
  if(activePage > 1) {
    final =  final * activePage;
    initial = (final + 1) - optionsToShow;
    if(totalElements === initial) {
      final = totalElements;
    }
  }
  return `${initial} - ${final}`
}

const GetPaginationIntervalFormat = {
  getPaginationIntervalFormat
}

export default GetPaginationIntervalFormat;