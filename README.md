# Bowling Challenge

Ten-pin bowling is a type of bowling in which a bowler rolls a bowling ball down a wood or synthetic lane toward ten pins positioned in a tetractys (equilateral triangle-based pattern) at the far end of the lane. 
The objective is to knock down all ten pins on the first roll of the ball (a strike), or failing that, on the second roll (a spare). The purpose of this project is to print the score given a tab-delimited text file using a command line by following the restrictions from the game.

## Rules

* **Strike**: When all ten pins are knocked down on the first roll (marked "X" on the scoresheet), the frame receives ten pins plus a bonus of pinfall on the next two rolls (not necessarily the next two frames). A strike in the tenth (final) frame receives two extra rolls for bonus pins.
* **Spare**: When a second roll of a frame is needed to knock down all ten pins (marked "/" on the scoresheet), the frame receives ten pins plus a bonus of pinfall in the next roll (not necessarily the next frame). A spare in the first two rolls in the tenth (final) frame receives a third roll for bonus pins.
* **Open Frame**: When less than all ten pins are knocked down in two rolls in a frame

## Resolution

This application uses Spring Boot framework with its command-line feature as well as other dependencies like Apache Commons and Lombok in order to achieve the desired result.
 The code is divided into two sections:
 * **CLIParser**: The main objective is to grab the CLI arguments and validates, which then returns a file path to be used in the next section.
 * **Bowling**: The objective of this class is to process a given file path and return the processed scored as String. It connects the following implementations: 
    * **FileProcessor**. Reads the file and returns a map with the player name as key and a list of rolls as the value.
    * **PlayerProcessor**. Accepts the map from the **FileProcessor**, process the map in order to return Player list, which contains the player name and a list of frames. Each frame is ordered logically based in the rolls and the given rules.
    * **OutputProcessor**: Prepares the player list and returns the calculated score per frame and display values as well as it contains the method to parse the Output class into a printable String.

## Getting Started

### Prerequisites

This project requires the following before using:

- Java version 8 or higher
- Maven 

### Installation and usage

The application follows the standard maven project structure and uses the maven commands in order to build a valid JAR file. 

1. Run `mvn clean package`, generating a valid jar called `bowling-challenge.jar`.
2. Run `java -jar target/bowling-challenge.jar -f sample.txt`, replacing the sample.txt with the absolute path (`/User/YourUser/bowling-challenge/src/test/resources/files/sample.txt`) to the sample text files. You can find all the sample files used to test under `src/test/resources/files`. 
* Expects one argument: `-f or --file`. You can use `-h or -help` for a brief explanation of the commands. 

**Note: The text files must follow the format given in the sample text files or as shown below**
```
Jeff	10
Jeff	7
Jeff	3
Jeff	9
Jeff	0
Jeff	10
Jeff	0
Jeff	8
Jeff	8
Jeff	2
Jeff	F
Jeff	6
Jeff	10
Jeff	10
Jeff	1
Jeff	8
``` 

### Example

Running `java -jar target/bowling-challenge.jar -f C:\Users\Jeries\IdeaProjects\bowling-challenge\src\test\resources\files\sample.txt` returns the result as show below:

```
Frame           1               2               3               4               5               6               7               8               9               10
John
Pinfalls        3       /       6       3               X       8       1               X               X       9       0       7       /       4       4       X       9       0
Score           16              25              44              53              82              101             110             124             132             151
Jeff
Pinfalls                X       7       /       9       0               X       0       8       8       /       F       6               X               X       X       8       1
Score           20              39              48              66              74              84              90              120             148             167

```

## Running the tests

All the tests can be ran using `mvn test`, although it is done when `mvn package` is executed. Each test executes various positive and negative scenarios per each notable class. 
