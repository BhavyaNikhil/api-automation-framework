package base;

import config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BaseTest {
    /*@BeforeSuite
    public void healthCheck() {

        Response response = given()
                .baseUri(ConfigManager.getProperty("base.url"))
                .get("/health");

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Environment is not healthy");
        }
    }*/

    @BeforeSuite
    public void setEnvironment() throws Exception {

        String env = ConfigManager.getProperty("base.url");

        FileWriter writer = new FileWriter("allure-results/environment.properties");
        writer.write("Environment=SIT\n");
        writer.write("BaseURL=" + env + "\n");
        writer.close();
    }

    @BeforeClass
    public void setup(){
        RestAssured.baseURI= ConfigManager.getProperty("base.url");
    }
}


//Common Setup
//        Step 1 → Create API class in api package
//        Step 2 → Add endpoint in Endpoints.java
//        Step 3 → Create schema in resources/schemas
//        Step 4 → Create separate test class in tests
//        Step 5 → Add entry in testng.xml

/*Built an API automation framework using Java, TestNG, Rest Assured, and Maven.
The goal was to automate REST API validation including response verification, schema validation, and
regression coverage.

        Framework Design:
        I designed the framework using a layered approach:
        Test classes under src/test/java
        Reusable utilities for request handling
        Config file for environment-specific data
        testng.xml for suite execution
        Maven Surefire plugin for execution and reporting
        This ensured the framework is:
        Scalable
        Maintainable
        CI/CD ready

For each API, I validated:
Status code
Response time
JSON body fields
Complete contract validation (schema validation)
Positive & negative scenarios
Instead of validating individual fields like firstname or lastname,
I implemented full JSON schema validation to ensure contract stability.

Reports are generated in target/surefire-reports.
The same setup is used in CI/CD pipelines.

CI/CD Integration
The framework is pipeline-ready.
It can be integrated with Jenkins or GitHub Actions.
Tests run automatically on every build.

I led the design and implementation of a modular API automation framework using Java, Rest Assured, TestNG,
and Maven.
I implemented full JSON contract validation using schema validation, externalized test data for
environment flexibility, ensured CI/CD compatibility, and maintained regression coverage for multiple
microservices. I also mentored team members and improved framework stability and maintainability.

I developed an API automation framework using Java, Rest Assured, TestNG, and Maven.
I automated multiple APIs with positive and negative scenarios, implemented JSON schema validation for
full contract validation, externalized test data using config-sit.properties, and configured
Maven Surefire for CI/CD execution with automated reporting.

TestNG(Test Next Generation) - Open source, java based framework from unit to integration and end to end testing.
Annotations like @Test, @BeforeSuite, etc. to control flow and execution sequence of tests.
Selective execution for smoke, regression, etc.
Data driven testing via xml files allowing same test to run with multiple data sets.
Parallel execution of tests to save time
Generation of html reports with number of tests, passed failed skipped
Integration with tools like Selenium, Jenkins, Maven

How My Framework Executes (Step-by-Step Flow)
1. Test execution starts
    TestNG triggers execution via testng.xml. It identifies test classes and starts the execution lifecycle.
2. BaseTest executes first - Centralizes Environment Configuration
    Since tests classes extends to BaseTest
    @BeforeClass - fetches base url from config-sit.properties. Sets RestAssured base URI.
    Initializes common setup (authentication if required)
3. Test Method Executes
    @Test
    public void methodnameContract()
    Fetches test data (like username) from ConfigManager
    Calls API layer method
4. ConfigManager loads properties
    From config-sit.properties under resources
    Reads configuration values
    Avoids hardcoding
    Enables environment flexibility
5. API class executes (api package) - This layer isolates request logic from test logic
    GetUserDevicesAPI.getUserDevices(userID);
    Adds headers/token using TokenManager
    Fetches endpoint from Endpoints.java
    Builds request using RestAssured
    Sends HTTP request
    Returns Response object
6. Response handling in Test
    Assert.assertEquals(response.getStatusCode(), 200);
    Validates HTTP status
7. Schema validation - Prevents contract level regressions meaning a new build didnt alter any existing behavior
    SchemaValidatorUtil.validateSchema(response,"schemas/GetUserDetailsByNameAPISchema.json");
    Loads JSON schema from resources >> schemas
    Validates the API response contract
    Ensures response structure stability
TestNG triggers execution → BaseTest sets environment → Test class fetches data → API layer builds request →
RestAssured sends request → Response validated → Schema checked for contract stability.

Why This Design Is Good
Separation of concerns
Reusable API layer
Centralized endpoint management
Configuration-driven test data
Contract validation support
Easily extendable for new APIs

The framework follows a layered architecture.
TestNG triggers execution, BaseTest initializes environment, test classes handle validations,
API classes manage request construction, and schema validation ensures contract stability.
Configuration and endpoints are centralized for reusability and maintainability.

Separation of concerns. The API layer handles request construction and communication logic,
while the test layer focuses only on validation and assertions.
This improves maintainability, reusability, and scalability.

If endpoint logic changes, only API layer needs modification without impacting test cases.
It also improves readability and reduces duplication.
"API layer isolates request logic from test layer" means that the mechanics of making an API call
(URL construction, headers, HTTP methods, authentication) are separated into a dedicated code layer
(often called a Service Layer or Wrapper) away from the actual test validation logic
(assertions, expected results).
This separation ensures that if an API endpoint changes, you only update the API layer,
rather than updating dozens of individual test scripts.
Key Benefits of this Isolation
Maintainability: Tests remain clean and focused on validation (e.g.Assert(response.status == 200)),
while request details are handled elsewhere.
Reduced Duplication: Instead of redefining API calls in every test, a single, reusable function can be
called by multiple tests.
Abstraction: The test layer does not need to know the complexity of how the backend works or
how data is structured.
Faster Debugging: If an API changes, only one place needs to be updated.
Example of Separation
Without Isolation: Every test script contains full URLs, headers, and payload structures.
If the endpoint URL changes, all test scripts break.
With Isolation: The test script calls a function UserAPI.create(user).
The API layer handles the POST /api/v1/users, JSON payload, and headers internally.
If the endpoint changes to /v2, only the UserAPI function needs to change.
This approach is crucial for robust automation frameworks, allowing testers to focus on testing,
not building HTTP requests.

Centralized endpoint management prevents hardcoding across multiple classes.
If base path or endpoint changes, it is updated in one place.
It also improves consistency and avoids typo-related bugs.

If BaseTest fails, the environment initialization fails and the entire suite stops.
To prevent this, we can implement proper exception handling and environment validation before execution
to fail fast with meaningful logs. We can also add a health-check API call in BaseTest to validate
environment availability before running suite.

Schema validation ensures backward compatibility and contract stability.
It prevents silent failures where status code is 200 but response structure has changed,
which could break dependent systems.
It helps detect breaking API changes early in CI.

How is your framework scalable for 200+ APIs?
Since APIs are separated into dedicated classes and endpoints are centralized, new APIs can be added without
affecting existing tests. The layered design allows parallel development. We can further scale by
introducing a common RequestBuilder utility and using POJOs for complex payloads.

If field name changes (vehicleType → vehicle_category)?
Status code validation will pass, but schema validation will fail immediately.
This ensures breaking changes are detected early.

How to integrate with Jenkins?
Flow:
1.Jenkins pulls code from Git
2. Executes:
mvn clean test
3. TestNG runs suite
4. Surefire generates reports
5. Jenkins publishes reports
You only need:
Maven installed
Git repo
Jenkins job configured

How to test Kafka-dependent API?
Use polling mechanism to wait for event completion. Alternatively, use consumer simulation
to verify event published to Kafka topic. We can implement retry logic with timeout.

In framework:
Add WaitUtility
Add Retry mechanism

Production bug escaped even though automation passed. What would you do?
Perform RCA
Identify coverage gap
Add missing validation (status, schema, DB validation)
Improve negative testing
Add contract test if missing

The framework follows layered architecture separating API layer, configuration layer, utilities, and test layer.
It supports environment-based execution, retry logic, token management, schema validation, and SLA validation.
The design ensures maintainability and scalability.

CI/CD integration means:

👉 Your tests run automatically
👉 On every code push
👉 In different environments
👉 With reports
👉 Without manual execution

Developer pushes code → Jenkins pulls code → Maven builds project → Tests run → Reports generated → Status published

CI/CD Architecture
My Framwork -> GitHub -> Jenkins -> Maven -> TestNG -> Reports

How did you integrate automation with CI/CD?
The framework is integrated with Jenkins using a declarative pipeline.
Environment parameters are passed dynamically using Maven system properties.
TestNG reports are published post execution, enabling automated regression and smoke execution on every commit.

✅ CI/CD Integration – Step by Step (What We Did)
🔹 1️⃣ Push Automation Project to GitHub
Step 1 – Initialize Git in project
Inside project folder >> cmd terminal:
git init
git add .
git commit -m "Initial commit"
Step 2 – Rename branch to main
git branch -M main
Step 3 – Connect to GitHub repository
git remote add origin https://github.com/BhavyaNikhil/api-automation-framework.git
Step 4 – Push code to GitHub
git push -u origin main
Now code is stored in GitHub.

🔹 2️⃣ Setup Jenkins Locally
Step 1 – Download Jenkins.war
Step 2 – Run Jenkins in cmd using:
java -jar jenkins.war
Open in browser:
http://localhost:8080
username: Bhavya
password: Bhavya@123

🔹 3️⃣ Install Required Plugins in Jenkins
Go to:
Manage Jenkins → Manage Plugins
Installed:
Git plugin
Pipeline plugin
Maven Integration
JUnit plugin

🔹 4️⃣ Configure Maven in Jenkins
Go to:
Manage Jenkins → Global Tool Configuration
Under Maven:
Click Add Maven
Name: Maven-3.9.12
Select Install automatically
Save

⚠ Tool name must match pipeline script exactly.

🔹 5️⃣ Create Jenkins Pipeline Job
Go to Jenkins Home:
Click New Item
Name: API-Automation
Select Pipeline
Click OK

🔹 6️⃣ Add Pipeline Script
Inside job → Configure → Pipeline → Pipeline Script:
pipeline {
    agent any
    tools {
        maven 'Maven-3.9.12'
    }
    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main',
                url: 'https://github.com/BhavyaNikhil/api-automation-framework.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }
        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }
    }
    post {
        always {
            junit allowEmptyResults: true,
            testResults: '/target/surefire-reports/*.xml' -- put ** infront
            allure([
            includeProperties: false,
            jdk: '',
            results: [[path: 'target/allure-results']]
        ])
        }
    }
}

Save.

🔹 7️⃣ Run the Pipeline
Click:
Build Now
Check:
Console Output

🔹 8️⃣ What Happens Internally (CI Flow)
Jenkins pulls code from GitHub
Maven builds project
TestNG runs automation tests
Surefire generates test reports
Jenkins publishes JUnit report

🔹 Final CI/CD Flow

Developer pushes code → GitHub stores code → Jenkins pulls code → Build + Test executed automatically → Reports generated -> Allure Report

Integrated my API automation framework with Jenkins CI.
Pushed my framework to GitHub, configured Maven in Jenkins, created a pipeline job to checkout code, build using Maven,
execute TestNG tests, and publish JUnit reports. This allows automated test execution on every build."

Integrated Allure reporting with TestNG and Jenkins, attached API request/response payloads,
configured environment metadata, enabled execution history trends, and automated report publishing in CI pipeline

2️⃣ Test Data Management using DataProvider

Right now your tests run only one dataset.

Production frameworks run multiple datasets automatically.

Example:

page = 1
page = 2
page = 3
invalid page
Create
utils → TestDataProvider.java
import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name="tripReportData")
    public static Object[][] tripReportData(){

        return new Object[][]{

                {1,10},
                {2,10},
                {3,10}

        };
    }
}
Use in Test
@Test(dataProvider="tripReportData", dataProviderClass = TestDataProvider.class)
public void verifyTripReportbyUsername(int page,int size)

Then

.setPage(page)
.setSize(size)
Interview Answer

We implemented TestNG DataProvider to run tests with multiple datasets, improving test coverage without duplicating test cases.

I developed an API automation framework using RestAssured, TestNG, and Maven.
The framework follows layered architecture with API layer, test layer, utility layer, and configuration layer.
It supports schema validation, retry mechanisms, token management, environment configuration, and
centralized request specifications.
I also integrated Allure reporting and Jenkins CI/CD pipeline for automated test execution and reporting.

After making changes, type as below in cmd to push to git so that jenkins can take the same
git commit -am "message"
git push

to check for errors -> mvn clean test
to run code in terminal - cmd -> mvn clean test -Denv=sit

The best implementation (so you never keep editing POJOs again) is to use
Project Lombok + Builder + Jackson annotations.
This is how most modern automation frameworks are written because it
eliminates getters/setters/constructors completely.
You will write very small model classes and Jackson will serialize them perfectly.

1. Add Lombok Dependency
2. Enable Annotation Processing - In Intellij, File >> Settings >> Build, Execution, Deployment >> Compiler >>
Annotation Processors >> Enable Annotation Processing
3. Add Model Classes
4. Add Factory Class
5. Add API Class
6. Add Test Class

Final Framework:
src/test/java
    api
    models
    factories
    tests
    utils

Final Steps:
1. Add Model Classes required only when request body is present
2. Add Factory Class required only when request body is present
3. Add API Class
4. Add Test Class
5. Add endpoint in Endpoints.java
6. Create schema in resources/schemas
7. Add entry in testng.xml

 */