// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
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

    // All the TBD constants have been set to 0

    public static final class OIConstants {

        public static final int DRIVER_CONTROLLER_PORT = 0;

        //Controller Controls
        public static final int yMovement = XboxController.Axis.kLeftX.value; //The X and Y Movement are switched
        public static final int xMovement = XboxController.Axis.kLeftY.value;
        public static final int rotation = XboxController.Axis.kRightX.value;
    }
    public static final class IndexerConstants {
        public static final double LoaderVoltage = 0;
        public static final double LoaderVoltageReverse = 0;
        public static final int LoaderID = 20;
    }

    public static final class IntakeConstants{

        public static final double hingeVoltage = 0;
        public static final double hingeVoltageReverse = 0;
        public static final int hingeID = 21;

        public static final double rollerVoltage = 0;
        public static final double rollerVoltageReverse = 0;
        public static final int rollerID = 22;

    }

    public static final class ShooterConstants {
        public static final double shooterMotorControllerVoltage = 0;
        public static final double shooterMotorControllerVoltageReverse = 0;
        public static final int shooterMotorControllerID = 23;
    }

    public static final class StorageConstants {
        public static final double conveyorVoltage = 0;
        public static final double conveyorVoltageReverse = 0;
        public static final int conveyorID = 24;
    }

    public static final class ClimbConstants {
        public static final double leftWinchVoltage = 0;
        public static final double leftWinchVoltageReverse = 0;
        public static final int leftWinchID = 25;

        public static final double rightWinchVoltage = 0;
        public static final double rightWinchVoltageReverse = 0;
        public static final int rightWinchID = 26;
    }
}

