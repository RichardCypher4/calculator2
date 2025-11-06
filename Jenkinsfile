pipeline {
    agent any

    environment {
        REPORT_DIR = 'calculator/target/site/jacoco'
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
                    bat "mvn clean verify"
                }
            }
        }

        stage("Extract Coverage") {
            steps {
                script {
                    def reportFile = readFile("${REPORT_DIR}/jacoco.xml")
                    def matcher = reportFile =~ /<counter type="INSTRUCTION" missed="(\\d+)" covered="(\\d+)"/
                    def totalMissed = 0
                    def totalCovered = 0
                    matcher.each { m ->
                        totalMissed += m[1].toInteger()
                        totalCovered += m[2].toInteger()
                    }
                    def coverage = (totalCovered * 100) / (totalCovered + totalMissed)
                    env.COVERAGE_PERCENT = String.format('%.2f', coverage)
                    echo "✅ Code Coverage: ${env.COVERAGE_PERCENT}%"
                }
            }
        }

        stage("Publish Reports") {
            steps {
                publishHTML(target: [
                    reportDir: "${REPORT_DIR}",
                    reportFiles: 'index.html',
                    reportName: 'JaCoCo Coverage Report',
                    keepAll: true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: false
                ])
            }
        }
    }

    post {
        success {
            emailext(
                to: 'oladayorichard1@gmail.com',
                subject: "✅ SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                <html>
                    <body style="font-family: Arial; background-color:#f9f9f9; padding:20px;">
                        <div style="background:#4CAF50; color:white; padding:10px; border-radius:5px;">
                            <h2>✅ Build Successful</h2>
                        </div>
                        <p><b>Project:</b> ${env.JOB_NAME}</p>
                        <p><b>Build Number:</b> #${env.BUILD_NUMBER}</p>
                        <p><b>Duration:</b> ${currentBuild.durationString}</p>
                        <p><b>Triggered by:</b> ${currentBuild.getBuildCauses()[0].userName ?: "Automated Trigger"}</p>
                        <p><b>Code Coverage:</b> ${env.COVERAGE_PERCENT}%</p>
                        <hr>
                        <p><b>Console Log:</b> <a href="${env.BUILD_URL}console">View Logs</a></p>
                        <p><b>Coverage Report:</b> <a href="${env.BUILD_URL}artifact/${REPORT_DIR}/index.html">View JaCoCo Report</a></p>
                        <p><b>Build URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                        <hr>
                        <p style="font-size:12px; color:gray;">Sent automatically by Jenkins on ${new Date()}</p>
                    </body>
                </html>
                """,
                mimeType: 'text/html'
            )
        }

        failure {
            emailext(
                to: 'oladayorichard1@gmail.com',
                subject: "❌ FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                <html>
                    <body style="font-family: Arial; background-color:#fff0f0; padding:20px;">
                        <div style="background:#f44336; color:white; padding:10px; border-radius:5px;">
                            <h2>❌ Build Failed</h2>
                        </div>
                        <p><b>Project:</b> ${env.JOB_NAME}</p>
                        <p><b>Build Number:</b> #${env.BUILD_NUMBER}</p>
                        <p><b>Duration:</b> ${currentBuild.durationString}</p>
                        <hr>
                        <p><b>View Logs:</b> <a href="${env.BUILD_URL}console">Console Output</a></p>
                        <p><b>Build URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                        <hr>
                        <p style="font-size:12px; color:gray;">Sent automatically by Jenkins on ${new Date()}</p>
                    </body>
                </html>
                """,
                mimeType: 'text/html'
            )
        }

        always {
            cleanWs()
        }
    }
}
