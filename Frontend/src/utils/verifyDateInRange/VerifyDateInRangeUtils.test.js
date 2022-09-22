import verifyDateInRangeUtils from './VerifyDateInRangeUtils';

describe('Verify dates in ranges', () => {

  test('Test 1: Verify if 2021-05-26 is in range 2020-03-11 and 2020-04-11', () => {
    const startDateString = '2020-03-11';
    const endDateString = '2020-07-11';
    const date = Date.parse('26 May 2020 00:12:00 GMT');
    expect(verifyDateInRangeUtils.verify(startDateString, endDateString)).toBeFalsy()
  })

  test('Test 2: Verify if 1995-12-04 is in range 2020-03-11 and 2020-04-11', () => {
    const startDateString = '2020-05-11';
    const endDateString = '2020-06-25';
    const date = Date.parse('04 Dec 1995 00:12:00 GMT');
    expect(verifyDateInRangeUtils.verify(startDateString, endDateString, date)).toBeFalsy()
  })

  test('Test 3: Verify if 2021-12-04 is in range 2021-12-1 and 2021-12-25', () => {
    const startDateString = '2021-12-1';
    const endDateString = '2021-12-25';
    const date = Date.parse('04 Dec 2021 00:12:00 GMT');
    expect(verifyDateInRangeUtils.verify(startDateString, endDateString, date)).toBeTruthy()
  })

})
