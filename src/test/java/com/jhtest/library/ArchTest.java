package com.jhtest.library;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.jhtest.library");

        noClasses()
            .that()
            .resideInAnyPackage("com.jhtest.library.service..")
            .or()
            .resideInAnyPackage("com.jhtest.library.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.jhtest.library.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
