package net.franckbenault.checkdb.struture;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

public class LayerRulesTest {
	
    @ArchTest
    public static final ArchRule layer_dependencies_are_respected = layeredArchitecture()
           .layer("main").definedBy("net.franckbenault.checkdb..")
           .layer("input").definedBy("net.franckbenault.checkdb.input..")
           .layer("output").definedBy("net.franckbenault.checkdb.output..")
           .whereLayer("main").mayNotBeAccessedByAnyLayer()
           .whereLayer("input").mayOnlyBeAccessedByLayers("main")
           .whereLayer("output").mayOnlyBeAccessedByLayers("main");

}
