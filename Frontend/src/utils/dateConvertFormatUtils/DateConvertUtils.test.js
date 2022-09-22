import dateConvertFormatUtils from './DateConvertUtils';

describe('Date convert format tests', () => {

  test('Test 1: Convert 2021-01-10 to Jan 10, 2021', () => {
    const dateToConvert = '2021-01-10';
    const expectedResult = 'Jan 10, 2021';
    expect(dateConvertFormatUtils.convert(dateToConvert)).toEqual(expectedResult);
  })

  test('Test 2: Convert 2021-02-10 to Feb 10, 2021', () => {
    const dateToConvert = '2021-02-10';
    const expectedResult = 'Feb 10, 2021';
    expect(dateConvertFormatUtils.convert(dateToConvert)).toEqual(expectedResult);
  })

  test('Test 3: Convert 2021-03-10 to Mar 10, 2021', () => {
    const dateToConvert = '2021-03-10';
    const expectedResult = 'Mar 10, 2021';
    expect(dateConvertFormatUtils.convert(dateToConvert)).toEqual(expectedResult);
  })

  test('Test 4: Convert 2021-04-10 to Apr 10, 2021', () => {
    const dateToConvert = '2021-04-10';
    const expectedResult = 'Apr 10, 2021';
    expect(dateConvertFormatUtils.convert(dateToConvert)).toEqual(expectedResult);
  })

  test('Test 5: Convert 2021-05-20 to May 20, 2021', () => {
    const dateToConvert = '2021-05-20';
    const expectedResult = 'May 20, 2021';
    expect(dateConvertFormatUtils.convert(dateToConvert)).toEqual(expectedResult);
  })

  test('Test 6: Convert 2021-06-20 to Jun 20, 2021', () => {
    const dateToConvert = '2021-06-20';
    const expectedResult = 'Jun 20, 2021';
    expect(dateConvertFormatUtils.convert(dateToConvert)).toEqual(expectedResult);
  })

  test('Test 7: Convert 2021-07-20 to Jul 20, 2021', () => {
    const dateToConvert = '2021-07-20';
    const expectedResult = 'Jul 20, 2021';
    expect(dateConvertFormatUtils.convert(dateToConvert)).toEqual(expectedResult);
  })

  test('Test 8: Convert 2021-08-20 to Aug 20, 2021', () => {
    const dateToConvert = '2021-08-20';
    const expectedResult = 'Aug 20, 2021';
    expect(dateConvertFormatUtils.convert(dateToConvert)).toEqual(expectedResult);
  })

  test('Test 9: Convert 2021-09-20 to Sep 20, 2021', () => {
    const dateToConvert = '2021-09-20';
    const expectedResult = 'Sep 20, 2021';
    expect(dateConvertFormatUtils.convert(dateToConvert)).toEqual(expectedResult);
  })

  test('Test 10: Convert 2021-10-20 to Oct 20, 2021', () => {
    const dateToConvert = '2021-10-20';
    const expectedResult = 'Oct 20, 2021';
    expect(dateConvertFormatUtils.convert(dateToConvert)).toEqual(expectedResult);
  })

  test('Test 11: Convert 2021-11-20 to Nov 20, 2021', () => {
    const dateToConvert = '2021-11-20';
    const expectedResult = 'Nov 20, 2021';
    expect(dateConvertFormatUtils.convert(dateToConvert)).toEqual(expectedResult);
  })

  test('Test 12: Convert 2021-12-20 to Dec 20, 2021', () => {
    const dateToConvert = '2021-12-20';
    const expectedResult = 'Dec 20, 2021';
    expect(dateConvertFormatUtils.convert(dateToConvert)).toEqual(expectedResult);
  })

})
