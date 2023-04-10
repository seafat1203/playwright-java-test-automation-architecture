# Playwright Java Test Automation Architecture

Ready-to-use UI Test Automation Architecture using Java and Playwright.

## Installation Steps

In order to use the framework:

1. [Fork](https://github.com/Tahanima/playwright-java-test-automation-architecture/fork) the repository.
2. Clone, i.e, download your copy of the repository to your local machine using
```
git clone https://github.com/[your_username]/playwright-java-test-automation-architecture.git
```
3. Import the project in [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).
4. Make your desired changes.
5. Use IntelliJ IDEA to run your desired tests. Alternatively, you can use the terminal to run the tests, for example `./gradlew test -Dbrowser=firefox -Dheadless=false` to run all the tests using the firefox browser in headed mode.

## Languages and Frameworks

The project uses the following:
- *[Java 11](https://openjdk.java.net/projects/jdk/11/)* as the programming language.
- *[Playwright](https://playwright.dev/)* as the web browser automation framework using the Java binding.
- *[Univocity Parsers](https://www.univocity.com/pages/univocity_parsers_tutorial)* to parse and handle CSV files.
- *[TestNG](https://testng.org/doc/)* as the testing framework.
- *[Lombok](https://projectlombok.org/)* to generate getters.
- *[Owner](http://owner.aeonbits.org/)* to minimize the code to handle properties file.
- *[Extent Reports](https://www.extentreports.com/)* as the test reporting strategy.
- *[Gradle](https://gradle.org/)* as the Java build tool.
- *[IntelliJ IDEA](https://www.jetbrains.com/idea/)* as the IDE.

## Project Structure

The project is structured as follows:

```bash
📦 playwright-java-test-automation-architecture
├─ .github
│  └─ workflows
│     └─ test-execution.yml
├─ .gitignore
├─ LICENSE
├─ README.md
├─ build.gradle
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradlew
├─ gradlew.bat
├─ settings.gradle
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ io
   │  │     └─ github
   │  │        └─ tahanima
   │  │           ├─ config
   │  │           │  ├─ Configuration.java
   │  │           │  └─ ConfigurationManager.java
   │  │           ├─ data
   │  │           │  ├─ BaseTestData.java
   │  │           │  └─ login
   │  │           │     └─ LoginTestData.java
   │  │           ├─ page
   │  │           │  ├─ BasePage.java
   │  │           │  ├─ BasePageFactory.java
   │  │           │  ├─ login
   │  │           │  │  └─ LoginPage.java
   │  │           │  └─ product
   │  │           │     └─ ProductsPage.java
   │  │           ├─ report
   │  │           │  └─ ExtentReportManager.java
   │  │           └─ util
   │  │              ├─ BrowserFactory.java
   │  │              └─ BrowserManager.java
   │  └─ resources
   │     └─ config.properties
   └─ test
      ├─ java
      │  └─ io
      │     └─ github
      │        └─ tahanima
      │           ├─ e2e
      │           │  ├─ BaseE2ETest.java
      │           │  └─ login
      │           │     └─ LoginE2ETest.java
      │           └─ util
      │              ├─ DataProviderUtils.java
      │              └─ TestListener.java
      └─ resources
         └─ testdata
            └─ login
               └─ login.csv
```
