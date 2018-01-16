all: schoolsearch.java
	@javac schoolsearch.java
	@java schoolsearch

test:
	@./test.sh
