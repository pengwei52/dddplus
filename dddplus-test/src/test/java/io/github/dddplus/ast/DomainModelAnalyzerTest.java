package io.github.dddplus.ast;

import io.github.dddplus.ast.model.AggregateEntry;
import io.github.dddplus.ast.model.KeyModelEntry;
import io.github.dddplus.ast.view.PlainTextBuilder;
import io.github.dddplus.ast.view.PlantUmlBuilder;
import io.github.dddplus.runtime.registry.IntegrationTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DomainModelAnalyzerTest {

    /**
     * {@link IntegrationTest#exportDomainArtifacts()} will export all extensions info.
     */
    @Test
    void analyze() throws IOException {
        DomainModelAnalyzer analyzer = new DomainModelAnalyzer();
        analyzer.scan(moduleRoot("dddplus-test"));
        ReverseEngineeringModel model = analyzer.analyze((level, path, file) -> path.contains("design"));
        assertNotNull(model.getAggregateReport());
        assertTrue(model.getKeyRelationReport().size() > 1);
        assertTrue(model.aggregates().size() > 0);
        List<KeyModelEntry> keyModelEntryList = model.getKeyModelReport().keyModelsOfPackage(model.getAggregateReport().getAggregateEntries().get(0).getPackageName());
        assertEquals(5, keyModelEntryList.size());
        assertEquals("io.github.design", keyModelEntryList.get(0).getPackageName());
        AggregateEntry firstAggregate = model.getAggregateReport().get(0);
        assertEquals(firstAggregate.getName(), "foo");
        // render
        PlantUmlBuilder pb = new PlantUmlBuilder()
                .header("header")
                .footer("footer")
                .title("title")
                .appendNote("abc")
                .appendNote("dc")
                .skinParam("ranksep 150")
                .direction(PlantUmlBuilder.Direction.LeftToRight)
                .build(model);
        String uml = pb.umlContent();
        assertFalse(uml.isEmpty());
    }

    @Test
    void renderText() throws IOException {
        DomainModelAnalyzer analyzer = new DomainModelAnalyzer();
        analyzer.rawSimilarity()
                .similarityThreshold(0)
                .scan(moduleRoot("dddplus-test"));
        ReverseEngineeringModel model = analyzer.analyze((level, path, file) -> path.contains("design"));
        new PlainTextBuilder()
                .build(model)
                .showRawSimilarities()
                .render("../model.txt");
    }

    @Test
    @Disabled
    void renderUml() throws IOException {
        ReverseEngineeringModel model = new DomainModelAnalyzer()
                .debug()
                .rawSimilarity()
                .scan(moduleRoot("dddplus-test"))
                .analyze((level, path, file) -> path.contains("design"));
        new PlantUmlBuilder()
                .appendNote("abc")
                .appendNote("dc")
                .skipParamHandWrittenStyle()
                .skinParamPolyline()
                .build(model).renderSvg("../test.svg");
    }

    static File moduleRoot(String module) throws IOException {
        return (projectRoot().listFiles(f -> f.getName().equals(module)))[0];
    }

    private static File projectRoot() throws IOException {
        File currentDir = new ClassPathResource("").getFile(); // dddplus-test/target/test-classes
        return currentDir
                .getParentFile()
                .getParentFile()
                .getParentFile();
    }

}
