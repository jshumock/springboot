# Coding Test
Our Application is largely configuration driven.  One of the configuration screens that was recently redesigned allowed us to configure which data sets are visible to which users.  I have provided a basic skeleton that renders a basic page and has stubbed out code to 
implement CRUD operations.  The data needs to be stored for different year/month time periods.  For example, Jan 2018 has a set of configs A,B,C,D.  However, Feb 2018 has the set of configs A,C,F,G,H. For convenience, you can use the string "022018" as Feb 2018 to avoid date code. 

### Minimum Requirements:
1. Display whatever is currently configured **(not having a configuration is not an error)** in a grid.
1. Add the ability to add new configurations for a time period.
1. Add the ability to delete all the configurations for a time period.

### Optional Requirements: 
1. Add the ability to remove a single configuration for a time period.

## Getting the Code and Running the App

### Dependencies
* JDK 1.8 (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Maven (https://maven.apache.org/download.cgi)
**The Maven POM is already configured and ready for spring boot.**

### Checking out
1. Clone the repo using git (git clone https://github.com/greatideaman/springboot.git)
1. Create your own branch (git -b <your_name>)

### Submitting the code
1. Push your local changes to the remote repo (git push)
1. Create a Pull Request to master of this repo.
