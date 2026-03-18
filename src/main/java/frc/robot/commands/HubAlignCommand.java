package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.SwerveSubsystem;
import swervelib.SwerveDrive;

public class HubAlignCommand extends Command {
    private final SwerveSubsystem swerve;
    private int aprilTag;

    public HubAlignCommand(SwerveSubsystem serveSub) {
        this.swerve = serveSub;
    }

    @Override
    public void initialize() {
        var optionalAlliance = DriverStation.getAlliance();
        if (optionalAlliance.isPresent()) {
            DriverStation.Alliance alliance = optionalAlliance.get();
            if (alliance == DriverStation.Alliance.Blue) {
                aprilTag = 25;
            } else {
                aprilTag = 9;
            }
        } else {
            aprilTag = 25;
        }
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
        double x = pose.getX();

        System.out.println(x);

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
        }
        // if we aren't turning then we are going forward/backwards
        if (turn != 0) {
            drive.drive(new ChassisSpeeds(0, 0, turn));
            return;
        }
        final double targetDistance = 10;
        final double distanceTolerance = 1;
        final double moveSpeed = 1;
        double distance = pose.getTranslation().getDistance(new Translation3d());

        System.out.println(distance);

        double move;
        if (distance < targetDistance - distanceTolerance) {
            move = -moveSpeed;
        } else if (distance > targetDistance + distanceTolerance) {
            move = moveSpeed;
        } else {
            move = 0;
        }
        if (move != 0) {
            drive.drive(new ChassisSpeeds(move, 0, 0));
            return;
        }
        drive.drive(new ChassisSpeeds());
    }

    @Override
    public void end(boolean interrupted) {
        swerve.driveFieldOriented(new ChassisSpeeds());
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
