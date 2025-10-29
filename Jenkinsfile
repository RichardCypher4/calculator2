pipeline {
    agent any
    stages {
        stage("Checkout") {
            steps {
                // Checkout the repo (Jenkins will put it in the workspace root)
                git url: 'https://github.com/RichardCypher4/calculator2.git', branch: 'main'
            }
        }

        stage("Compile") {
            steps {
                // Move into the calculator directory where pom.xml is
                dir('calculator') {
                    bat "mvn clean compile"
                }
            }
        }

        stage("Unit test") {
            steps {
                dir('calculator') {
                    bat "mvn test"
                }
            }
        }
    }
}
