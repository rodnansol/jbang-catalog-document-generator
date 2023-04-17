//usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.rodnansol:jbang-catalog-document-generator:999-SNAPSHOT
//JAVA 17

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class JbangCatalogDocumenter {

    public static void main(String[] args) {
        Quarkus.run(JbangCatalogDocumentGenerator.class, args);
    }
}

