// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
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
    private final IndexerSubsystem indexSub = new IndexerSubsystem();
    private final ClimbSubsystem climbSub = new ClimbSubsystem();
    private final StorageSubsystem conveyorSub = new StorageSubsystem();
    //private final SendableChooser<Command> autoChooser;

    // We use `CommandXboxController` and `CommandJoystick` to hopefully improve polling with the scheduler
   CommandXboxController commandXboxController = new CommandXboxController(Constants.OIConstants.DRIVER_CONTROLLER_PORT);
    XboxController driverController = commandXboxController.getHID();
   // CommandJoystick commandFlightstick = new CommandJoystick(Constants.OIConstants.FLIGHT_CONTROLLER_PORT);
   // Joystick flightController = commandFlightstick.getHID();
    // Replace with CommandPS4Controller or CommandJoystick if needed
    SwerveInputStream driveAngularVelocity = SwerveInputStream.of(
                    swerveSub.getSwerveDrive(),
                    () -> rotation().x,
                    () -> rotation().y
            ) // Axis which give the desired translational angle and speed.
            .withControllerRotationAxis(
                    () -> driverController.getLeftTriggerAxis() - driverController.getRightTriggerAxis()
            ) // Axis which give the desired angular velocity.
            .deadband(0.01)                // Controller deadband
            .scaleTranslation(1)           // Scaled controller translation axis
            .allianceRelativeControl(true);  // Alliance relative controls.
    Command driveFieldOrientedAngularVelocity = swerveSub.driveFieldOriented(driveAngularVelocity);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the trigger bindings

      //  NamedCommands.registerCommand("ClimbDown", new AutoClimbDownCommand(climbSub));
       // System.out.println("Finished registering named commands");

      //  swerveSub.configureAutoBuilder();

       // autoChooser = AutoBuilder.buildAutoChooser();
       // SmartDashboard.putData("Auto Chooser", autoChooser);


        configureBindings();
        swerveSub.setDefaultCommand(driveFieldOrientedAngularVelocity);
    }

    private Vector2 rotation() {
        Vector2 direction = new Vector2(driverController.getLeftX(), -driverController.getLeftY());
        direction.rotate(Rotation.rotation270());
        return direction;
    }

    private void configureBindings() {
        new JoystickButton(driverController, Constants.XboxControllerButtons.TareButton)
                .onTrue(new TareCommand(swerveSub));

/*  FLIGHT CONTROLLER KEYBINDS (inactive)
        new JoystickButton(flightController, Constants.FlightControllerButtons.Climbdown)
                    .onTrue(new ClimbDownCommand(climbSub))
                    .onFalse(new ClimbStopCommand(climbSub));
        new JoystickButton(flightController, Constants.FlightControllerButtons.ClimbUp)
                .onTrue(new ClimbUpCommand(climbSub))
                .onFalse(new ClimbStopCommand(climbSub));

        new JoystickButton(flightController, Constants.FlightControllerButtons.ConveyorInMacro)
                .onTrue(new ConveyorMacroInCommand(conveyorSub, indexSub))
                .onFalse(new ConveyorMacroStopCommand(conveyorSub, indexSub));
        new JoystickButton(flightController, Constants.FlightControllerButtons.ConveyorOutMacro)
                .onTrue(new ConveyorMacroOutCommand(conveyorSub, indexSub))
                .onFalse(new ConveyorMacroStopCommand(conveyorSub, indexSub));
        new JoystickButton(flightController, Constants.FlightControllerButtons.ShooterShoot)
                .onTrue(new ShooterShoot(shooterSub))
                .onFalse(new ShooterStop(shooterSub));
        new JoystickButton(flightController, Constants.FlightControllerButtons.ReversedIndexer)
                .onTrue(new IndexerUnload(indexSub))
                .onFalse(new IndexerStop(indexSub));
        new JoystickButton (flightController, Constants.FlightControllerButtons.IntakeIn)
                .onTrue(new IntakeIn(intakeSub))
                .onFalse(new IntakerRollerStop(intakeSub));
        new JoystickButton(flightController, Constants.FlightControllerButtons.IntakeOut)
                .onTrue(new IntakerOut(intakeSub))
                .onFalse(new IntakerRollerStop(intakeSub));
*/


        new POVButton(driverController, 90)
                .onTrue(new ConveyorMacroOutCommand(conveyorSub, indexSub))
                .onFalse(new ConveyorMacroStopCommand(conveyorSub, indexSub));
        new POVButton(driverController, 0)
                .onTrue(new IndexerUnload(indexSub))
                .onFalse(new IndexerStop(indexSub));


        new JoystickButton(driverController, Constants.XboxControllerButtons.IntakerDeploy)
                .onTrue(new IntakerDeploy(intakeSub))
                .onFalse(new IntakerHingeStop(intakeSub));
        new JoystickButton(driverController, Constants.XboxControllerButtons.IntakerRetract)
                .onTrue(new IntakerRetract(intakeSub))
                .onFalse(new IntakerHingeStop(intakeSub));

        new JoystickButton(driverController, Constants.XboxControllerButtons.IntakeIn)
                .onTrue(new IntakeIn(intakeSub))
                .onFalse(new IntakerRollerStop(intakeSub));
        new JoystickButton(driverController, Constants.XboxControllerButtons.IntakeOut)
                .onTrue(new IntakerOut(intakeSub))
                .onFalse(new IntakerRollerStop(intakeSub));
        new JoystickButton(driverController, Constants.XboxControllerButtons.ConveyorInMacro)
                .onTrue(new ConveyorMacroInCommand(conveyorSub, indexSub))
                .onFalse(new ConveyorMacroStopCommand(conveyorSub, indexSub));
        new JoystickButton(driverController, Constants.XboxControllerButtons.ShooterShoot)
                .onTrue(new ShooterShoot(shooterSub))
                .onFalse(new ShooterStop(shooterSub));
    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */



    public Command getAutonomousCommand() {
        // An example command will be run in autonomous
        //return ShootAutoCommand.getCommand(shooterSub, indexSub);
        return Commands.run(
                () -> swerveSub.driveFieldOriented(new ChassisSpeeds(1.0,1.0,0.0)),
                        swerveSub
        ).withTimeout(3.0);
    }
}
