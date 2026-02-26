package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Translation2d;
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

double limelight_aim_proportional () {

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
        double targetingForwardSpeed = ty.getDouble(0) * kP;
        targetingForwardSpeed *= Constants.getMaxVelocity;
        return targetingForwardSpeed;
    }


    double limelight_domain_proportional()
    {
        double kP = .1;
        double targetingForwardSpeed = tx.getDouble(0) * kP;
        targetingForwardSpeed *= Constants.getMaxVelocity;
        return targetingForwardSpeed;
    }


    public Command limeAuto(NetworkTableEntry tx, NetworkTableEntry ty, NetworkTableEntry txnc) {
        return run(() -> {



            double xSpeed = limelight_domain_proportional(); // get tx speed and apply deadband with if statements, then multiply with constant max speed

            double ySpeed = limelight_range_proportional();

            if (xSpeed > 0.02 && ySpeed > 0.02) {
                    swerveSub.swerveDrive.drive(new Translation2d(-xSpeed, -ySpeed));
                }
            else if (xSpeed < 0.02 && ySpeed < 0.02)

            swerveSub.swerveDrive.drive(new Translation2d(tx.getDouble(0) * Constants.getMaxVelocity,
                            ty.getDouble(0 * Constants.getMaxVelocity,
                    txnc.getDouble(0) * Constants.getMaxAngularVelocity),
                    true,
                    false);
        });
    }
/*
1. configure limelight to dashboard
2. link apriltags to limelight commands
3. have commands look for center point of apriltag if want to recalibrate direction

 */

}
