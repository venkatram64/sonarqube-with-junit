
run the following command in cmd(windows powershell)
step 1: docker pull sonarqube

step 2: start the sonar qube from the docker desktop 

            OR

step 2: docker run -d --name sonarqube -p 9000:9000 sonarqube:latest
or
step 2: docker run -rm -d --name sonarqube -p 9000:9000 -e SONARQUBE_ADMIN_PASSWORD="admin"
step 3: docker ps -a
docker ps -a -f name=sonarqube


***************
step 1:after starting the SonarQube, visit the following url
http://localhost:9000
step 2: enter user name and password
admin/admin

asks new password
test123

step 3: create the project, which will give the below one

mvn clean verify sonar:sonar -Pcoverage -Dsonar.projectKey=MyProject  -Dsonar.host.url=http://localhost:9000  -Dsonar.login=sqp_73731e3a8e5a3ec62b00a8f527c4a7fc35426787


mvn clean verify sonar:sonar  -Dsonar.projectKey=MyProject  -Dsonar.host.url=http://localhost:9000  -Dsonar.login=sqp_73731e3a8e5a3ec62b00a8f527c4a7fc35426787

step 4: visit the sonarqube
http://localhost:9000, check your code coverage


