import GetPaginationIntervalFormat from './PaginationUtils';

describe('Get pagination interval', () => {
  
  test('Test 1: Get pagination interval "1 -20 of 25" in first page', () => {
    const option = 20;
    const totalElements = 25;
    const page = 1;
    expect(GetPaginationIntervalFormat.getPaginationIntervalFormat(option, totalElements, page)).toEqual("1 - 20 of 25")
  })

  test('Test 2: Get pagination interval "21 -40 of 25" in second page', () => {
    const option = 20;
    const totalElements = 25;
    const page = 2;
    expect(GetPaginationIntervalFormat.getPaginationIntervalFormat(option, totalElements, page)).toEqual("21 - 40 of 25")
  })

  test('Test 3: Get pagination interval "9 - 9 of 9" in last page', () => {
    const option = 4;
    const totalElements = 9;
    const page = 3;
    expect(GetPaginationIntervalFormat.getPaginationIntervalFormat(option, totalElements, page)).toEqual("9 - 9 of 9")
  })

  test('Test 4: Get pagination interval "21 - 21 of 21" in last page', () => {
    const option = 5;
    const totalElements = 21;
    const page =5;
    expect(GetPaginationIntervalFormat.getPaginationIntervalFormat(option, totalElements, page)).toEqual("21 - 21 of 21")
  })
})
