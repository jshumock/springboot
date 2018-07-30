# Coding Test
Our Application is largely configuration driven.  One of the configuration screens that was recently redesigned allowed us to configure which data sets are visible to which users.  I have provided a basic skeleton that renders a basic page and has stubbed out code to 
implement CRUD operations.  The data needs to be stored for different year/month time periods.  For example, Jan 2018 has a set of configs A,B,C,D.  However, Feb 2018 has the set of configs A,C,F,G,H. For convenience, you can use the string "022018" as Feb 2018 to avoid date code. Once you have it working, please submit your code by issuing a PR to master in the repo.

**Hint: There are a couple of bugs that will need to be corrected.**

### Minimum Requirements:
1. Display whatever is currently configured **(not having a configuration is not an error)** in a grid.
1. Add the ability to add new configurations for a time period.
1. Add the ability to delete all the configurations for a time period.

### Optional Requirements: 
1. Add the ability to remove a single configuration for a time period.

## Getting the Code and Running the App

### Dependencies
* [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](https://maven.apache.org/download.cgi)  **The Maven POM is already configured and ready for spring boot.**

### Checking out
1. Clone the repo using git (git clone https://github.com/greatideaman/springboot.git)
1. Create your own branch (git -b <your_name>)

### Building and Running the App
1. In the configurationapp directory (under the directory you checked out the code to) run: mvn clean package.
1. In the configurationapp/target directory run: java -jar configurationapp.jar
1. In your favorite browser navigate to localhost:9000
1. You should see a welcome message if you haven't made any changes.

### Submitting the code
1. Push your local changes to the remote repo (git push)
1. Create a Pull Request to master of this repo.
