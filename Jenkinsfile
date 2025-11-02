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

                    // Check coverage thresholds (fail build if below limits)
                    bat "mvn jacoco:check"
                }

                // Publish JaCoCo report in Jenkins UI
                jacoco(
                    execPattern: '**/target/jacoco.exec',
                    classPattern: '**/target/classes',
                    sourcePattern: '**/src/main/java',
                    inclusionPattern: '**/*.class',
                    exclusionPattern: '**/test/**'
                )
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
