// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

/** An example command that uses an example subsystem. */
public class ShooterCommand extends Command
{
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final ShooterSubsystem subsystem;


    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public ShooterCommand(ShooterSubsystem subsystem)
    {
        this.subsystem = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);

    }
    
    
    // Called when the command is initially scheduled.
    // Run once at beginning
    @Override
    public void initialize() {
        subsystem.ShooterStop();
    }
    
    
    // Called every time the scheduler runs while the command is scheduled.
    // Sort of a loop
    @Override
    public void execute() {
        subsystem.ShooterShoot();
    }
    
    
    // Called once the command ends or is interrupted.
    // Happens at the end
    @Override
    public void end(boolean interrupted) {
        subsystem.ShooterStop();
    }
    
    
    // Returns true when the command should end.
    @Override
    public boolean isFinished()
    {
        return false;
    }
}
