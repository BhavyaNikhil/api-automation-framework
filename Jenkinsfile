pipeline {
    agent any

    parameters {
        string(name: 'ENV', defaultValue: 'sit', description: 'Environment to run tests')
    }

    stages {

        stage('Checkout Code') { //pulls latest code
            steps {
                git 'https://your-repo-url.git'
            }
        }

        stage('Build') { //compiles project
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Run Tests') { //executes tests with selected environment
            steps {
                bat "mvn test -Denv=${params.ENV}"
            }
        }
    }

    post { //publishes TestNG reports
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}