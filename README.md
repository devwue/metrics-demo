# metrics-demo
Demo solution showing how to use Spring AOP to automatically collect RED metrics from your SpringBoot based Microservice

Follow steps below If you are interested in checking out this sample

Clone this repository
* git clone https://github.com/rprakashg/metrics-demo.git

Install metrics-common library
* switch directory to metrics-demo/metrics-common
* mvn clean install

Install stock-quote-service 
* switch directory to metrics-demo/stock-quote-service
* mvn clean install

Build docker image for stock-quote-service
* switch directory back to metrics-demo
* docker-compose -f metrics.yml build

Run the entire stack including graphite, grafana
* docker-compose -f metrics.yml up

