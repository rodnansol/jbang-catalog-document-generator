#!/usr/bin/env just --justfile

#!/usr/bin/env just --justfile

set export

home_dir  := env_var('HOME')
JAVA_HOME := home_dir + "/.sdkman/candidates/java/22.3.r17-grl"
MAVEN_HOME := home_dir + "/.sdkman/candidates/maven/3.9.0"

# Maven build without tests
build:
   mvn -DskipTests clean install

# Maven Verify
verify:
   mvn clean verify

# Build samples
build-samples: build
  cd samples && mvn clean install

cleanup:
  rm -rf ~/.m2/repository/org/rodnansol/jbang-catalog-document-generator-{core,cli}

clean-build: cleanup
  ./mvnw clean install
  ./mvnw package -Puber-jar -f jbang-catalog-document-generator-cli/pom.xml

# Debug samples
debug-samples: build
  cd samples && mvnDebug clean install -X

# Debug Multi Module Docs sample
debug-multi-module-docs: build
  cd samples/multi-module/multi-module-docs && mvnDebug clean install -X

# Dry full-release
dry-release:
  mvn clean -Prelease deploy -DaltDeploymentRepository=local::file:./target/staging-deploy
  mvn jreleaser:full-release -Prelease -Djreleaser.dry.run

# Snapshot release
snapshot-release version: cleanup
  ./mvnw versions:set -DnewVersion={{version}}
  ./mvnw clean
  ./mvnw -Prelease,uber-jar deploy -DaltDeploymentRepository=local::file:./target/staging-deploy
  ./scripts/jbang-version-release.sh {{version}}
  ./mvnw jreleaser:release -Prelease -N -Djreleaser-nexus-deploy.active=SNAPSHOT -Djreleaser-github-release.pre-release=true

# Draft release
draft-release version: (create-jbang-release version)
  mvn versions:set -DnewVersion={{version}}
  mvn jreleaser:release -Prelease -Djreleaser-github-release.draft=true -Djreleaser-nexus-deploy.active=NEVER -N
  mvn versions:set -DnewVersion=999-SNAPSHOT

# Full-release
full-release version:
  mvn versions:set -DnewVersion={{version}}
  mvn clean -Prelease deploy -DaltDeploymentRepository=local::file:./target/staging-deploy
  mvn jreleaser:full-release -Prelease N
  mvn versions:set -DnewVersion=999-SNAPSHOT

announce-release version:
  mvn versions:set -DnewVersion={{version}}
  #mvn clean -Prelease deploy -DaltDeploymentRepository=local::file:./target/staging-deploy
  mvn jreleaser:announce -Prelease -X -N
  mvn versions:set -DnewVersion=999-SNAPSHOT

# JReleaser config
dry-release-config:
  mvn -Prelease jreleaser:config -Djreleaser.dry.run

# Creates a JBang "release"
create-jbang-release version:
  ./scripts/jbang-version-release.sh {{version}}

# Runs with AsciiDoc output
help-example:
  java -jar jbang-catalog-document-generator-cli/target/jbang-catalog-document-generator-cli-999-SNAPSHOT.jar generate

# Runs with AsciiDoc output
adoc-example:
  java -Dapp.document.current-working-directory=docs/modules/ROOT/examples -Dquarkus.log.category.\"org.rodnansol\".level=DEBUG  \
  -Dapp.document.alias-usage-folder-path=docs/modules/ROOT/examples/alias-usages \
  -jar jbang-catalog-document-generator-cli/target/jbang-catalog-document-generator-cli-999-SNAPSHOT.jar \
   generate docs/modules/ROOT/examples/jbang-catalog.json \
   -ch custom-header.adoc -cf custom-footer.adoc -o target/jbang-catalog.adoc --check-checksum=false -hfdr -pcn="personal-rodnan" -cus=true

# Runs with Markdown output
md-example:
  java -Dapp.document.current-working-directory=docs/modules/ROOT/examples -Dquarkus.log.category.\"org.rodnansol\".level=DEBUG  \
  -jar jbang-catalog-document-generator-cli/target/jbang-catalog-document-generator-cli-999-SNAPSHOT.jar \
   generate -tt MD docs/modules/ROOT/examples/jbang-catalog.json -ch custom-header.md -cf custom-footer.md -o target/jbang-catalog.md --check-checksum=false -pcn="personal-rodnan" -cus=true -toc=true

