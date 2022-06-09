#!/bin/sh
sudo apt update
sudo apt install git
sudo apt install openjdk-11-jdk
sudo apt install openjdk-11-jre
sudo apt install maven
sudo apt update
sudo apt install apt-transport-https ca-certificates curl software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu focal stable"
sudo apt update
apt-cache policy docker-ce
sudo apt install docker-ce
sudo usermod -aG docker ${USER}
newgrp docker
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
git clone -b docker_4 https://github.com/ChumakAn/Library_REST_API/
cd Library_REST_API/
mvn clean package
sudo docker-compose up build
sudo docker-compose up -d
