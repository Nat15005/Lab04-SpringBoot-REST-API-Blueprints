package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.filter.impl.SubsamplingFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubsamplingFilterTest {

    private SubsamplingFilter subsamplingFilter;

    @BeforeEach
    public void setUp() {
        subsamplingFilter = new SubsamplingFilter();
    }

    @Test
    public void testFilterBlueprintWithSubsampling() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.add(new Point(3, 3));
        points.add(new Point(4, 4));
        points.add(new Point(5, 5));

        Point[] pointsArray = points.toArray(new Point[0]);

        Blueprint bp = new Blueprint("author", "subsampledBlueprint", pointsArray);

        Blueprint filteredBlueprint = subsamplingFilter.filterBlueprint(bp);

        List<Point> expectedPoints = new ArrayList<>();
        expectedPoints.add(new Point(0, 0));
        expectedPoints.add(new Point(2, 2));
        expectedPoints.add(new Point(4, 4));

        assertEquals(expectedPoints, filteredBlueprint.getPoints(), "Los puntos filtrados no coinciden con el submuestreo esperado.");
    }

    @Test
    public void testFilterBlueprintWithOddNumberOfPoints() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.add(new Point(3, 3));
        points.add(new Point(4, 4));
        points.add(new Point(5, 5));
        points.add(new Point(6, 6));

        Point[] pointsArray = points.toArray(new Point[0]);

        Blueprint bp = new Blueprint("author", "oddPointsBlueprint", pointsArray);

        Blueprint filteredBlueprint = subsamplingFilter.filterBlueprint(bp);

        List<Point> expectedPoints = new ArrayList<>();
        expectedPoints.add(new Point(0, 0));
        expectedPoints.add(new Point(2, 2));
        expectedPoints.add(new Point(4, 4));
        expectedPoints.add(new Point(6, 6));

        assertEquals(expectedPoints, filteredBlueprint.getPoints());
    }


}
