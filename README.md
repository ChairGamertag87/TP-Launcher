# TP-Launcher

## Description

TP-Launcher est un petit projet Java (avec un script batch) qui sert de **lanceur** (launcher) pour exécuter une application Java.  
Il permet de simplifier la phase de démarrage du programme principal, notamment pour les utilisateurs qui ne veulent pas lancer manuellement les classes Java via la ligne de commande.

## Fonctionnalités

- Exécution de l’application Java via un script `launch.bat` (ou autre selon le système)  
- Organisation du code source dans un dossier `src`  
- Facilité de lancement pour les utilisateurs non techniques  

## Prérequis

- Java JDK installé (version 8 ou supérieure recommandée)  
- Système d’exploitation Windows (pour le script `.bat`) — tu peux aussi créer un script pour macOS / Linux

## Structure du projet

```
TP-Launcher/
├── src/                 # Code source Java
│   └── dir
├── launch.bat           # Script pour démarrer l’application sous Windows
└── README.md            # Ce fichier
```

## Installation et utilisation

1. Cloner le dépôt  
   ```bash
   git clone https://github.com/ChairGamertag87/TP-Launcher.git
   cd TP-Launcher
   ```

2. Compiler le code Java (depuis le dossier du projet)  
   ```bash
   javac -d bin src/dir/*.java
   ```

3. Lancer l’application  
   - Sous Windows : double-cliquer sur `launch.bat` ou l’exécuter dans l’invite de commandes  
   - Ou bien lancer manuellement la classe principale, par exemple :  
     ```bash
     java -cp bin Main.java
     ```

## Contribution

Si tu veux contribuer :  
- Fork le projet  
- Crée une branche (`git checkout -b feature/ma-fonctionnalité`)  
- Fais tes modifications / ajouts  
- Ouvre une pull request
