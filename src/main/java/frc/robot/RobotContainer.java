// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.Vector;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import org.dyn4j.geometry.Rotation;
import org.dyn4j.geometry.Vector2;
import swervelib.SwerveInputStream;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final SwerveSubsystem swerveSub = new SwerveSubsystem();
    private final IntakeSubsystem intakeSub = new IntakeSubsystem();
    private final ShooterSubsystem shooterSub = new ShooterSubsystem();

    private Vector2 rotation() {
        Vector2 direction = new Vector2(driverController.getLeftX(), -driverController.getLeftY());
        direction.rotate(Rotation.rotation270());
        return direction;
    }
    // driver controller
    XboxController driverController = new XboxController(Constants.OIConstants.DRIVER_CONTROLLER_PORT);
    // Replace with CommandPS4Controller or CommandJoystick if needed
    SwerveInputStream driveAngularVelocity = SwerveInputStream.of(
                    swerveSub.getSwerveDrive(),
                    () -> rotation().x,
                    () -> rotation().y
            ) // Axis which give the desired translational angle and speed.
            .withControllerRotationAxis(
                    () -> driverController.getLeftTriggerAxis() - driverController.getRightTriggerAxis()
            ) // Axis which give the desired angular velocity.
            .deadband(0.01)                  // Controller deadband
            .scaleTranslation(0.8)           // Scaled controller translation axis
            .allianceRelativeControl(true);  // Alliance relative controls.

    Command driveFieldOrientedAngularVelocity = swerveSub.driveFieldOriented(driveAngularVelocity);
    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the trigger bindings
        configureBindings();
        swerveSub.setDefaultCommand(driveFieldOrientedAngularVelocity);
    }

    private void configureBindings() {
        // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
        new Trigger(swerveSub::exampleCondition)
                .onTrue(new ExampleCommand(swerveSub));

        // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
        // cancelling on release.

    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An example command will be run in autonomous
        return Autos.exampleAuto(swerveSub);
    }


}
