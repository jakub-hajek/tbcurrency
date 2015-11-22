#Java Demo - payment tracker

##Installation (maven and jdk >= 1.6.x required):
* a) cd tbcurrency
* b) mvn clean install

##Run:
* a) `java -jar target/tb-currency-1.0-SNAPSHOT.jar path/to/input/file`

##Notes:
* Input file is optional, when it is not specified std in is read. When file is specified, it is read first, after that std in is being read.
* Input in incorrect format is ingored - it is just reported to user and program continues.
* Blank characters are also ignored.
* Quit command is not case-sensitive.
