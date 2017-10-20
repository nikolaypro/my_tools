package com.mascot.my_tools.container;

import java.util.*;

public class ContainerComputer {
    private final SortedMap<Double, Integer> orderLengthToCount = new TreeMap<>(new Comparator<Double>() {
        @Override
        public int compare(Double o1, Double o2) {
            return o2.compareTo(o1);
        }
    });

    private final SortedMap<Double, Double> containerLengthToCost = new TreeMap<>(new Comparator<Double>() {
        @Override
        public int compare(Double o1, Double o2) {
            return o2.compareTo(o1);
        }
    });

    public void add(double length, int count) {
        orderLengthToCount.put(length, count);
    }

    public double computeCost(double containerLength, double containerCost, boolean printDetails) {
        final List<Double> usageContainers = new ArrayList<>(orderLengthToCount.size());
        final Map<Integer, List<Double>> containerIndex2Lengths = new HashMap<>();

        for (Map.Entry<Double, Integer> entry : orderLengthToCount.entrySet()) {
            final double length = entry.getKey();
            final int count = entry.getValue();
            for (int i = 0; i < count; i++) {
                final int containerIndex = findContainerWithMinRemains(usageContainers, length, containerLength);
                usageContainers.set(containerIndex, usageContainers.get(containerIndex) + length);
                addLengthUsage(containerIndex2Lengths, containerIndex, length);
            }
        }

        // ************** PRINT RESULT ***************

        final double cost = usageContainers.size() * containerCost;
        if (true || printDetails) {
            System.out.println("Containers count: " + usageContainers.size());
            System.out.println("Project cost: " + cost);
            System.out.println("Details: ");
            for (Map.Entry<Integer, List<Double>> entry : containerIndex2Lengths.entrySet()) {
                final StringBuilder lengthsStr = new StringBuilder();
                for (Double length : entry.getValue()) {
                    lengthsStr.append(length).append(", ");
                }
                Integer index = entry.getKey();
                System.out.println("Container #" + index + " (use: " + usageContainers.get(index) + "): " + lengthsStr.toString());
            }
        }
        return cost;
    }

    private void addLengthUsage(Map<Integer, List<Double>> containerIndex2Lengths, int containerIndex, double length) {
        List<Double> lengths = containerIndex2Lengths.get(containerIndex);
        if (lengths == null) {
            lengths = new ArrayList<>();
            containerIndex2Lengths.put(containerIndex, lengths);
        }
        lengths.add(length);
    }

    private int findContainerWithMinRemains(List<Double> usageContainers, double needsLength, double containerLength) {
        int resultIndex = -1;
        double minFreeSpace = containerLength;
        for (int i = 0; i < usageContainers.size(); i++) {
            final Double containerUsage = usageContainers.get(i);
            final double initialFreeSpace = containerLength - containerUsage;
            final double freeSpace = initialFreeSpace - needsLength;
            if (freeSpace >= 0 && freeSpace < minFreeSpace) {
                minFreeSpace = freeSpace;
                resultIndex = i;
            }
        }
        if (resultIndex == -1) {
            if (containerLength < needsLength) {
                throw new IllegalStateException("Unable allocate because container small: " + needsLength + " > " + containerLength);
            }
            usageContainers.add(0.0);
            resultIndex = usageContainers.size() - 1;
        }
        return resultIndex;
    }

}