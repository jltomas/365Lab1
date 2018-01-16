# CSC 365. Winter 2018
# Lab 1-1 test suite
# @author Justin Tomas & Victor Bodell
# @version Lab1
#
# Instructions: Run ./test.sh. You can adjust the tests.txt file
# to test the different expectations. The output will be redirected to
# a file called tests.out. You can diff tests.out with expected.out.
# If the program is correct, there will be no difference.
#
#

javac schoolsearch.java
java schoolsearch < tests.txt > tests.out

DIFF=$(diff tests.out expected.out)
if [ "$DIFF" != "" ]
then echo "Something went wrong."
else echo "All tests pass."
fi
