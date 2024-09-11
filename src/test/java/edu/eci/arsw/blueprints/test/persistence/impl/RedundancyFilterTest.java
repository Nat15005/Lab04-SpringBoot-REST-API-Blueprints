package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.filter.impl.RedundancyFilter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedundancyFilterTest {

    @Test
    public void testFilterBlueprintWithRedundancy() {
        Point[] points = new Point[]{new Point(10, 10), new Point(20, 20), new Point(20, 20), new Point(30, 30)};
        Blueprint blueprint = new Blueprint("author1", "blueprint1", points);

        RedundancyFilter redundancyFilter = new RedundancyFilter();
        Blueprint filteredBlueprint = redundancyFilter.filterBlueprint(blueprint);

        List<Point> filteredPoints = filteredBlueprint.getPoints();
        assertEquals(3, filteredPoints.size());
        assertEquals(new Point(10, 10), filteredPoints.get(0));
        assertEquals(new Point(20, 20), filteredPoints.get(1));
        assertEquals(new Point(30, 30), filteredPoints.get(2));
    }

    @Test
    public void testFilterBlueprintWithoutRedundancy() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.add(new Point(3, 3));
        points.add(new Point(4, 4));

        Point[] pointsArray = points.toArray(new Point[0]);

        Blueprint bp = new Blueprint("author", "noRedundancyBlueprint", pointsArray);

        RedundancyFilter redundancyFilter = new RedundancyFilter();
        Blueprint filteredBlueprint = redundancyFilter.filterBlueprint(bp);

        assertEquals(points, filteredBlueprint.getPoints(), "El filtro eliminó puntos que no eran redundantes.");
    }

}
