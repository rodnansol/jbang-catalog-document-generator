#!/usr/bin/env just --justfile

# Maven build without tests
build:
   mvn -DskipTests clean install

# Maven Verify
verify:
   mvn clean verify

# Build samples
build-samples: build
  cd samples && mvn clean install

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
snapshot-release version:
  mvn versions:set -DnewVersion={{version}}
  mvn clean -Prelease deploy -DaltDeploymentRepository=local::file:./target/staging-deploy
  mvn jreleaser:full-release -Prelease -N
  mvn versions:set -DnewVersion=999-SNAPSHOT

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
adoc-example:
  ./target/jbang-catalog-document-generator-999-SNAPSHOT-runner generate jbang-catalog.json -ch examples/custom-header.adoc -cf examples/custom-footer.adoc -o target/jbang-catalog.adoc

# Runs with Markdown output
md-example:
  ./target/jbang-catalog-document-generator-999-SNAPSHOT-runner generate jbang-catalog.json -ch examples/custom-header.md -cf examples/custom-footer.md -o target/jbang-catalog.md