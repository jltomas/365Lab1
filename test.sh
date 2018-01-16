# CSC 365. Winter 2018
# Lab 1-1 test suite
# @author Justin Tomas & Victor Bodell
# @version Lab1
#
# Instructions: Run ./test.sh. You can adjust the commands.txt file
# to test the different expectations. The output will be redirected to
# a file called output.txt. You can diff output.txt with expectoutput.txt.
# If the program is correct, there will be no difference.
#
#

javac schoolsearch.java
java schoolsearch < commands.txt > output.txt

DIFF=$(diff output.txt expectedoutput.txt)
if [ "$DIFF" != "" ]
then echo "Something went wrong."
else echo "All tests pass."
fi
