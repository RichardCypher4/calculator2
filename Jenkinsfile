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
                bat "mvn clean compile"
            }
        }
        stage("Unit test") {
            steps {
                bat "mvn test"
            }
        }
    }
}
