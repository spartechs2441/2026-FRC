package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
import frc.robot.Robot;
import frc.robot.subsystems.SwerveSubsystem;
import swervelib.SwerveDrive;

public class HubAlignCommand extends Command {
    private final SwerveSubsystem swerve;
    private boolean done = false;

    public HubAlignCommand(SwerveSubsystem serveSub) {
        this.swerve = serveSub;
    }


    @Override
    public void execute() {
        var fid = Robot.getHubTag();
        if (fid == null) return;
        SwerveDrive drive = swerve.getSwerveDrive();
        Pose3d pose = fid.getTargetPose_RobotSpace();
        double x = pose.getX();

        final double turnTolerance = 0.3;
        final double turnSpeed = 0.1;
        // in radians
        double turn;
        if (x > turnTolerance) {
            turn = -turnSpeed;
        } else if (x > -turnTolerance) {
            turn = turnSpeed;
        } else {
            turn = 0;
            done = true;
        }
        System.out.println("Turn: " + turn);
        drive.drive(new ChassisSpeeds(0, 0, turn));
    }

    @Override
    public void end(boolean interrupted) {
        swerve.driveFieldOriented(new ChassisSpeeds());
    }

    @Override
    public boolean isFinished() {
        return done;
    }
}