# Runs with AsciiDoc output
adoc-native-example:
  ./jbang-catalog-document-generator-cli/target/jbang-catalog-document-generator-cli-999-SNAPSHOT -Dapp.document.current-working-directory=docs/modules/ROOT/examples \
   generate docs/modules/ROOT/examples/jbang-catalog.json \
  -ch custom-header.adoc -cf custom-footer.adoc -o target/jbang-catalog.adoc --check-checksum=false -hfdr -pcn="personal-rodnan" -cus=true

# Runs with Markdown output
md-native-example:
  ./jbang-catalog-document-generator-cli/target/jbang-catalog-document-generator-cli-999-SNAPSHOT -Dapp.document.current-working-directory=docs/modules/ROOT/examples \
  generate docs/modules/ROOT/examples/jbang-catalog.json \
  -tt MD -ch custom-header.md -cf custom-footer.md -o target/jbang-catalog.md --check-checksum=false -pcn="personal-rodnan" -cus=true -toc=true

# Runs with AsciiDoc output
adoc-native-docker-example:
  docker run --user $(id -u):$(id -g) -v $(pwd):/project -it ghcr.io/rodnansol/jbang-catalog-document-generator:999-SNAPSHOT-native -Dapp.document.current-working-directory=/project/docs/modules/ROOT/examples \
   generate /project/docs/modules/ROOT/examples/jbang-catalog.json \
  -ch custom-header.adoc -cf custom-footer.adoc -o /project/target/jbang-catalog.adoc --check-checksum=false -hfdr -pcn="personal-rodnan" -cus=true

# Runs with Markdown output
md-native-docker-example:
  docker run --user $(id -u):$(id -g) -v $(pwd):/project -it ghcr.io/rodnansol/jbang-catalog-document-generator:999-SNAPSHOT-native -Dapp.document.current-working-directory=/project/docs/modules/ROOT/examples \
  generate /project/docs/modules/ROOT/examples/jbang-catalog.json \
  -tt MD -ch custom-header.md -cf custom-footer.md -o /project/target/jbang-catalog.md --check-checksum=false -pcn="personal-rodnan" -cus=true -toc=true

# Runs with AsciiDoc output
adoc-custom-template-example:
  java -Dapp.document.current-working-directory=docs/modules/ROOT/examples \
  -jar jbang-catalog-document-generator-cli/target/jbang-catalog-document-generator-cli-999-SNAPSHOT.jar \
  generate docs/modules/ROOT/examples/jbang-catalog.json \
  -ch custom-header.adoc -cf custom-footer.adoc -o target/jbang-catalog.adoc --check-checksum=false -hfdr -pcn="personal-rodnan" -cus=true -t custom-template.adoc

# Runs with Markdown output
md-custom-template-example:
  java -Dapp.document.current-working-directory=docs/modules/ROOT/examples \
  -jar jbang-catalog-document-generator-cli/target/jbang-catalog-document-generator-cli-999-SNAPSHOT.jar \
   generate -tt MD docs/modules/ROOT/examples/jbang-catalog.json \
  -ch examples/custom-header.md -cf examples/custom-footer.md -o target/jbang-catalog.md --check-checksum=false -pcn="personal-rodnan" -cus=true -toc=true -t custom-template.md

# Runs with AsciiDoc output
adoc-custom-template-native-example:
  ./jbang-catalog-document-generator-cli/target/jbang-catalog-document-generator-cli-999-SNAPSHOT -Dquarkus.log.category.\"org.rodnansol\".level=DEBUG \
  -Dapp.document.current-working-directory=docs/modules/ROOT/examples \
  generate docs/modules/ROOT/examples/jbang-catalog.json \
  -ch custom-header.adoc -cf custom-footer.adoc -o target/jbang-catalog.adoc --check-checksum=false -hfdr -pcn="personal-rodnan" -cus=true -t custom-template.adoc

# Runs with Markdown output
md-custom-template-native-example:
  ./jbang-catalog-document-generator-cli/target/jbang-catalog-document-generator-cli-999-SNAPSHOT -Dquarkus.log.category.\"org.rodnansol\".level=DEBUG -Dapp.document.current-working-directory=docs/modules/ROOT/examples \
   generate -tt MD docs/modules/ROOT/examples/jbang-catalog.json \
  -ch custom-header.md -cf custom-footer.md -o target/jbang-catalog.md --check-checksum=false -pcn="personal-rodnan" -cus=true -toc=true -t custom-template.md