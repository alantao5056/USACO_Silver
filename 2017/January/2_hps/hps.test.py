import unittest 
from hps2 import main

class hpsTest(unittest.TestCase):
  
  testDataFolder = 'test'

  def do_test(self, testNumber):
    testFile = self.testDataFolder + "/" + str(testNumber)
    main(testFile + ".in", testFile + "_actual.out")
    # compare the result
    expectedOut = open(testFile + ".out", 'r')
    actualOut = open(testFile + "_actual.out", 'r')
    expectedLines = expectedOut.readlines()
    actualLines = actualOut.readlines()
    expectedOut.close()
    actualOut.close()
    self.assertEqual(actualLines, expectedLines)
  
def generate_test(testNumber):
  def test(self):
    self.do_test(testNumber)
  return test

if __name__ == '__main__':
  for i in range(1, 11):
    test_name = 'test_%s' % str(i)
    test = generate_test(i)
    setattr(hpsTest, test_name, test)
  unittest.main()
