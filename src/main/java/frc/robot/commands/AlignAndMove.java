package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.SwerveSubsystem;


public class AlignAndMove extends Command {
    private final SwerveSubsystem swerve;

    public AlignAndMove(SwerveSubsystem swerve){
        this.swerve = swerve;
        addRequirements(swerve);
    }

    @Override
    public void execute() {
        var fid = Robot.getAprilTag(); //gets target apriltag data
        if (fid == null) {
            swerve.driveRobotRelative(new ChassisSpeeds());
            return;
        }

        Pose3d pose = fid.getTargetPose_RobotSpace();

        double x = pose.getX(); //how far forward the tag is
        double y = pose.getY(); //how far left or right the tag is



        // align
        double turnTolerance = 0.3; //how far it can be from centering on the tag before it stops
        // in radians

        double turn;

        if (Math.abs(y) > turnTolerance){
            // if far from centering on the tag, keep rotating to center it and slow during approaching
            turn = MathUtil.clamp(y*2,-0.3,0.3);
        } else {
            turn = 0;
        }

//move
        final double targetDistance = 1;
        double distanceError = x - targetDistance;
        double distanceTolerance = 0.1;
        //in meters

        double move;

        if (Math.abs(distanceError) > distanceTolerance) {
            // if current magnitude from apriltag is still far from 0.1 meter away, move forward and slow during approach
            move = MathUtil.clamp(distanceError * 1.2,-0.5,0.5);
        }  else {
            move = 0;
        }

        swerve.driveRobotRelative(new ChassisSpeeds(move,0,turn));

    }

    @Override
    public void end(boolean interrupted) {
        swerve.driveRobotRelative(new ChassisSpeeds());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
