.PHONY: run

run:
	@echo "Running ./gradlew docker"
	@C:\Users\legen\src\MSA-Study\gradlew docker
	@echo "Running docker-compose up -d"
	@docker-compose up -d