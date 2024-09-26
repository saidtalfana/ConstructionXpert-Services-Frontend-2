pipeline {
    agent any

    tools {
        maven 'mvn'
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-hub-credentials')
        SONARQUBE_TOKEN = 'squ_b8dfc6a136619a3402ca80b2bd649262e24b8b44'
        SONARQUBE_SERVER = 'http://localhost:9000'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/oussamacharafi99/TestRe.git'
            }
        }

        stage('Build & Test Microservices') {
            parallel {
                stage('Build & Test user-service') {
                    steps {
                        dir('user-service') {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }

                stage('Build & Test project-service') {
                    steps {
                        dir('project-service') {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }

                stage('Build & Test task-service') {
                    steps {
                        dir('task-service') {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }

                stage('Build & Test resource-service') {
                    steps {
                        dir('resource-service') {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }

                stage('Build & Package gateway-service') {
                    steps {
                        dir('gateway-service') {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }

                stage('Build & Test discovery-service') {
                    steps {
                        dir('discovery-service') {
                            withMaven(maven: 'mvn') {
                                bat 'mvn clean package'
                            }
                        }
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'SonarQubeScanner'


                    dir('user-service') {
                        bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=user-service -Dsonar.sources=. -Dsonar.host.url=${SONARQUBE_SERVER} -Dsonar.login=${SONARQUBE_TOKEN} -Dsonar.java.binaries=target/classes"
                    }


                    dir('project-service') {
                        bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=projet-service -Dsonar.sources=. -Dsonar.host.url=${SONARQUBE_SERVER} -Dsonar.login=${SONARQUBE_TOKEN} -Dsonar.java.binaries=target/classes"
                    }


                    dir('task-service') {
                        bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=tache-service -Dsonar.sources=. -Dsonar.host.url=${SONARQUBE_SERVER} -Dsonar.login=${SONARQUBE_TOKEN} -Dsonar.java.binaries=target/classes"
                    }


                    dir('resource-service') {
                        bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ressource-service -Dsonar.sources=. -Dsonar.host.url=${SONARQUBE_SERVER} -Dsonar.login=${SONARQUBE_TOKEN} -Dsonar.java.binaries=target/classes"
                    }


                    dir('gateway-service') {
                        bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=gateway-service -Dsonar.sources=. -Dsonar.host.url=${SONARQUBE_SERVER} -Dsonar.login=${SONARQUBE_TOKEN} -Dsonar.java.binaries=target/classes"
                    }


                    dir('discovery-service') {
                        bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=discovery-service -Dsonar.sources=. -Dsonar.host.url=${SONARQUBE_SERVER} -Dsonar.login=${SONARQUBE_TOKEN} -Dsonar.java.binaries=target/classes"
                    }
                }
            }
        }

        stage('Build Docker Images & Push') {
            parallel {
                stage('Build Docker & Push for user-service') {
                    steps {
                        dir('user-service') {
                            script {
                                def dockerImage = docker.build("oussamacharafi/user-service:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
                }

                stage('Build Docker & Push for project-service') {
                    steps {
                        dir('project-service') {
                            script {
                                def dockerImage = docker.build("oussamacharafi/project-service:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
                }

                stage('Build Docker & Push for task-service') {
                    steps {
                        dir('task-service') {
                            script {
                                def dockerImage = docker.build("oussamacharafi/task-service:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
                }

                stage('Build Docker & Push for resource-service') {
                    steps {
                        dir('resource-service') {
                            script {
                                def dockerImage = docker.build("oussamacharafi/resource-service:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
                }

                stage('Build Docker & Push for gateway-service') {
                    steps {
                        dir('gateway-service') {
                            script {
                                def dockerImage = docker.build("oussamacharafi/gateway-service:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
                }

                stage('Build Docker & Push for discovery-service') {
                    steps {
                        dir('discovery-service') {
                            script {
                                def dockerImage = docker.build("oussamacharafi/discovery-service:${env.TAG_VERSION ?: 'latest'}")
                                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                                    dockerImage.push()
                                }
                            }
                        }
                    }
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                bat 'docker-compose up'
            }
        }
    }
}
