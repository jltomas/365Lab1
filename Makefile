all: schoolsearch.java
	@javac schoolsearch.java
	@java schoolsearch

test:
	@./test.sh

clean:
	rm tests.out schoolsearch.class schoolsearch\$$Student.class
