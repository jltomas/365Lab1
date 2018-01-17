# 365Lab1
## Authors
- Victor Bodell (sbodell@calpoly.edu)
- Justin Tomas (jltomas@calpoly.edu)

## How to run
Run 'make' or 'make students' to compile & launch program

## Testing
To run a simple test case, simply run 'make test' to receive a notification
of whether program is functioning correctly. More info in test file test.sh

To modify the commands of the test suite modify 'tests.txt', remember to 
update 'expected.out' accordingly if test.sh shall continue to work properly.

## Program Commands
- S[tudent]: <LastName>			# Find student w/ given Last Name
- S[tudent]: B[us]			# Find student w/ given Last Name & notify bus route
- T[eacher]: <LastName> 		# Find teacher w/ given Last Name
- B[us]: <BusRoute>			# List students taking specified Bus route
- G[rade]: <Grade>			# List students in given Grade
- G[rade]: <Grade> H[igh]/L[ow] 	# List student w/ highest/lowest GPA in given Grade
- A[verage]: <Grade>			# List GPA Average for given Grade
- I[nfo]				# List no of students in each grade
- Q[uit] 				# Terminate program

### 2.0 Commands
- C[lass]: <ClassroomNum>		# List all students in given Classroom
- C[lass]: <ClassroomNum> T[eacher]	# List teacher(s) teaching in given Classroom
- G[rade]: <Grade> T[eacher]		# List all teacher(s) teaching given Grade
- E[nrolled]				# List no of students in each classroom
