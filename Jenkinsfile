pipeline{
    agent any
        stages{
          stage('Run Tests'){
                steps{
                  sh '/opt/homebrew/bin/mvn clean test -Dheadless=true'
                      }
                    }
                }
            }