pipeline {
    agent any

    stages {
        stage("Checkout") {
            steps {
                git url: 'https://github.com/RichardCypher4/calculator2.git', branch: 'main'
            }
        }

        stage("Compile") {
            steps {
                dir('calculator') {
                    bat "mvn clean compile"
                }
            }
        }

        stage("Unit Test & Code Coverage") {
            steps {
                dir('calculator') {
                    // Run unit tests and generate JaCoCo coverage report
                    bat "mvn test jacoco:report"

                    // Check coverage thresholds (equivalent to jacocoTestCoverageVerification)
                    bat "mvn jacoco:check"
                }
            }
        }
    }

    post {
        always {
            echo "Cleaning up workspace..."
            cleanWs()
        }
    }
}
