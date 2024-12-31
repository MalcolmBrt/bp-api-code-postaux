# 🔎 API de recherche des codes postaux

Ce projet a pour but de fournir une API permettant de trouver différentes informations autour des codes postaux.

---

## 🚀 Initialisation du projet

### 1. Cloner le projet

Pour commencer, clonez le dépôt du projet depuis GitLab.

```shell
git clone https://gitlab.intranet.opt/postal/bp/bp-api-code-postaux.git
cd bp-api-code-postaux
```

### 2. Compiler le projet

Avant de démarrer les conteneurs, compilez le projet Java avec Gradle.

```shell
./gradlew build
```

### 3. Démarrer les conteneurs

Vous pouvez démarrer tous les conteneurs nécessaires à l'application en utilisant Docker Compose.

```shell
docker-compose up -d
```

### 4. Accéder à l'API

Une fois les conteneurs démarrés, vous pouvez accéder à l'application via votre navigateur à l'adresse suivante : 

```
http://localhost:8080
```

---

## 🌐 Documentation de l'API

La documentation complète de l'API est accessible via Swagger.
Une fois les conteneurs démarrés, vous pouvez y accéder à l'adresse suivante :

```
http://localhost:8080/swagger-ui/index.html
```