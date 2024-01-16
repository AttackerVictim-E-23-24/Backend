package com.ucab.cmcapp.common.util;

import com.ucab.cmcapp.logic.dtos.CoordenadaDto;

import java.awt.geom.Path2D;
import java.util.List;

public class ZonaSegValidator {

    public static boolean isPointInPolygon(double pointLat, double pointLong, List<CoordenadaDto> polygonCoordinates) {
        Path2D.Double polygon = new Path2D.Double();
        if (polygonCoordinates.size() < 3) {
            throw new IllegalArgumentException("Un polígono debe tener al menos 3 coordenadas.");
        }
        CoordenadaDto firstCoordinate = polygonCoordinates.get(0);
        polygon.moveTo(firstCoordinate.getLongitud(), firstCoordinate.getLatitud());
        for (int i = 1; i < polygonCoordinates.size(); i++) {
            CoordenadaDto coordinate = polygonCoordinates.get(i);
            polygon.lineTo(coordinate.getLongitud(), coordinate.getLatitud());
        }
        polygon.closePath();
        return polygon.contains(pointLong, pointLat);
    }

}