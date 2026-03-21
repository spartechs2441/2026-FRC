package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootAutoCommand {
    public static Command getCommand(ShooterSubsystem shootSub, IndexerSubsystem indexSub) {
        var actualShoot = new ShooterShoot(shootSub)
                .alongWith(new IndexerLoad(indexSub));
        return new ShooterShoot(shootSub)
                .withTimeout(1)
                .andThen(actualShoot);
    }
}
