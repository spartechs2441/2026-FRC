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

public class HubMoveCommand extends Command {
    private final SwerveSubsystem swerve;
    private int aprilTag;
    private boolean done = false;

    public HubMoveCommand(SwerveSubsystem serveSub) {
        this.swerve = serveSub;
    }

    @Override
    public void initialize() {
        aprilTag = Robot.getHubTag();
    }

    @Override
    public void execute() {
        LimelightHelpers.LimelightTarget_Fiducial fid = null;
        var results = LimelightHelpers.getLatestResults("limelight");
        for (var fiducials : results.targets_Fiducials) {
            if (fiducials.fiducialID == aprilTag) {
                fid = fiducials;
            }
        }
        if (fid == null) return;
        SwerveDrive drive = swerve.getSwerveDrive();
        Pose3d pose = fid.getTargetPose_RobotSpace();

        final double targetDistance = 10;
        final double distanceTolerance = 1;
        final double moveSpeed = 1;
        double distance = pose.getTranslation().getDistance(new Translation3d());

        double move;
        if (distance < targetDistance - distanceTolerance) {
            move = -moveSpeed;
        } else if (distance > targetDistance + distanceTolerance) {
            move = moveSpeed;
        } else {
            move = 0;
            done = true;
        }
        System.out.println("Move: " + move);
        drive.drive(new ChassisSpeeds(move, 0, 0));
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
