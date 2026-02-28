package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants;

import java.util.function.DoubleSupplier;

public class LimelightSubsystem extends SubsystemBase {
    NetworkTable limelight;
    SwerveSubsystem swerveSub;
    private final NetworkTableEntry txnc;
    private final NetworkTableEntry tx;
    private final NetworkTableEntry ty;
    private final NetworkTableEntry ta;
    private final NetworkTableEntry tv;
    
    public LimelightSubsystem(NetworkTable limeLight) {
        limelight = limeLight;
        swerveSub = new SwerveSubsystem();
        ta = limelight.getEntry("ta"); //area of reflective object
        tx = limelight.getEntry("tx"); //displacement on x axis
        ty = limelight.getEntry("ty"); //displacement on y axis
        tv = limelight.getEntry("tv"); //0 or 1 depending on if there is a reflective object
        txnc = limelight.getEntry("txnc"); // Angle on the april tag
    }

double limelight_angle_proportional () {

    double kP = .035;

    // tx ranges from (-hfov/2) to (hfov/2) in degrees. If your target is on the rightmost edge of
    // your limelight 3 feed, tx should return roughly 31 degrees.

    double targetingAngularVelocity = tx.getDouble(0) * kP;

    // convert to radians per second for our drive method
    targetingAngularVelocity *= Constants.getMaxAngularVelocity;

    //invert since tx is positive when the target is to the right of the crosshair
    return targetingAngularVelocity;
}

double limelight_range_proportional()
    {
        double kP = .1;
        double targetingForwardSpeed = ta.getDouble(0) * kP;
        targetingForwardSpeed *= Constants.getMaxVelocity;
        return targetingForwardSpeed;
    }


    double limelight_domain_proportional()
    {
        double kP = .1;
        double area = ta.getDouble(0);
        double targetingSidewaysAngle = tx.getDouble(0);
        double dist = area + 1;


        double targetingSidewaysSpeed = (Math.tan(targetingSidewaysAngle) * area) * kP;
        targetingSidewaysSpeed *= Constants.getMaxVelocity;
        return targetingSidewaysSpeed;
    }


    public Command limeAuto(NetworkTableEntry tx, NetworkTableEntry ta, NetworkTableEntry txnc, double target, double rottarget, double xCenter) {
        return run(() -> {

            double sidewaysSpeed = limelight_domain_proportional();
            double forwardSpeed = limelight_range_proportional();

            double area = ta.getDouble(0);

            double rot = limelight_angle_proportional();
            // for the swerve algorithm, make sure it turns to the correct angle AND THEN strafe to the correct position
            // the previous year, we had it move forward and turn towards the correct angle which wasn't useful at all to our drive team
            // "swerve-new" has this code
            if (rot > rottarget) {
                swerveSub.swerveDrive.drive(new ChassisSpeeds(0, 0, rot));
            } else if (rot < rottarget) {
                swerveSub.swerveDrive.drive(new ChassisSpeeds(0, 0, -rot));



            } else if (area < target && xCenter > 0) {
                swerveSub.swerveDrive.drive(new ChassisSpeeds(forwardSpeed, sidewaysSpeed, 0));
            }
            else if (area >= target && xCenter <= 0){
                swerveSub.swerveDrive.drive(new ChassisSpeeds(-forwardSpeed, sidewaysSpeed, 0));
            }

/*
1. configure limelight to dashboard
2. link apriltags to limelight commands
3. have commands look for center point of apriltag if want to recalibrate direction

 */

        })

