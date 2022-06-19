pipeline {
  agent any
  environment{
    JAVA_HOME = '/usr/lib/jvm/java-11-openjdk-amd64/'
  }

  stages {
    stage('Build') {
      steps {
        gitlabCommitStatus(name: 'Build') {
          sh 'mvn -B -DskipTests clean package'
        }
      }
    }

    stage('Test') {
      steps {
        gitlabCommitStatus(name: 'Test') {
          sh 'mvn test'
        }
      }
      post {
        always {
          junit 'target/surefire-reports/*.xml'
        }
      }
    }

  }

}