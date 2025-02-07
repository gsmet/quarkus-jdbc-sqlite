package io.quarkiverse.jdbc.sqlite.jdbc.sqlite.it.jpa;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.quarkus.test.junit.nativeimage.ClassInclusionReport;

/**
 * Test various JPA operations running in native mode
 */
@QuarkusIntegrationTest
public class JPAFunctionalityInGraalITCase extends JPAFunctionalityTest {

    @Test
    public void verifyJdkXmlParsersHavebeenEcludedFromNative() {
        final ClassInclusionReport report = ClassInclusionReport.load();
        //The following classes should be included in this applications;
        //if not, that would be a sign that this test has become too weak
        //to identify the well working of the exclusions.
        report.assertContains(org.hibernate.type.PostgresUUIDType.class);

        //And finally verify we managed to exclude the JDK XML because of having hinted the analysis
        //(See io.quarkus.jdbc.postgresql.runtime.graal.SQLXLMFeature )
        report.assertContainsNot(javax.xml.transform.TransformerFactory.class);
    }

}
