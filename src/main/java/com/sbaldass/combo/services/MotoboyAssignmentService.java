package com.sbaldass.combo.services;

import com.sbaldass.combo.domain.Location;
import com.sbaldass.combo.domain.Motoboy;
import com.sbaldass.combo.domain.MotoboyRequest;
import com.sbaldass.combo.domain.MotoboyStatus;
import com.sbaldass.combo.repositories.MotoboyRepository;
import com.sbaldass.combo.repositories.MotoboyRequestRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MotoboyAssignmentService {

    @Autowired
    private MotoboyRepository motoboyRepository;

    @Autowired
    private MotoboyRequestRepository motoboyRequestRepository;

    @Autowired
    private NotificationService notificationService;

    public void assignMotoboy(MotoboyRequest request) {
        // Find available motoboy based on request details (location, availability, etc.)
        Motoboy motoboy = findAvailableMotoboy(request.getPickupLocation());

        if (motoboy != null) {
            // Assign motoboy to the request
            request.setMotoboyId(motoboy.getId());
            request.setStatus(MotoboyStatus.ASSIGNED);
            motoboyRequestRepository.save(request);

            // Notify the user
            notificationService.notifyUser(request.getUserId(), motoboy.getId());

            // Notify the motoboy
            notificationService.notifyMotoboy(motoboy.getId(), request.getId());
        } else {
            // Handle case where no motoboy is available
            request.setStatus(MotoboyStatus.BUSY);
            motoboyRequestRepository.save(request);
//            notificationService.notifyUserNoMotoboyAvailable(request.getUserId());
        }
    }

    private Motoboy findAvailableMotoboy(Location pickupLocation) {
        // Use a geospatial query to find the nearest available motoboy
        Point location = new Point(pickupLocation.getLongitude(), pickupLocation.getLatitude());
        Distance distance = new Distance(10, Metrics.KILOMETERS); // Example radius

        List<Motoboy> availableMotoboys = motoboyRepository.findByLocationNearAndStatus(location, distance, MotoboyStatus.AVAILABLE);
        return availableMotoboys.isEmpty() ? null : availableMotoboys.get(0);
    }

    private Optional<Motoboy> findOptimalMotoboy(Location pickupLocation) {
        // Use a geospatial query to find motoboys within a certain radius
        Point location = new Point(pickupLocation.getLongitude(), pickupLocation.getLatitude());
        Distance distance = new Distance(10, Metrics.KILOMETERS); // Example radius

        List<Motoboy> availableMotoboys = motoboyRepository.findByLocationNearAndStatus(location, distance, MotoboyStatus.AVAILABLE);

        return availableMotoboys.stream()
                .map(motoboy -> {
                    double score = calculateScore(motoboy, pickupLocation);
                    return new MotoboyScore(motoboy, score);
                })
                .max(Comparator.comparingDouble(MotoboyScore::getScore))
                .map(MotoboyScore::getMotoboy);
    }

    private double calculateScore(Motoboy motoboy, Location pickupLocation) {
        double distanceScore = calculateDistanceScore(motoboy.getLocation(), pickupLocation);
        double ratingScore = motoboy.getRating(); // Assume rating is on a scale of 1 to 5

        // Weights for each criterion (adjust as needed)
        double distanceWeight = 0.5;
        double ratingWeight = 0.5;

        return (distanceScore * distanceWeight) + (ratingScore * ratingWeight);
    }

    private double calculateDistanceScore(Location motoboyLocation, Location pickupLocation) {
        double distance = haversine(motoboyLocation.getLatitude(), motoboyLocation.getLongitude(), pickupLocation.getLatitude(), pickupLocation.getLongitude());
        return 1 / (1 + distance); // Inverse distance score, higher is better
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in km
    }

    // Helper class to hold motoboy and score
    @Getter
    private static class MotoboyScore {
        private final Motoboy motoboy;
        private final double score;

        public MotoboyScore(Motoboy motoboy, double score) {
            this.motoboy = motoboy;
            this.score = score;
        }

    }
}
