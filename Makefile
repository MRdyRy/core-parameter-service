COMMIT_HASH ?= $(shell git rev-parse HEAD)
BRANCH ?= $(shell git symbolic-ref --short HEAD)
APP ?= $(shell mvn help:evaluate -Dexpression=project.name -q -DforceStdout)
IMAGE ?= ${APP}:${COMMIT_HASH}
RELEASE_TAG ?= $(shell mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
RELEASE_VERSION ?= $(APP):$(RELEASE_TAG)

DOCKER_HUB ?= serfanrud

.PHONY: clean-docker

clean-docker:
			@echo "clean docker container"
			- docker rm -f $(APP)
verify:
			@echo "verify stage"
			- mvn clean verify -DskipTests
build:
			@echo "build stage"
			- mvn clean install -DskipTests
test:
			@echo "test stage"
			- mvn test -T 4
run-app:
			@echo "run spring apps"
			- mvn spring-boot:run
docker-build:
			@echo "docker build"
			- docker build -f docker/Dockerfile -t $(DOCKER_HUB)/$(APP):$(RELEASE_VERSION)
push-img:
			@echo "push image to hub"
			- make docker-build
			- docker tag $(DOCKER_HUB)/$(APP):$(RELEASE_VERSION) $(DOCKER_HUB)/$(APP):$(RELEASE_VERSION)
 			- docker push $(DOCKER_HUB)/$(APP):$(RELEASE_VERSION)