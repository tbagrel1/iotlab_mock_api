# iotlab_mock_api

## Description

Cette API Spring Boot permet de mocker l'API du vrai IOTLab pour tester son application, ainsi que le déclenchement des vibrations, des notifications, des mails...

Elle intègre une base de données intégrée Derby qui sert à représenter la valeur des motes pendant l'exécution. La base étant *embedded*, toute configuration est perdue lorsque l'API est fermée.

## Utilisation sur l'hôte

Le fichier `requests.http` permet d'utiliser facilement l'API sans sortir d'IntelliJ.

### Lister la valeur de toutes les *motes*

L'URL a été choisie pour que le suffixe soit identique à celui du vrai IOTLab : `http://iotlab.telecomnancy.eu:8080/iotlab/rest/data/1/light1/last`

```http request
GET http://localhost:8080/iotlab/rest/data/1/light1/last
```

### Ajouter ou changer la valeur de lumière d'une *mote*

```http request
PUT http://localhost:8080/iotlab/rest/data/1/light1/last/<moteId>
Content-Type: application/json

{"value": <lightValue>}
``` 

### Mettre l'API en mode erreur

Afin de tester son application quand la récupération de données n'est pas possible, il est possible de demander à l'API de retourner une erreur 404 ou 500 pour la route `GET http://localhost:8080/iotlab/rest/data/1/light1/last`

```http request
PUT http://localhost:8080/api_response
Content-Type: application/json

{"http_status_code": 500}
```

### Remettre l'API en fonctionnement normal

```http request
PUT http://localhost:8080/api_response
Content-Type: application/json

{"http_status_code": 200}
```

### Consulter le mode actuel de l'API

```http request
GET http://localhost:8080/api_response
```

## Utilisation avec Android

Si vous utilisez l'émulateur Android, et que l'API de mock tourne sur la machine hôte, il faut utiliser l'URL suivante dans le projet Android : `http://10.0.2.2:8080/iotlab/rest/data/1/light1/last`.

En effet, [`10.0.2.2` référence le `localhost` de la machine hôte depuis l'émulateur Android](https://stackoverflow.com/questions/5528850/how-do-you-connect-localhost-in-the-android-emulator).

De plus, il faut également que votre application Android autorise le traffic HTTP non chiffré vers ce domaine :

`res/xml/network_security_config.xml` :

```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">iotlab.telecomnancy.eu</domain>
        <domain includeSubdomains="true">10.0.2.2</domain>
    </domain-config>
</network-security-config>
```

`manifests/AndroidManifest.xml` :

```xml
...

<application
        ...
        android:networkSecurityConfig="@xml/network_security_config"
        ...   
>

...
```
