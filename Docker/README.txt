Pour installer docker :
	Emergency:
		cd .../Docker/Emergency
		docker-compose up
		Lancer console du container emergency_db:
			su postgres
			psql -U postgres < 'dumps/emergency.dmp'
	Grafana:
		cd .../Docker/Grafana
		docker-compose up
		Aller dans le container postgres:
			su postgres
			psql -f scripts/createRole
			psql -U postgres < "dumps/grafana.dmp"
		Aller sous localhost:3001:
			connecter la BDD:
				ip: host.docker.internal:5434
				database: grafana
				user: grafana
				password: grafana
				SSL Mode: disable
			Importer le dashboard:
				Icone + (à gauche)
				import
				Importer le fichier Capteurs.json