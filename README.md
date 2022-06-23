# **ing-sw-2022-Legramandi-Leone-Locatelli**
## **Developers**
The team is made up of Pietro Legramandi, Simone Leone and Filippo Locatelli.
## **Implemented Features**
Our version of the game features:
- advanced rules
- a command line interface (CLI)
- a graphical user interface (GUI)
- Socket compatibility 
- Multiple Matches (Advanced Feature)
- 12 Character Cards (Advanced Feature)
## **jar executables instructions**
To play the game:
- install Java (https://www.java.com/en/download/manual.jsp)
- install JDK 17 (https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- generate the jar files on Maven's lifecycle by selecting the "package" entry
- collect the jar files in the target folder of the project: rename "original-PSP60-1.0-SNAPSHOT.jar" to "server.jar" and "PSP60-1.0-SNAPSHOT-shaded.jar" to "client.jar"
- use _java -jar server.jar_ on the server machine or _-jar client.jar_ on the client machines 
Please Note that server will open a socket with the local ethernet ip address
