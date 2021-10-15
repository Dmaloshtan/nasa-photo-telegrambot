<div align=center>

# NasaPhoto Telegram Bot

This Bot can send beautiful photos of the day from NASA.

Bot is deployed to Heroku and available in telegram --> [**NasaPhoto Telegram Bot**][1]


<img alt="Telegram" src="https://img.shields.io/badge/Telegram-2CA5E0?style=for-the-badge&logo=telegram&logoColor=white"/>
<img alt="Java" src="https://img.shields.io/badge/java-%23ED8B00.svg?&style=for-the-badge&logo=java&logoColor=white"/>
<img alt="Spring" src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>



![gif][2]

</div>

# 💻 Deployment  

Deployment process as easy as possible:
Required software:
- terminal for running bash scripts
- docker
- docker-compose
- directories for environments:

1. `<root project>/jrtb-bot.env`

   🗝️File contents:
   ```    
   - BOT_USERNAME=<username value>
   - BOT_TOKEN=<token value>
   - BOT_DB_USERNAME=<db username>
   - BOT_DB_PASSWORD=<db password>

2. `<root project>/jrtb-db-prod.env`

   🗝️File contents:
   ```
   - MYSQL_DATABASE=<db name>
   - JDBC_DATABASE_USERNAME=<db username>
   - JDBC_DATABASE_PASSWORD=<db user password>
   - MYSQL_ROOT_PASSWORD=<root password>

to deploy application, switch to needed branch and run bash script:

$ `bash start.sh`

That's all.

# 🖱️ Local development
For local development and testing, use `docker-compose-test.yml`.
Run command:
```shell
docker-compose -f docker-compose-test.yml up
```
Next step, is to run SpringBoot app with configured **Edit Configuration** in which five env vars are provided:

`bot.token=${token};
bot.name=${username};
nasa.api_key=${api_key};
spring.datasource.username=${db_username};
spring.datasource.password=${db_password}`

File for environments `<root project>/jrtb-db-dev.env`

🗝️File contents:


    - MYSQL_DATABASE=<db name>
    - JDBC_DATABASE_USERNAME=<db username>
    - JDBC_DATABASE_PASSWORD=<db user password>
    - MYSQL_ROOT_PASSWORD=<root password>


And add VM Options:

`-Dspring.profiles.active=test `

With these configurations - run SpringBoot main method.

# 🧰 Technological stack 
- SpringBoot as a skeleton framework
- Spring Scheduler as a task manager
- MySQL database as a database for saving user and subscription info
- Flyway database migration tool
- Telegram-bot SpringBoot starter
- Spring Data starter

#### Powered by Intellij
___
<p align="center">
   <a href="https://www.jetbrains.com/"><img src="https://user-images.githubusercontent.com/14723332/87232472-0439e500-c3c8-11ea-8e21-f81ea3af8b70.png" width="100"></a>
</p>



[1]:https://t.me/NasaPhoto_bot
[2]:https://media.giphy.com/media/lWJCPIRMRQLmBvyGLT/giphy.gif
