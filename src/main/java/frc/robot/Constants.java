// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.XboxController;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    /**
     * This is the max speed of our robot, choose this value carefully...
     * 4.5 is too low btw.
     */
    public static final double maxSpeed = Units.feetToMeters(4.5);

    public static final class OIConstants {
        public static final int DRIVER_CONTROLLER_PORT = 0;
        public static final int FLIGHT_CONTROLLER_PORT = 1;
    }

    public static final class IndexerConstants {
        public static final double LoaderVoltage = -4;
        public static final double LoaderVoltageReverse = 4;
        public static final int LoaderID = 21;
    }

    public static final class IntakeConstants {

        public static final double hingeVoltage = -3;
        public static final double hingeVoltageReverse = 2;
        public static final int hingeID = 13;

        public static final double rollerVoltage = -4;
        public static final double rollerVoltageReverse = 4;
        public static final int rollerID = 20;

    }

    public static final class ShooterConstants {
        public static final double shooterVoltage = 8;
        public static final double shooterVoltageReverse = -8;
        public static final int shooterMotorControllerID = 8;
    }

    public static final class StorageConstants {
        public static final double conveyorVoltage = 2;
        public static final double conveyorVoltageReverse = 2;
        public static final int conveyorID = 7;
    }

    public static final class ClimbConstants {
        public static final double winchVoltage = 2;
        public static final int winchId = 12;
        /**
         * The relative encoder value the winch up command will stop at
         */
        public static final int relativeEncoderValue = 0;
    }

    public static final class XboxControllerButtons {
        public static final int TareButton = XboxController.Button.kStart.value;
        public static final int HubAlignMacro = XboxController.Button.kY.value;
        public static final int HubMoveMacro = XboxController.Button.kB.value;

        //Slurp, driver 2
//        public static final int IntakeOut = XboxController.Button.kA.value;
//        public static final int IntakeIn = XboxController.Button.kX.value;
        public static final int IntakerRetract = XboxController.Button.kLeftBumper.value;
        public static final int IntakerDeploy = XboxController.Button.kRightBumper.value;
    }

    public static final class FlightControllerButtons {
        public static final int IntakeIn = 3;
        public static final int IntakeOut = 5;
        public static final int ConveyorInMacro = 2;
        public static final int ShooterShoot = 1;
        public static final int ClimbUp = 6;
        public static final int Climbdown = 4;
        public static final int reversedIndexer = 9;

    }
}

