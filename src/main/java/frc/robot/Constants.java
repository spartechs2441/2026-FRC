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
    public static double getMaxVelocity = 4;
    public static double getMaxAngularVelocity = 4;
    public static final double maxSpeed = Units.feetToMeters(4.5);

    public static final class OIConstants {
        public static final int DRIVER_CONTROLLER_PORT = 0;
        public static final int FLIGHT_CONTROLLER_PORT = 1;
    }

    public static final class IndexerConstants {
        public static final double LoaderVoltage = -2;
        public static final double LoaderVoltageReverse = 2;
        public static final int LoaderID = 21;
    }

    public static final class IntakeConstants {

        public static final double hingeVoltage = -1.5;
        public static final double hingeVoltageReverse = 1;
        public static final int hingeID = 13;

        public static final double rollerVoltage = -2;
        public static final double rollerVoltageReverse = 2;
        public static final int rollerID = 20;

    }

    public static final class ShooterConstants {
        public static final double shooterVoltage = 5;
        public static final double shooterVoltageReverse = -5;
        public static final int shooterMotorControllerID = 8;
    }

    public static final class StorageConstants {
        public static final double conveyorVoltage = 2;
        public static final double conveyorVoltageReverse = 2;
        public static final int conveyorID = 7;
    }

    public static final class ClimbConstants {
        public static final double leftWinchVoltage = 2;
        public static final double leftWinchSpeed = 2;
        public static final double leftWinchVoltageReverse = 0;
        public static final int leftWinchID = 12;
        public static final int RelativeEncoder = 0;
        public static final double rightWinchVoltage = 0;
        public static final double rightWinchSpeed = 0;
        public static final double rightWinchVoltageReverse = 0;
        public static final int rightWinchID = 26;
    }

    public static final class XboxControllerButtons {
        //Key bindings haven't assigned yet
        public static final int ShooterShoot = XboxController.Button.kB.value;

        public static final int IndexerLoad = XboxController.Button.kY.value;
        public static final int IndexerOut = 90;

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

    }
}

