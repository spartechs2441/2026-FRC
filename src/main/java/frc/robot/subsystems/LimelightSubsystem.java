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


    public Command limeAuto(NetworkTableEntry tx, NetworkTableEntry ty) {
        return run(() -> {


            // MAKE EQUATION FOR LIMELIGHT AUTO
            /*
            YOU WILL NEED TO HAVE A DEADBAND SO THAT CAN CORRECT YOUR POSITION

            (1 - (Current dist/desired dist)) * speed = ouptut speed
            also need a speed cap
             */
            swerveSub.swerveDrive.drive(new Translation2d(translationX.getAsDouble() * Constants.getMaxVelocity,
                            translationY.getAsDouble() * Constants.getMaxVelocity),
                    angularRotationX.getAsDouble() * Constants.getMaxAngularVelocity,
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
