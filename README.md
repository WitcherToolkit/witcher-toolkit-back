# The Witcher Application

## 📋 Description
Application complète pour gérer l'univers du Witcher avec :
- **Backend** : Spring Boot + MyBatis + PostgreSQL
- **Frontend** : Angular
- **Infrastructure** : Docker + PostgreSQL 16

## 🚀 Démarrage rapide

### Prérequis
- Docker Desktop
- Java 17+
- Node.js 18+
- Maven 3.8+

### Lancer la base de données
```bash
docker-compose up -d
```

### Lancer le backend
```bash
cd witcher-toolkit-back
mvn spring-boot:run
```

### Lancer le frontend
```bash
cd witcher-toolkit-front
npm install
ng serve
```

## 📁 Structure du projet
```
D:\TheWitcherApplication\
├── infrastructure/           # Scripts SQL PostgreSQL
├── witcher-toolkit-back/    # API Spring Boot
├── witcher-toolkit-front/   # Interface Angular
├── questconnect/            # Autre module
└── docker-compose.yml       # Configuration Docker
```

## 🗄️ Base de données
- **SGBD** : PostgreSQL 16
- **Schémas** : witcher, market, resolver
- **Type ID** : UUID



Arrête une application qui passe par le port 8080 :
Dans le terminal CMD : `netstat -ano | findstr :8080`

Noter le PID (exemple : 00000)
Note le PID, puis :
`taskkill /PID <PID> /F`
`taskkill /PID 00000 /F`