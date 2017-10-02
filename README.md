# Unique Permutations Of A String

### Problem
<hr>
In a language of your choice, write a command line utility that given a string as an input will print out all unique permutations of that string. Example inputs to consider are "aaa", "cake", and "banana".
Please include a means of building and/or packaging your utility for distribution to other systems using whatever build technologies that work for you and/or the language you've chosen.

### Solution
<hr>

#### Design considerations

- Solution is writen in Java language and provides means to build executable
  artifacts for any platform (Mac, Linux, Windows)
- Since it wasn't specified wheather permutations need to be in any particular
  order, author of this solution decided to use algorithm for lexicographical 
  search of all unique permutations.
- For simplicity, solution considers input strings as if they were entered with all uppercase letters.
  Therefore, if you for example enter "BaNana" as input string, solution will search only for all
  unique permutations of "BANANA".
- Details of algorithm and its internal working can be found in JavaDoc 
  description of class `UniquePermutation.java` which can be found in path 
  `<PROJECT_ROOT>/src/main/java/com/acton/interview/stringperm/UniquePermutation.java`
- Solution includes tests that are covering some regular cases as well as some corner cases. 
  These can be found in class `UniquePermutationTest.java`
- Solution is using logging to console to help reader better track steps performed by algorithm

#### Running program

Prerequisite for building and running this solution is to have JDK version `1.8` installed on your machine.

##### Building code

This solution uses [Gradle Build Tool](https://gradle.org/) to build Java code into running executable (JAR). However, it is not neccessary to have Gradle locally installed as this solution comes with Gradle Wrapper which allows execution on systems that don't have it installed locally.

To build this solution use following steps:

1. Clone this project to local directory by running:
   
   ```shell
   git clone https://github.com/ZeKoU/stringperm.git
   ```

2. Move to cloned project directory by running:

   ```shell
   cd stringperm
   ```

3. (a) On Mac or Linux run following to build solution:

   ```shell
   ./gradlew clean fatJar
   ```
   
   If successful, above step should create executable JAR file at 
   `<PROJECT_ROOT>/build/libs/stringperm-all-1.0.jar`
   
   (b) On Windows, run following to build solution:
   
   ```bat
   gradlew.bat clean fatJar
   ```
   
   If successful, above step should create executable JAR file at
   `<PROJECT_ROOT>\build\libs\stringperm-all-1.0.jar`
   
##### Running solution

After successfuly building solution, you can run it by using following steps:

1. From the <PROJECT_ROOT> directory (should be 'stringperm') navigate to 
   `build/libs` directory by running:

	```shell
	cd build/libs
	```
	
2. Run program by executing:
   
   ```shell
   java -jar stringperm-all-1.0.jar "BANANA"
   ```
   In the command above word "BANANA" is used as an example of word for 
   which you are searching unique permutations.

##### Running tests

As mentioned above, solution comes with set of JUnit tests. Gradle 'test' task is configured to show test run statuses for each test in console.
To run tests, follow these steps:

1. Navigate to <PROJECT_ROOT> directory
2. (a) On Mac or Linux run gradle test task by executing:
   
   ```shell
   ./gradlew test
   ```
   (b) On Windows run gradle test task by executing:
   
   ```shell
   ./gradle test
   ```
   You should see output similar to following:

	```
	:compileJava UP-TO-DATE
	:processResources UP-TO-DATE
	:classes UP-TO-DATE
	:compileTestJava
	:processTestResources
	:testClasses
	:test
	
	BUILD SUCCESSFUL
	
	Total time: 1.373 secs
	```
   


