# Cursor current Task:

```agsl
Зробити REST API Для Student ентіті



Student має мати :



List<Courses>



Зробити ані для:



Додавання курсу для студенту

Вивести всі курси студента

Видалити студента з курсу
```

## For project need install nodejs v18.14.0, jdk 17.0.1, and gradle 7.6, docker and docker-compose:
1. [Install nodejs](https://nodejs.org/en/download/)
2. [Install jdk use jvms](https://github.com/ystyle/jvms)
3. [Install gradle](https://gradle.org/install/)
4. [Install docker](https://docs.docker.com/engine/install/)
5. [Install docker-compose](https://docs.docker.com/compose/install/)


## For run project need run command:

```bash
# Run docker-compose
docker-compose up -d
# do npm install
npm install
# do npm run build
npm run build
# do gradle build
gradle build
# do gradle bootRun
gradle bootRun
# do run test
gradle test
# go to http://localhost:5000 you will see the page react
# go to http://localhost:5000/api
```

## Open swagger-ui on url: http://localhost:5000/swagger-ui/index.html