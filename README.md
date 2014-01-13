<h1>Heracles-Sport</h1>

<h2>Authors</h2>

<b>Barbier Clément (3061254)</b><br/>
<b>Suau Therry (2405893)</b><br/>

<h2>Important</h2>
<h3>Quotas Google App Engine</h3>
Nous nous sommes apercu que le site devenait inopérationel lorsque que nous atteignons les limites de requêtes en lecture dans le DataStore fixées par Google App Engine en compte gratuit.
Pour pallier à çà nous n'avons pas de solution, il faut attendre que les quotas soient remis à zéro.

<h3>Requêtes à l'API</h3>
Afin de ne pas avoir à attendre les tâches Cron chargées de récupérer les rencontres, vous pouvez récupérer, en plus des données de tests disponibles, de véritables rencontre grâce à cette procédure :
- Se connecter en admin
- Ajouter à l'url de base (le domain) : /cron/team (cela rempli la bd des équipes de la NBA
- Ajouter à l'url de base (le domain) : /cron/schedule (cela rempli la bdd des rencontres de la NBA, mais il s'agit d'une grosse requete parfois trop longue pour google !

<h2>URL</h2>

<b>Application URL : </b> <a href="http://heracles-sport.appspot.com/" target="_blank">heracles-sport</a><br/>

<b>GEA Project URL : </b> <a href="https://appengine.google.com/dashboard?app_id=s~heracles-sport" target="_blank">googleappengine-heracles-sport-project</a><br/>

<b>Soutenances PDF URL : </b> <a href="https://github.com/BlackSlashProd/Heracles-Sport/blob/master/HeraclesSport/doc/soutenances_aar_barbier_suau.pdf" target="_blank">SoutenancesBarbierSuau.pdf</a><br/>
