# Simple Student Management System
Simple CRUD application using Angular 4 , Spring boot and Mongo DB

## Getting Started

Write sample REST API using spring boot and integrate together with an Angular 4 
frontend project.

### Prerequisites

* Git 2.8.3 or later
* JDK 8 or later
* Maven 3.0 or later
* MongoDB 3.4 or later
* Node JS v8.5.0 or later

### Clone
To get started you can simply clone this repository using git:
```
git clone https://github.com/sampathsl/StudentManagementSystem.git
cd StudentManagementSystem
```

## Running

```
git clone https://github.com/sampathsl/StudentManagementSystem.git
cd StudentManagementSystem
mvn clean package
cd traget
java -jar StudentManagementSystem-0.0.1-SNAPSHOT.jar
```

## Deployment

Install the Heroku CLI
Download and install the Heroku CLI.
If you haven't already, log in to your Heroku account and follow the prompts to create a new SSH public key.
```
$ heroku login
```
Clone the repository

Use Git to clone heroku-app-name's source code to your local machine.

```
$ heroku git:clone -a heroku-app-name
$ cd heroku-app-name
```
Deploy your changes

Make some changes to the code you just cloned and deploy them to Heroku using Git.

```
$ git add .
$ git commit -am "make it better"
$ git push heroku master
```

At last make sure you need to add heroku mongodb addone to your app.
https://elements.heroku.com/addons/mongolab

** Note : To enable mongo db addone you need payment details.

## License

This project is licensed under the MIT License

## TODO

    1)Add DOB field
    2)Add REST security
    3)Add Student list pagination
    4)Write frontend and backend test cases
    ...

![Angular 4 MongoDB archicture](http://www.davismol.net/wp-content/uploads/2017/07/000-Spring-Boot-and-AngularJS-4-Web-Application-Architecture-Layers-228x300.png)


