# Unique Permutations Of A String

### Problem
<hr>
In a language of your choice, write a command line utility that given a string as an input will print out all unique permutations of that string. Example inputs to consider are "aaa", "cake", and "banana".
Please include a means of building and/or packaging your utility for distribution to other systems using whatever build technologies that work for you and/or the language you've chosen.

### Solution
<hr>

#### Design considerations

- The solution is written in the Java language and provides means to build executable
  artifacts for any platform (Mac, Linux, Windows).
- Since it wasn't specified whether the permutations need to be in any particular
  order, the author of this solution decided to use an algorithm for lexicographical 
  search of all unique permutations.
- For simplicity, the solution considers input strings as if they were all entered with uppercase letters.
  For example, if you enter "BaNana" as an input string, the solution will only search for all
  unique permutations of "BANANA".
- Details of the algorithm and its internal working can be found in the JavaDoc 
  description of the class [UniquePermutation.java](https://github.com/ZeKoU/stringperm/blob/master/src/main/java/com/acton/interview/stringperm/UniquePermutation.java) .
- The solution includes tests that are covering some regular cases as well as some corner cases, which can be found in class [UniquePermutationTest.java](https://github.com/ZeKoU/stringperm/blob/master/src/test/java/com/acton/interview/stringperm/UniquePermutationTest.java) .
- The solution outputs logs to the console (and not to the file) to help the reader better track the steps performed by the algorithm.

#### Running program

A prerequisite for building and running this solution is to have JDK version `1.8` installed on your machine.

##### Building code

This solution uses [Gradle Build Tool](https://gradle.org/) to build Java code and create a running executable (JAR). However, it is not necessary to have Gradle locally installed as this solution comes with Gradle Wrapper, which allows execution on systems that don't have it installed locally.

To build this solution use the following steps:

1. Clone this project to a local directory by running:
   
   ```shell
   git clone https://github.com/ZeKoU/stringperm.git
   ```

2. Change the current directory (in the console) to the cloned project directory by running:

   ```shell
   cd stringperm
   ```

3. (a) On Mac or Linux run the following to build the solution:

   ```shell
   ./gradlew clean fatJar
   ```
   
   If successful, the above step should create an executable JAR file at 
   `<PROJECT_ROOT>/build/libs/stringperm-all-1.0.jar`
   
   (b) On Windows, run the following to build the solution:
   
   ```bat
   gradlew.bat clean fatJar
   ```
   
   If successful, the above step should create an executable JAR file at
   `<PROJECT_ROOT>\build\libs\stringperm-all-1.0.jar`
   
##### Running solution

After successfully building the solution, you can run it by using the following steps:

1. From the <PROJECT_ROOT> directory (should be 'stringperm') navigate to 
   `build/libs` directory by running:

	```shell
	cd build/libs
	```
	
2. Run program by executing:
   
   ```shell
   java -jar stringperm-all-1.0.jar "BANANA"
   ```
   The above command will search for all unique permutations of the word "BANANA".

##### Running tests

As mentioned above, the solution comes with a set of JUnit tests. Gradle 'test' task is configured to show test run statuses for each test in the console.
To run the tests, follow these steps:

1. Navigate to <PROJECT_ROOT> directory
2. (a) On Mac or Linux run gradle test task by executing:
   
   ```shell
   ./gradlew test
   ```
   (b) On Windows run gradle test task by executing:
   
   ```shell
   ./gradle test
   ```
   You should see an output similar to the following:

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
   