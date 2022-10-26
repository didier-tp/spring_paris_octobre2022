profiles spring de cette application:

- "remoteDB" (avec base distante gérée par un serveur MySql ou PostgresSql ou ...)
   ou "embeddedDB" (avec base de données embarquée telle que H2)
- "perf" declenche l'aspect mesurer et afficher les temps d'executions (MyPerfLogAspect)
- "reInit" pour reinitialiser un jeu de données au re-démarrage de l'appli (en dev )
- "withSecurity" pour la securité
- "dev" ou "prod"
- ...
===========================
Attention, en production: remoteDB,withSecurity,prod
   mais surtout pas "reInit" ni "dev"
