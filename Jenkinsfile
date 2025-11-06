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
        success {
            emailext(
                to: 'oladayorichard1@gmail.com',
                subject: "✅ SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                <h3>Build Successful!</h3>
                <p>Job: ${env.JOB_NAME}</p>
                <p>Build Number: ${env.BUILD_NUMBER}</p>
                <p>URL: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                """,
                mimeType: 'text/html'
            )
        }

        failure {
            emailext(
                to: 'oladayorichard1@gmail.com',
                subject: "❌ FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                <h3>Build Failed!</h3>
                <p>Job: ${env.JOB_NAME}</p>
                <p>Build Number: ${env.BUILD_NUMBER}</p>
                <p>URL: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                """,
                mimeType: 'text/html'
            )
        }

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
