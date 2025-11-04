pipeline {
    agent any

    triggers {
        // Run every 5 minutes
        cron('H/5 * * * *')
    }

    stages {
        stage("Checkout") {
            steps {
                git url: 'https://github.com/RichardCypher4/calculator2.git', branch: 'main'
            }
        }

        stage("Build & Test") {
            steps {
                dir('calculator') {
                    // Clean, compile, run tests, generate JaCoCo report, enforce coverage
                    bat "mvn clean verify"
                }
            }
        }
    }

    post {
        always {
            echo "Publishing JaCoCo HTML report..."
            publishHTML(target: [
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'calculator/target/site/jacoco',
                reportFiles: 'index.html',
                reportName: 'JaCoCo Coverage'
            ])

            echo "Cleaning workspace..."
            cleanWs()
        }
    }
}
