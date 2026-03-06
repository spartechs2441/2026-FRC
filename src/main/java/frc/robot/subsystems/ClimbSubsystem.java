package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {
    private final SparkMax winch = new SparkMax(Constants.StorageConstants.conveyorID, SparkLowLevel.MotorType.kBrushless);
    private final RelativeEncoder climbEncoder = winch.getEncoder();

    public void winchUp() {
        if (climbEncoder.getPosition() < Constants.ClimbConstants.RelativeEncoder) {
            winch.setVoltage(Constants.ClimbConstants.leftWinchVoltage);
            winch.set(Constants.ClimbConstants.leftWinchSpeed);
        } else {
            winchStop();
        }
    }

    public void winchDown() {
        winch.setVoltage(-Constants.ClimbConstants.leftWinchVoltage);
        winch.set(-Constants.ClimbConstants.leftWinchSpeed);
    }

    public void winchStop() {
        winch.setVoltage(0);

    }
}

