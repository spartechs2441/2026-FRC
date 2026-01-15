// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Filesystem;
import frc.robot.Constants;
import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;
import java.io.File;
import java.io.IOException;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveSubsystem extends SubsystemBase
{
    SwerveDrive swerveDrive;
    /** Creates a new ExampleSubsystem. */
    public SwerveSubsystem (File directory) {

        double maximumSpeed = Units.feetToMeters(4.5);
        File swerveJsonDirectory = new File(Filesystem.getDeployDirectory(),"swerve");

        try {
            swerveDrive = new SwerveParser(directory).createSwerveDrive(maximumSpeed);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Example command factory method.
     *
     * @return a command
     */
    public Command driveCommand (DoubleSupplier translationX, DoubleSupplier translationY, DoubleSupplier angularRotationX)
    {
        return run(() -> {
            // Make the robot move
            swerveDrive.drive(new Translation2d(translationX.getAsDouble() * Constants.getMaxVelocity,
                            translationY.getAsDouble() * Constants.getMaxVelocity),
                    angularRotationX.getAsDouble() * Constants.getMaxAngularVelocity,
                    true,
                    false);
        });
    }


    /**
     * An example method querying a boolean state of the subsystem (for example, a digital sensor).
     *
     * @return value of some boolean subsystem state, such as a digital sensor.
     */
    public boolean exampleCondition()
    {
        // Query some boolean state, such as a digital sensor.
        return false;
    }


    @Override
    public void periodic()
    {
        // This method will be called once per scheduler run
    }


    @Override
    public void simulationPeriodic()
    {
        // This method will be called once per scheduler run during simulation
    }
}
