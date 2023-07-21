![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)

# Alura Challenge Back-End 7

## Descrição

Este é um projeto desenvolvido durante o Alura Challenge Backend 7ª edição. O projeto consiste no desenvolvimento de uma API que será integrada ao frontend. O objetivo é disponibilizar informações e recursos do banco de dados relacionados a possíveis destinos de viagem, exibindo fotos e um texto chamativo que instigue o usuário a querer visitar aquele destino.

### Tecnologias utilizadas

| Tecnologia         | Versão  |
| ------------------ | ------- |
| Java               | 17      |
| Spring             | 3.1.1   |
| H2 Database        | 2.1.214 |
| Springdoc Open API | 2.1.0   |
| MySQL Database     | 8.0.31  |
| Docker             | 4.21.1  |

## Profiles

Para esta aplicação foram criados dois perfis:
* test - para subir a aplicação em memória utilizando o H2
* dev - para subir a aplicação em container utilizando Docker

## Criando o Dockerfile

```bash
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src
EXPOSE 8082
CMD [ "./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=dev"]
```

## Criando o .dockerignore

```bash
target
```

## Build da imagem Docker

```bash
# cria a imagem
docker build --tag jornada-milhas:v1.0.0 .

# sobe o container
docker-compose -f docker-compose-dev.yml up --build -d

# remove o container
docker-compose -f docker-compose-dev.yml down
```
