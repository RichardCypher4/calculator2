pipeline {
    agent any

    stages {
        stage("Checkout") {
            steps {
                git url: 'https://github.com/RichardCypher4/calculator2.git', branch: 'main'
            }
        }

        stage("Build & Test") {
            steps {
                dir('calculator') {
                    // Clean, compile, run tests, generate JaCoCo report, and enforce coverage rules
                    bat "mvn clean verify"
                }
            }
        }
    }

    post {
        always {
            echo "Archiving JaCoCo HTML reports..."
            // Archive the report so it can be viewed in Jenkins
            archiveArtifacts artifacts: 'calculator/target/site/jacoco/**', fingerprint: true
            cleanWs()
        }
    }
}
