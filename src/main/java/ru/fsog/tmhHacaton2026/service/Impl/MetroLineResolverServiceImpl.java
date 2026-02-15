package ru.fsog.tmhHacaton2026.service.Impl;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import ru.fsog.tmhHacaton2026.service.MetroLineResolverService;

import java.util.HashMap;
import java.util.Map;

@Service
public class MetroLineResolverServiceImpl implements MetroLineResolverService {

    public final GeometryFactory factory = new GeometryFactory();
    public final Map<String, LineString> metroLines = new HashMap<>();


    public MetroLineResolverServiceImpl() {
        // Координаты - это [longitude (долгота), latitude (широта)]

        metroLines.put("Сокольническая (1)", factory.createLineString(new Coordinate[]{
                new Coordinate(37.748, 55.826), new Coordinate(37.666, 55.777),
                new Coordinate(37.601, 55.751), new Coordinate(37.558, 55.728),
                new Coordinate(37.452, 55.637)
        }));

        metroLines.put("Замоскворецкая (2)", factory.createLineString(new Coordinate[]{
                new Coordinate(37.474, 55.870), new Coordinate(37.533, 55.795),
                new Coordinate(37.632, 55.744), new Coordinate(37.638, 55.633),
                new Coordinate(37.684, 55.610)
        }));

        metroLines.put("Арбатско-Покровская (3)", factory.createLineString(new Coordinate[]{
                new Coordinate(37.359, 55.854), new Coordinate(37.444, 55.741),
                new Coordinate(37.603, 55.752), new Coordinate(37.775, 55.789),
                new Coordinate(38.169, 55.825)
        }));

        metroLines.put("Филёвская (4)", factory.createLineString(new Coordinate[]{
                new Coordinate(37.453, 55.743), new Coordinate(37.560, 55.748),
                new Coordinate(37.531, 55.747)
        }));

        metroLines.put("Кольцевая (5)", factory.createLineString(new Coordinate[]{
                new Coordinate(37.583, 55.777), new Coordinate(37.655, 55.776),
                new Coordinate(37.657, 55.729), new Coordinate(37.587, 55.728),
                new Coordinate(37.583, 55.777) // Замкнутая
        }));

        metroLines.put("Калужско-Рижская (6)", factory.createLineString(new Coordinate[]{
                new Coordinate(37.661, 55.879), new Coordinate(37.632, 55.759),
                new Coordinate(37.603, 55.727), new Coordinate(37.553, 55.640)
        }));

        metroLines.put("Таганско-Краснопресненская (7)", factory.createLineString(new Coordinate[]{
                new Coordinate(37.436, 55.860), new Coordinate(37.564, 55.760),
                new Coordinate(37.653, 55.742), new Coordinate(37.853, 55.683)
        }));

        metroLines.put("Калининско-Солнцевская (8/8А)", factory.createLineString(new Coordinate[]{
                new Coordinate(37.350, 55.630), new Coordinate(37.533, 55.748),
                new Coordinate(37.670, 55.747), new Coordinate(37.828, 55.749)
        }));

        metroLines.put("Серпуховско-Тимирязевская (9)", factory.createLineString(new Coordinate[]{
                new Coordinate(37.588, 55.889), new Coordinate(37.614, 55.750),
                new Coordinate(37.596, 55.611)
        }));

        metroLines.put("Люблинско-Дмитровская (10)", factory.createLineString(new Coordinate[]{
                new Coordinate(37.545, 55.904), new Coordinate(37.601, 55.770),
                new Coordinate(37.676, 55.711), new Coordinate(37.717, 55.620)
        }));

        metroLines.put("БКЛ (11)", factory.createLineString(new Coordinate[]{
                new Coordinate(37.470, 55.800), new Coordinate(37.700, 55.800),
                new Coordinate(37.700, 55.650), new Coordinate(37.470, 55.650),
                new Coordinate(37.470, 55.800)
        }));

        metroLines.put("МЦК (14)", factory.createLineString(new Coordinate[]{
                new Coordinate(37.500, 55.840), new Coordinate(37.700, 55.750),
                new Coordinate(37.500, 55.660), new Coordinate(37.400, 55.750),
                new Coordinate(37.500, 55.840)
        }));
    }

    @Override
    public String getLineByCoords(Double lat, Double lon) {
        if (lat == null || lon == null) return "Координаты отсутствуют";

        Point userPoint = factory.createPoint(new Coordinate(lon, lat));
        String bestLine = "Вне метро";
        double minDistance = Double.MAX_VALUE;

        double threshold = 0.008;

        for (Map.Entry<String, LineString> entry : metroLines.entrySet()) {
            double distance = entry.getValue().distance(userPoint);
            if (distance < threshold && distance < minDistance) {
                minDistance = distance;
                bestLine = entry.getKey();
            }
        }
        return bestLine;
    }
}
