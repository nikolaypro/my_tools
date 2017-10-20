package com.mascot.my_tools.container;

import org.testng.annotations.Test;

/**
 * Project: Santa-cruz
 * Author:  Nikolay Prokopov
 * Created:  20.10.2017
 * <p/>
 * Copyright (c) 1999-2017) Magenta Corporation Ltd. All Rights Reserved.
 * Magenta Technology proprietary and confidential.
 * Use is subject to license terms.
 * <p/>
 **/
@Test
public class ContainerComputerUTest {
    public void test2And_3Meter() {
        // **************** INITIAL DATA *********************
        ContainerComputer computer = new ContainerComputer();
        computer.add(1.0, 11);// 11 Штук по 1 метру
        computer.add(1.9, 2);
        computer.add(1.85, 2);
        computer.add(1.95, 2);
        computer.add(1.2, 8);
        computer.add(0.32, 2);
        computer.add(0.095, 2);
        computer.add(1.4, 2);

//        final double containerLength = 3;// 3 метра
//        final double containerCost = 142;

        // ****************************************************
        final double cost1 = computer.computeCost(2, 91, false);

        computer = new ContainerComputer();
        computer.add(2.1, 2);

        final double cost2 = computer.computeCost(3, 142, false);

        log("Container length: 2 and 3, container cost: 91 and 142", cost1 + cost2);
    }

    public void test3Meter() {
        // **************** INITIAL DATA *********************
        ContainerComputer computer = new ContainerComputer();
        computer.add(1.0, 11);// 11 Штук по 1 метру
        computer.add(1.9, 2);
        computer.add(1.85, 2);
        computer.add(2.1, 2);
        computer.add(1.95, 2);
        computer.add(1.2, 8);
        computer.add(0.32, 2);
        computer.add(0.095, 2);
        computer.add(1.4, 2);

//        final double containerLength = 3;// 3 метра
//        final double containerCost = 142;

        // ****************************************************
        final double cost1 = computer.computeCost(3, 142, false);
        log("Container length: 3, container cost: 142", cost1);
    }

    public void test2Meter() {
        // **************** INITIAL DATA *********************
        // 2 доски сделать меньшего размера
        ContainerComputer computer = new ContainerComputer();
        computer.add(1.0, 11);// 11 Штук по 1 метру
        computer.add(1.9, 2);
        computer.add(1.85, 2);
        computer.add(2.0, 2);// changed!!!
        computer.add(1.95, 2);
        computer.add(1.2, 8);
        computer.add(0.32, 2);
        computer.add(0.095, 2);
        computer.add(1.4, 2);

//        final double containerLength = 3;// 3 метра
//        final double containerCost = 142;

        // ****************************************************
        final double cost1 = computer.computeCost(2, 91, false);
        log("Container length: 2, container cost: 91", cost1);
    }

    void log(String description, Double cost) {
        System.out.println("********* RESULT: " + cost + ". Details: " + description);
    }
    
}
